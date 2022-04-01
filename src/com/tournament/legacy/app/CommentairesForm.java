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
public class CommentairesForm extends Form {
    Form current;
    Resources res;
   public CommentairesForm(Resources res) {
       
        current=this;
        setTitle("Produits");
        setLayout(BoxLayout.y());
        add(new Label("Choose an option"));
        Button btnAjout = new Button("Ajout d'un Commentaire");
        Button btnListe = new Button("Liste des commentaires");
        Button btnSuppriemr = new Button("Supprimer un commentaire");
        btnAjout.addActionListener(e-> new AjoutCommentaireForm(res,current).show());
        btnListe.addActionListener(e-> new ListCommentairesForm(current).show());
        addAll(btnAjout, btnListe,btnSuppriemr);
        
}

   


}
