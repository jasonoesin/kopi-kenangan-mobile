package com.example.qualification_js22_1.model;

public class Transaction {
    private int id;
    private int total;
    private String time;

    public Transaction(int id, int total, String time) {
        this.id = id;
        this.total = total;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
