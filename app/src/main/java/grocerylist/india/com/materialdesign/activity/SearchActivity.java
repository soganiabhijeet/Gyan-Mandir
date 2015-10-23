package grocerylist.india.com.materialdesign.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import grocerylist.india.com.materialdesign.R;
import grocerylist.india.com.materialdesign.fragment.ProductListFragment;

public class SearchActivity extends AppCompatActivity {
    private SearchView searchView;
    private FrameLayout searchContainer;
    private ProductListFragment productListFragment;
    private String TAG = "SearchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate : ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        searchView = (SearchView) findViewById(R.id.search_products);
        searchContainer = (FrameLayout) findViewById(R.id.product_container);
        //showSoftKeyboard(getApplicationContext());
        imm.showSoftInput(searchView, InputMethodManager.SHOW_IMPLICIT);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);

                //hideSoftKeyboard(getApplicationContext());
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                executeSearchOperation(newText);
                return true;
            }
        });

    }

    private void executeSearchOperation(String searchText) {
        Log.d(TAG, "executeSearchOperation searchText : " + searchText);
        FragmentManager fragmentManager = getSupportFragmentManager();
        productListFragment = (ProductListFragment) fragmentManager.findFragmentById(R.id.product_container);
        if (productListFragment != null) {
            productListFragment.updateListSearch(searchText);
        } else {
            productListFragment = ProductListFragment.newInstance(searchText);
            fragmentManager.beginTransaction().add(R.id.product_container, productListFragment).commit();
        }

    }

    public static void showSoftKeyboard(Context ctx) {
        InputMethodManager inputManager = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View v = ((Activity) ctx).getCurrentFocus();
        if (v == null)
            return;

        inputManager.showSoftInputFromInputMethod(v.getWindowToken(), 0);
    }

    public static void hideSoftKeyboard(Context ctx) {
        InputMethodManager inputManager = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View v = ((Activity) ctx).getCurrentFocus();
        if (v == null)
            return;

        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
