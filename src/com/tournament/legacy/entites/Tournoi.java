package com.tournament.legacy.entites;

import java.util.Arrays;
import java.util.Objects;

public class Tournoi {
    private int id;
    private String nom;
    private int nbr_equipes;
    private int nbr_joueur_eq;
    private float prix;
    private String image;
    private String discord_channel;
    private String time;
    private String timeEnd ;
    private User organisateur;
    private Equipe[] equipes;
    private Jeu jeu ;

    public Tournoi() {
id=-1;
    }

    public Tournoi ( String nom, int nbr_equipes, int nbr_joueur_eq, float prix,
                   String image, String discord_channel) {

        this.nom = nom;
        this.nbr_equipes = nbr_equipes;
        this.nbr_joueur_eq = nbr_joueur_eq;
        this.prix = prix;
        this.image = image;
        this.discord_channel = discord_channel;

    }

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

    public int getNbr_equipes() {
        return nbr_equipes;
    }

    public void setNbr_equipes(int nbr_equipes) {
        this.nbr_equipes = nbr_equipes;
    }

    public int getNbr_joueur_eq() {
        return nbr_joueur_eq;
    }

    public void setNbr_joueur_eq(int nbr_joueur_eq) {
        this.nbr_joueur_eq = nbr_joueur_eq;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDiscord_channel() {
        return discord_channel;
    }

    public void setDiscord_channel(String discord_channel) {
        this.discord_channel = discord_channel;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public User getOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(User organisateur) {
        this.organisateur = organisateur;
    }

    public Equipe[] getEquipes() {
        return equipes;
    }

    public void setEquipes(Equipe[] equipes) {
        this.equipes = equipes;
    }

    public Jeu getJeu() {
        return jeu;
    }

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }

    @Override
    public String toString() {
        return "Tournoi{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", nbr_equipes=" + nbr_equipes +
                ", nbr_joueur_eq=" + nbr_joueur_eq +
                ", prix=" + prix +
                ", image='" + image + '\'' +
                ", discord_channel='" + discord_channel + '\'' +
                ", time='" + time + '\'' +
                ", timeEnd='" + timeEnd + '\'' +
                ", organisateur=" + organisateur +
                ", equipes=" + Arrays.toString(equipes) +
                ", jeu=" + jeu +
                '}';
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Tournoi tournoi = (Tournoi) o;
//        return id == tournoi.id && nbr_equipes == tournoi.nbr_equipes && nbr_joueur_eq == tournoi.nbr_joueur_eq && Float.compare(tournoi.prix, prix) == 0 && nom.equals(tournoi.nom) && image.equals(tournoi.image) && discord_channel.equals(tournoi.discord_channel) && time.equals(tournoi.time) && timeEnd.equals(tournoi.timeEnd) && organisateur.equals(tournoi.organisateur) && Arrays.equals(equipes, tournoi.equipes) && jeu.equals(tournoi.jeu);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = Objects.hash(id, nom, nbr_equipes, nbr_joueur_eq, prix, image, discord_channel, time, timeEnd, organisateur, jeu);
//        result = 31 * result + Arrays.hashCode(equipes);
//        return result;
//    }
}
