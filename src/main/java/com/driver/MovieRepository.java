package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {
    private HashMap<String,Movie> movie_db = new HashMap<>();
    private HashMap<String,Director> director_db = new HashMap<>();
    private HashMap<String, List<String>> directorMoviesDB = new HashMap<>();

    public void addMovie(Movie movie) {
        movie_db.put(movie.getName(),movie);
    }

    public void addDirector(Director director) {
        director_db.put(director.getName(),director);
    }

    public void addMovieDirectorPair(String movieName, String directorName) {
        if(directorMoviesDB.containsKey(directorName)) {
            directorMoviesDB.get(directorName).add(movieName);
        }
        else {
            List<String> movies = new ArrayList<>();
            movies.add(movieName);
            directorMoviesDB.put(directorName,movies);
        }
    }
    public Movie getMovieByName(String name) {
        return movie_db.get(name);
    }
    public Director getDirectorByName(String name) {
        return director_db.get(name);
    }

    public List<String> getMoviesByDirectorName(String directorName) {
        return directorMoviesDB.get(directorName);
    }

    public List<String> getAllMovieNames() {
        List<String> keysList = new ArrayList<>(movie_db.keySet());
        return keysList;
    }
    public void deleteDirectorByName(String directorName) {
        List<String> movies = directorMoviesDB.get(directorName);
        directorMoviesDB.remove(directorName);
        director_db.remove(directorName);
        for(String m : movies) {
            movie_db.remove(m);
        }
    }

    public void deleteAllDirectors() {
        for(String key : directorMoviesDB.keySet()) {
            List<String> movie_list = directorMoviesDB.get(key);
            for(String movie : movie_list) {
                movie_db.remove(movie);
            }
        }
        directorMoviesDB.clear();
        director_db.clear();
    }
}
