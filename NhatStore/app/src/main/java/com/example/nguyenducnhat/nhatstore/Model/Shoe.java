package com.example.nguyenducnhat.nhatstore.Model;

public class Shoe extends Products {
    private int size;

    public  Shoe()
    {}
    public Shoe(int id, String name, int price, String descrption, String image,int size)
    {
        super(id,name,price,descrption,image);
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
