package com.tournament.legacy.app;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.tournament.legacy.entites.Tournoi;

public class DetailTournoiForm extends BaseForm{
    public  DetailTournoiForm (Form previous, Tournoi t) {
        setTitle("Add a new tournoi");
        setLayout(BoxLayout.y());
        SpanLabel addLabel = new SpanLabel("Remplir les champs necessaires pour organiser votre tournoi :");
        addLabel.getAllStyles().setFgColor(0xff000);
        add(addLabel);

    }
}
