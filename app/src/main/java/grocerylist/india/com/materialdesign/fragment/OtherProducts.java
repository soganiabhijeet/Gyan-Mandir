package grocerylist.india.com.materialdesign.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import grocerylist.india.com.materialdesign.R;

/**
 * Created by abhijeetsogani on 9/17/15.
 */
public class OtherProducts extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.other_products,container,false);
        return v;
    }
}
