package com.example.nguyenducnhat.nhatstore.Model;

public class Products {
    public int id;
    public String Name;
    public int Price;
    public String Descrption;
    public String Image;

    public Products()
    {}

    public Products(int id, String name, int price, String descrption, String image) {
        this.id = id;
        this.Name = name;
        this.Price = price;
        this.Descrption = descrption;
        this.Image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getDescrption() {
        return Descrption;
    }

    public void setDescrption(String descrption) {
        Descrption = descrption;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
