package com.example.lokdaki;

public class workers {
    private String Wname,Wphone,profession;


    public workers(String wname, String wphone, String profession) {
        this.Wname = wname;
        this.Wphone = wphone;
        this.profession = profession;
    }


    public String getWname() {
        return Wname;
    }

    public void setWname(String wname) {
        Wname = wname;
    }

    public String getWphone() {
        return Wphone;
    }

    public void setWphone(String wphone) {
        Wphone = wphone;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
