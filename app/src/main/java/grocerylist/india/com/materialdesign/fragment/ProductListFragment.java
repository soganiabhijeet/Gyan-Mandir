package grocerylist.india.com.materialdesign.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import grocerylist.india.com.materialdesign.R;
import grocerylist.india.com.materialdesign.activity.AddProductActivity;
import grocerylist.india.com.materialdesign.activity.UpdateProduct;
import grocerylist.india.com.materialdesign.adapter.ProductListAdapter;
import grocerylist.india.com.materialdesign.database.DatabaseHandler;
import grocerylist.india.com.materialdesign.database.adapter.CategoryAdapter;
import grocerylist.india.com.materialdesign.database.adapter.ProductAdapter;
import grocerylist.india.com.materialdesign.dialog.SelectColorDialog;
import grocerylist.india.com.materialdesign.model.Product;


public class ProductListFragment extends Fragment implements DatabaseHandler, ProductListAdapter.OnAddToCartClicked, ProductListAdapter.OnProductLongPressed {
    // TODO: Rename and change types of parameters
    public static final String TAB_ID = "id";
    public static final String TAB_TITLE = "title";
    public static final String SEARCH_QUERY = "search_query";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String BOOL_IS_UPDATE_PRODUCT = "is_update_product";
    public static final String PRODUCT_ID="product_id";
    private int id;
    private String TAG = "ProductListFragment";
    private String title;
    private String searchQuery;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView productsRecyclerView;
    private OnProductCLickedListener mListener;
    private ProductListAdapter productListAdapter;
    private ArrayList<Product> products;
    private ProductAdapter productAdapter;
    private AlertDialog onLongPressDialog;
    private static Product longPressedProduct = null;
    private CategoryAdapter categoryAdapter;
    final CharSequence[] longPressDialogItems = {
            "Update Product", "View Sale History"
    };

    public ProductListFragment() {
        // Required empty public constructor
    }

    public static Product getLongClickedProduct() {
        return longPressedProduct;
    }

    // TODO: Rename and change types and number of parameters
    public static ProductListFragment newInstance(int id, String mTitle) {
        Log.d("ProductListFragment", "newInstance : tab");
        ProductListFragment fragment = new ProductListFragment();
        Bundle args = new Bundle();
        args.putInt(TAB_ID, id);
        args.putString(TAB_TITLE, mTitle);
        fragment.setArguments(args);
        return fragment;
    }

    public static ProductListFragment newInstance(String searchProduct) {
        Log.d("ProductListFragment", "newInstance : search");
        ProductListFragment fragment = new ProductListFragment();
        Bundle args = new Bundle();
        args.putString(SEARCH_QUERY, searchProduct);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ProductListFragment", "onCreate");
        if (getArguments() != null) {
            initializeAdapter();
            openAdapter();
            id = getArguments().getInt(TAB_ID);
            title = getArguments().getString(TAB_TITLE);
            //Called from search
            searchQuery = getArguments().getString(SEARCH_QUERY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("ProductListFragment", "onCreateView");
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        initViews(view);
        return view;
    }

    public void onButtonPressed(Product product) {
        if (mListener != null) {
            Log.d(TAG, "Product hasColor " + product.getHasColor());
            if (product.getHasColor()) {
                showColorDialog();
            } else {
                mListener.onProductClicked(product.getProductId());
            }
        }
    }

    private void showColorDialog() {
        SelectColorDialog selectColorDialogDialog = new SelectColorDialog();
        selectColorDialogDialog.show(getFragmentManager(), "colors_dialog");
    }

    private void initViews(View view) {
        productsRecyclerView = (RecyclerView) view.findViewById(R.id.products_recyclerview);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        productsRecyclerView.setLayoutManager(linearLayoutManager);
        productListAdapter = new ProductListAdapter(getActivity(), products, this, this);
        productsRecyclerView.setAdapter(productListAdapter);
        initializeProductList(title);
    }


    private void initializeProductList(String category) {
        if (title != null) {
            products = new ArrayList<>();
            Log.d(TAG, "category :" + category);
            if (categoryAdapter.getCategoryFromName(category) != null) {
                products = (ArrayList<Product>) productAdapter.getProductListForCategory(categoryAdapter.getCategoryFromName(category));
                //TODO stop creating new adapter everytime
                productListAdapter = new ProductListAdapter(getActivity(), products, this, this);
                productsRecyclerView.setAdapter(productListAdapter);
                Log.d(TAG, "products size for category :" + products.size());
            }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnProductCLickedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnDateSelectedListener");
        }
    }


    @Override
    public void initializeAdapter() {
        productAdapter = new ProductAdapter(getActivity());
        categoryAdapter = new CategoryAdapter(getActivity());
    }

    @Override
    public void openAdapter() {
        productAdapter.open();
        categoryAdapter.open();
    }

    @Override
    public void closeAdapter() {
        productAdapter.close();
        productAdapter.close();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        closeAdapter();
    }

    public void updateListSearch(String searchText) {
        products = new ArrayList<>();
        Log.d("ProductListFragment", "updateListSearch");
        this.searchQuery = searchText;
        products = (ArrayList<Product>) productAdapter.getProductListForSearch(searchText);
        productListAdapter = new ProductListAdapter(getActivity(), products, this, this);
        productsRecyclerView.setAdapter(productListAdapter);
    }

    @Override
    public void onAddToCartCLicked(Product product) {
        Log.e(TAG, "Item clicked " + product.toString());
        onButtonPressed(product);

    }

    @Override
    public void onProductLongPressed(final Product product) {
        longPressedProduct = product;
        Log.e(TAG, "Product long pressed " + product.getProductId());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setItems(longPressDialogItems, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                switch (item) {
                    case 0:
                        openUpdateProduct(product.getProductId());
                        break;
                    case 1:
                        openProductHistory(product.getProductId());
                        break;
                }
            }
        });
        onLongPressDialog = builder.create();
        onLongPressDialog.show();
    }

    private void openProductHistory(long productId) {
        onLongPressDialog.dismiss();
        Intent openUpdateProduct = new Intent(getActivity(), UpdateProduct.class);
        startActivity(openUpdateProduct);
    }

    private void openUpdateProduct(long productId) {
        onLongPressDialog.dismiss();
        Intent addProductIntent = new Intent(getActivity(), AddProductActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean(BOOL_IS_UPDATE_PRODUCT, true);
        bundle.putLong(PRODUCT_ID,productId);
        addProductIntent.putExtras(bundle);
        startActivity(addProductIntent);
    }

    public interface OnProductCLickedListener {
        public void onProductClicked(long id);
    }

}
