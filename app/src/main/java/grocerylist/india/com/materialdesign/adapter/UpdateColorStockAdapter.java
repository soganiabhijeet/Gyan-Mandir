package grocerylist.india.com.materialdesign.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import grocerylist.india.com.materialdesign.R;
import grocerylist.india.com.materialdesign.model.Item;

/**
 * Created by abhijeetsogani on 10/31/15.
 */
public class UpdateColorStockAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static Context mContext;
    private LayoutInflater layoutInflater;
    private String TAG = "UpdateColorStockAdapter";
    List<Item> mItems;

    public UpdateColorStockAdapter(Context context, List<Item> items) {
        mContext = context;
        this.mItems = items;
        layoutInflater = ((Activity) context).getLayoutInflater();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View view = layoutInflater.inflate(R.layout.select_color_stock_item, parent, false);
        return new ColorStockViewHolder(view, new MyCustomEditTextListener());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //Log.d(TAG, "position: " + position + "Inflating color: " + colors.get(position).toString());
        ColorStockViewHolder viewHolder = (ColorStockViewHolder) holder;
        viewHolder.colorName.setText(mItems.get(position).getColor().getColorName());
        viewHolder.myCustomEditTextListener.updatePosition(position);
        viewHolder.color.setBackgroundColor(Color.parseColor(mItems.get(position).getColor().getColorCode()));
        viewHolder.stock.setText(String.valueOf(mItems.get(position).getItemStock()));
        //TODO VIEWHOLDER Is selected
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    public static class ColorStockViewHolder extends RecyclerView.ViewHolder {
        private TextView colorName;
        private Button color;
        private EditText stock;
        private MyCustomEditTextListener myCustomEditTextListener;

        public ColorStockViewHolder(View itemView, MyCustomEditTextListener myCustomEditTextListener) {
            super(itemView);
            colorName = (TextView) itemView.findViewById(R.id.select_color_stock_name);
            this.myCustomEditTextListener = myCustomEditTextListener;
            color = (Button) itemView.findViewById(R.id.select_color_stock_button);
            stock = (EditText) itemView.findViewById(R.id.select_color_stock);
            stock.addTextChangedListener(myCustomEditTextListener);
        }
    }

    public List<Item> getUpdatedColorsList() {
        return mItems;
    }

    private class MyCustomEditTextListener implements TextWatcher {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            // no op
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            try {
                Log.d(TAG, "UPDATING VAUE : at position " + position + " value " + charSequence.toString());
                mItems.get(position).setItemStock(Integer.valueOf(charSequence.toString()));
            } catch (Exception e) {
                Log.d(TAG, "Exception occured: Error parsing number");
                //Toast.makeText(,"Please enter valid number",Toast.LENGTH_SHORT);
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {
            // no op
        }
    }

}
