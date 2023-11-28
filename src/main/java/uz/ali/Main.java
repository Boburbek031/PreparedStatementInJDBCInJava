package uz.ali;

public class Main {
    public static void main(String[] args) {

        MovieRepo movieRepo = new MovieRepo();

        // get All movies
//        allMovies.forEach(movie -> System.out.println(movie));
        /*List<Movie> allMovies = movieRepo.getAllMovies();
        allMovies.forEach(System.out::println);*/

        // get a movie by Id
  /*      Movie movieById = movieRepo.getMovieById(3);
        System.out.println(movieById == null ? "Siz kiritgan id lik movie topilmadi" : movieById);*/

        // Insert a movie
        /*Integer insertMovie = movieRepo.insertMovie(new Movie("Beee",
                3203L, LocalDateTime.now(), LocalDate.now(), 6.6f));
        System.out.println(insertMovie);*/


        // Update a movie by Id
        /*Integer updatedMovie = movieRepo.updateMovieById(7, new Movie("Bumble Bee",
                5555L, LocalDateTime.now(), LocalDate.now(), 9.9f));
        System.out.println(updatedMovie.equals(0) ? "Not Updated" : "Updated");*/

        // Delete a movie by ID
        System.out.println(movieRepo.deleteMovieById(15));


    }
}