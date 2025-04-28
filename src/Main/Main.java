package Main;

import java.util.Scanner;
import vm.VM1;
import vm.VM2;
import factory.AbstractFactory;
import factory.VM1Factory;
import factory.VM2Factory;
import mda_efsm.MDA_EFSM;
import op.OP;
import datastore.DataStore;

public class Main {

    static float currentCoins = 0;
    static float currentPrice = 0;
    static int cupsAvailable = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n==========================================");
        System.out.println(" Welcome to the Vending Machine Simulator ");
        System.out.println("==========================================\n");

        System.out.println("Select Vending Machine to Initialize:");
        System.out.println("1. Vending Machine 1 (VM1)");
        System.out.println("2. Vending Machine 2 (VM2)");
        System.out.print("Enter 1 or 2: ");
        int choice = sc.nextInt();

        if (choice == 1) {
            runVM1(sc);
        } else if (choice == 2) {
            System.out.println("[VM2 not implemented yet. Focus is on VM1 based on State Diagram]");
        } else {
            System.out.println("Invalid Machine Selection. Program exiting.");
        }

        sc.close();
    }

    private static void runVM1(Scanner sc) {
        AbstractFactory af = new VM1Factory();
        DataStore ds = af.createDataStore();
        OP op = new OP();
        op.setDataStore(ds);
        op.setStorePrice(af.createStorePrice());
        op.setReturnCoins(af.createReturnCoins());
        op.setIncreaseCF(af.createIncreaseCF());
        op.setDisposeDrink(af.createDisposeDrink());
        op.setDisposeAdditives(af.createDisposeAdditives());
        MDA_EFSM mda = new MDA_EFSM(op);
        VM1 vm1 = new VM1(mda, ds);

        System.out.println("\nVending Machine-1 Initialized Successfully.");

        char ch = '1';
        while (ch != 'q') {
            System.out.println("\n==== VM1 MENU ====");
            System.out.println("0. create(float)");
            System.out.println("1. insert_cups(int)");
            System.out.println("2. coin(float)");
            System.out.println("3. card(float)");
            System.out.println("4. set_price(float)");
            System.out.println("5. sugar()");
            System.out.println("6. chocolate()");
            System.out.println("7. cappuccino()");
            System.out.println("8. cancel()");
            System.out.println("q. Quit");
            System.out.println("===================");

            System.out.print("Select Operation: ");
            ch = sc.next().charAt(0);

            switch (ch) {
                case '0':
                    System.out.print("Enter price: ");
                    currentPrice = sc.nextFloat();
                    vm1.create(currentPrice);
                    currentCoins = 0;
                    cupsAvailable = 0;
                    System.out.println("[Action] Machine created with price: " + currentPrice);
                    showStatus(mda);
                    break;

                case '1':
                    System.out.print("Enter number of cups: ");
                    int n = sc.nextInt();
                    vm1.insert_cups(n);
                    cupsAvailable += n;
                    System.out.println("[Action] Cups inserted: " + n);
                    showStatus(mda);
                    break;

                case '2': // coin
                    System.out.print("Enter coin value: ");
                    float v = sc.nextFloat();

                    if (getStateName(mda.getStateID()).equals("No Cups")) {
                        // If in No Cups state -> only return coin
                        vm1.coin(0);  // pass f=0, no IncreaseCF
                        System.out.println("[Action] No cups available! Coin returned: " + v);
                        // Don't update currentCoins
                    } else {
                        // Normal behavior in Idle or CoinsInserted
                        vm1.coin(v >= currentPrice ? 1 : 0); // f=1 if enough
                        currentCoins += v;
                        System.out.println("[Action] Coin inserted: " + v);
                    }

                    showStatus(mda);
                    break;


                case '3':
                    System.out.print("Enter card value: ");
                    float cardVal = sc.nextFloat();
                    vm1.card(cardVal);
                    currentCoins += cardVal;
                    System.out.println("[Action] Card used with value: " + cardVal);
                    showStatus(mda);
                    break;

                case '4':
                    System.out.print("Enter new price: ");
                    currentPrice = sc.nextFloat();
                    vm1.set_price(currentPrice);
                    System.out.println("[Action] Price updated to: " + currentPrice);
                    showStatus(mda);
                    break;

                case '5':
                    vm1.sugar();
                    System.out.println("[Additive] Sugar Added.");
                    showStatus(mda);
                    break;

                case '6':
                    if (checkTransaction(mda)) {
                        vm1.chocolate();
                        cupsAvailable--;
                        currentCoins = 0;
                        System.out.println("[Drink] Chocolate dispensed.");
                    }
                    showStatus(mda);
                    break;

                case '7':
                    if (checkTransaction(mda)) {
                        vm1.cappuccino();
                        cupsAvailable--;
                        currentCoins = 0;
                        System.out.println("[Drink] Cappuccino dispensed.");
                    }
                    showStatus(mda);
                    break;

                case '8':
                    vm1.cancel();
                    System.out.println("[Cancel] Transaction canceled. Coins returned: " + currentCoins);
                    currentCoins = 0;
                    showStatus(mda);
                    break;

                case 'q':
                    System.out.println("Exiting VM1. Thank you!");
                    break;

                default:
                    System.out.println("[Error] Invalid operation. Try again.");
                    break;
            }
        }
    }

    private static boolean checkTransaction(MDA_EFSM mda) {
        if (currentCoins < currentPrice) {
            System.out.println("[Error] Not enough balance. Insert more coins/card.");
            return false;
        }
        if (cupsAvailable <= 0) {
            System.out.println("[Error] No cups available. Please insert cups.");
            return false;
        }
        return true;
    }

    private static void showStatus(MDA_EFSM mda) {
        System.out.println("--------------------------------------------");
        System.out.println("State         : " + getStateName(mda.getStateID()));
        System.out.println("Cups Available: " + cupsAvailable);
        System.out.println("Current Coins : " + currentCoins);
        System.out.println("Drink Price   : " + currentPrice);
        System.out.println("--------------------------------------------");
    }

    private static String getStateName(int id) {
        switch (id) {
            case 0: return "Start";
            case 1: return "No Cups";
            case 2: return "Idle";
            case 3: return "Coins Inserted";
            default: return "Unknown";
        }
    }
}
