package de.fh_zwickau.ciapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class CI implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private final int id;
    @ColumnInfo(name = "title")
    private final String title;
    @ColumnInfo(name = "story")
    private final String story;
    @ColumnInfo(name = "place")
    private final String place;
    @ColumnInfo(name = "perspective")
    private final String perspective;

    @ColumnInfo(name = "is_favorite")
    private Boolean isFavorite;

    @ColumnInfo(name = "author")
    private final String author;


    public CI(int id, String title, String story, String place, String perspective, Boolean isFavorite, String author) {
        this.id = id;
        this.title = title;
        this.story = story;
        this.place = place;
        this.perspective = perspective;
        this.isFavorite = isFavorite;
        this.author = author;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getStory() {
        return this.story;
    }

    public String getPlace() {
        return this.place;
    }

    public String getPerspective() {
        return this.perspective;
    }

    public Boolean getIsFavorite() {
        return this.isFavorite;
    }
    public void setIsFavorite(Boolean isFavorite) {
         this.isFavorite = isFavorite;
    }

    public String getAuthor() { return this.author; }
}