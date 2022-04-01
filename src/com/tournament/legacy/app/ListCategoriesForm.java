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
import com.tournament.legacy.entites.Categories;
import com.tournament.legacy.services.ServiceCategorie;
import com.tournament.legacy.services.ServiceCommentaire;
import java.util.ArrayList;

import java.util.ArrayList;

public class ListCategoriesForm extends Form {

    public ListCategoriesForm(Resources res) {

        Button btnSuppriemr = new Button("X");
        setTitle("Liste des categories");
        setLayout(BoxLayout.y());
        ArrayList<Categories> list = ServiceCategorie.getInstance().getAllCategories();
        Container list1 = new Container(BoxLayout.y());
        list1.setScrollableY(true);
        list1.setScrollableX(true);
        for ( Categories cat : list) {


            SpanLabel LabelUser = new SpanLabel();
            MultiButton sp = new MultiButton();
            sp.getAllStyles().setFgColor(0x350a4e);
            Label l = new Label(" ");

            

            sp.setText("Id : "+cat.getId());
            sp.setTextLine2("Nom : "+cat.getNom());
            list1.add(LabelUser);
            list1.add(sp);
            btnSuppriemr.addActionListener(e-> ServiceCategorie.getInstance().supprimerCategories(cat.getId()));
            add(btnSuppriemr);

        }

        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {

            new com.tournament.legacy.app.CategorieForm(res).show();

        });

        this.add(list1);


    }
}