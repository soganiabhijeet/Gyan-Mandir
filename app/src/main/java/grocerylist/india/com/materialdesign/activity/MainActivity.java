package grocerylist.india.com.materialdesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import grocerylist.india.com.materialdesign.R;
import grocerylist.india.com.materialdesign.adapter.ViewPagerAdapter;
import grocerylist.india.com.materialdesign.database.DatabaseHandler;
import grocerylist.india.com.materialdesign.database.adapter.CategoryAdapter;
import grocerylist.india.com.materialdesign.database.adapter.ProductAdapter;
import grocerylist.india.com.materialdesign.model.Product;
import grocerylist.india.com.materialdesign.slidingtab.SlidingTabLayout;

/**
 * Created by abhijeetsogani on 9/2/15.
 */
public class MainActivity extends AppCompatActivity implements DatabaseHandler {
    private Toolbar mToolbar;
    ViewPager pager;
    SlidingTabLayout tabs;
    ViewPagerAdapter adapter;
    private String DATABASE_SAVED = "is_database_saved";
    CategoryAdapter categoryAdapter;
    ProductAdapter productAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            if (!savedInstanceState.getBoolean(DATABASE_SAVED))
                testDatabase();
        } catch (NullPointerException e) {
            testDatabase();
        }


        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), this);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom ItemColor for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.colorAccent);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_search) {
            Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
            Intent searchIntent = new Intent(this, SearchActivity.class);
            startActivity(searchIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void testDatabase() {
        initializeAdapter();
        openAdapter();
        addCategory();
        addProduct();
        closeAdapter();
    }

    private void addCategory() {
        categoryAdapter.addCategory(getResources().getString(R.string.tab_pen));
        categoryAdapter.addCategory(getResources().getString(R.string.tab_canvas));
        categoryAdapter.addCategory(getResources().getString(R.string.tab_color));
    }

    private void addProduct() {
        Product product = new Product("Camlin Pencil", "Camlin", false, false, categoryAdapter.getCategoryFromName(getResources().getString(R.string.tab_color)), 30, 15);
        productAdapter.insertProduct(product);
        product = new Product("Camlin Canvas", "Camlin", false, false, categoryAdapter.getCategoryFromName(getResources().getString(R.string.tab_canvas)), 30, 15);
        productAdapter.insertProduct(product);
        product = new Product("Camlin Canvas 1", "Camlin", false, false, categoryAdapter.getCategoryFromName(getResources().getString(R.string.tab_canvas)), 30, 15);
        productAdapter.insertProduct(product);
        product = new Product("Camlin Canvas 2", "Camlin", false, false, categoryAdapter.getCategoryFromName(getResources().getString(R.string.tab_canvas)), 30, 15);
        productAdapter.insertProduct(product);
        product = new Product("Camlin Canvas 3", "Camlin", false, false, categoryAdapter.getCategoryFromName(getResources().getString(R.string.tab_canvas)), 30, 15);
        productAdapter.insertProduct(product);
        product = new Product("Camlin Canvas 4", "Camlin", false, false, categoryAdapter.getCategoryFromName(getResources().getString(R.string.tab_canvas)), 30, 15);
        productAdapter.insertProduct(product);
        product = new Product("Camlin Canvas 5", "Camlin", false, false, categoryAdapter.getCategoryFromName(getResources().getString(R.string.tab_canvas)), 30, 15);
        productAdapter.insertProduct(product);
        product = new Product("Camlin Canvas 6", "Camlin", false, false, categoryAdapter.getCategoryFromName(getResources().getString(R.string.tab_canvas)), 30, 15);
        productAdapter.insertProduct(product);
        product = new Product("Camlin Canvas 7", "Camlin", false, false, categoryAdapter.getCategoryFromName(getResources().getString(R.string.tab_canvas)), 30, 15);
        productAdapter.insertProduct(product);
        product = new Product("Camlin Canvas 8", "Camlin", false, false, categoryAdapter.getCategoryFromName(getResources().getString(R.string.tab_canvas)), 30, 15);
        productAdapter.insertProduct(product);
        product = new Product("Camlin Canvas 9", "Camlin", false, false, categoryAdapter.getCategoryFromName(getResources().getString(R.string.tab_canvas)), 30, 15);
        productAdapter.insertProduct(product);
        product = new Product("Camlin Canvas 10", "Camlin", false, false, categoryAdapter.getCategoryFromName(getResources().getString(R.string.tab_canvas)), 30, 15);
        productAdapter.insertProduct(product);
        product = new Product("Camlin Canvas 11", "Camlin", false, false, categoryAdapter.getCategoryFromName(getResources().getString(R.string.tab_canvas)), 30, 15);
        productAdapter.insertProduct(product);
        product = new Product("Camlin Canvas 12", "Camlin", false, false, categoryAdapter.getCategoryFromName(getResources().getString(R.string.tab_canvas)), 30, 15);
        productAdapter.insertProduct(product);
        product = new Product("Camlin Canvas 13", "Camlin", false, false, categoryAdapter.getCategoryFromName(getResources().getString(R.string.tab_canvas)), 30, 15);
        productAdapter.insertProduct(product);

        product = new Product("Camlin Pen", "Camlin", false, false, categoryAdapter.getCategoryFromName(getResources().getString(R.string.tab_pen)), 30, 15);
        productAdapter.insertProduct(product);

    }

    @Override
    public void initializeAdapter() {
        categoryAdapter = new CategoryAdapter(this);
        productAdapter = new ProductAdapter(this);
    }

    @Override
    public void openAdapter() {
        categoryAdapter.open();
        productAdapter.open();
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(DATABASE_SAVED, true);
    }

    @Override
    public void closeAdapter() {
        categoryAdapter.close();
        productAdapter.close();
    }

}
