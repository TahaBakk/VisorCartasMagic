package com.example.x3727349s.visorcartasmagic;

/**
 * Created by x3727349s on 14/10/16.
 */

import android.text.TextUtils;

import java.util.ArrayList;


public class Cartas {

    private String name;
    private String rarity;
    private int toughness;
    private int power;
    private int manaCost;
    private String  imageUrl;
    private String text;


    public void setText(String text) {this.text = text;}

    public void setImageUrl(String imageUrl) {this.imageUrl = imageUrl;}

    public String getImageUrl() {return imageUrl;}

    public String getText() {return text;}

    public String getName() {
        return name;
    }

    public String getRarity() {
        return rarity;
    }

    public int getToughness() {
        return toughness;
    }

    public int getPower() {
        return power;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public void setToughness(int toughness) {
        this.toughness = toughness;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public String toString() {
        return "Carta{" +
               "name='" + name + '\'' +
               "Text='" + text + '\'' +
               ", rarity=" + rarity +
               ", toughness='" + toughness + '\'' +
               ", power='" + power + '\'' +
               ", manaCost=" + manaCost +
               ", imageUrl=" + imageUrl +
               '}';
    }


}