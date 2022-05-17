/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tournamentlegacy.myapp.gui;

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
public class LoginUserForm extends Form {
    public LoginUserForm(Form previous) {
        setTitle("Connexion");
        setLayout(BoxLayout.y());
        TextField tfUsername = new TextField("", "Nom utilisateur", 10, TextField.ANY);
        TextField tfPassword = new TextField("", "Mot de passe", 10, TextField.PASSWORD);
        Button btnValider = new Button("Connexion");
        
        btnValider.addActionListener(e-> {
                if(tfUsername.getText().length()==0 || tfPassword.getText().length()==0) {
                    Dialog.show("Alerte", "Merci d'entrer tous les champs", new Command("OK"));
                } else {
                        ServiceUser.getInstance().connexionUser(tfUsername, tfPassword);
                }
        });
        
        addAll(tfUsername, tfPassword, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
}
