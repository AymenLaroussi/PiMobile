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
 * @author Aymen Laroussi
 */
public class SupprimerCommentaireForm extends Form {

   public SupprimerCommentaireForm(Resources res,Form previous) {
    
       setTitle("Supprimer d'un commentaire");
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
       Toolbar tb = new Toolbar(true);
        
        setToolbar(tb);
        getContentPane().setScrollVisible(false);


        tb.addSearchCommand(e -> {

        });
    
           
       TextField id = new TextField("","Supprimer votre commentaire!!");
       id.setUIID("TextFieldBlack");
       addStringValue("id",id);
       
       Button btnSupprimer = new Button("Supprimer");
       addStringValue("",btnSupprimer);
       btnSupprimer.addActionListener((e) -> {
            
                try{
                  if(id.getText()=="") {
                    Dialog.show("Veuillez entrer l'ID du commentaire","","Annuler","OK");
                  }
                  else{
                        InfiniteProgress ip = new InfiniteProgress();;
                        final Dialog iDialog = ip.showInfiniteBlocking();
                        Commentaires c ;
                        
                        String ids = String.valueOf(id.getText().toString());
                        System.out.println(String.valueOf(id.getText().toString()));
                       
                    
                      ServiceCommentaire.getInstance().supprimerCommentaire(ids);
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
