/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tournament.legacy.app;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.tournament.legacy.entites.Commentaires;
import com.tournament.legacy.services.ServiceCategorie;
import com.tournament.legacy.services.ServiceCommentaire;
import java.util.ArrayList;

import java.util.ArrayList;

public class ListCommentairesForm extends Form {

    public ListCommentairesForm(Resources res) {

        Button btnSuppriemr = new Button("X");
        setTitle("Liste des categories");
        setLayout(BoxLayout.y());
        ArrayList<Commentaires> list = ServiceCommentaire.getInstance().ListeCommentaires();
        Container list1 = new Container(BoxLayout.y());
        list1.setScrollableY(true);
        list1.setScrollableX(true);
        for ( Commentaires coment : list) {


            SpanLabel LabelComent = new SpanLabel();
            MultiButton sp = new MultiButton();
            sp.getAllStyles().setFgColor(0x350a4e);
            Label l = new Label(" ");

            

            sp.setText("Id : "+coment.getId());
            sp.setTextLine2("message : "+coment.getMessage());
            sp.setTextLine2("Date : "+coment.getDate());
            
            list1.add(LabelComent);
            list1.add(sp);
            

        }

        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {

            new com.tournament.legacy.app.CategorieForm(res).show();

        });

        this.add(list1);


    }
}