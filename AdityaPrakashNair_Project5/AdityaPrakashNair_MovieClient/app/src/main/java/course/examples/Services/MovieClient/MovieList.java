package course.examples.Services.MovieClient;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MovieList extends ListActivity {
    String[] movies;
    String[] directors;
    String[] urls;
    String[] movieDescription;

    private ListView listView ;
    private ArrayAdapter<String> movieAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        movies = intent.getStringArrayExtra("MOVIES");
        directors = intent.getStringArrayExtra("DIRECTORS");
        urls = intent.getStringArrayExtra("URLS");
        createMovieList();

        listView = getListView();
        movieAdapter = new ArrayAdapter<>(this, R.layout.activity_movie_list, movieDescription);
        setListAdapter(movieAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("LIST","Position = " + i);
                Intent videoIntent = new Intent(getApplicationContext(), VideoActivity.class);
                videoIntent.putExtra("URL", urls[i]);
                startActivity(videoIntent);
            }
        });


    }

    private void createMovieList(){
        movieDescription = new String[movies.length];
        for(int m = 0; m < movies.length; m++){
            movieDescription[m] = "Title: " + movies[m] + '\n' + "Director: " + directors[m];
        }
    }
}