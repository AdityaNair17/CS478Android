package course.examples.Services.MovieCommon;

interface MovieGenerator {
    String[] getAllMoviesTitles();
    String[] getAllMoviesDirectors();
    String[] getAllMoviesURL();
    String[] getMovieById(int id);
    String getMovieUrl(int id);
}