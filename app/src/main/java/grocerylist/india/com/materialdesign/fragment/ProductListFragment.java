package grocerylist.india.com.materialdesign.fragment;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import grocerylist.india.com.materialdesign.R;
import grocerylist.india.com.materialdesign.adapter.ProductListAdapter;
import grocerylist.india.com.materialdesign.database.DatabaseHandler;
import grocerylist.india.com.materialdesign.database.adapter.CategoryAdapter;
import grocerylist.india.com.materialdesign.database.adapter.ProductAdapter;
import grocerylist.india.com.materialdesign.model.Product;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductListFragment extends android.support.v4.app.Fragment implements DatabaseHandler {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    public static final String TAB_ID = "id";
    public static final String TAB_TITLE = "title";
    public static final String SEARCH_QUERY = "search_query";
    private int id;
    private String TAG = "ProductListFragment";
    private String title;
    private String searchQuery;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView productsRecyclerView;
    private OnFragmentInteractionListener mListener;
    private ProductListAdapter productListAdapter;
    private ArrayList<Product> products;
    private ProductAdapter productAdapter;
    private CategoryAdapter categoryAdapter;


    // TODO: Rename and change types and number of parameters
    public static ProductListFragment newInstance(int id, String mTitle) {
        ProductListFragment fragment = new ProductListFragment();
        Bundle args = new Bundle();
        args.putInt(TAB_ID, id);
        args.putString(TAB_TITLE, mTitle);
        fragment.setArguments(args);
        return fragment;
    }

    public static ProductListFragment newInstance(String searchProduct) {
        ProductListFragment fragment = new ProductListFragment();
        Bundle args = new Bundle();
        args.putString(SEARCH_QUERY, searchProduct);
        fragment.setArguments(args);
        return fragment;
    }

    public ProductListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        initViews(view);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void initViews(View view) {
        if (title != null)
            initializeProductList(title);
        products=new ArrayList<>();
            productsRecyclerView = (RecyclerView) view.findViewById(R.id.products_recyclerview);
            linearLayoutManager = new LinearLayoutManager(getActivity());
            productsRecyclerView.setLayoutManager(linearLayoutManager);
            productListAdapter = new ProductListAdapter(getActivity(), products);
            productsRecyclerView.setAdapter(productListAdapter);

    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void initializeProductList(String category) {

        Log.d(TAG, "category :" + category);
        if (categoryAdapter.getCategoryFromName(category) != null)
            products = (ArrayList<Product>) productAdapter.getProductListForCategory(categoryAdapter.getCategoryFromName(category));
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
        this.searchQuery = searchText;
        products.clear();
        products = (ArrayList<Product>) productAdapter.getProductListForSearch(searchText);
        productListAdapter.notifyDataSetChanged();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
