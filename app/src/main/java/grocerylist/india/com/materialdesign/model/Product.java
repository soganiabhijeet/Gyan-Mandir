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
}
