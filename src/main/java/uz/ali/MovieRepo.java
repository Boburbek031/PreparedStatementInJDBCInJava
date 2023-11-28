package uz.ali;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class MovieRepo {

    // Retrieve all movies
    public List<Movie> getAllMoviesByPreparedStatement() {
        List<Movie> movies = new LinkedList<>();
        String selectQuery = "SELECT * FROM movie";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                movies.add(extractMovieFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movies;
    }

    // get a movie by ID
    public Movie getMovieByIdByPreparedStatement(Integer id) {
        String selectQueryById = "SELECT * FROM movie WHERE id = ?";
        Movie movie = null;

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQueryById)) {

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    movie = new Movie(resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getLong("duration"),
                            resultSet.getTimestamp("created_date").toLocalDateTime(),
                            resultSet.getDate("publish_date").toLocalDate(),
                            resultSet.getFloat("rating"));
                }
                return movie;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // Insert a movie by ID
    public Integer insertMovieByPreparedStatement(Movie movie) {
        String insertQuery = "insert into movie (title, duration, created_date, publish_date, rating)" +
                " values (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setLong(2, movie.getDuration());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(movie.getCreated_date()));
            preparedStatement.setDate(4, Date.valueOf(movie.getPublish_date()));
            preparedStatement.setFloat(5, movie.getRating());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // Update a movie by ID
    public Integer updateMovieByIdByPreparedStatement(Integer id, Movie updatedMovie) {
        String isThereMovieWithSuchId = "SELECT * FROM movie WHERE id = ?";
        String updateQuery = "UPDATE movie SET title = ?, duration = ?, created_date = ?, " +
                "publish_date = ?, rating = ? WHERE id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement selectStatement = connection.prepareStatement(isThereMovieWithSuchId)) {

            selectStatement.setInt(1, id);

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                        updateStatement.setString(1, updatedMovie.getTitle());
                        updateStatement.setLong(2, updatedMovie.getDuration());
                        updateStatement.setTimestamp(3, Timestamp.valueOf(updatedMovie.getCreated_date()));
                        updateStatement.setDate(4, Date.valueOf(updatedMovie.getPublish_date()));
                        updateStatement.setFloat(5, updatedMovie.getRating());
                        updateStatement.setInt(6, id);
                        return updateStatement.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }


    // Delete a movie by ID
    public Integer deleteMovieByIdByPreparedStatement(Integer id) {
        String query = "DELETE FROM movie WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
