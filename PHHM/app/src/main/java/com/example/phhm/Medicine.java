package com.example.phhm;


public class Medicine {
    String id;
    String medispin;
    String time;

    public Medicine(){

    }

    public Medicine(String time,String medispin,String id) {

        this.id = id;
        this.medispin = medispin;
        this.time=time;
    }

    public String getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getMedispin() {
        return medispin;
    }
}
