package dk.au.mad21fall.assignment1.au535993.ListActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import dk.au.mad21fall.assignment1.au535993.ListActivity.Models.MovieData;
import dk.au.mad21fall.assignment1.au535993.ListActivity.MovieItemClickedListener.IMovieItemClickedListener;
import dk.au.mad21fall.assignment1.au535993.R;

public class MovieListActivity extends AppCompatActivity  implements IMovieItemClickedListener {

    private static final String LogTag = "MovieList";
    // listview
    private RecyclerView recyclerView;
    // adaptor
    private MovieAdaptor movieAdaptor;

    private ArrayList<MovieData> movieDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideBlueBar();
        setContentView(R.layout.activity_list);


        movieAdaptor = new MovieAdaptor(this);
        recyclerView = findViewById(R.id.movieRcvList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(movieAdaptor);

        // LoadData data from .csv and parse to movieData objects
        loadMovieData();
        // update the movieDataList with the newly loaded data
        movieAdaptor.updateMovieList(movieDataList);
    }

    private void hideBlueBar(){
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
    }

    private void loadMovieData() {
        // load csv files and parse
        movieDataList = new ArrayList<>();
        try {
            InputStreamReader is = new InputStreamReader(getAssets()
                    .open("movie_data.csv"));

            BufferedReader reader = new BufferedReader(is);
            // the first line is collum values, which we don't need
            String line = reader.readLine();

            // parse .csv string to objects
            while ((line = reader.readLine()) != null) {
                String[] lineContents = line.split(",");
                String name = lineContents[0].trim();
                String genre = lineContents[1].trim();
                String year = lineContents[2].trim();
                String rating = lineContents[3].trim();
                String plot = "";

                // If plot has a ',' within the text it will have '"' around the text. This takes care of both scenario's
                if(lineContents[4].contains("\""))
                {
                    plot = line.split("\"")[1].trim();
                }
                else {
                    plot = lineContents[4].substring(0,lineContents[4].length()-2).trim();
                }

                MovieData movieData = new MovieData(name, genre, plot, year, rating);
                movieDataList.add(movieData);
            }
        } catch (IOException e) {
            Log.d(LogTag, "Failed to load .csv file " + e.getStackTrace());
        }
    }

    @Override
    public void onMovieItemClicked(int index) {
        // open new details activity
        Log.d(LogTag, "onMovieItemClicked ");
    }

}