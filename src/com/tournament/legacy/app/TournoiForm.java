package com.tournament.legacy.app;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.*;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.tournament.legacy.entites.Tournoi;
import services.TournoiServices;

import java.io.IOException;
import java.util.ArrayList;

public class TournoiForm extends BaseForm{
    Form  current;
    static boolean show =true ;
    public TournoiForm (Resources res) {

        super("Tournoi", BoxLayout.y());
        current = this;
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Tournoi");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });
        Tabs swipe = new Tabs();
        Label spacer1 = new Label(

        );
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("blog-img-1-4.jpg"), spacer1, "15 Likes  ", "85 Comments", "Organiser votre tournoi en ligne   ");
        addTab(swipe, res.getImage("tournoi2.jpg"), spacer2, "100 Likes  ", "66 Comments", "Choisir le tournoi qui vous plaisez et rejoindre une equipe pour jouer avec  ");

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("Tournois disponibles", barGroup);
        all.setUIID("SelectBar");

        RadioButton featured = RadioButton.createToggle("Mes tournois", barGroup);
        featured.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(2, all, featured),
                FlowLayout.encloseBottom(arrow)
        ));



        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(all, arrow);
        });
        bindButtonSelection(all, arrow);
        bindButtonSelection(featured, arrow);

        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

        all.addActionListener(actionEvent -> {
            TournoiForm.show = true;
            new TournoiForm(res).show();


        });
        featured.addActionListener(actionEvent -> {
            TournoiForm.show = false;
            new TournoiForm(res).show();


        });
        if (show) {
            ArrayList<Tournoi> list = TournoiServices.getInstance().getAllTournoi();
            for (Tournoi ex : list) {
                System.out.println("ok");
                addButton(res.getImage("tournoi2.jpg"), ex.getNom(), ex,res);
            }
        }

   }
    private void addButton(Image img, String title,Tournoi t,Resources res) {
       int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
//        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);


//        Label likes = new Label(  " Update  ", "NewsBottomLine");
//        likes.setTextPosition(RIGHT);

//        FontImage.setMaterialIcon(likes, FontImage.MATERIAL_ALARM);
        Button update = new Button("Uplate");

        Button delete = new Button("Delete");



//        Label comments = new Label( " Delete", "NewsBottomLine");
//        FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);



        cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(
                ta,
                BoxLayout.encloseX(update, delete)
        ));
        delete.addActionListener(e -> TournoiServices.getInstance().supprimerTournoi(t));
        update.addActionListener(e ->  new UpdateTournoiForm(current,t).show());
        image.addActionListener(e ->{
                    new DetailTournoiForm(current,t,res).show();

        } );

        add(cnt);

 //       image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
    }


    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();


    }

    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        Button ajout = new Button("Organiser tournoi");
        ajout.addActionListener(actionEvent ->  new AddTournoiForm(current).show());

        Container page1 =
                LayeredLayout.encloseIn(
                        image,
                        overlay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text, "LargeWhiteText"),
                                        FlowLayout.encloseIn(ajout, comments),
                                        spacer
                                )
                        )
                );

        swipe.addTab("", page1);

    }




    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
}


