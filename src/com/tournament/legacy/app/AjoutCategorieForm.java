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
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import com.tournament.legacy.entites.Categories;
import com.tournament.legacy.services.ServiceCategorie;

/**
 *
 * @author Aymen Laroussi
 */
public class AjoutCategorieForm extends Form {
    
    Form current;
    private Resources theme;
    Resources res;

    public AjoutCategorieForm(Resources res,Form previous) {

        Toolbar tb = new Toolbar(true);
        
        setToolbar(tb);
        getContentPane().setScrollVisible(false);


        tb.addSearchCommand(e -> {

        });
    
           
       TextField nom = new TextField("","entrer le nom du catégorie !!");
        nom.setUIID("TextFieldBlack");
       addStringValue("Catégorie",nom);
       
       Button btnAjouter = new Button("Ajouter");
       addStringValue("",btnAjouter);
       btnAjouter.addActionListener((e) -> {
            
                try{
                  if(nom.getText()=="") {
                    Dialog.show("Veuillez entrer la catégorie","","Annuler","OK");
                  }
                  else{
                        InfiniteProgress ip = new InfiniteProgress();;
                        final Dialog iDialog = ip.showInfiniteBlocking();
                        Categories Categorie ;
                        Categorie = new Categories(
                        String.valueOf(nom.getText().toString())
                       );
                    System.out.println("data Auto == "+Categorie );
                      ServiceCategorie.getInstance().AjoutCategorie(Categorie);
                    iDialog.dispose();

                    new CategorieForm(res).show();

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
