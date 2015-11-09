package grocerylist.india.com.materialdesign.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import grocerylist.india.com.materialdesign.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnDateSelectedListener} interface
 * to handle interaction events.
 * Use the {@link DateRangePicker#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DateRangePicker extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
   /* private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
*/
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    // private DatePicker datePicker;
    private EditText fromDate;
    private Button confirmDateButton;
    private EditText toDate;
    private Date fromSelectedDate;
    private Date toSelectedDate;
    private DatePickerDialog fromDatePicker;
    private DatePickerDialog toDatePicker;
    private OnDateSelectedListener mListener;
    private SimpleDateFormat simpleDateFormat;


    // TODO: Rename and change types and number of parameters
    public static DateRangePicker newInstance() {
        DateRangePicker fragment = new DateRangePicker();
      /*  Bundle args = new Bundle();
        fragment.setArguments(args);*/
        return fragment;
    }

    public DateRangePicker() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_date_range_picker, container, false);
        initViews(view);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Date fromDate, Date toDate) {
        if (mListener != null) {
            mListener.onFragmentInteraction(fromDate, toDate);
        }
    }

    private void initViews(View view) {
        /*datePicker = (DatePicker) view.findViewById(R.id.date_picker);
        datePicker.setVisibility(View.GONE);*/
        fromDate = (EditText) view.findViewById(R.id.from_date);
        toDate = (EditText) view.findViewById(R.id.to_date);
        confirmDateButton = (Button) view.findViewById(R.id.confirm_date_button);
        fromDate.setOnClickListener(this);
        toDate.setOnClickListener(this);
        confirmDateButton.setOnClickListener(this);
        simpleDateFormat = new SimpleDateFormat("dd-MMM-yy");
        initializeDatesDialog();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnDateSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnDateSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm_date_button:
                validateInput();
                break;
            case R.id.from_date:
                fromDatePicker.show();
                break;
            case R.id.to_date:
                toDatePicker.show();
                break;

        }
    }

    private void initializeDatesDialog() {
        //datePicker.setVisibility(View.VISIBLE);
        Calendar calendar = Calendar.getInstance();
        fromDatePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                fromDate.setText(simpleDateFormat.format(newDate.getTime()));
                fromSelectedDate = newDate.getTime();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));

        toDatePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                toDate.setText(simpleDateFormat.format(newDate.getTime()));
                toSelectedDate = newDate.getTime();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));

    }

    private void getToDate() {
    }

    private void validateInput() {
        if (fromSelectedDate == null || toSelectedDate == null) {
            Toast.makeText(getActivity(), "Please select all dates ", Toast.LENGTH_SHORT).show();
            return;
        }


        if (toSelectedDate.before(fromSelectedDate)) {
            Toast.makeText(getActivity(), "To date cannot be after from date ", Toast.LENGTH_SHORT).show();
            return;
        }
        onButtonPressed(fromSelectedDate, toSelectedDate);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnDateSelectedListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Date fromDate, Date toDate);
    }

}
