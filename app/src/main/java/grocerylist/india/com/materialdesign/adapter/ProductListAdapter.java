package grocerylist.india.com.materialdesign.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import grocerylist.india.com.materialdesign.model.Product;

/**
 * Created by abhijeetsogani on 10/21/15.
 */
public class ProductListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Product> products = new ArrayList<>();
    private static Context mContext;
    private LayoutInflater layoutInflater;

    public ProductListAdapter(Context context, List<Product> products) {
        mContext=context;
        this.products = products;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
