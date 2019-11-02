package com.example.nguyenducnhat.nhatstore.Model;

public class cart {
    public int id;
    public String Name;
    public int Price;
    public String Image;
    public int quantity;

    public cart(int id, String name, int price, String image, int quantity) {
        this.id = id;
        Name = name;
        Price = price;
        Image = image;
        this.quantity = quantity;
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

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
