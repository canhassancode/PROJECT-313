package com.example.project_313;

public class NohayModel {

    public String noha;

    public String title;

    public String category;


    public NohayModel() {}

    public NohayModel(String Noha) {
        this.noha = Noha;
    }


    // NOHA
    public String getNoha() {
        String newNoha = noha.replace('.','\n');
        return newNoha;
    }

    public void setNoha(String noha) {
        this.noha = noha;
    }

    // TITLE
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    //CATEGORY
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {

        this.category = category;
    }
}
