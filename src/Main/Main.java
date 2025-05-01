package Main;

import java.util.Scanner;
import factory.*;
import vm.*;
import datastore.*;
import op.*;
import mda_efsm.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("======================================");
        System.out.println("   Welcome to the Vending Machine");
        System.out.println("======================================\n");
        System.out.println("Select Vending Machine:");
        System.out.println("1. VM1");
        System.out.println("2. VM2");
        System.out.print("Choice: ");
        int choice = sc.nextInt();

        if (choice == 1) {
            AbstractFactory af = new VM1Factory();
            runVM(new VM1(initSystem(af), af.createDataStore()), sc, true);
        } else if (choice == 2) {
            AbstractFactory af = new VM2Factory();
            runVM(new VM2(initSystem(af), af.createDataStore()), sc, false);
        } else {
            System.out.println("Invalid option.");
        }

        sc.close();
    }

    private static MDA_EFSM initSystem(AbstractFactory af) {
        DataStore ds = af.createDataStore();
        OP op = new OP();
        op.setDataStore(ds);
        op.setAbstractFactory(af);
        op.setStorePrice(af.createStorePrice());
        op.setReturnCoins(af.createReturnCoins());
        op.setIncreaseCF(af.createIncreaseCF());
        op.setZeroCF(af.createZeroCF());
        op.setDisposeDrink(af.createDisposeDrink());
        op.setDisposeAdditives(af.createDisposeAdditives());

        return new MDA_EFSM(op);
    }

    private static void runVM(Object vm, Scanner sc, boolean isVM1) {
        char ch = ' ';
        while (ch != 'q') {
            displayMenu(isVM1);
            System.out.print("Select Operation: ");
            ch = sc.next().charAt(0);

            try {
                switch (ch) {
                    case '0':
                        System.out.print("Enter price: ");
                        float p = sc.nextFloat();
                        if (isVM1) ((VM1) vm).create(p);
                        else ((VM2) vm).CREATE((int) p);
                        break;

                    case '1':
                        System.out.print("Enter coin value: ");
                        float c = sc.nextFloat();
                        if (isVM1) ((VM1) vm).coin(c);
                        else ((VM2) vm).COIN((int) c);
                        break;

                    case '2':
                        if (isVM1) {
                            ((VM1) vm).sugar();
                        } else {
                            ((VM2) vm).SUGAR();
                        }
                        break;

                    case '3':
                        if (isVM1) {
                            ((VM1) vm).chocolate();
                        } else {
                            ((VM2) vm).CREAM();
                        }
                        break;

                    case '4':
                        if (isVM1) {
                            ((VM1) vm).cappuccino();
                        } else {
                            ((VM2) vm).COFFEE();
                        }
                        break;

                    case '5':
                        System.out.print("Enter number of cups: ");
                        int n = sc.nextInt();
                        if (isVM1) ((VM1) vm).insert_cups(n);
                        else ((VM2) vm).InsertCups(n);
                        break;

                    case '6':
                        System.out.print("Enter new price: ");
                        float np = sc.nextFloat();
                        if (isVM1) ((VM1) vm).set_price(np);
                        else ((VM2) vm).SetPrice((int) np);
                        break;

                    case '7':
                        if (isVM1) ((VM1) vm).cancel();
                        else ((VM2) vm).CANCEL();
                        break;

                    case '8':
                        if (isVM1) {
                            System.out.print("Enter card value: ");
                            float x = sc.nextFloat();
                            ((VM1) vm).card(x);
                        } else {
                            System.out.println("[Card option not available in VM2]");
                        }
                        break;

                    case 'q':
                        System.out.println("Exiting. Thank you!");
                        break;

                    default:
                        System.out.println("Invalid input.");
                }
            } catch (Exception e) {
                System.out.println("Error executing operation: " + e.getMessage());
            }
        }
    }

    private static void displayMenu(boolean isVM1) {
        System.out.println("\n========== MENU ==========");
        System.out.println("0. create(" + (isVM1 ? "float" : "int") + ")");
        System.out.println("1. coin(" + (isVM1 ? "float" : "int") + ")");
        System.out.println("2. " + (isVM1 ? "sugar()" : "SUGAR()"));
        System.out.println("3. " + (isVM1 ? "chocolate()" : "CREAM()"));
        System.out.println("4. " + (isVM1 ? "cappuccino()" : "COFFEE()"));
        System.out.println("5. insert_cups(int)");
        System.out.println("6. set_price(float/int)");
        System.out.println("7. cancel()");
        if (isVM1) System.out.println("8. card(float)");
        System.out.println("q. Quit");
        System.out.println("==========================");
    }
}
