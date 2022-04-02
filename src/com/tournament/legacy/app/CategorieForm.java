/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tournament.legacy.app;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Aymen Laroussi
 */
public class CategorieForm extends Form {
    Form current;
    Resources res;
   public CategorieForm(Resources res) {
        current=this;
        setTitle("Categories");
        setLayout(BoxLayout.y());
        add(new Label("Choose an option"));
        Button btnAjout = new Button("Ajout Categories");
        Button btnListe = new Button("Liste des Categories");
        btnAjout.addActionListener(e-> new AjoutCategorieForm(res,current).show());
        btnListe.addActionListener(e-> new ListCategoriesForm(res).show());
        addAll(btnAjout, btnListe);
        
    getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {

            new com.tournament.legacy.app.Dashboard(res).show();

        });    
        
}
}
