package edu.uw.fragmentdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class MainActivity extends AppCompatActivity implements MoviesFragment.OnMovieSelectedListener, SearchFragment.searchListener {

    private static final String TAG = "MainActivity";
    private ViewPager pager;
    private MoviePagerAdapter pagerAdapter;
    private static MoviesFragment mf;
    private static DetailFragment df;
    private static SearchFragment sf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.pager = (ViewPager)findViewById(R.id.viewPagerId);
        pagerAdapter = new MoviePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
    }


    @Override
    public void onSearched(String term) {
        mf = MoviesFragment.newInstance(term);
        pagerAdapter.notifyDataSetChanged();
        pager.setCurrentItem(1);
    }

    public class MoviePagerAdapter extends FragmentStatePagerAdapter {

        public MoviePagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0) {
                return MainActivity.sf;
            } else if(position == 1) {
                return MainActivity.mf;
            } else if(position == 2) {
                return MainActivity.df;
            }
            return null;
        }

        @Override
        public int getCount() {
            if(MainActivity.df != null) {
                return 3;
            } else if(MainActivity.mf != null) {
                return 2;
            } else {
                return 1;
            }
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }

    //respond to search button clicking
    public void handleSearchClick(View v){
        EditText text = (EditText)findViewById(R.id.txtSearch);
        String searchTerm = text.getText().toString();

        //add a new results fragment to the page
        MoviesFragment fragment = MoviesFragment.newInstance(searchTerm);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment, "MoviesFragment");
        ft.addToBackStack(null); //remember for the back button
        ft.commit();
    }

    @Override
    public void onMovieSelected(Movie movie) {
        DetailFragment df = DetailFragment.newInstance(movie.toString(), movie.imdbId);

        pagerAdapter.notifyDataSetChanged();
        pager.setCurrentItem(2);
    }
}
