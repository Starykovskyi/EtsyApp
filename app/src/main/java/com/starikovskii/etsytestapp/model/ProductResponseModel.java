
package com.starikovskii.etsytestapp.model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductResponseModel implements Serializable {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("results")
    @Expose
    private List<ProductModel> results = null;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("pagination")
    @Expose
    private PaginationModel pagination;
    private final static long serialVersionUID = 2929734140458562462L;

    /**
     * @return The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * @return The results
     */
    public List<ProductModel> getResults() {
        return results;
    }

    /**
     * @param results The results
     */
    public void setResults(List<ProductModel> results) {
        this.results = results;
    }

    /**
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The pagination
     */
    public PaginationModel getPagination() {
        return pagination;
    }

    /**
     * @param pagination The pagination
     */
    public void setPagination(PaginationModel pagination) {
        this.pagination = pagination;
    }

}
