package grocerylist.india.com.materialdesign.fragment;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import grocerylist.india.com.materialdesign.R;
import grocerylist.india.com.materialdesign.adapter.ProductListAdapter;
import grocerylist.india.com.materialdesign.model.Product;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductListFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    public static final String TAB_ID = "id";
    public static final String TAB_TITLE = "title";
    private int id;
    private String title;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView productsRecyclerView;
    private OnFragmentInteractionListener mListener;
    private ProductListAdapter productListAdapter;
    private ArrayList<Product> products;
    

    // TODO: Rename and change types and number of parameters
    public static ProductListFragment newInstance(int id, String title) {
        ProductListFragment fragment = new ProductListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, TAB_ID);
        args.putString(ARG_PARAM2, TAB_TITLE);
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
            id = getArguments().getInt(TAB_ID);
            title = getArguments().getString(TAB_TITLE);
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
    private void initViews(View view)
    {
        productsRecyclerView=(RecyclerView)view.findViewById(R.id.products_recyclerview);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        productsRecyclerView.setLayoutManager(linearLayoutManager);
        products = new ArrayList<>();
        productListAdapter = new ProductListAdapter(getActivity(), products);
        productsRecyclerView.setAdapter(productListAdapter);
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
