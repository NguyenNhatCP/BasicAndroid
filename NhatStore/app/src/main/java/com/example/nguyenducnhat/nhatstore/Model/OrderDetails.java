package com.example.nguyenducnhat.nhatstore.Model;

public class OrderDetails {
        public int STT;
        public int MaHD;
        public int MaSP;
        public String TenKH;
        public int Soluong;
        public int Gia;
        public String Ngay;

    public OrderDetails(int STT, int maHD, int maSP, String tenKH, int soluong, int gia, String ngay) {
        this.STT = STT;
        MaHD = maHD;
        MaSP = maSP;
        TenKH = tenKH;
        Soluong = soluong;
        Gia = gia;
        Ngay = ngay;
    }

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int maHD) {
        MaHD = maHD;
    }

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int maSP) {
        MaSP = maSP;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String tenKH) {
        TenKH = tenKH;
    }

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int soluong) {
        Soluong = soluong;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }

    public String getNgay() {
        return Ngay;
    }

    public void setNgay(String ngay) {
        Ngay = ngay;
    }
}
