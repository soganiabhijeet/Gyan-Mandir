package grocerylist.india.com.materialdesign.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import grocerylist.india.com.materialdesign.R;
import grocerylist.india.com.materialdesign.database.DatabaseHandler;
import grocerylist.india.com.materialdesign.database.adapter.CategoryAdapter;
import grocerylist.india.com.materialdesign.database.adapter.ItemAdapter;
import grocerylist.india.com.materialdesign.database.adapter.ProductAdapter;
import grocerylist.india.com.materialdesign.dialog.SelectColorDialog;
import grocerylist.india.com.materialdesign.dialog.SetColorStockDialog;
import grocerylist.india.com.materialdesign.fragment.ProductListFragment;
import grocerylist.india.com.materialdesign.model.Item;
import grocerylist.india.com.materialdesign.model.ItemColor;
import grocerylist.india.com.materialdesign.model.Product;

;
;

public class AddProductActivity extends AppCompatActivity implements TextView.OnClickListener, SelectColorDialog.SelectColorDialogListener, SetColorStockDialog.OnStockAddedListener, DatabaseHandler {
    private String TAG = "AddProductActivity";
    private Toolbar mToolbar;
    private TextView addProductButton;
    private EditText productName;
    private EditText productCompany;
    private EditText productSp;
    private Spinner categorySpinner;
    private EditText productCp;
    private boolean isProductAdded = false;
    private boolean isUpdateProduct = false;
    private boolean isAddProduct = false;
    private AppCompatCheckBox hasColor;
    private Product initialProduct = null;
    CategoryAdapter categoryAdapter;
    private ProductAdapter productAdapter;
    private ItemAdapter itemAdapter;
    private long mProductId;
    private long updateProductId;
    private static List<Item> selectedItems;

    public static List<Item> getSelectedColors() {
        return selectedItems;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedItems = new ArrayList<>();
        setContentView(R.layout.activity_add_product);
        if (getIntent().getExtras().getBoolean(MainActivity.BOOL_IS_ADD_PRODUCT)) {
            isAddProduct = true;
            initViewsForAddProduct();
        } else if (getIntent().getExtras().getBoolean(ProductListFragment.BOOL_IS_UPDATE_PRODUCT)) {
            isUpdateProduct = true;
            mProductId=getIntent().getExtras().getLong(ProductListFragment.PRODUCT_ID);
            initViewsForUpdateProduct();
        }


    }

    private void initViewsForAddProduct() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Add Product");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        categorySpinner = (Spinner) findViewById(R.id.spinner_label);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
        addProductButton = (TextView) findViewById(R.id.add_product_button);
        productName = (EditText) findViewById(R.id.product_name);
        productCompany = (EditText) findViewById(R.id.product_company);
        productSp = (EditText) findViewById(R.id.product_selling_price);
        productCp = (EditText) findViewById(R.id.product_cost_price);
        hasColor = (AppCompatCheckBox) findViewById(R.id.product_has_color);
        addProductButton.setText("Add Product");
        addProductButton.setOnClickListener(this);
        initializeAdapter();
        openAdapter();
    }

    private void initViewsForUpdateProduct() {
        initProduct();
        if (initialProduct != null) {
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(mToolbar);
            getSupportActionBar().setTitle("Update Product");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            categorySpinner = (Spinner) findViewById(R.id.spinner_label);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.category_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            categorySpinner.setAdapter(adapter);
            addProductButton = (TextView) findViewById(R.id.add_product_button);
            productName = (EditText) findViewById(R.id.product_name);
            productName.setText(initialProduct.getProductName());
            productCompany = (EditText) findViewById(R.id.product_company);
            productCompany.setText(initialProduct.getCompanyName());
            productSp = (EditText) findViewById(R.id.product_selling_price);
            productSp.setText(initialProduct.getSellingPrice().toString());
            productCp = (EditText) findViewById(R.id.product_cost_price);
            productCp.setText(initialProduct.getCostPrice().toString());
            hasColor = (AppCompatCheckBox) findViewById(R.id.product_has_color);
            hasColor.setVisibility(View.GONE);
            addProductButton.setOnClickListener(this);
            initializeAdapter();
            openAdapter();
        }

    }

    private void initProduct() {
        initialProduct = ProductListFragment.getLongClickedProduct();
        Log.d(TAG, "Initialized product " + initialProduct.getProductId());
    }


    @Override
    public void onClick(View v) {
        if (isAddProduct) {
            if (v.getId() == addProductButton.getId() && !isProductAdded && validateProduct()) {
                if (hasColor.isChecked()) {
                    showColorDialog();
                } else {
                    addProductWithoutColor();
                }
            } else {
                Toast.makeText(this, "Product has already been added", Toast.LENGTH_SHORT);
            }
        } else if (isUpdateProduct) {

        }
    }

    private void addProductWithoutColor() {

        long productId = productAdapter.insertProduct(createProduct());
        if (productId == -1)
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT);
        else {
            mProductId = productId;
            Log.d(TAG, "Product added : " + productId);
            addProductButton.setText("Product added ");
            isProductAdded = true;
            addSingleItem(productId);
        }
    }

    private void addSingleItem(long productId) {
        Item item = new Item();
        item.setColor(null);
        item.setProductId(productId);
        itemAdapter.insertItem(item);
    }

    private void addProductWithColor() {

        long productId = productAdapter.insertProduct(createProduct());
        if (productId == -1)
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT);
        else {
            mProductId = productId;
            Log.d(TAG, "Product added : " + productId);
            addProductButton.setText("Product added ");
            isProductAdded = true;
        }
    }


    private boolean validateProduct() {
        if (!(productName.getText().toString() != null && !productName.getText().toString().equals(""))) {
            Toast.makeText(this, "Product name cannot be empty ", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!(productCompany.getText().toString() != null && !productCompany.getText().toString().equals(""))) {
            Toast.makeText(this, "Product Company cannot be empty ", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!(productSp.getText().toString() != null && !productSp.getText().toString().equals(""))) {
            Toast.makeText(this, "Product Selling Price cannot be empty ", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!(productCp.getText().toString() != null && !productCp.getText().toString().equals(""))) {
            Toast.makeText(this, "Product Cost Price cannot be empty ", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void showColorDialog() {
        FragmentManager fm = getSupportFragmentManager();
        SelectColorDialog selectColorDialogDialog = new SelectColorDialog();
        selectColorDialogDialog.show(fm, "colors_dialog");

    }

    private void showColorStockDialog() {
        FragmentManager fm = getSupportFragmentManager();
        SetColorStockDialog colorStockDialog = new SetColorStockDialog();
        colorStockDialog.show(fm, "colors_stock_dialog");
    }


    @Override
    public void onSelectColorInteraction(List<ItemColor> colors) {
        selectedItems.clear();
        addProductWithColor();
        Log.d(TAG, "Selected colors : " + colors.toString());
        for (ItemColor color : colors) {
            if (color.getIsSelected())
                selectedItems.add(initializeItems(color, mProductId));
        }
        showColorStockDialog();
    }

    private Item initializeItems(ItemColor color, long productId) {
        Item item = new Item();
        item.setColor(color);
        item.setProductId(productId);
        return item;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeAdapter();
    }

    private Product createProduct() {

        Product product = new Product();
        product.setProductName(productName.getText().toString());
        product.setHasColor(hasColor.isChecked());
        product.setHasSize(false);
        product.setCategory(categoryAdapter.getCategoryFromName(getResources().getStringArray(R.array.category_array)[categorySpinner.getSelectedItemPosition()]));
        product.setSellingPrice(Integer.valueOf(productSp.getText().toString()));
        product.setCostPrice(Integer.valueOf(productCp.getText().toString()));
        product.setCompanyName(productCompany.getText().toString());
        return product;
    }

    @Override
    public void initializeAdapter() {
        productAdapter = new ProductAdapter(this);
        categoryAdapter = new CategoryAdapter(this);
        itemAdapter = new ItemAdapter(this);
    }

    @Override
    public void openAdapter() {
        productAdapter.open();
        categoryAdapter.open();
        itemAdapter.open();
    }

    @Override
    public void closeAdapter() {
        productAdapter.close();
        categoryAdapter.close();
        itemAdapter.close();
    }


    @Override
    public void onStockAdded(List<Item> itemColorList) {
        Log.d(TAG, itemColorList.toString());
        for (Item item : itemColorList) {
            itemAdapter.insertItem(item);
        }

    }
}
