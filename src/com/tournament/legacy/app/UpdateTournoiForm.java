package com.tournament.legacy.app;

import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.tournament.legacy.entites.Tournoi;
import services.TournoiServices;

import java.io.IOException;

public class UpdateTournoiForm extends BaseForm {

    public UpdateTournoiForm(Form previous,Tournoi t) {

        setTitle("mettre a jour le tournoi");
        setLayout(BoxLayout.y());
        SpanLabel addLabel = new SpanLabel("Remplir les champs necessaires pour mettre a jours votre tournoi :");
        addLabel.getAllStyles().setFgColor(0xff000);
        add(addLabel);

        TextField tnom = new TextField("", "tnom", 20, TextArea.ANY);
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
                        t.setNom(tnom.getText());
                        t.setNbr_equipes(Integer.parseInt(tNombreEquipe.getText()));
                        t.setNbr_joueur_eq(Integer.parseInt(tnombreJoueurEquipe.getText()));
                        t.setPrix(Float.parseFloat(tprix.getText()));
                        t.setDiscord_channel(tdiscord.getText());
//                        Tournoi t = new Tournoi(Integer.parseInt(tfStatus.getText()), tfName.getText().toString());
                        if (TournoiServices.getInstance().updateTournoi(t)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
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


