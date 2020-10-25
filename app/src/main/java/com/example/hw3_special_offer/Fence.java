package com.example.hw3_special_offer;

import java.io.Serializable;

public class Fence implements Serializable {
    private String id;
    private String address;
    private String website;
    private float radius;
    private int type;
    private String message;
    private String code;
    private String color;
    private String logo;
    private double lat;
    private double lng;

    public Fence(String id, String address, String website, float radius, int type, String message, String code, String color, String logo, double lat, double lng) {
        this.id = id;
        this.address = address;
        this.website = website;
        this.radius = radius;
        this.type = type;
        this.message = message;
        this.code = code;
        this.color = color;
        this.logo = logo;
        this.lat = lat;
        this.lng = lng;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
