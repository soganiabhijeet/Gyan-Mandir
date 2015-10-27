package grocerylist.india.com.materialdesign.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import grocerylist.india.com.materialdesign.R;
import grocerylist.india.com.materialdesign.database.DatabaseHandler;
import grocerylist.india.com.materialdesign.database.adapter.CategoryAdapter;
import grocerylist.india.com.materialdesign.database.adapter.ProductAdapter;
import grocerylist.india.com.materialdesign.dialog.SelectColorDialog;
import grocerylist.india.com.materialdesign.model.ItemColor;
import grocerylist.india.com.materialdesign.model.Product;

;
;

public class AddProductActivity extends AppCompatActivity implements TextView.OnClickListener, SelectColorDialog.SelectColorDialogListener, DatabaseHandler {
    private String TAG = "AddProductActivity";
    private Toolbar mToolbar;
    private TextView addProductButton;
    private EditText productName;
    private EditText productCompany;
    private EditText productSp;
    private EditText productCp;
    private AppCompatCheckBox hasSize;
    private boolean isProductAdded = false;
    private AppCompatCheckBox hasColor;
    CategoryAdapter categoryAdapter;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        initViews();
    }

    private void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Add Product");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        addProductButton = (TextView) findViewById(R.id.add_product_button);
        productName = (EditText) findViewById(R.id.product_name);
        productCompany = (EditText) findViewById(R.id.product_company);
        productSp = (EditText) findViewById(R.id.product_selling_price);
        productCp = (EditText) findViewById(R.id.product_cost_price);
        hasSize = (AppCompatCheckBox) findViewById(R.id.product_has_size);
        hasColor = (AppCompatCheckBox) findViewById(R.id.product_has_color);
        addProductButton.setOnClickListener(this);
        initializeAdapter();
        openAdapter();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == addProductButton.getId() && !isProductAdded) {
            if (hasColor.isChecked()) {
                showColorDialog();
            }
            long id = productAdapter.insertProduct(createProduct());
            if (id == -1)
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT);
            else {
                Log.d(TAG, "Product added : " + id);
                addProductButton.setText("Product added !!");
                isProductAdded = true;
            }

        } else {
            Toast.makeText(this, "Product has already been added", Toast.LENGTH_SHORT);
        }

    }

    private void showColorDialog() {
        FragmentManager fm = getSupportFragmentManager();
        SelectColorDialog selectColorDialogDialog = new SelectColorDialog();
        selectColorDialogDialog.show(fm, "colors_dialog");
    }

    @Override
    public void onSelectColorInteraction(List<ItemColor> colors) {
        Log.d(TAG, "Selected colors : " + colors.toString());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeAdapter();
    }

    private Product createProduct() {

        Product product = new Product();
        product.setHasColor(hasColor.isChecked());
        product.setHasSize(false);
        product.setCategory(categoryAdapter.getCategoryFromName(getResources().getString(R.string.tab_color)));
        product.setSellingPrice(Integer.valueOf(productSp.getText().toString()));
        product.setCostPrice(Integer.valueOf(productCp.getText().toString()));
        product.setCompanyName(productCompany.getText().toString());
        return product;
    }

    @Override
    public void initializeAdapter() {
        productAdapter = new ProductAdapter(this);
        categoryAdapter = new CategoryAdapter(this);
    }

    @Override
    public void openAdapter() {
        productAdapter.open();
        categoryAdapter.open();
    }

    @Override
    public void closeAdapter() {
        productAdapter.close();
        categoryAdapter.close();
    }
}
