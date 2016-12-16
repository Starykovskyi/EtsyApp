package com.starikovskii.etsytestapp.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoriesModel implements Serializable {

    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("short_name")
    @Expose
    private String shortName;
    @SerializedName("long_name")
    @Expose
    private String longName;
    private final static long serialVersionUID = -7361811298419741380L;

    public CategoriesModel() {
    }

    /**
     * @return The categoryId
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId The category_id
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return The categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName The category_name
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * @return The shortName
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * @param shortName The short_name
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * @return The longName
     */
    public String getLongName() {
        return longName;
    }

    /**
     * @param longName The long_name
     */
    public void setLongName(String longName) {
        this.longName = longName;
    }

    @Override
    public String toString() {
        return shortName;
    }
}