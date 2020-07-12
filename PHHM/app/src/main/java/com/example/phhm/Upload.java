package com.example.phhm;

import androidx.annotation.NonNull;

import com.google.firebase.database.Exclude;

public class Upload {
    private String imageName;
    private String imageUrl;

    @Exclude
    public String getKey() {
        return key;
    }
    @Exclude
    public void setKey(String key) {
        this.key = key;
    }

    private String key;

    public Upload()
    {

    }

    public Upload(String imageName,String imageUrl)
    {
        this.imageName=imageName;
        this.imageUrl=imageUrl;
    }
    public  String getImageName()
    {
        return imageName;
    }
    public void setImageName(String imageName)
    {
        this.imageName=imageName;
    }
    public String getImageUrl()
    {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl)
    {this.imageUrl=imageUrl;
    }


    @NonNull
    @Override
    public String toString() {
        return "Upload: " + this.imageName + " " + this.imageUrl;
    }
}
