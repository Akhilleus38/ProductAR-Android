package com.tsoftmobile.t_softar;

public class Book {

    private String Title;
    String host_url;              //URL
    private String Category;
    private String Description;
    private int Thumbnail;
    private int can;



    public Book(String title,String category,String description, int thumbnail,String host_url){
        Title = title;
        Category = category;
        Description = description;
        Thumbnail = thumbnail;
        this.host_url = host_url;
    }


    public String getTitle() {
        return Title;
    }

    public String getCategory() {
        return Category;
    }

    public String getDescription() {
        return Description;
    }

    public String getHost_url() {
        return host_url;
    }

    public int getThumbnail() {
        return Thumbnail;
    }


    public void setTitle(String title) {
        Title = title;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }

    public void setHost_url(String host_url) {
        this.host_url = host_url;
    }
}
