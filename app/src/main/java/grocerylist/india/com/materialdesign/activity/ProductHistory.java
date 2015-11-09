package grocerylist.india.com.materialdesign.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import java.util.Date;

import grocerylist.india.com.materialdesign.R;
import grocerylist.india.com.materialdesign.fragment.DateRangePicker;

public class ProductHistory extends AppCompatActivity implements DateRangePicker.OnDateSelectedListener {
    private FrameLayout datePicker;
    private DateRangePicker dateRangePicker;
    private String TAG = "ProductHistory";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_history);
        initViews();
        addDateFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product_history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        datePicker = (FrameLayout) findViewById(R.id.date_picker_frame);
    }

    private void addDateFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        dateRangePicker = (DateRangePicker) fragmentManager.findFragmentById(R.id.date_picker_frame);
        if (dateRangePicker != null) {
            //dateRangePicker.updateListSearch(searchText);
            fragmentManager.beginTransaction().add(R.id.date_picker_frame, dateRangePicker).commit();
        } else {
            dateRangePicker = DateRangePicker.newInstance();
            fragmentManager.beginTransaction().add(R.id.date_picker_frame, dateRangePicker).commit();
        }
    }

    @Override
    public void onFragmentInteraction(Date fromDate, Date toDate) {
        Log.e(TAG, "fromdate " + fromDate.toString() + " todate " + toDate);
    }
}
