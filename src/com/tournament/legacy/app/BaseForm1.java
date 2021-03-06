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


import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.tournament.legacy.entites.User;

/**
 * Base class for the forms with common functionality
 *
 * @author Aymen Laroussi
 */
public class BaseForm1 extends Form {
    public static User currentUser;

    public BaseForm1() {
    }

    public BaseForm1(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseForm1(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }
    
    
    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    
    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    protected void addSideMenu(Resources res) {
        Toolbar tb = getToolbar();
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl,
                FlowLayout.encloseCenterBottom(
                        new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
        ));
        
        tb.addMaterialCommandToSideMenu("UTULISATEUR", FontImage.MATERIAL_FACE, e -> new CategorieForm(res).show());
        tb.addMaterialCommandToSideMenu("EVENEMENT", FontImage.MATERIAL_EVENT, e -> new CategorieForm(res).show());
        tb.addMaterialCommandToSideMenu("TOURNOI", FontImage.MATERIAL_EVENT_SEAT, e -> new CategorieForm(res).show());
        tb.addMaterialCommandToSideMenu("CATEGORIE", FontImage.MATERIAL_CATEGORY, e -> new CategorieForm(res).show());
        tb.addMaterialCommandToSideMenu("PRODUITS", FontImage.MATERIAL_PRODUCTION_QUANTITY_LIMITS, e -> new ProduitsForm(res).show());
        tb.addMaterialCommandToSideMenu("COMMENTAIRES", FontImage.MATERIAL_COMMENT, e -> new CommentairesForm(res).show());
        tb.addMaterialCommandToSideMenu("COMMANDES", FontImage.MATERIAL_SHOP, e -> new CommentairesForm(res).show());
        tb.addMaterialCommandToSideMenu("FRONTOFFICE", FontImage.MATERIAL_HOME, e -> new ProduitsfeedForm1(res).show());
        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new WalkthruForm(res).show());
       
    }
}
