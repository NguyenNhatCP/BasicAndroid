package com.example.nguyenducnhat.nhatstore.Model;

public class Clothes extends Products {
    private String size;
    public  Clothes()
    {}
    public Clothes(int id, String name, int price, String descrption, String image,String size)
    {
        super(id,name,price,descrption,image);
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
