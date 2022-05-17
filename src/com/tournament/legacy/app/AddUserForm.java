/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tournament.legacy.app;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

import com.tournament.legacy.entites.User;
import com.tournament.legacy.services.ServiceUser;

/**
 *
 * @author mind
 */
public class AddUserForm extends Form {
    public AddUserForm(Form previous) {
        setTitle("Inscription");
        setLayout(BoxLayout.y());
        TextField tfUsername = new TextField("", "Nom utilisateur", 10, TextField.ANY);
        TextField tfEmail = new TextField("", "Email", 10, TextField.EMAILADDR);
        TextField tfPassword = new TextField("", "Mot de passe", 10, TextField.PASSWORD);
        Button btnValider = new Button("Inscription");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(tfUsername.getText().length()==0 || tfEmail.getText().length()==0 || tfPassword.getText().length()==0) {
                    Dialog.show("Alerte", "Merci d'entrer tous les champs", new Command("OK"));
                } else {
                    try {
                        User t = new User(tfUsername.getText(), tfEmail.getText(), tfPassword.getText());
                        if(new ServiceUser().addUser(t)) {
                            Dialog.show("Succès", "Vous vous êtes bien inscrit", new Command("OK"));
                        } else {
                            Dialog.show("Erreur", "Erreur serveur", new Command("OK"));
                        }
                    } catch(Error e) {
                        Dialog.show("Erreur", "L'erreur est " + e.getMessage(), new Command("OK"));
                    }
                }
            }
        });
        
        addAll(tfUsername, tfEmail, tfPassword, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
}