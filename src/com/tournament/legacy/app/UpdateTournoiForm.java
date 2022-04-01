package com.tournament.legacy.app;

import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.tournament.legacy.entites.Jeu;
import com.tournament.legacy.entites.Tournoi;
import services.JeuServices;
import services.TournoiServices;

import java.io.IOException;
import java.util.ArrayList;

public class UpdateTournoiForm extends BaseForm {

    public UpdateTournoiForm(Form previous, Tournoi t) {

        setTitle("mettre a jour le tournoi");
        setLayout(BoxLayout.y());
        SpanLabel addLabel = new SpanLabel("Remplir les champs necessaires pour mettre a jours votre tournoi :");
        addLabel.getAllStyles().setFgColor(0xff000);
        add(addLabel);

        TextField tnom = new TextField("", t.getNom());
        tnom.setUIID("TextFieldBlack");

        TextField tprix = new TextField("", String.valueOf(t.getPrix()));
        tprix.setUIID("TextFieldBlack");
        TextField tdiscord = new TextField("", t.getDiscord_channel());
        tdiscord.setUIID("TextFieldBlack");
//        Picker dateTimePicker = new Picker();
//        dateTimePicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);
//        dateTimePicker.setDate(new Date());


        Button btnValider = new Button("update tournoi");
        Button btnRetour = new Button("revenir a la liste ");
        btnValider.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                    try {
                        if (tnom.getText().length()!= 0) {
                            t.setNom(tnom.getText());
                            System.out.println(tnom.getText());
                        }
                        if (tprix.getText().length()!= 0) {
                            t.setPrix(Float.parseFloat(tprix.getText()));
                        }
                        if (tdiscord.getText().length()!= 0) {
                            t.setDiscord_channel(tdiscord.getText());
                        }


//                                (tnom.getText(), Integer.parseInt(tNombreEquipe.getText()), Integer.parseInt(tnombreJoueurEquipe.getText()),
//                                Float.parseFloat(tprix.getText()), tdiscord.getText());
                        if (TournoiServices.getInstance().updateTournoi(t)) {
                            Dialog.show("Success", "le tournoi est modifié avec succes", new Command("OK"));
//                            new TournoiForm(previous).show();

                        } else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException | IOException e) {
                        Dialog.show("ERROR", "Erreur de saisie", new Command("OK"));
                    }

                }




        });
        btnRetour.addActionListener(e -> previous.showBack());


        addAll(tnom,tprix,tdiscord, btnValider,btnRetour);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}


