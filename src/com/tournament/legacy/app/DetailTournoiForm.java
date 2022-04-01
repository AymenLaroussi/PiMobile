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
        addButton( res.getImage("tournoi2.jpg"),t.getNom(), t,previous);

    }
    private void addButton(Image img, String title, Tournoi t,Form previous) {
        int height = Display.getInstance().convertToPixels(41.5f);
        int width = Display.getInstance().convertToPixels(54f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.north(image);

        TextArea ta = new TextArea("Nom :"+t.getNom());
        ta.setUIID("BlackTextField");
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
        TextArea prix = new TextArea("Montant à gagné: "+ String.valueOf((int)t.getPrix()));
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
                ta,nombreEq,nombreJ,discord,prix

        ));


        add(cnt);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        //       image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
    }
}
