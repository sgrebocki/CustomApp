package com.example.customapp;

public class DataClass {

    private String dataTitle;
    private String dataDescription;
    private String dataImage;

    public DataClass(String dataTitle, String dataDescription, String dataImage) {
        this.dataTitle = dataTitle;
        this.dataDescription = dataDescription;
        this.dataImage = dataImage;
    }

    public DataClass(){

    }

    public String getDataTitle() {
        return dataTitle;
    }

    public String getDataDescription() {
        return dataDescription;
    }

    public String getDataImage() {
        return dataImage;
    }

}
