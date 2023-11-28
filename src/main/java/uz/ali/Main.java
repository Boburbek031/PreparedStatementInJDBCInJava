package uz.ali;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        MovieRepo movieRepo = new MovieRepo();

        // get All movies
//        allMovies.forEach(movie -> System.out.println(movie));
      /*  List<Movie> allMovies = movieRepo.getAllMoviesByPreparedStatement();
        allMovies.forEach(System.out::println);*/

        // get a movie by Id
       /* Movie movieById = movieRepo.getMovieByIdByPreparedStatement(3);
        System.out.println(movieById == null ? "Siz kiritgan id lik movie topilmadi" : movieById)*/;

        // Insert a movie
        /*Integer insertMovie = movieRepo.insertMovieByPreparedStatement(new Movie("Expendables",
                1111L, LocalDateTime.now(), LocalDate.now(), 7.9f));
        System.out.println(insertMovie);*/


        // Update a movie by Id
        Integer updatedMovie = movieRepo.updateMovieByIdByPreparedStatement(12, new Movie("new 7777554",
                5555L, LocalDateTime.now(), LocalDate.now(), 5.8f));
        System.out.println(updatedMovie.equals(0) ? "Not Updated" : "Updated");

        // Delete a movie by ID
//        System.out.println(movieRepo.deleteMovieById(15));


    }
}