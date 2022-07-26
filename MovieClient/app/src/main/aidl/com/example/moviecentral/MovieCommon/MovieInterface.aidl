// MovieInterface.aidl
package com.example.moviecentral.MovieCommon;

// Declare any non-default types here with import statements

interface MovieInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);

    String[] getAllMoviesTitles();
    String[] getAllMoviesDirectors();
    String[] getAllMoviesURL();
    String[] getMovieById(int id);
    String getMovieUrl(int id);
}