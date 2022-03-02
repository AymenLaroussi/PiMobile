/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tournament.legacy.entites;

import java.util.Date;

/**
 *
 * @author aymen
 */
public class Commentaires {
    private int id;
    private User user;
    private String message;
    private Produits produit;
    private Date date;

    public Commentaires(User user, String message, Produits produit, Date date) {
        this.user = user;
        this.message = message;
        this.produit = produit;
        this.date = date;
    }

    public Commentaires(int id, User user, String message, Produits produit, Date date) {
        this.id = id;
        this.user = user;
        this.message = message;
        this.produit = produit;
        this.date = date;
    }

    public Commentaires() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}

