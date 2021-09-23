package dk.au.mad21fall.assignment1.au535993.ListActivity.Models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;


public class MultipleMovieDataViewModel extends ViewModel {

    private MutableLiveData<ArrayList<MovieData>> movieDataList;

    public void createMovieData(ArrayList<MovieData> movieDataArrayList){
        if (movieDataList == null) {
            movieDataList = new MutableLiveData<ArrayList<MovieData>>();
            movieDataList.setValue(movieDataArrayList);
        }
    }

    public LiveData<ArrayList<MovieData>> getMovieData()
    {
        return movieDataList;
    }

    public void updateMovieDataElement(MovieData newMovieDataElement){
        int position = Integer.parseInt(newMovieDataElement.position);
        movieDataList.getValue().set(position, newMovieDataElement);
        movieDataList.setValue(movieDataList.getValue());
    }
}
