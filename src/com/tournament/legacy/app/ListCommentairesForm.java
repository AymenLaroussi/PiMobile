/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tournament.legacy.app;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.tournament.legacy.entites.Commentaires;
import com.tournament.legacy.services.ServiceCommentaire;
import java.util.ArrayList;

/**
 *
 * @author Aymen Laroussi
 */
public class ListCommentairesForm extends Form {
    Resources res;
    Form current;
    Button btnSuppriemr = new Button("X");
    public ListCommentairesForm(Form previous){
        setTitle("Liste des  commentaires");
        add(new Label("Liste des  commentaires /n"));
        
        ArrayList<Commentaires> list = ServiceCommentaire.getInstance().ListeCommentaires();
        for ( Commentaires c : list){
            System.out.println(c.getMessage());
            btnSuppriemr.addActionListener(e-> ServiceCommentaire.getInstance().supprimerCommentaire(c.getId()));
            
         add(new Label(c.getId()));   
         add(new Label(c.getMessage()));
         addAll(btnSuppriemr);
        }
          
          
        Form hi = new Form("Table Layout 2x2", new TableLayout(2, 2));
        hi.add(new Label("First")).
            add(new Label("Second")).
            add(new Label("Third")).
            add(new Label("Fourth")).
            add(new Label("Fifth"));
        hi.show();
        

        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
        
        
    }   
}
