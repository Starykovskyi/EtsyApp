package com.starikovskii.etsytestapp.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CategoriesResponseModel implements Serializable
{

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("results")
    @Expose
    private List<CategoriesModel> results = null;
    @SerializedName("params")
    @Expose
    private Object params;
    @SerializedName("type")
    @Expose
    private String type;

    /**
     *
     * @return
     * The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     *
     * @param count
     * The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     *
     * @return
     * The results
     */
    public List<CategoriesModel> getResults() {
        return results;
    }

    /**
     *
     * @param results
     * The results
     */
    public void setResults(List<CategoriesModel> results) {
        this.results = results;
    }

    /**
     *
     * @return
     * The params
     */
    public Object getParams() {
        return params;
    }

    /**
     *
     * @param params
     * The params
     */
    public void setParams(Object params) {
        this.params = params;
    }

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }


}
