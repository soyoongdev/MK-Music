package com.example.miku_project.models;

public class Response2pikModel {
    private String saved;

    public Response2pikModel() {
    }

    public Response2pikModel(String saved) {
        this.saved = saved;
    }

    public String getSaved() {
        return "https://2.pik.vn/" + saved;
    }

    public void setSaved(String saved) {
        this.saved = saved;
    }
}
