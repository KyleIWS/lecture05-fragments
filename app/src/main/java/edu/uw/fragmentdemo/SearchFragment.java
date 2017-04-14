package edu.uw.fragmentdemo;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    private searchListener callback;

    public interface searchListener {
        public void onSearched(String term);
    }

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {

        Bundle args = new Bundle();
        
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callback = (searchListener) context;
        } catch(ClassCastException exc) {
            throw new ClassCastException("Must implement searchListener in context");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_search, container, false);

        final EditText text = (EditText) view.findViewById(R.id.txtSearch);
        Button button = (Button) view.findViewById(R.id.btnSearch);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searched = text.getText().toString();
                callback.onSearched(searched);
            }
        });

        return view;
    }

}
