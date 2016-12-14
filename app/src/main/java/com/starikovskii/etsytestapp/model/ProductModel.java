
package com.starikovskii.etsytestapp.model;

import java.io.Serializable;
import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Table(name = "Product")
public class ProductModel extends Model implements Serializable {

    @Column(name = "title")
    @SerializedName("title")
    @Expose
    private String title;

    @Column(name = "description")
    @SerializedName("description")
    @Expose
    private String description;

    @Column(name = "price")
    @SerializedName("price")
    @Expose
    private String price;

    @Column(name = "currency_code")
    @SerializedName("currency_code")
    @Expose
    private String currencyCode;

    @Column(name = "quantity")
    @SerializedName("quantity")
    @Expose
    private Integer quantity;

    @Column(name = "mainImage")
    @SerializedName("MainImage")
    @Expose
    private ProductImageModel mainImage;

    private final static long serialVersionUID = 394002314050108551L;

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The price
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price The price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * @return The currencyCode
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * @param currencyCode The currency_code
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    /**
     * @return The quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity The quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * @return The mainImage
     */
    public ProductImageModel getMainImage() {
        return mainImage;
    }

    /**
     * @param mainImage The MainImage
     */
    public void setMainImage(ProductImageModel mainImage) {
        this.mainImage = mainImage;
    }



}
