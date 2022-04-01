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
    private String user;
    private String message;
    private String produit;
    private String date;

    public Commentaires(String user, String message, String produit, String date) {
        this.user = user;
        this.message = message;
        this.produit = produit;
        this.date = date;
    }

    

    public Commentaires(String id, String user, String message, String produit, String date) {
        this.id = id;
        this.user = user;
        this.message = message;
        this.produit = produit;
        this.date = date;
    }
    
    public Commentaires(String message) {
        this.message = message;
    }

    public Commentaires(String message, String produit) {
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

    public String getUser() {
        return user;
    }

    public Commentaires(String id, String message, String date) {
        this.id = id;
        this.message = message;
        this.date = date;
    }

   

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    

    
    
    
}

