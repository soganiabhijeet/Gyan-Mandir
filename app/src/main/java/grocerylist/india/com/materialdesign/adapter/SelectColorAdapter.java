package grocerylist.india.com.materialdesign.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import grocerylist.india.com.materialdesign.R;
import grocerylist.india.com.materialdesign.model.ItemColor;

/**
 * Created by abhijeetsogani on 10/26/15.
 */
public class SelectColorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static Context mContext;
    private LayoutInflater layoutInflater;
    private String TAG = "SelectColorAdapter";
    List<ItemColor> colors;

    public SelectColorAdapter(Context context, List<ItemColor> colors) {
        mContext = context;
        this.colors = colors;
        layoutInflater = ((Activity) context).getLayoutInflater();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View view = layoutInflater.inflate(R.layout.select_color_item, parent, false);
        return new ColorViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Log.d(TAG, "position: " + position + "Inflating color: " + colors.get(position).toString());
        ColorViewHolder viewHolder = (ColorViewHolder) holder;
        viewHolder.colorName.setText(colors.get(position).getColorName());
        viewHolder.color.setBackgroundColor(Color.parseColor(colors.get(position).getColorCode()));
        viewHolder.isSelected.setChecked(colors.get(position).getIsSelected());
        viewHolder.isSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*CheckBox cb = (CheckBox) v;
                ItemColor itemColor = (ItemColor) cb.getTag();
                itemColor.setIsSelected(!itemColor.getIsSelected());*/
                colors.get(position).setIsSelected(!colors.get(position).getIsSelected());
            }
        });
        //TODO VIEWHOLDER Is selected
    }


    @Override
    public int getItemCount() {
        return colors == null ? 0 : colors.size();
    }

    public static class ColorViewHolder extends RecyclerView.ViewHolder {
        private TextView colorName;
        private Button color;
        private CheckBox isSelected;

        public ColorViewHolder(View itemView) {
            super(itemView);
            colorName = (TextView) itemView.findViewById(R.id.select_color_name);
            color = (Button) itemView.findViewById(R.id.select_color_button);
            isSelected = (CheckBox) itemView.findViewById(R.id.select_color_checkbox);
        }
    }

    public List<ItemColor> getUpdatedColorsList() {
        return colors;
    }
}
