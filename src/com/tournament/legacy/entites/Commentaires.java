/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tournament.legacy.entites;

/**
 *
 * @author Aymen Laroussi
 */
public class Commentaires {
    private String id;
    private User user;
    private String message;
    private Produits produit;
    private String date;

    public Commentaires(User user, String message, Produits produit, String date) {
        this.user = user;
        this.message = message;
        this.produit = produit;
        this.date = date;
    }

    public Commentaires(String id, User user, String message, Produits produit, String date) {
        this.id = id;
        this.user = user;
        this.message = message;
        this.produit = produit;
        this.date = date;
    }
    
    public Commentaires(String message) {
        this.message = message;
    }

    public Commentaires(String message, Produits produit) {
        this.message = message;
        this.produit = produit;
    }

    public Commentaires() {
    }

    @Override
    public String toString() {
        return "Commentaires{" + "id=" + id + ", user=" + user + ", message=" + message + ", produit=" + produit + ", date=" + date + '}';
    }

    

   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Produits getProduit() {
        return produit;
    }

    public void setProduit(Produits produit) {
        this.produit = produit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    

    
    
    
}

