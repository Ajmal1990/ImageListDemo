
package com.ajmal.imagelistdemo.flickrutilities;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ApiResponse {

    @SerializedName("photoset")
    @Expose
    private Photoset photoset;
    @SerializedName("stat")
    @Expose
    private String stat;

    /**
     * 
     * @return
     *     The photoset
     */
    public Photoset getPhotoset() {
        return photoset;
    }

    /**
     * 
     * @param photoset
     *     The photoset
     */
    public void setPhotoset(Photoset photoset) {
        this.photoset = photoset;
    }

    /**
     * 
     * @return
     *     The stat
     */
    public String getStat() {
        return stat;
    }

    /**
     * 
     * @param stat
     *     The stat
     */
    public void setStat(String stat) {
        this.stat = stat;
    }

}
