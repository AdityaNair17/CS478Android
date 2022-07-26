package course.examples.Services.MovieClient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import course.examples.Services.MovieCommon.MovieGenerator;

public class MovieServiceUser extends Activity implements AdapterView.OnItemSelectedListener {

	protected static final String TAG = "MovieServiceUser";
	protected static final String movieNumbers[] = {"1","2","3","4","5","6"};
	protected static final int PERMISSION_REQUEST = 0;
	private MovieGenerator movieGeneratorService;
	private boolean isMovieServiceBound = false;
	private int selectedNumber = 1;
	TextView serviceStatus;
	protected static final String BOUNDED = "Movie Service Status: Bounded";
	protected static final String UNBOUNDED = "Movie Service Status: Unbounded";


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		Button bindServiceButton = (Button) findViewById(R.id.bindService);
		Button unbindServiceButton = (Button) findViewById(R.id.unbindService);
		Button moviesListButton = (Button) findViewById(R.id.movies_list_button);
		Button movieDetailButton = (Button) findViewById(R.id.specific_movie_button);
		Spinner numberSpinner = (Spinner) findViewById(R.id.numberSpinner);
		Button viewVideoButton = (Button) findViewById(R.id.specific_url_button);
		serviceStatus = (TextView) findViewById(R.id.serviceStatus);

		bindServiceButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				if(!isMovieServiceBound){
					bindService();
				} else {
					Toast.makeText(getApplicationContext(), "Movie Service Already Bounded", Toast.LENGTH_SHORT).show();
				}
			}
		});

		unbindServiceButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				if(isMovieServiceBound){
					unbindService(movieConnection);
					Toast.makeText(getApplicationContext(), "UNBOUNDED Movie Service", Toast.LENGTH_SHORT).show();
					isMovieServiceBound = false;
					serviceStatus.setText(UNBOUNDED);
				} else {
					Toast.makeText(getApplicationContext(), "Movie Service is NOT Bound", Toast.LENGTH_SHORT).show();
				}
			}
		});


		moviesListButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				try {
					if(isMovieServiceBound){
						String[] movies = movieGeneratorService.getAllMoviesTitles();
						String[] directors = movieGeneratorService.getAllMoviesDirectors();
						String[] urls = movieGeneratorService.getAllMoviesURL();

						Intent movieListIntent = new Intent(getApplicationContext(), MovieList.class);
						movieListIntent.putExtra("MOVIES", movies);
						movieListIntent.putExtra("DIRECTORS",directors);
						movieListIntent.putExtra("URLS", urls);

						startActivity(movieListIntent);
					} else {
						Toast.makeText(getApplicationContext(), "Please Bind Movie Service", Toast.LENGTH_SHORT).show();
					}
				} catch (RemoteException e){
					Log.i(TAG, e.toString());
				}
			}
		});

		numberSpinner.setOnItemSelectedListener(this);
		ArrayAdapter numberAdapter = new ArrayAdapter(this ,android.R.layout.simple_spinner_item, movieNumbers);
		numberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		numberSpinner.setAdapter(numberAdapter);

		movieDetailButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				try{
					if(isMovieServiceBound){
						String[] movie = movieGeneratorService.getMovieById(selectedNumber);

						Intent movieListIntent = new Intent(getApplicationContext(), MovieList.class);
						movieListIntent.putExtra("MOVIES", new String[]{movie[0]});
						movieListIntent.putExtra("DIRECTORS",new String[]{movie[1]});
						movieListIntent.putExtra("URLS", new String[]{movie[2]});

						startActivity(movieListIntent);
					} else {
						Toast.makeText(getApplicationContext(), "Please Bind Movie Service", Toast.LENGTH_SHORT).show();
					}
				} catch (RemoteException e) {
					Log.i(TAG, e.toString());
				}
			}
		});

		viewVideoButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				try{
					if(isMovieServiceBound){
						String url = movieGeneratorService.getMovieUrl(selectedNumber);

						Intent videoIntent = new Intent(getApplicationContext(), VideoActivity.class);
						videoIntent.putExtra("URL", url);
						startActivity(videoIntent);
					} else {
						Toast.makeText(getApplicationContext(), "Please Bind Movie Service", Toast.LENGTH_SHORT).show();
					}
				} catch (RemoteException e) {
					Log.i(TAG, e.toString());
				}
			}
		});
	}

	// Bind to KeyGenerator Service
	@Override
	protected void onStart() {
		super.onStart();

		if (checkSelfPermission("course.examples.Services.MovieService.GEN_ID")
			!= PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(this,
					new String[]{"course.examples.Services.MovieService.GEN_ID"},
					PERMISSION_REQUEST);
		}
		else {
			bindService();
		}
	}

	protected void bindService() {
		if (!isMovieServiceBound) {

			boolean isBindServiceSuccessful = false;
			Intent i = new Intent(MovieGenerator.class.getName());
			ResolveInfo info = getPackageManager().resolveService(i, 0);
			i.setComponent(new ComponentName(info.serviceInfo.packageName, info.serviceInfo.name));

			isBindServiceSuccessful = bindService(i, this.movieConnection, Context.BIND_AUTO_CREATE);
			if (isBindServiceSuccessful) {
				Toast.makeText(getApplicationContext(),"Movie Service BOUNDED",Toast.LENGTH_LONG).show();
				serviceStatus.setText(BOUNDED);
			} else {
				Toast.makeText(getApplicationContext(),"Movie Service Bound FAILED",Toast.LENGTH_LONG).show();
				serviceStatus.setText(UNBOUNDED);
			}
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		switch (requestCode) {
			case PERMISSION_REQUEST: {

				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					bindService();
				}
				else {
					Toast.makeText(this, "BUMMER: No Permission :-(", Toast.LENGTH_LONG).show() ;
				}
			}
			default: {
			}
		}
	}
	// Unbind from KeyGenerator Service
	@Override
	protected void onStop() {

		super.onStop();
	}

	private final ServiceConnection movieConnection = new ServiceConnection() {

		public void onServiceConnected(ComponentName className, IBinder iservice) {
			movieGeneratorService = MovieGenerator.Stub.asInterface(iservice);
			isMovieServiceBound = true;
			serviceStatus.setText(BOUNDED);
		}

		public void onServiceDisconnected(ComponentName className) {
			movieGeneratorService = null;
			isMovieServiceBound = false;
			serviceStatus.setText(UNBOUNDED);
		}
	};

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
		selectedNumber = Integer.parseInt(movieNumbers[pos]);
	}

	@Override
	public void onNothingSelected(AdapterView<?> adapterView) {

	}
}
