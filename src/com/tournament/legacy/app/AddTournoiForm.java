package com.tournament.legacy.app;

import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.tournament.legacy.entites.Jeu;
import com.tournament.legacy.entites.Tournoi;
import services.JeuServices;
import services.TournoiServices;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class AddTournoiForm extends BaseForm {

    public  AddTournoiForm (Form previous) {

        setTitle("Add a new tournoi");
        setLayout(BoxLayout.y());
        SpanLabel addLabel = new SpanLabel("Remplir les champs necessaires pour organiser votre tournoi :");
        addLabel.getAllStyles().setFgColor(0xff000);
        add(addLabel);

        TextField tnom = new TextField("", "Nom");
        tnom.setUIID("TextFieldBlack");

        TextField tNombreEquipe = new TextField("","Nombre d'equipe");
        tNombreEquipe.setUIID("TextFieldBlack");
        TextField tnombreJoueurEquipe = new TextField("","Nombre Joueur par Equipe");
       tnombreJoueurEquipe.setUIID("TextFieldBlack");
        TextField tprix = new TextField("","Prix");
        tprix.setUIID("TextFieldBlack");
        TextField tdiscord = new TextField("","discord");
        tdiscord.setUIID("TextFieldBlack");
//        Picker dateTimePicker = new Picker();
//        dateTimePicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);
//        dateTimePicker.setDate(new Date());
        Picker jeuPicker = new Picker();
        jeuPicker.setUIID("TextFieldBlack");

        jeuPicker.setType(Display.PICKER_TYPE_STRINGS);
        ArrayList<Jeu> jeux = new ArrayList<>();
        jeux = JeuServices.getInstance().getAllJeu();
        jeuPicker.setSelectedString(jeux.get(0).getNom().toString());
        jeuPicker.setStrings( jeux.get(0).getNom());
        if ( jeux.size()>1) {

            jeuPicker.setStrings( jeux.get(0).getNom(),
                    jeux.get(1).getNom());
        }
        if ( jeux.size()>2) {
            jeuPicker.setStrings( jeux.get(0).getNom(),
                    jeux.get(1).getNom(),
                    jeux.get(2).getNom()
                    );
        }


        Button btnValider = new Button("Add tournoi");
        Button btnRetour = new Button("revenir a la liste ");
        btnValider.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                if  ((tnom.getText().length() == 0) || (tNombreEquipe.getText().length() == 0) || (tnombreJoueurEquipe.getText().length() == 0)||
                (tprix.getText().length() == 0)||(tdiscord.getText().length() == 0)||jeuPicker.getText().length()==0)
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else {
                    try {
                        Tournoi t = new Tournoi(tnom.getText(), Integer.parseInt(tNombreEquipe.getText()),Integer.parseInt(tnombreJoueurEquipe.getText()),
                                Float.parseFloat(tprix.getText())  ,tdiscord.getText());
                        if (TournoiServices.getInstance().ajoutTournoi(t,jeuPicker.getText())) {
                            Dialog.show("Success", "le tournoi est ajouté avec succes", new Command("OK"));
//                            new TournoiForm(previous).show();

                        } else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException | IOException e) {
                        Dialog.show("ERROR", "Erreur de saisie", new Command("OK"));
                    }

                }


            }
        });






        btnRetour.addActionListener(e -> previous.showBack());


        addAll(tnom, tNombreEquipe,tnombreJoueurEquipe,tprix,tdiscord,jeuPicker, btnValider,btnRetour);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        }

}


