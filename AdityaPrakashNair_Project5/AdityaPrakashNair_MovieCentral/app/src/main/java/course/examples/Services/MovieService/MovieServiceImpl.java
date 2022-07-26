package course.examples.Services.MovieService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import course.examples.Services.MovieCommon.MovieGenerator;

public class MovieServiceImpl extends Service {

	String[] movieTitle = new String[]{"Fight Club","Inception","Zindagi Na Milegi Dobara","Your Name (Kimi No Na Wa)","Drishyam","3 Idiots"};
	String[] movieDirector = new String[]{"David Fincher","Christopher Nolan","Zoya Akhtar","Makato Shinkai","Jeethu Joseph","Rajkumar Hirani"};
	String[] movieUrl = new String[]{"https://www.youtube.com/watch?v=SUXWAEX2jlg",
			"https://www.youtube.com/watch?v=YoHD9XEInc0",
			"https://www.youtube.com/watch?v=FJrpcDgC3zU",
			"https://www.youtube.com/watch?v=xU47nhruN-Q",
			"https://www.youtube.com/watch?v=eMASubc1y_k",
			"https://www.youtube.com/watch?v=xvszmNXdM4w"};

	private final MovieGenerator.Stub movieBinder = new MovieGenerator.Stub() {

		@Override
		public String[] getAllMoviesTitles() throws RemoteException {
			return movieTitle;
		}

		@Override
		public String[] getAllMoviesDirectors() throws RemoteException {
			return movieDirector;
		}

		@Override
		public String[] getAllMoviesURL() throws RemoteException {
			return movieUrl;
		}

		@Override
		public String[] getMovieById(int id) throws RemoteException {
			id--;
			if(id >= movieTitle.length){
				return new String[]{String.valueOf(movieTitle.length)};
			} else {
				return new String[]{movieTitle[id], movieDirector[id], movieUrl[id]};
			}
		}

		@Override
		public String getMovieUrl(int id) throws RemoteException {
			id = id == 0 ? id : id-1;
			if(id >= movieTitle.length){
				return String.valueOf(movieTitle.length);
			} else {
				return movieUrl[id];
			}
		}
	};

	// Return the Stub defined above
	@Override
	public IBinder onBind(Intent intent) {
		return movieBinder;
	}
}
