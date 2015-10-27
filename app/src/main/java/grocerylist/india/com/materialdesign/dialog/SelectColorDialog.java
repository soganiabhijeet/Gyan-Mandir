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

import java.util.ArrayList;
import java.util.List;

import grocerylist.india.com.materialdesign.R;
import grocerylist.india.com.materialdesign.adapter.SelectColorAdapter;
import grocerylist.india.com.materialdesign.model.ItemColor;


public class SelectColorDialog extends DialogFragment implements TextView.OnClickListener {
    RecyclerView colorRecyclerView;
    SelectColorAdapter colorAdapter;
    LinearLayoutManager linearLayoutManager;
    TextView okButton;
    List<ItemColor> colorList = new ArrayList<>();
    private SelectColorDialogListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_select_color, container, false);
        initializeColors();
        colorRecyclerView = (RecyclerView) v.findViewById(R.id.color_recycler_view);
        colorAdapter = new SelectColorAdapter(getActivity(), colorList);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        colorRecyclerView.setLayoutManager(linearLayoutManager);
        colorRecyclerView.setAdapter(colorAdapter);
        okButton = (TextView) v.findViewById(R.id.select_color_ok);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        okButton.setOnClickListener(this);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed() {
        if (mListener != null) {
            List<ItemColor> colors = colorAdapter.getUpdatedColorsList();
            mListener.onSelectColorInteraction(colors);
        }
    }

    private void initializeColors() {
        ItemColor red = new ItemColor("Red", "#F44336", false);
        ItemColor pink = new ItemColor("Pink", "#E91E63", false);
        ItemColor yellow = new ItemColor("Yellow", "#FFEB3B", false);
        ItemColor green = new ItemColor("Green", "#4CAF50", false);
        ItemColor blue = new ItemColor("Blue", "#2196F3", false);
        ItemColor violet = new ItemColor("Indigo", "#3F51B5", false);
        ItemColor orange = new ItemColor("Orange", "#FF9800", false);
        ItemColor black = new ItemColor("Black", "#000000", false);
        ItemColor white = new ItemColor("White", "#FFFFFF", false);
        colorList.add(red);
        colorList.add(pink);
        colorList.add(yellow);
        colorList.add(green);
        colorList.add(violet);
        colorList.add(blue);
        colorList.add(orange);
        colorList.add(black);
        colorList.add(white);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (SelectColorDialogListener) activity;
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

    public interface SelectColorDialogListener {
        // TODO: Update argument type and name
        public void onSelectColorInteraction(List<ItemColor> itemColorList);
    }

}
