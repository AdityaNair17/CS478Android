package com.example.moviecentral.MovieService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.moviecentral.MovieCommon.MovieInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieService extends Service {

    String[] movieTitle = new String[]{"Movie1","Movie2","Movie3","Movie4","Movie5","Movie6"};
    String[] movieDirector = new String[]{"Director1","Director2","Director3","Director4","Director5","6"};
    String[] movieUrl = new String[]{"url1","url2","url3","url4","url5","url6"};


    public MovieService() {
        Log.i("Movie Service","Constructer Called");
    }

    private final MovieInterface.Stub movieBinder = new MovieInterface.Stub() {
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
            return new String[]{movieTitle[id], movieDirector[id], movieUrl[id]};
        }

        @Override
        public String getMovieUrl(int id) throws RemoteException {
            return movieUrl[id];
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return  movieBinder;
    }

}