/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.tournament.legacy.app;


import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.tournament.legacy.entites.Produits;
import com.tournament.legacy.entites.Commentaires;
import com.tournament.legacy.services.ServiceProduits;
import com.tournament.legacy.services.ServiceCommentaire;
import java.util.ArrayList;

/**
 * The newsfeed form
 *
 * @author Aymen Laroussi
 */
public class ProduitsUniqueForm extends BaseForm {

    public ProduitsUniqueForm(Resources res,String id,String titre,String prix ,String  description,String ld,String ref ,String promo ) {
        super("Produit "+titre, BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle(titre);
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        
        addTab(swipe, res.getImage("news-item.jpg"), spacer1, "  ", "", "");       
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
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("", barGroup);
        all.setUIID("SelectBar");
        RadioButton featured = RadioButton.createToggle("", barGroup);
        featured.setUIID("SelectBar");
        RadioButton popular = RadioButton.createToggle("", barGroup);
        popular.setUIID("SelectBar");
        RadioButton myFavorite = RadioButton.createToggle("", barGroup);
        myFavorite.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(4, all, featured, popular, myFavorite),
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
        bindButtonSelection(popular, arrow);
        bindButtonSelection(myFavorite, arrow);
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
        
        
         
          
            Button btnSuppriemr = new Button("AJOUTER AU PANIER");
        
            
        addButton(res.getImage("news-item-1.jpg"), titre,prix, false, res, id,ref,description,ld,promo);
        add(btnSuppriemr);
        
      
        int i=0;
        
        setLayout(BoxLayout.y());
       
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        list.setScrollableX(true);
          ArrayList<Commentaires> list1 = ServiceCommentaire.getInstance().ListeCommentairesIDDDDDDDDDDDDDDDDDDDDD(id);
        for ( Commentaires c : list1){
         i++;
            String message = c.getMessage().toString();
            String date = c.getDate().toString();
          SpanLabel LabelUser = new SpanLabel();
            MultiButton sp = new MultiButton();
            sp.getAllStyles().setFgColor(0x350a4e);
            Label l = new Label("HERE WE GO");

            FontImage.setMaterialIcon( LabelUser,FontImage.MATERIAL_PERSON_OUTLINE);

            sp.setText(" "+c.getUser());
            sp.setTextLine2("le "+c.getDate());
            sp.setTextLine3(" "+c.getMessage());
            list.add(LabelUser);
            list.add(sp);
         
        }
        
        Button btnListe = new Button("Liste des commentaires");
        btnListe.addActionListener(e-> new ListCommentairesFormFront(res,id).show());
        addAll(btnListe);
        System.out.println(id);
       }
    
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
    
    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        
        
        
        

        
        
        
        
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            FlowLayout.encloseIn(),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
    
    
    private void addButton1(Image img,String date, String message, boolean liked, int commentCount, Resources res) {
       int height = Display.getInstance().convertToPixels(10.5f);
       int width = Display.getInstance().convertToPixels(10f);
       Button image = new Button(img.fill(width, height));
       
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(date);
       TextArea ka = new TextArea(message);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);
       ka.setUIID("NewsBottomLine");
       ka.setEditable(false);

       Label likes = new Label(commentCount + "commentaires", "");
       likes.setTextPosition(BOTTOM);
       if(!liked) {
           FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
       } else {
           Style s = new Style(likes.getUnselectedStyle());
           s.setFgColor(0xff2d55);
           FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
           likes.setIcon(heartImage);
       }
       Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
       FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);
       
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseX(likes, comments,
               BoxLayout.encloseY(
                       
                       ta,ka
                       
               )));
       add(cnt);
   }
    
    
    
    
   private void addButton(Image img, String titre, String prix, boolean liked,  Resources res, String id,String ref, String description,String ld,String promo ) {
       int height = Display.getInstance().convertToPixels(30.5f);
       int width = Display.getInstance().convertToPixels(30f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea titre1 = new TextArea("titre du produit : "+titre);
       TextArea prix1 = new TextArea("Prix du produit : "+prix);
       TextArea ref1 = new TextArea("reference : #"+ref);
       TextArea des1 = new TextArea("description : "+description);
       TextArea des2 = new TextArea("détail : "+ld);
       TextArea promo1 = new TextArea("au lieu de  "+promo + " TND");
       
       promo1.setUIID("NewsTopLine");
       promo1.setEditable(false);
       titre1.setUIID("NewsTopLine");
       titre1.setEditable(false);
       prix1.setUIID("NewsBottomLine");
       prix1.setEditable(false);
       
       ref1.setUIID("NewsTopLine");
       ref1.setEditable(false);
       des1.setUIID("NewsBottomLine");
       des1.setEditable(false);

       Label likes = new Label("titre: "+titre.toString());
       likes.setTextPosition(BOTTOM);
       
       Label comments = new Label( titre.toString());
       FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);
       
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       titre1,ref1,des1,des2,prix1,promo1
               ));
       add(cnt);
       image.addActionListener(e -> ToastBar.showMessage(titre, FontImage.MATERIAL_INFO));
    }
    
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
}
