package grocerylist.india.com.materialdesign.model;

/**
 * Created by abhijeetsogani on 10/20/15.
 */
public class Item {
    private int itemId;
    private int sellingPrice;
    private int costPrice;
    private ItemColor color;
    private int itemStock;
    private long productId;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(int costPrice) {
        this.costPrice = costPrice;
    }

    public ItemColor getColor() {
        return color;
    }

    public void setColor(ItemColor color) {
        this.color = color;
    }

    public int getItemStock() {
        return itemStock;
    }

    public void setItemStock(int itemStock) {
        this.itemStock = itemStock;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }


}
