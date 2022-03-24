package com.tournament.legacy.app;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.tournament.legacy.entites.Tournoi;
import services.TournoiServices;

import java.io.IOException;

public class AddTournoiForm extends BaseForm {

    public  AddTournoiForm (Form previous) {

        setTitle("Add a new tournoi");
        setLayout(BoxLayout.y());

        TextField tnom = new TextField("", "Nom");
        TextField tNombreEquipe = new TextField("","Nombre d'equipe");
        TextField tnombreJoueurEquipe = new TextField("","Nombre Joueur par Equipe");
        TextField tprix = new TextField("","Prix");
        TextField timage = new TextField("","image");
        TextField tdiscord = new TextField("","discord");
        Button btnValider = new Button("Add tournoi");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if  ((tnom.getText().length() == 0) || (tNombreEquipe.getText().length() == 0) || (tnombreJoueurEquipe.getText().length() == 0)||
                (tprix.getText().length() == 0)||(tdiscord.getText().length() == 0))
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else {
                    try {
                        Tournoi t = new Tournoi(tnom.getText(), Integer.parseInt(tNombreEquipe.getText()),Integer.parseInt(tnombreJoueurEquipe.getText()),
                                Float.parseFloat(tprix.getText()),timage.getText()  ,tdiscord.getText());
//                        Tournoi t = new Tournoi(Integer.parseInt(tfStatus.getText()), tfName.getText().toString());
                        if (TournoiServices.getInstance().ajoutTournoi(t)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                        } else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException | IOException e) {
                        Dialog.show("ERROR", "Erreur de saisie", new Command("OK"));
                    }

                }


            }
        });

        addAll(tnom, tNombreEquipe,tnombreJoueurEquipe,tprix,timage,tdiscord, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
}


