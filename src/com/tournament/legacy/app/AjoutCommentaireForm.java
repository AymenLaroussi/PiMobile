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
import com.codename1.ui.util.Resources;
import com.tournament.legacy.entites.Commentaires;
import com.tournament.legacy.services.ServiceCommentaire;

/**
 *
 * @author aymen
 */
public class AjoutCommentaireForm extends Form {

   public AjoutCommentaireForm(Resources res,Form previous) {
    
       setTitle("Ajout d'un commentaire");
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
       Toolbar tb = new Toolbar(true);
        
        setToolbar(tb);
        getContentPane().setScrollVisible(false);


        tb.addSearchCommand(e -> {

        });
    
           
       TextField message = new TextField("","entrer votre commentaire!!");
        message.setUIID("TextFieldBlack");
       addStringValue("Commentaire",message);
       
        TextField produit = new TextField("","entrer l' id  du produit !!");
        produit.setUIID("TextFieldBlack");
       addStringValue("Produit",produit);
       
       Button btnAjouter = new Button("Ajouter");
       addStringValue("",btnAjouter);
       btnAjouter.addActionListener((e) -> {
            
                try{
                  if(produit.getText()==""|message.getText()=="") {
                    Dialog.show("Veuillez entrer les données necéssaire","","Annuler","OK");
                  }
                  else{
                        InfiniteProgress ip = new InfiniteProgress();;
                        final Dialog iDialog = ip.showInfiniteBlocking();
                        Commentaires c ;
                        c = new Commentaires(
                        String.valueOf(message.getText().toString())
                       );
                    System.out.println("data Auto == "+c );
                      ServiceCommentaire.getInstance().AjoutCommentaire(c);
                    iDialog.dispose();

                    new CommentairesForm(res).show();

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
