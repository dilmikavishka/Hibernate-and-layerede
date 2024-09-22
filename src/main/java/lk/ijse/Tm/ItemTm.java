package lk.ijse.Tm;

public class ItemTm {
    private String ItemCode;
    private String ItemDesc;

    private int qtyOnHand;
    private Double ItemPrice;

    public ItemTm() {
    }

    public ItemTm(String itemCode, String itemDesc, int qtyOnHand, Double itemPrice) {
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
        return "ItemTm{" +
                "ItemCode='" + ItemCode + '\'' +
                ", ItemDesc='" + ItemDesc + '\'' +
                ", qtyOnHand=" + qtyOnHand +
                ", ItemPrice=" + ItemPrice +
                '}';
    }
}
