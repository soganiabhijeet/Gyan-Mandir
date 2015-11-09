package grocerylist.india.com.materialdesign.model;

/**
 * Created by abhijeetsogani on 10/20/15.
 */
public class ItemColor {
    private int colorId;
    private String colorName;
    private String colorCode;
    private Boolean isSelected;

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    public ItemColor(String colorName, String colorCode, Boolean isSelected) {
        this.colorCode = colorCode;
        this.colorName = colorName;
        this.isSelected = isSelected;
    }

    public ItemColor(int colorId, String colorName, String colorCode, Boolean isSelected) {
        this.colorId = colorId;
        this.colorName = colorName;
        this.colorCode = colorCode;
        this.isSelected = isSelected;
    }

    @Override
    public String toString() {
        return "ItemColor{" +
                "isSelected=" + isSelected +
                ", colorName='" + colorName + '\'' +
                ", colorCode='" + colorCode + '\'' +
                '}';
    }
}
