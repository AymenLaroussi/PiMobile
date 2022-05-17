/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tournament.legacy.app;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

import com.tournament.legacy.entites.User;
import com.tournament.legacy.services.ServiceUser;

/**
 *
 * @author mind
 */
public class PrintAllUsersForm extends Form {
    public PrintAllUsersForm(Form previous) {
        Button btnValider = new Button("Afficher");
        SpanLabel lbl = new SpanLabel();
        Label lb = new Label();

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                    try {
                        lbl.setText(ServiceUser.getInstance().printallUsers());
                    } catch(Error e) {
                        Dialog.show("Erreur", "L'erreur est " + e.getMessage(), new Command("OK"));
                    }
                }
        });  
        addAll(btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

    } 
    
}

