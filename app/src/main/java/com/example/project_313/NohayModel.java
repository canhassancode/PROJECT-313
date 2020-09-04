package com.example.project_313;

public class NohayModel {

    private String noha;

    // May not be required
    private String title;


    private NohayModel() {}

    private NohayModel(String Noha) {
        this.noha = Noha;
    }

    public String getNoha() {
        return noha;
    }

    public void setNoha(String noha) {
        this.noha = noha;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
