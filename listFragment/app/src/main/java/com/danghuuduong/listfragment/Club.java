package com.danghuuduong.listfragment;

public class Club {
    private int id;
    private String name, url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Club(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }
}
