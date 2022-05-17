/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tournament.legacy.app;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;

import com.codename1.ui.layouts.BorderLayout;
import com.tournament.legacy.entites.Produits;
import com.tournament.legacy.services.ServiceProduits;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Aymen Laroussi
 */
public class AjoutProduitForm extends Form {
     Form current;
     Ressources res;
    public AjoutProduitForm(Resources res,Form previous){
        setTitle("Ajout des  produits");
        Toolbar tb = new Toolbar(true);
        current= this;
        setToolbar(tb);
        getContentPane().setScrollVisible(false);


        tb.addSearchCommand(e -> {

        });
     

       TextField titre = new TextField("","entrer la titre ");
        titre.setUIID("TextFieldBlack");
       addStringValue("Titre",titre);
       
       TextField description = new TextField("","saisir la description");
       description.setUIID("TextFieldBlack");
       addStringValue("Description",description);

       TextField promo = new TextField("","entrer promotion ");
        promo.setUIID("TextFieldBlack");
       addStringValue("promotion du produit",promo);
       
       TextField stock = new TextField("","Nbr de stock");
        stock.setUIID("TextFieldBlack");
       addStringValue("Stock",stock);

       
        TextField ref = new TextField("","Réference du produit!!");
        ref.setUIID("TextFieldBlack");
       addStringValue("ref",ref);

        TextField longdescription = new TextField("","Long Description !!");
        longdescription.setUIID("TextFieldBlack");
        addStringValue("description",longdescription);

       TextField prix = new TextField("","Prix du produit!!");
        prix.setUIID("TextFieldBlack");
        addStringValue("Prix en DN/Jour",prix);
        
        TextField categories = new TextField("","categories ID");
        categories.setUIID("TextFieldBlack");
        addStringValue("categorie",categories);
       
       Button btnAjouter = new Button("Ajouter");
       addStringValue("",btnAjouter);
       btnAjouter.addActionListener((e) -> {
            
                try{
                  if(titre.getText()=="" || description.getText()==""|| promo.getText()==""|| stock.getText()==""|| ref.getText()==""|| longdescription.getText()==""|| prix.getText()=="" || categories.getText()=="") {
                    Dialog.show("Veuillez vérifier les données","","Annuler","OK");
                  }
                  else{
                        InfiniteProgress ip = new InfiniteProgress();;
                        final Dialog iDialog = ip.showInfiniteBlocking();
                        Produits p ;
                        p = new Produits(
                        String.valueOf(titre.getText().toString()),
                        String.valueOf(description.getText().toString()),
                        String.valueOf(promo.getText().toString()),
                        String.valueOf(stock.getText().toString()),
                        String.valueOf(ref.getText().toString()),
                        String.valueOf(longdescription.getText().toString()),
                        String.valueOf(prix.getText().toString()),
                        String.valueOf(categories.getText().toString())
                        //127.0.0.1:8000/web/service/produits/ajout?titre=ka&description=test&promo=1&stock=10&ref=10&longdescription=test&prix=1000.0&categories=3
                        );
                    System.out.println("data Auto == "+p );
                    ServiceProduits.getInstance().AjoutProduits(p);
                    iDialog.dispose();

                    new ProduitsForm(res).show();

                    refreshTheme();
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
       });
        
    }


    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
                .add(BorderLayout.CENTER,v));

    }
    
    

    
}
