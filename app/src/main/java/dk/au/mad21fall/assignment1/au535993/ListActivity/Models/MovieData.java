package dk.au.mad21fall.assignment1.au535993.ListActivity.Models;

import java.time.Year;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import dk.au.mad21fall.assignment1.au535993.R;

public class MovieData {

    public String name,genre, plot, year, rating;

    Map<String, Integer> genreMapping;

    public MovieData(String name, String genre, String plot, String year, String rating) {
        this.name = name;
        this.genre = genre;
        this.plot = plot;
        this.year = year;
        this.rating = rating;

        genreMapping = new HashMap<String, Integer>();
        loadMappingScheme();
    }

    private void loadMappingScheme()
    {
        genreMapping.put("Action", R.drawable.ic_action);
        genreMapping.put("Comedy", R.drawable.ic_comedy);
        genreMapping.put("Drama", R.drawable.ic_drama);
        genreMapping.put("Horror", R.drawable.ic_horror);
        genreMapping.put("Romance", R.drawable.ic_romance);
        genreMapping.put("Western", R.drawable.ic_western);
    }

    public int mapGenreToId(){
        return genreMapping.get(this.genre);
    }

}
