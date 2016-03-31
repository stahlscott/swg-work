/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.dvdlibrarymvc.dto;

import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class Dvd {

    private Integer dvdId;
    @NotEmpty(message = "You must supply a value for Title")
    @Length(max = 50, message="Title must be no more than 50 characters in length")
    private String title;
    @NotEmpty(message = "You must supply a value for Release Date")
    @Length(max = 15, message = "Title must be no more than 15 characters in length")
    private String releaseDate;
    @Length(max = 10, message = "MPAA Rating must be no more than 10 characters in length")
    private String mpaaRating;
    @Length(max = 50, message = "Studio must be no more than 50 characters in length")
    private String studio;
    @NotEmpty(message = "You must supply a value for Director")
    @Length(max = 50, message = "Director must be no more than 50 characters in length")
    private String director;
    @NotEmpty(message = "You must supply a value for Reviewer Rating")
    @Length(max = 50, message = "Reviewer Rating must be no more than 2 characters in length")
    private String reviewerRating;
    @Length(max = 500, message = "Notes must be no more than 500 characters in length")
    private String notes;

    public Integer getDvdId() {
        return dvdId;
    }

    public void setDvdId(Integer dvdId) {
        this.dvdId = dvdId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getReviewerRating() {
        return reviewerRating;
    }

    public void setReviewerRating(String reviewerRating) {
        this.reviewerRating = reviewerRating;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.dvdId);
        hash = 17 * hash + Objects.hashCode(this.title);
        hash = 17 * hash + Objects.hashCode(this.releaseDate);
        hash = 17 * hash + Objects.hashCode(this.mpaaRating);
        hash = 17 * hash + Objects.hashCode(this.studio);
        hash = 17 * hash + Objects.hashCode(this.director);
        hash = 17 * hash + Objects.hashCode(this.reviewerRating);
        hash = 17 * hash + Objects.hashCode(this.notes);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dvd other = (Dvd) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.releaseDate, other.releaseDate)) {
            return false;
        }
        if (!Objects.equals(this.mpaaRating, other.mpaaRating)) {
            return false;
        }
        if (!Objects.equals(this.studio, other.studio)) {
            return false;
        }
        if (!Objects.equals(this.director, other.director)) {
            return false;
        }
        if (!Objects.equals(this.notes, other.notes)) {
            return false;
        }
        if (!Objects.equals(this.dvdId, other.dvdId)) {
            return false;
        }
        if (!Objects.equals(this.reviewerRating, other.reviewerRating)) {
            return false;
        }
        return true;
    }

}
