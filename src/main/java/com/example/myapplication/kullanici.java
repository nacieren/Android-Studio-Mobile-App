package com.example.myapplication;

public class kullanici  {
    String email;
    String isim;
    String soyisim;
    String şifre;
    String doğumYılı;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getŞifre() {
        return şifre;
    }

    public void setŞifre(String şifre) {
        this.şifre = şifre;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getSoyisim() {
        return soyisim;
    }

    public void setSoyisim(String soyisim) {
        this.soyisim = soyisim;
    }


    public String getDoğumYılı() {
        return doğumYılı;
    }

    public void setDoğumYılı(String doğumYılı) {
        this.doğumYılı = doğumYılı;
    }

    public kullanici(String email, String isim, String soyisim, String şifre, String doğumYılı){
    this.email=email;
    this.şifre=şifre;
    this.isim=isim;
    this.soyisim=soyisim;
    this.doğumYılı=doğumYılı;

    }
    public kullanici(){

    }

}
