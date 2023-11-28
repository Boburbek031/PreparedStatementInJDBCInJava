package uz.ali;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class MovieRepo {

    static PreparedStatement preparedStatement = null;
    static ResultSet resultSet = null;
    private final Connection connection = DatabaseUtil.getConnection();

    // Retrieve all movies
    public List<Movie> getAllMoviesByPreparedStatement() {
        List<Movie> movies = new LinkedList<>();
        String query = "SELECT * FROM movie";

        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                movies.add(extractMovieFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return movies;
    }

    // get a movie by ID
   /* public Movie getMovieById(Integer id) {
        String query = "SELECT * FROM movie WHERE id = " + id;
        Movie movie = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                movie = new Movie();
                movie.setId(id);
                movie.setTitle(resultSet.getString("title"));
                movie.setDuration(resultSet.getLong("duration"));
                movie.setCreated_date(resultSet.getTimestamp("created_date").toLocalDateTime());
                movie.setPublish_date(resultSet.getDate("publish_date").toLocalDate());
                movie.setRating(resultSet.getFloat("rating"));
                return movie;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return movie;
    }


    // Insert a movie by ID
    public Integer insertMovie(Movie movie) {
        try {
            statement = connection.createStatement();
            String insertQuery = "insert into movie (title, duration, created_date, publish_date, rating)" +
                    " values ('%s', %d, '%s', '%s', '%s')";
            insertQuery = String.format(insertQuery, movie.getTitle(), movie.getDuration(),
                    movie.getCreated_date().toString(), movie.getPublish_date().toString(), movie.getRating());
//            System.out.println(insertQuery);
            return statement.executeUpdate(insertQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    // Update a movie by ID
    public Integer updateMovieById(Integer id, Movie updatedMovie) {
        String isThereMovieWithSuchId = "SELECT * FROM movie WHERE id = " + id;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(isThereMovieWithSuchId);
            if (resultSet.next()) {
                String updateQuery = String.format("UPDATE movie SET title = '%s', duration = %d, created_date = '%s', " +
                                "publish_date = '%s', rating = '%s' WHERE id = %d",
                        updatedMovie.getTitle(), updatedMovie.getDuration(),
                        updatedMovie.getCreated_date().toString(), updatedMovie.getPublish_date().toString(),
                        updatedMovie.getRating().toString(), id);
                return statement.executeUpdate(updateQuery);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return 0;
    }


    // Delete a movie by ID
    public Integer deleteMovieById(Integer id) {
        String query = "DELETE FROM movie WHERE id = " + id;
        try {
            statement = connection.createStatement();
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
*/
    // Close the connection
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        if (resultSet != null) {
            resultSet.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }

    // Helper method to extract a Movie object from a ResultSet
    private Movie extractMovieFromResultSet(ResultSet resultSet) throws SQLException {
        return new Movie(resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getLong("duration"),
                resultSet.getTimestamp("created_date").toLocalDateTime(),
                resultSet.getDate("publish_date").toLocalDate(),
                resultSet.getFloat("rating"));
    }


}
