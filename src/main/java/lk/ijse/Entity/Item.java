package lk.ijse.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Item {
    @Id
    private String ItemCode;
    private String ItemDesc;
    private int qtyOnHand;
    private Double ItemPrice;

    public Item() {
    }

    public Item(String itemCode, String itemDesc, int qtyOnHand, Double itemPrice) {
        ItemCode = itemCode;
        ItemDesc = itemDesc;
        this.qtyOnHand = qtyOnHand;
        ItemPrice = itemPrice;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getItemDesc() {
        return ItemDesc;
    }

    public void setItemDesc(String itemDesc) {
        ItemDesc = itemDesc;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public Double getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        ItemPrice = itemPrice;
    }

    @Override
    public String toString() {
        return "Item{" +
                "ItemCode='" + ItemCode + '\'' +
                ", ItemDesc='" + ItemDesc + '\'' +
                ", qtyOnHand=" + qtyOnHand +
                ", ItemPrice=" + ItemPrice +
                '}';
    }
}
