package com.lightedcode.dfamily;

/**
 * Created by joebuntu on 11/18/16.
 */

public class Data {
    int imgid, phone; String name, emails;

    public Data(int imgid, String name) {
        this.imgid = imgid;
        this.name = name;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
