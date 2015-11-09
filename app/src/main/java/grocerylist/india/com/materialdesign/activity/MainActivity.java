package grocerylist.india.com.materialdesign.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import grocerylist.india.com.materialdesign.R;
import grocerylist.india.com.materialdesign.adapter.ViewPagerAdapter;
import grocerylist.india.com.materialdesign.database.DatabaseHandler;
import grocerylist.india.com.materialdesign.database.adapter.CategoryAdapter;
import grocerylist.india.com.materialdesign.database.adapter.ProductAdapter;
import grocerylist.india.com.materialdesign.dialog.SelectColorDialog;
import grocerylist.india.com.materialdesign.drawer.FragmentDrawer;
import grocerylist.india.com.materialdesign.fragment.ProductListFragment;
import grocerylist.india.com.materialdesign.model.ItemColor;
import grocerylist.india.com.materialdesign.slidingtab.SlidingTabLayout;

/**
 * Created by abhijeetsogani on 9/2/15.
 */
public class MainActivity extends AppCompatActivity implements DatabaseHandler, FragmentDrawer.FragmentDrawerListener, ProductListFragment.OnProductCLickedListener, SelectColorDialog.SelectColorDialogListener {
    private int counter = 0;
    private Toolbar mToolbar;
    ViewPager pager;
    SlidingTabLayout tabs;
    ViewPagerAdapter adapter;
    private String DATABASE_SAVED = "is_database_saved";
    CategoryAdapter categoryAdapter;
    ProductAdapter productAdapter;
    private FragmentDrawer drawerFragment;
    public static String BOOL_IS_ADD_PRODUCT = "is_add_product";

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
        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_cart);
        item.getActionView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                animate();
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            //noinspection SimplifiableIfStatement
          /*  case R.id.action_cart:
                animate();
                return true;*/

            case R.id.action_search:
                Intent searchIntent = new Intent(this, SearchActivity.class);
                startActivity(searchIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void animate() {
        counter++;
        Log.d("MainActivity", "animation called onCreateOptionsMenu" + counter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView textView = (TextView) findViewById(R.id.cart_layout_number);
        textView.setText(String.valueOf(counter));
       /* LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView iv = (TextView) inflater.inflate(R.layout.cart_layout, null);
        Animation rotation = AnimationUtils.loadAnimation(this, R.anim.zoom);
        iv.startAnimation(rotation);*/

    }

    private void testDatabase() {
        initializeAdapter();
        openAdapter();
        addCategory();
        // addProduct();
        closeAdapter();
    }

    private void addCategory() {
        categoryAdapter.addCategory(getResources().getString(R.string.tab_pen));
        categoryAdapter.addCategory(getResources().getString(R.string.tab_canvas));
        categoryAdapter.addCategory(getResources().getString(R.string.tab_color));
    }

   /* private void addProduct() {
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

    }*/

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

    @Override
    public void onDrawerItemSelected(View view, int position) {
        switch (position) {
            case 0:
                Toast.makeText(this, "position " + position, Toast.LENGTH_SHORT).show();
            case 1:
                Intent addProductIntent = new Intent(this, AddProductActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean(BOOL_IS_ADD_PRODUCT, true);
                addProductIntent.putExtras(bundle);
                startActivity(addProductIntent);
            case 2:
                Toast.makeText(this, "position " + position, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onProductClicked(long id) {
        TextView textView = (TextView) findViewById(R.id.cart_layout_number);
        textView.setText(String.valueOf(id));
    }

    @Override
    public void onSelectColorInteraction(List<ItemColor> itemColorList) {

    }
}
