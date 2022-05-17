/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.tournament.legacy.app;


import com.codename1.components.FloatingHint;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.tournament.legacy.entites.User;
import com.tournament.legacy.services.ServiceUser;

/**
 * Signup UI
 *
 * @author Aymen Laroussi
 */
public class SignUpForm extends BaseForm {

    public SignUpForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");

        TextField tfUsername = new TextField("", "Nom utilisateur", 10, TextField.ANY);
        TextField tfEmail = new TextField("", "Email", 10, TextField.EMAILADDR);
        TextField tfPassword = new TextField("", "Mot de passe", 10, TextField.PASSWORD);
        tfUsername.setSingleLineTextArea(false);
        tfEmail.setSingleLineTextArea(false);
        tfPassword.setSingleLineTextArea(false);
        Button next = new Button("Next");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
        
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(tfUsername),
                createLineSeparator(),
                new FloatingHint(tfEmail),
                createLineSeparator(),
                new FloatingHint(tfPassword),
                createLineSeparator()
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(tfUsername.getText().length()==0 || tfEmail.getText().length()==0 || tfPassword.getText().length()==0) {
                    Dialog.show("Alerte", "Merci d'entrer tous les champs", new Command("OK"));
                } else {
                    try {
                        User t = new User(tfUsername.getText(), tfEmail.getText(), tfPassword.getText());
                        if(new ServiceUser().addUser(t)) {
                            Dialog.show("Succès", "Vous vous êtes bien inscrit", new Command("OK"));
                            new SignInForm(res).show();
                        } else {
                            Dialog.show("Erreur", "Erreur serveur", new Command("OK"));
                        }
                    } catch(Error e) {
                        Dialog.show("Erreur", "L'erreur est " + e.getMessage(), new Command("OK"));
                    }
                }
            }
        });
    }
    
}
