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
public class Produits {
    // Déclaration des attributs du Produits
    
    private int id;
    private String titre;
    private String description;
    private String promo;
    private String stock;
    private String flash;
    private String image;
    private String ref;
    private String longdescription;
    private String prix;
    private String categories;
    public Produits(int id, String titre, String description, String promo, String stock, String ref, String longdescription, String prix, String categories) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.promo = promo;
        this.stock = stock;
        this.ref = ref;
        this.longdescription = longdescription;
        this.prix = prix;
        this.categories = categories;
    }

    public Produits(String titre, String description, String promo, String stock, String ref, String longdescription, String prix, String categories) {
        this.titre = titre;
        this.description = description;
        this.promo = promo;
        this.stock = stock;
        this.ref = ref;
        this.longdescription = longdescription;
        this.prix = prix;
        this.categories = categories;
    }
    

    
    public Produits(int id, String titre, String description, String promo, String ref, String longdescription, String prix, String categories) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.promo = promo;
        this.ref = ref;
        this.longdescription = longdescription;
        this.prix = prix;
        this.categories = categories;
    }

    public Produits(int id, String titre, String description, String promo, String stock, String flash, String image, String ref, String longdescription, String prix, String categories) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.promo = promo;
        this.stock = stock;
        this.flash = flash;
        this.image = image;
        this.ref = ref;
        this.longdescription = longdescription;
        this.prix = prix;
        this.categories = categories;
    }

    public Produits(String valueOf, String valueOf0, String valueOf1, String valueOf2, String valueOf3, String valueOf4, String valueOf5) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
   

    public Produits(int id, String titre, String description, String promo, String flash, String image, String ref, String longdescription, String prix, String categories) {
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

    public Produits(String titre, String description, String promo, String flash, String image, String ref, String longdescription, String prix, String categories) {
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

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public String isFlash() {
        return flash;
    }

    public void setFlash(String flash) {
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

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Produits() {
    }
    
    
    
    
    
    
    
    
    
    
}
