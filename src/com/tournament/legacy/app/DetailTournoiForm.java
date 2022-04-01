package com.tournament.legacy.app;

import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.tournament.legacy.entites.Tournoi;
import services.TournoiServices;

public class DetailTournoiForm extends BaseForm{
    public  DetailTournoiForm (Form previous, Tournoi t,Resources res) {
        setTitle("Add a new tournoi");
        setLayout(BoxLayout.y());
        SpanLabel addLabel = new SpanLabel("Tournoi :"+t.getNom());
        addLabel.getAllStyles().setFgColor(0xff000);
        add(addLabel);
        addButton( res.getImage("tournoi2.jpg"),t.getNom(), t,res,previous);

    }
    private void addButton(Image img, String title, Tournoi t,Resources res,Form previous) {
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);

        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);

        TextArea discord = new TextArea("Discord: "+t.getDiscord_channel());
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);

        TextArea nombreEq = new TextArea("Nombre d'equipe: "+ String.valueOf(t.getNbr_equipes()));
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);

        TextArea nombreJ = new TextArea("Nombre d'equipe: "+ String.valueOf(t.getNbr_joueur_eq()));
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);


//        Label likes = new Label(  " Update  ", "NewsBottomLine");
//        likes.setTextPosition(RIGHT);

//        FontImage.setMaterialIcon(likes, FontImage.MATERIAL_ALARM);
        Button update = new Button("Uplate");

        Button delete = new Button("Delete");
        Button btnRetour = new Button("revenir a la liste ");


//        Label comments = new Label( " Delete", "NewsBottomLine");
//        FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);



        cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(
                ta,nombreEq,nombreJ,discord,
                BoxLayout.encloseX(update, delete)
        ));


        add(cnt);
        btnRetour.addActionListener(e -> previous.showBack());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        //       image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
    }
}
