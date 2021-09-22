package dk.au.mad21fall.assignment1.au535993.DetailsActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import dk.au.mad21fall.assignment1.au535993.IntentConstants;
import dk.au.mad21fall.assignment1.au535993.ListActivity.DataLoader.MovieDataJsonWriter;
import dk.au.mad21fall.assignment1.au535993.ListActivity.Models.MovieData;
import dk.au.mad21fall.assignment1.au535993.R;
import dk.au.mad21fall.assignment1.au535993.Utils.Utils;

public class DetailsActivity extends AppCompatActivity {

    TextView detailsName, detailsYear, detailsGenre, detailsPlot, detailsIBDMValue, detailsUserNotesValue,detailsUserRating;
    ImageView detailsIcon;

    Button detailsBackButton, detailsRatingButton;

    private MovieData movieData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.hideBlueBar(this);
        setContentView(R.layout.activity_details);

        setUpUIElements();
        updateUi();
    }

    private void updateUi() {
        Intent intentListView = getIntent();
        JSONObject movieDataInJson = null;
        try {
            movieDataInJson = new JSONObject(intentListView.getStringExtra(IntentConstants.DETAILS));
            movieData = MovieDataJsonWriter.convertJsonToMovieData(movieDataInJson);
            detailsYear.setText(movieData.year);
            detailsGenre.setText(movieData.genre);
            detailsPlot.setText(movieData.plot);
            detailsIBDMValue.setText(movieData.rating);
            detailsName.setText(movieData.name);
            detailsUserRating.setText(movieData.userRating);
            detailsUserNotesValue.setText(movieData.notes);

            detailsIcon.setImageResource(movieData.mapGenreToId());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        movieData = MovieDataJsonWriter.convertJsonToMovieData(movieDataInJson);
    }


    private void setUpUIElements() {
        detailsYear = findViewById(R.id.detailsYear);
        detailsGenre = findViewById(R.id.detailsGenre);
        detailsPlot = findViewById(R.id.detailsPlotText);
        detailsIcon = findViewById(R.id.detailsIcon);
        detailsIBDMValue = findViewById(R.id.detailsIMDBValue);
        detailsName = findViewById(R.id.detailsName);

        detailsUserRating = findViewById(R.id.detailsUserRatingValue);

        detailsUserNotesValue = findViewById(R.id.detailsNotesValue);

        detailsRatingButton = findViewById(R.id.detailsRatingBttn);

        detailsBackButton = findViewById(R.id.detailsBackBttn);
        detailsBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieData.notes = detailsUserNotesValue.getText().toString();
                movieData.userRating = detailsUserRating.getText().toString();
                Intent resultIntent = new Intent();
                JSONObject movieDataInJson = MovieDataJsonWriter.convertMovieDataToJson(movieData);
                resultIntent.putExtra(IntentConstants.DETAILS, movieDataInJson.toString());
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }


}