package com.fmg.gmf_core.entitys;

public class Opinion {
    private int id_recipe;
    private String email;
    private int rate;
    private String comment;

    public Opinion(int id_recipe, String email, int rate, String comment) {
        this.id_recipe = id_recipe;
        this.email = email;
        this.rate = rate;
        this.comment = comment;
    }

    public int getId_recipe() {
        return id_recipe;
    }

    public void setId_recipe(int id_recipe) {
        this.id_recipe = id_recipe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
