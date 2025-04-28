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

    static float currentCoins = 0;     // Track total coins inserted
    static float currentPrice = 0;     // Track current drink price
    static int cupsAvailable = 0;      // Track number of cups

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
            runVM2(sc);
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

        boolean paid = false;

        System.out.println("\nVending Machine-1 Initialized Successfully.");

        char ch = '1';
        while (ch != 'q') {
            System.out.println("\n==== VM1 MENU ====");
            System.out.println("0. create(float)");
            System.out.println("1. coin(float)");
            System.out.println("2. card(float)");
            System.out.println("3. insert_cups(int)");
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
                case '0': // create
                    System.out.print("Enter price: ");
                    currentPrice = sc.nextFloat();
                    vm1.create(currentPrice);
                    paid = false;
                    currentCoins = 0;
                    cupsAvailable = 0;
                    System.out.println("[Action] Created VM with price = " + currentPrice);
                    showStatus(mda);
                    break;

                case '1': // coin
                    System.out.print("Enter coin value: ");
                    float v = sc.nextFloat();
                    vm1.coin(v);
                    currentCoins += v;
                    paid = true;
                    System.out.println("[Action] Coin inserted: " + v);
                    showStatus(mda);
                    break;

                case '2': // card
                    System.out.print("Enter card value: ");
                    float x = sc.nextFloat();
                    vm1.card(x);
                    currentCoins += x;
                    paid = true;
                    System.out.println("[Action] Card inserted: " + x);
                    showStatus(mda);
                    break;

                case '3': // insert cups
                    System.out.print("Enter number of cups to insert: ");
                    int cups = sc.nextInt();
                    vm1.insert_cups(cups);
                    cupsAvailable += cups;
                    System.out.println("[Action] Inserted cups: " + cups);
                    showStatus(mda);
                    break;

                case '4': // set_price
                    System.out.print("Enter new price: ");
                    currentPrice = sc.nextFloat();
                    vm1.set_price(currentPrice);
                    System.out.println("[Action] Price updated to: " + currentPrice);
                    showStatus(mda);
                    break;

                case '5': // sugar
                    if (paid) {
                        vm1.sugar();
                        System.out.println("[Action] Additive Added: Sugar");
                        showStatus(mda);
                    } else {
                        System.out.println("[Error] Insert coin/card first.");
                    }
                    break;

                case '6': // chocolate
                    if (paid) {
                        if (cupsAvailable > 0) {
                            if (currentCoins >= currentPrice) {
                                vm1.chocolate();
                                currentCoins -= currentPrice;
                                cupsAvailable--;
                                paid = currentCoins >= currentPrice;
                                System.out.println("[Action] Drink Dispensed: Chocolate");
                            } else {
                                System.out.println("[Error] Not enough coins! Insert more coins.");
                            }
                        } else {
                            System.out.println("[Error] No cups available. Insert cups first.");
                        }
                    } else {
                        System.out.println("[Error] Insert coin/card first.");
                    }
                    showStatus(mda);
                    break;

                case '7': // cappuccino
                    if (paid) {
                        if (cupsAvailable > 0) {
                            if (currentCoins >= currentPrice) {
                                vm1.cappuccino();
                                currentCoins -= currentPrice;
                                cupsAvailable--;
                                paid = currentCoins >= currentPrice;
                                System.out.println("[Action] Drink Dispensed: Cappuccino");
                            } else {
                                System.out.println("[Error] Not enough coins! Insert more coins.");
                            }
                        } else {
                            System.out.println("[Error] No cups available. Insert cups first.");
                        }
                    } else {
                        System.out.println("[Error] Insert coin/card first.");
                    }
                    showStatus(mda);
                    break;

                case '8': // cancel
                    if (paid) {
                        vm1.cancel();
                        System.out.println("[Action] Transaction canceled. Returned coins: " + currentCoins);
                        currentCoins = 0;
                        paid = false;
                    } else {
                        System.out.println("[Error] No active transaction to cancel.");
                    }
                    showStatus(mda);
                    break;

                case 'q':
                    System.out.println("Exiting VM1. Thank you!");
                    break;

                default:
                    System.out.println("Invalid operation. Try again.");
                    break;
            }
        }
    }

    private static void runVM2(Scanner sc) {
        System.out.println("[VM2 not implemented yet. Focus is on VM1 based on your State Diagram]");
    }

    private static void showStatus(MDA_EFSM mda) {
        System.out.println("--------------------------------------------------");
        System.out.println("Current State: " + getStateName(mda));
        System.out.println("Cups Available: " + cupsAvailable);
        System.out.println("Coins/Card Balance: " + currentCoins);
        System.out.println("Drink Price: " + currentPrice);
        System.out.println("--------------------------------------------------");
    }

    private static String getStateName(MDA_EFSM mda) {
        switch (mda.getCurrentStateId()) {
            case 0: return "Start";
            case 1: return "No Cups";
            case 2: return "Idle";
            case 3: return "Coins Inserted";
            default: return "Unknown State";
        }
    }
}
