package layout;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.*;
import android.widget.EditText;
import android.widget.Button;

import edu.uw.fragmentdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private OnSearchListener field;

    public interface OnSearchListener {
        public void onSearchSubmitted(String search);
    }

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            field = (OnSearchListener) context;
        }catch(ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnSearchListener");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        EditText text = (EditText) rootView.findViewById(R.id.txtSearch);

        Button button = (Button) rootView.findViewById(R.id.btnSearch);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String text2 = text.getText().toString();
                field.onSearchSubmitted(text2);
            }
        });

        return inflater.inflate(R.layout.fragment_search, container, false);
    }

}
