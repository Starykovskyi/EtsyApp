package com.starikovskii.etsytestapp.model;
import java.io.Serializable;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Table(name = "Image")
public class ProductImageModel implements Serializable
{
    @Column(name = "listing_image_id")
    @SerializedName("listing_image_id")
    @Expose
    private Integer listingImageId;

    @Column(name = "url_75x75")
    @SerializedName("url_75x75")
    @Expose
    private String url75x75;

    @Column(name = "url_170x135")
    @SerializedName("url_170x135")
    @Expose
    private String url170x135;

    @Column(name = "url_570xN")
    @SerializedName("url_570xN")
    @Expose
    private String url570xN;

    @Column(name = "url_fullxfull")
    @SerializedName("url_fullxfull")
    @Expose
    private String urlFullxfull;
    private final static long serialVersionUID = -5542754780403665421L;

    /**
     *
     * @return
     *     The listingImageId
     */
    public Integer getListingImageId() {
        return listingImageId;
    }

    /**
     *
     * @param listingImageId
     *     The listing_image_id
     */
    public void setListingImageId(Integer listingImageId) {
        this.listingImageId = listingImageId;
    }


    /**
     *
     * @return
     *     The url75x75
     */
    public String getUrl75x75() {
        return url75x75;
    }

    /**
     *
     * @param url75x75
     *     The url_75x75
     */
    public void setUrl75x75(String url75x75) {
        this.url75x75 = url75x75;
    }

    /**
     *
     * @return
     *     The url170x135
     */
    public String getUrl170x135() {
        return url170x135;
    }

    /**
     *
     * @param url170x135
     *     The url_170x135
     */
    public void setUrl170x135(String url170x135) {
        this.url170x135 = url170x135;
    }

    /**
     *
     * @return
     *     The url570xN
     */
    public String getUrl570xN() {
        return url570xN;
    }

    /**
     *
     * @param url570xN
     *     The url_570xN
     */
    public void setUrl570xN(String url570xN) {
        this.url570xN = url570xN;
    }

    /**
     *
     * @return
     *     The urlFullxfull
     */
    public String getUrlFullxfull() {
        return urlFullxfull;
    }

    /**
     *
     * @param urlFullxfull
     *     The url_fullxfull
     */
    public void setUrlFullxfull(String urlFullxfull) {
        this.urlFullxfull = urlFullxfull;
    }
}
