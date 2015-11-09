package grocerylist.india.com.materialdesign.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
public class ProductListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnLongClickListener {
    private List<Product> products = new ArrayList<>();
    private static Context mContext;
    private LayoutInflater layoutInflater;
    private OnAddToCartClicked addToCart;
    private OnProductLongPressed productLongPressed;
    private String TAG = "ProductListAdapter";

    public ProductListAdapter(Context context, List<Product> products, OnAddToCartClicked mAddToCart, OnProductLongPressed mOnLongPressed) {
        mContext = context;
        this.products = products;
        layoutInflater = ((Activity) context).getLayoutInflater();
        this.addToCart = mAddToCart;
        this.productLongPressed=mOnLongPressed;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Log.d(TAG, "onCreateViewHolder");
        View view = layoutInflater.inflate(R.layout.product_list_item, viewGroup, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder");
        ProductViewHolder holder = (ProductViewHolder) viewHolder;
        holder.productPrice.setText(products.get(i).getSellingPrice().toString());
        holder.productCompanyName.setText(products.get(i).getCompanyName());
        holder.productName.setText(products.get(i).getProductName());
        holder.addToCartImage.setImageResource(R.drawable.heart_selected);
        holder.addToCartImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart.onAddToCartCLicked(products.get(i));
            }
        });
        holder.productCard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                productLongPressed.onProductLongPressed(products.get(i));
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return products == null ? 0 : products.size();
    }

    /**
     * Called when a view has been clicked and held.
     *
     * @param v The view that was clicked and held.
     * @return true if the callback consumed the long click, false otherwise.
     */
    @Override
    public boolean onLongClick(View v) {
        //Log.e(TAG, "Item at postion " + v. + " long pressed");
        return true;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        private TextView productName;
        private TextView productCompanyName;
        private TextView productPrice;
        private ImageView addToCartImage;
        private CardView productCard;

        public ProductViewHolder(View itemView) {
            super(itemView);
            productName = (TextView) itemView.findViewById(R.id.productName);
            productCompanyName = (TextView) itemView.findViewById(R.id.productCompany);
            productPrice = (TextView) itemView.findViewById(R.id.productPrice);
            addToCartImage = (ImageView) itemView.findViewById(R.id.product_save);
            productCard = (CardView) itemView.findViewById(R.id.product_card);
        }
    }

    public interface OnAddToCartClicked {
        void onAddToCartCLicked(Product productId);
    }

    public interface OnProductLongPressed {
        void onProductLongPressed(Product product);
    }

}
