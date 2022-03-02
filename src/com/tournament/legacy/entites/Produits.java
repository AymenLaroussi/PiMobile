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
public class Produits {
    // Déclaration des attributs du Produits
    
    private int id;
    private String titre;
    private String description;
    private int promo;
    private boolean flash;
    private String image;
    private String ref;
    private String longdescription;
    private float prix;
    private Categories categories;

    public Produits(int id, String titre, String description, int promo, boolean flash, String image, String ref, String longdescription, float prix, Categories categories) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.promo = promo;
        this.flash = flash;
        this.image = image;
        this.ref = ref;
        this.longdescription = longdescription;
        this.prix = prix;
        this.categories = categories;
    }

    public Produits(String titre, String description, int promo, boolean flash, String image, String ref, String longdescription, float prix, Categories categories) {
        this.titre = titre;
        this.description = description;
        this.promo = promo;
        this.flash = flash;
        this.image = image;
        this.ref = ref;
        this.longdescription = longdescription;
        this.prix = prix;
        this.categories = categories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPromo() {
        return promo;
    }

    public void setPromo(int promo) {
        this.promo = promo;
    }

    public boolean isFlash() {
        return flash;
    }

    public void setFlash(boolean flash) {
        this.flash = flash;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getLongdescription() {
        return longdescription;
    }

    public void setLongdescription(String longdescription) {
        this.longdescription = longdescription;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Produits() {
    }
    
    
    
    
    
    
    
    
    
    
}
