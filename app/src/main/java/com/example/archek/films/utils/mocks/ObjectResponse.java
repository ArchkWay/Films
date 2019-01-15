package com.example.archek.films.utils.mocks;

import com.google.gson.annotations.SerializedName;

public class ObjectResponse {
    String id;
    @SerializedName("localized_name")
    String localizedName;
    String name;
    String year;
    String rating;
    @SerializedName("image_url")
    String imageUrl;
    String description;
    Boolean oneYear = false;

    public Boolean getOneYear() {
        return oneYear;
    }

    public void setOneYear(Boolean onYear) {
        this.oneYear = onYear;
    }

    public String getId() {
        return id;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getRating() {
        return rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }
}
