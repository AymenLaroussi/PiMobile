/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tournament.legacy.app;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Aymen Laroussi
 */
public class ProduitsForm extends Form {
    Form current;
    Resources res;
   public ProduitsForm(Resources res) {
       
        current=this;
        setTitle("Produits");
        setLayout(BoxLayout.y());
        add(new Label("Choose an option"));
        Button btnAjout = new Button("Ajout d'un produit");
        Button btnListe = new Button("Liste des produits");
        Button btnSuppriemr = new Button("Supprimer un produit");
        btnAjout.addActionListener(e-> new AjoutProduitForm(res,current).show());
        btnListe.addActionListener(e-> new ListProduitsForm(res).show());
        addAll(btnAjout, btnListe,btnSuppriemr);
        
}

   


}
