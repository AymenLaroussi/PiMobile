/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tournament.legacy.app;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.tournament.legacy.entites.Produits;
import com.tournament.legacy.services.ServiceCommentaire;
import com.tournament.legacy.services.ServiceProduits;
import java.util.ArrayList;

/**
 *
 * @author Aymen Laroussi
 */
public class ListProduitsForm extends Form {
    Resources res;
    Form current;
    Button btnSuppriemr = new Button("X");
    public ListProduitsForm(Resources res,Form current){
        
        setTitle("Liste des  commentaires");
        add(new Label("Liste des  commentaires "));
        
        ArrayList<Produits> list = ServiceProduits.getInstance().ListeProduits();
        Form hi = new Form("Table Layout 2x2", new TableLayout(1, 6));
        hi.add("ID");
        hi.add("TITRE");
        hi.add("PROMO");
        hi.add("STOCK");
        hi.add("ACTION");
        hi.add("");
        for ( Produits c : list){
           
            add(new Label(c.getId().toString())).
            add(new Label(c.getTitre().toString())).
            add(new Label(c.getPromo().toString())).
            add(new Label(c.getStock().toString())).
            add(new Button(""));
            
           
        }
        hi.show(); 
        
          
          
        
        

        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e->current.showBack());
        
        
    }   }
