package com.tournament.legacy.app;

import com.codename1.components.SpanLabel;
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
        SpanLabel addLabel = new SpanLabel("Remplir les champs necessaires pour organiser votre tournoi :");
        addLabel.getAllStyles().setFgColor(0xff000);
        add(addLabel);

        TextField tnom = new TextField("", "Nom");
        TextField tNombreEquipe = new TextField("","Nombre d'equipe");
        TextField tnombreJoueurEquipe = new TextField("","Nombre Joueur par Equipe");
        TextField tprix = new TextField("","Prix");
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
                                Float.parseFloat(tprix.getText())  ,tdiscord.getText());
//                        Tournoi t = new Tournoi(Integer.parseInt(tfStatus.getText()), tfName.getText().toString());
                        if (TournoiServices.getInstance().ajoutTournoi(t)) {
                            Dialog.show("Success", "le tournoi est modifié avec success", new Command("OK"));
                        } else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException | IOException e) {
                        Dialog.show("ERROR", "Erreur de saisie", new Command("OK"));
                    }

                }


            }
        });

        addAll(tnom, tNombreEquipe,tnombreJoueurEquipe,tprix,tdiscord, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
}


