package Main;

import java.util.Scanner;
import factory.*;
import vm.*;
import datastore.*;
import mda_efsm.*;
import op.*;

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

            // [ABSTRACT FACTORY PATTERN] Using factory to create VM instance

            AbstractFactory af = new VM1Factory();
            DataStore ds = af.createDataStore();
            MDA_EFSM mda = initSystem(af, ds);
            runVM(new VM1(mda, ds), sc, true);
        } else if (choice == 2) {
            AbstractFactory af = new VM2Factory();
            DataStore ds = af.createDataStore();
            MDA_EFSM mda = initSystem(af, ds);
            runVM(new VM2(mda, ds), sc, false);
        } else {
            System.out.println("Invalid option.");
        }

        sc.close();
    }

    private static MDA_EFSM initSystem(AbstractFactory af, DataStore ds) {
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
                        if (isVM1) {
                            float p = sc.nextFloat();
                            ((VM1) vm).create(p);
                        } else {
                            int p = sc.nextInt();
                            ((VM2) vm).setTemp_p(p);
                            ((VM2) vm).CREATE();
                        }
                        break;

                    case '1':
                        System.out.print("Enter coin value: ");
                        if (isVM1) {
                            float c = sc.nextFloat();
                            ((VM1) vm).coin(c);
                        } else {
                            int c = sc.nextInt();
                            ((VM2) vm).COIN(c);
                        }
                        break;

                    case '2':
                        if (isVM1) ((VM1) vm).sugar();
                        else ((VM2) vm).SUGAR();
                        break;

                    case '3':
                        if (isVM1) ((VM1) vm).chocolate();
                        else ((VM2) vm).CREAM();
                        break;

                    case '4':
                        if (isVM1) ((VM1) vm).cappuccino();
                        else ((VM2) vm).COFFEE();
                        break;

                    case '5':
                        System.out.print("Enter number of cups: ");
                        int n = sc.nextInt();
                        if (isVM1) ((VM1) vm).insert_cups(n);
                        else ((VM2) vm).InsertCups(n);
                        break;

                    case '6':
                        System.out.print("Enter new price: ");
                        if (isVM1) {
                            float np = sc.nextFloat();
                            ((VM1) vm).set_price(np);
                        } else {
                            int np = sc.nextInt();
                            ((VM2) vm).SetPrice(np);
                        }
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
        System.out.println("6. set_price(" + (isVM1 ? "float" : "int") + ")");
        System.out.println("7. cancel()");
        if (isVM1) System.out.println("8. card(float)");
        System.out.println("q. Quit");
        System.out.println("==========================");
    }
}
