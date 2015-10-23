package grocerylist.india.com.materialdesign.model;

/**
 * Created by abhijeetsogani on 10/20/15.
 */
public class Product {
    private int productId;
    private String productName;
    private String companyName;
    private Boolean hasColor;
    private Boolean hasSize;
    private Category category;
    private Integer sellingPrice;
    private Integer costPrice;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Boolean getHasColor() {
        return hasColor;
    }

    public void setHasColor(Boolean hasColor) {
        this.hasColor = hasColor;
    }

    public Boolean getHasSize() {
        return hasSize;
    }

    public void setHasSize(Boolean hasSize) {
        this.hasSize = hasSize;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Integer sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Integer costPrice) {
        this.costPrice = costPrice;
    }

    public Product(String productName, String companyName, Boolean hasColor, Boolean hasSize, Category category, Integer sellingPrice, Integer costPrice) {
        this.productName = productName;
        this.companyName = companyName;
        this.hasColor = hasColor;
        this.hasSize = hasSize;
        this.category = category;
        this.sellingPrice = sellingPrice;
        this.costPrice = costPrice;
    }
    public Product()
    {

    }
}
