package grocerylist.india.com.materialdesign.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import java.util.List;

import grocerylist.india.com.materialdesign.R;
import grocerylist.india.com.materialdesign.activity.AddProductActivity;
import grocerylist.india.com.materialdesign.adapter.UpdateColorStockAdapter;
import grocerylist.india.com.materialdesign.model.Item;
import grocerylist.india.com.materialdesign.model.ItemColor;


public class SetColorStockDialog extends DialogFragment implements TextView.OnClickListener {
    private OnStockAddedListener mListener;
    private RecyclerView colorRecyclerView;
    private UpdateColorStockAdapter colorStockAdapter;
    private LinearLayoutManager linearLayoutManager;
    private TextView okButton;
    List<Item> itemsList;

    public static SetColorStockDialog newInstance(List<ItemColor> colors) {
        SetColorStockDialog fragment = new SetColorStockDialog();
        return fragment;
    }

    public SetColorStockDialog() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        itemsList = AddProductActivity.getSelectedColors();
        View v = inflater.inflate(R.layout.fragment_set_color_stock_dialog, container, false);
        colorRecyclerView = (RecyclerView) v.findViewById(R.id.color_stock_recycler_view);
        colorStockAdapter = new UpdateColorStockAdapter(getActivity(), itemsList);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        colorRecyclerView.setLayoutManager(linearLayoutManager);
        colorRecyclerView.setAdapter(colorStockAdapter);
        okButton = (TextView) v.findViewById(R.id.select_color_stock_ok);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        okButton.setOnClickListener(this);
        return v;
    }

    public void onButtonPressed() {
        if (mListener != null) {
            List<Item> items = colorStockAdapter.getUpdatedColorsList();
            mListener.onStockAdded(items);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnStockAddedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == okButton.getId()) {
            onButtonPressed();
            getDialog().dismiss();
        }
    }


    public interface OnStockAddedListener {
        public void onStockAdded(List<Item> itemColorList);
    }



}
