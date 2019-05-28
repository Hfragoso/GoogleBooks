
package com.example.googlebooks.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VolumeInfo implements Serializable {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("publishedDate")
    @Expose
    private String publishedDate;
    @SerializedName("imageLinks")
    @Expose
    private ImageLinks imageLinks;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }

}
