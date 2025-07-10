package Lib;
import java.util.ArrayList;

public class ShoppingCartManualTest {

    public static void run() {
        System.out.println("--- Starting Shopping Cart Calculator Tests ---\n");

        int passedCount = 0;
        int failedCount = 0;

        // Test 1: ตะกร้าเป็น null
        try {
            double total1 = ShoppingCartCalculator.calculateTotalPrice(null);
            if (total1 == 0.0) {
                System.out.println("PASSED: Null cart should return 0.0");
                passedCount++;
            } else {
                System.out.println("FAILED: Null cart expected 0.0 but got " + total1);
                failedCount++;
            }
        } catch (Exception e) {
            System.out.println("FAILED: Null cart caused an exception: " + e.getMessage());
            failedCount++;
        }

        // Test 2: ตะกร้าว่าง
        ArrayList<CartItem> emptyCart = new ArrayList<>();
        double total2 = ShoppingCartCalculator.calculateTotalPrice(emptyCart);
        if (total2 == 0.0) {
            System.out.println("PASSED: Empty cart should return 0.0");
            passedCount++;
        } else {
            System.out.println("FAILED: Empty cart expected 0.0 but got " + total2);
            failedCount++;
        }

        // Test 3: คำนวณปกติ ไม่มีส่วนลด
        ArrayList<CartItem> simpleCart = new ArrayList<>();
        simpleCart.add(new CartItem("NORMAL", "Bread", 25.0, 2)); // 50
        simpleCart.add(new CartItem("NORMAL", "Milk", 15.0, 1));      // 15
        double total3 = ShoppingCartCalculator.calculateTotalPrice(simpleCart);
        if (total3 == 65.0) {
            System.out.println("PASSED: Simple cart total is correct (65.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Simple cart total expected (65.0) but got " + total3);
            failedCount++;
        }

        // Test 4: คำนวณแบบ 1 แถม 1
        ArrayList<CartItem>  BOGO = new ArrayList<>();
        BOGO.add(new CartItem("BOGO","Orange",10.0,5)); //30
        BOGO.add(new CartItem("BOGO","Snack",5.0,4)); //10
        BOGO.add(new CartItem("NORMAL","Cake",30.0,2)); //60
        double total4 = ShoppingCartCalculator.calculateTotalPrice(BOGO); 
        if (total4 == 100.0) {
            System.out.println("PASSED: BOGO total is correct (100.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: BOGO total expected (100.0) but got " + total4);
            failedCount++;
        }

        // Test 5: คำนวณแบบซื้อมากกว่าหรือเท่ากับ 6 ลด 10%
        ArrayList<CartItem> BULK = new ArrayList<>();
        BULK.add(new CartItem("BULK","Orange",10.0,5)); //50
        BULK.add(new CartItem("BULK","Snack",5.0,6)); //27
        BULK.add(new CartItem("NORMAL","Cake",30.0,2)); //60
        double total5 = ShoppingCartCalculator.calculateTotalPrice(BULK); 
        if (total5 == 137.0) {
            System.out.println("PASSED: BULK total is correct (137.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: BULK total expected (137.0) but got " + total5);
            failedCount++;
        }

        // Test 6: ถ้า price หรือ quantity ติดลบ
        ArrayList<CartItem> minus = new ArrayList<>();
        minus.add(new CartItem("BULK","Orange",-10.0,5)); 
        minus.add(new CartItem("BULK","Snack",5.0,-6)); 
        minus.add(new CartItem("NORMAL","Cake",30.0,2)); 
        double total6 = ShoppingCartCalculator.calculateTotalPrice(minus); 
        if (total6 == 0.0) {
            System.out.println("PASSED: Do not minus total is correct (0.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Do not minus expected (0.0) but got " + total6);
            failedCount++;
        }

        // Test 7: ถ้ารหัสผิด
        ArrayList<CartItem> skumiss = new ArrayList<>();
        skumiss.add(new CartItem("BU","Orange",10.0,5)); 
        skumiss.add(new CartItem("BULK","Snack",5.0,6)); 
        skumiss.add(new CartItem("NORMAL","Cake",30.0,2)); 
        double total7 = ShoppingCartCalculator.calculateTotalPrice(skumiss); 
        if (total7 == 0.0) {
            System.out.println("PASSED: skumiss total is correct (0.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: skumiss expected (0.0) but got " + total7);
            failedCount++;
        }

        // --- Test Summary ---
        System.out.println("\n--------------------");
        System.out.println("--- Test Summary ---");
        System.out.println("Passed: " + passedCount + ", Failed: " + failedCount);
        if (failedCount == 0) {
            System.out.println("Excellent! All tests passed!");
        } else {
            System.out.println("Some tests failed.");
        }
    }
}