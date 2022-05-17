
package com.tournament.legacy.app;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.util.Resources;
import com.tournament.legacy.entites.Produits;
import com.tournament.legacy.services.ServiceCategorie;
import com.tournament.legacy.services.ServiceProduits;

import java.util.ArrayList;

public class ListProduitsForm extends Form {

String ch;
    public ListProduitsForm(Resources res) {

        Button btnSuppriemr = new Button("X");

        setTitle("Liste des categories");
        setLayout(com.codename1.ui.layouts.BoxLayout.y());
        ArrayList<Produits> list = ServiceProduits.getInstance().ListeProduits();
        Container list1 = new Container(com.codename1.ui.layouts.BoxLayout.y());
        list1.setScrollableY(true);
        list1.setScrollableX(true);
         for ( Produits produit : list){
           Button show = new Button("Show Dialog");
           SpanLabel LabelComent = new SpanLabel();
            MultiButton sp = new MultiButton();
            sp.getAllStyles().setFgColor(0x350afe);
            Label l = new Label(" ");
            sp.setText("Id : "+produit.getId());
            sp.setTextLine1("titre : "+produit.getTitre());
            sp.setTextLine2("Prix : "+produit.getPrix()+" TND");
            sp.setTextLine3("Description : "+produit.getDescription());
            sp.setTextLine4("ref : "+produit.getRef());
            sp.setTextLine4("promo : "+produit.getPromo());
            ch = produit.getId();
            list1.add(LabelComent);

            sp.setLeadComponent(show);
            list1.add(sp);
            
         
         
                     show.addActionListener(e -> {if (Dialog.show("Confirmer", "", "SUPPRIMER", "ANNULER")) {
                System.out.println("test");
                       try{
        ServiceProduits.getInstance().supprimerProduits(show.getText());}
        catch (NullPointerException npe){
            new CategorieForm(res).show();
                }
                       
    }
             

});
         
         }



        

        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {

            new com.tournament.legacy.app.ProduitsForm(res).show();

        });

        this.add(list1);


    }
    }
