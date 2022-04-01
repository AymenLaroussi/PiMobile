package com.tournament.legacy.entites;

public class Jeu {
    private int id ;
    private String nom;
    private String image;
    private Tournoi tournoi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public Jeu() {

    }
    public Jeu(String nom) {
        this.nom = nom;
    }

    public Jeu(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Jeu{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", image='" + image + '\'' +
                ", tournoi=" + tournoi +
                '}';
    }
}
