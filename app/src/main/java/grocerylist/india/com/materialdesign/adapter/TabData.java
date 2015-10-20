package grocerylist.india.com.materialdesign.adapter;

/**
 * Created by abhijeetsogani on 10/20/15.
 */
public class TabData {

    public TabData(int id, String title) {
        this.id = id;
        this.title = title;
    }

    int id;

    String title;


    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

}
