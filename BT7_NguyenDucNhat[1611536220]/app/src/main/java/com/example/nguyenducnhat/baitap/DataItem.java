package com.example.nguyenducnhat.baitap;

public class DataItem {
    private int resid;
    private float a,b,c;

    public void setA(float a) {
        this.a = a;
    }

    public void setB(float b) {
        this.b = b;
    }

    public void setC(float c) {
        this.c = c;
    }

    public float getA() {

        return a;
    }

    public float getB() {
        return b;
    }

    public float getC() {
        return c;
    }

    public int getResid() {
        return resid;
    }


    public void setResid(int resid) {
        this.resid = resid;
    }

    public DataItem(int resid,float a,float b,float c)
    {
        this.resid = resid;
        this.a =a;
        this.b =b;
        this.c =c;
    }
}

