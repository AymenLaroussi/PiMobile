/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tournament.legacy.entites;

/**
 *
 * @author aymen
 */
public class Categories {
    // Déclaration des attributs du Catégories
    
    private int id;
    private String nom;

    public Categories(String nom) {
        this.nom = nom;
    }

    public Categories() {
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
    
    
}
