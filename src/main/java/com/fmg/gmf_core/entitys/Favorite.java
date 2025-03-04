package com.fmg.gmf_core.entitys;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Favorite {
    private String email;
    private String favoriteable_type;
    private int favoriteable_id;
    private LocalDateTime create_time;

    public Favorite(String email, String favoriteable_type, int favoriteable_id, LocalDateTime create_time) {
        this.email = email;
        this.favoriteable_type = favoriteable_type;
        this.favoriteable_id = favoriteable_id;
        this.create_time = create_time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFavoriteable_type() {
        return favoriteable_type;
    }

    public void setFavoriteable_type(String favoriteable_type) {
        this.favoriteable_type = favoriteable_type;
    }

    public int getFavoriteable_id() {
        return favoriteable_id;
    }

    public void setFavoriteable_id(int favoriteable_id) {
        this.favoriteable_id = favoriteable_id;
    }

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }
}
