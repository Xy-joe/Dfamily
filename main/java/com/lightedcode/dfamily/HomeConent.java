package com.lightedcode.dfamily;

/**
 * Created by joebuntu on 12/9/16.
 */

public class HomeConent {
    String name, email, phone;
    int img1, img2;

    public HomeConent(String name, String email, String phone, int img1, int img2){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.img1 = img1;
        this.img2 = img2;

    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getImg1() {
        return img1;
    }

    public void setImg1(int img1) {
        this.img1 = img1;
    }

    public int getImg2() {
        return img2;
    }

    public void setImg2(int img2) {
        this.img2 = img2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
