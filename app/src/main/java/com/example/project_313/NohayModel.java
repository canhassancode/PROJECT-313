package com.example.project_313;

public class NohayModel {

    public String noha;

    // May not be required
    public String title;


    public NohayModel() {}

    public NohayModel(String Noha) {
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
