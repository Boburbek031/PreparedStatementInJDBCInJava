package uz.ali;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Movie {

    private Integer id;
    private String title;
    private Long duration;
    private LocalDateTime created_date;
    private LocalDate publish_date;
    private Float rating;

    public Movie() {
    }

    public Movie(Integer id, String title, Long duration, LocalDateTime created_date, LocalDate publish_date, Float rating) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.created_date = created_date;
        this.publish_date = publish_date;
        this.rating = rating;
    }

    public Movie(String title, Long duration, LocalDateTime created_date, LocalDate publish_date, Float rating) {
        this.title = title;
        this.duration = duration;
        this.created_date = created_date;
        this.publish_date = publish_date;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    public LocalDate getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(LocalDate publish_date) {
        this.publish_date = publish_date;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", created_date=" + created_date +
                ", publish_date=" + publish_date +
                ", rating=" + rating +
                '}';
    }
}
