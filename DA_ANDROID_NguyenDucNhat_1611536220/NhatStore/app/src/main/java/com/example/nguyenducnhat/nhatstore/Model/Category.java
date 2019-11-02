package com.example.nguyenducnhat.nhatstore.Model;

public class Category {
    int id;
    String Code;
    String Name;
    String Image;

    public Category(int id, String code, String name, String image) {
        this.id = id;
        Code = code;
        Name = name;
        Image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
