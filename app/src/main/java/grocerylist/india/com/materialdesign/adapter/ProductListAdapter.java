package grocerylist.india.com.materialdesign.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import grocerylist.india.com.materialdesign.R;
import grocerylist.india.com.materialdesign.model.Product;

/**
 * Created by abhijeetsogani on 10/21/15.
 */
public class ProductListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Product> products = new ArrayList<>();
    private static Context mContext;
    private LayoutInflater layoutInflater;

    public ProductListAdapter(Context context, List<Product> products) {
        mContext = context;
        this.products = products;
        layoutInflater = ((Activity) context).getLayoutInflater();
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.product_list_item, viewGroup, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ProductViewHolder holder = (ProductViewHolder) viewHolder;
        holder.productPrice.setText(products.get(i).getSellingPrice().toString());
        holder.productCompanyName.setText(products.get(i).getCompanyName());
        holder.productName.setText(products.get(i).getProductName());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        private TextView productName;
        private TextView productCompanyName;
        private TextView productPrice;
        private ImageView addToCartImage;

        public ProductViewHolder(View itemView) {
            super(itemView);
            productName = (TextView) itemView.findViewById(R.id.productName);
            productCompanyName = (TextView) itemView.findViewById(R.id.productCompany);
            productPrice = (TextView) itemView.findViewById(R.id.productPrice);
        }
    }
}
