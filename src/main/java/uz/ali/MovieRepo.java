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
        String selectQuery = "SELECT * FROM movie";

        try {
            preparedStatement = connection.prepareStatement(selectQuery);
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
    public Movie getMovieByIdByPreparedStatement(Integer id) {
        String selectQueryById = "SELECT * FROM movie WHERE id = ?";
        Movie movie = null;
        try {
            preparedStatement = connection.prepareStatement(selectQueryById);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                movie = new Movie(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getLong("duration"),
                        resultSet.getTimestamp("created_date").toLocalDateTime(),
                        resultSet.getDate("publish_date").toLocalDate(),
                        resultSet.getFloat("rating"));
                return movie;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                close();
                return movie;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    // Insert a movie by ID
    public Integer insertMovieByPreparedStatement(Movie movie) {
        try {
            String insertQuery = "insert into movie (title, duration, created_date, publish_date, rating)" +
                    " values (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setLong(2, movie.getDuration());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(movie.getCreated_date()));
            preparedStatement.setDate(4, Date.valueOf(movie.getPublish_date()));
            preparedStatement.setFloat(5, movie.getRating());
            return preparedStatement.executeUpdate();
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
    public Integer updateMovieByIdByPreparedStatement(Integer id, Movie updatedMovie) {
        String isThereMovieWithSuchId = "SELECT * FROM movie WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(isThereMovieWithSuchId);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                String updateQuery = "UPDATE movie SET title = ?, duration = ?, created_date = ?, " +
                        "publish_date = ?, rating = ? WHERE id = ?";

                preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setString(1, updatedMovie.getTitle());
                preparedStatement.setLong(2, updatedMovie.getDuration());
                preparedStatement.setTimestamp(3, Timestamp.valueOf(updatedMovie.getCreated_date()));
                preparedStatement.setDate(4, Date.valueOf(updatedMovie.getPublish_date()));
                preparedStatement.setFloat(5, updatedMovie.getRating());
                preparedStatement.setInt(6, id);
                return preparedStatement.executeUpdate();
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
    public Integer deleteMovieByIdByPreparedStatement(Integer id) {
        String query = "DELETE FROM movie WHERE id = ?" ;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
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
