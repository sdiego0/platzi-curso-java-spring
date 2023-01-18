package com.platzi.marker.domain;

public class Category {
    private int catefgoryId;
    private String category;
    private boolean active;



    public int getCatefgoryId() {
        return catefgoryId;
    }

    public void setCatefgoryId(int catefgoryId) {
        this.catefgoryId = catefgoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
