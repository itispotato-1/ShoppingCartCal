package Lib;

import java.util.ArrayList;

public class ShoppingCartCalculator {

    /**
     * เป็นเมธอดที่คำนวณราคาสินค้าที่ต้องจ่าย โดยถ้ารับรหัสเป็น "NORMAL" ผลจะเป็น price*quantity, ถ้ารับรหัสเป็น "BOGO" ผลจะเป็น ถ้าซื้อ 2 จะจ่ายแค่ 1, ถ้ารับรหัสเป็น "BULK" ผลจะเป็น ถ้าสินค้ามากกว่าเท่ากับ 6 จะลด 10% 
     * ถ้าค่าว่างเปล่า หรือNull หรือค่าติดลบ หรือรหัสไม่ตรงกับที่มีจะออกเป็น 0.0
     * @param sku เป็น String ของข้อมูล:รหัส
     * @param name เป็น String ของข้อมูล:ชื่อสินค่า
     * @param price เป็น int ของข้อมูล:ราคาสินค้า
     * @param quantity เป็น int ของข้อมูล:จำนวนสินค้า
     * @return ราคาสินค้าที่ต้องจ่ายของตะกร้านี้
     */
    public static double calculateTotalPrice(ArrayList<CartItem> items) {
        if(items == null||items.isEmpty()){
            return 0.0;
        }
        float sumPrice = 0;
        for (CartItem item : items) {
            if(item.price() < 0||item.quantity() < 0){ //ถ้าพบว่ามันน้อยกว่า 0
                return 0.0;
            }

            if(item.sku().equals("NORMAL")){
               sumPrice += item.price() * item.quantity();
            }
            else if(item.sku().equals("BOGO")){
                sumPrice += ((int)item.quantity()/2 + (int)item.quantity()%2) * item.price();
            }
            else if(item.sku().equals("BULK")){
                if(item.quantity() >= 6){
                    sumPrice += item.price() * item.quantity() * 0.9;
                }
                else sumPrice += item.price() * item.quantity();
            }
            else {return 0.0;} //ถ้าไม่ใช่ skuที่มีอยู่ให้
        }
        return sumPrice;
    }
}