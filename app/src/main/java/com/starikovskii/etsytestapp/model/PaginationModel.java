package com.starikovskii.etsytestapp.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaginationModel implements Serializable
{

    @SerializedName("effective_limit")
    @Expose
    private Integer effectiveLimit;
    @SerializedName("effective_offset")
    @Expose
    private Integer effectiveOffset;
    @SerializedName("next_offset")
    @Expose
    private Integer nextOffset;
    @SerializedName("effective_page")
    @Expose
    private Integer effectivePage;
    @SerializedName("next_page")
    @Expose
    private Integer nextPage;
    private final static long serialVersionUID = -3080266657556039835L;

    /**
     *
     * @return
     *     The effectiveLimit
     */
    public Integer getEffectiveLimit() {
        return effectiveLimit;
    }

    /**
     *
     * @param effectiveLimit
     *     The effective_limit
     */
    public void setEffectiveLimit(Integer effectiveLimit) {
        this.effectiveLimit = effectiveLimit;
    }

    /**
     *
     * @return
     *     The effectiveOffset
     */
    public Integer getEffectiveOffset() {
        return effectiveOffset;
    }

    /**
     *
     * @param effectiveOffset
     *     The effective_offset
     */
    public void setEffectiveOffset(Integer effectiveOffset) {
        this.effectiveOffset = effectiveOffset;
    }

    /**
     *
     * @return
     *     The nextOffset
     */
    public Integer getNextOffset() {
        return nextOffset;
    }

    /**
     *
     * @param nextOffset
     *     The next_offset
     */
    public void setNextOffset(Integer nextOffset) {
        this.nextOffset = nextOffset;
    }

    /**
     *
     * @return
     *     The effectivePage
     */
    public Integer getEffectivePage() {
        return effectivePage;
    }

    /**
     *
     * @param effectivePage
     *     The effective_page
     */
    public void setEffectivePage(Integer effectivePage) {
        this.effectivePage = effectivePage;
    }

    /**
     *
     * @return
     *     The nextPage
     */
    public Integer getNextPage() {
        return nextPage;
    }

    /**
     *
     * @param nextPage
     *     The next_page
     */
    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

}
