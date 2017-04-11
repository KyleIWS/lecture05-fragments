package edu.uw.fragmentdemo;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    public static final String TAG = "MoveFragment";
    private static final String SEARCH_ARG_KEY = "search_key";
    private ArrayAdapter<Movie> adapter;


    public MovieFragment() {
        // Required empty public constructor
    }

    interface OnMovieClickListener {
        void onMovieClicked(Movie movie);
    }

    // Factory method - build us a fragment
    public static MovieFragment newInstance(String searchTerm) {

        Bundle args = new Bundle();
        args.putString(SEARCH_ARG_KEY, searchTerm);
        MovieFragment fragment = new MovieFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflator.infalte(R.layout.fragment_movies, container, false);
        adapter = new ArrayAdapter<Movie>(getActivity(), R.layout.list_item, R.id.txtItem , new ArrayList<Movie>());
        ListView listView = (ListView) rootView.findViewById(R.id.listView);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int poisiton, long id) {
                Movie movie = (Movie) parent.getItemAtPosition(position);

                ((OnMovieClickListener)getActivity()).onMovieClicked(movie);

            }
        });

        String searchTerm = getArguments().getString(SEARCH_ARG_KEY);
        downloadMovieData(searchTerm);
        // Inflate the layout for this fragment


        return rootView;
    }



}
