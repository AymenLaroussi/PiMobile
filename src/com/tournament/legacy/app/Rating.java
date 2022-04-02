/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tournament.legacy.app;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.tournament.legacy.entites.Commentaires;
import com.tournament.legacy.services.ServiceCommentaire;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Rating  extends Form {

    Rating() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
      
}

private Slider createStarRankSlider() {
    Slider starRank = new Slider();
    starRank.setEditable(true);
  
    starRank.setMinValue(0);
    starRank.setMaxValue(5);
    starRank.setProgress(0);
    
    Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
            derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
    Style s = new Style(0xffff33, 0, fnt, (byte)0);
    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    s.setOpacity(100);
    s.setFgColor(0);
    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
    initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
    starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
    System.out.println();
    return starRank;
}

 

public SwipeableContainer createRankWidget(String title, String year,int i,String date) {
    MultiButton button = new MultiButton(title);
    button.setTextLine2(year);
    button.setTextLine3(date);
     Slider slaid =createStarRankSlider();
     slaid.setProgress(i);
    return new SwipeableContainer(FlowLayout.encloseCenterMiddle(slaid), 
            button);
}
//Container viewretaing;
 public ArrayList<Commentaires> coment;
  Container list;
public Rating(Form previous,String id) {
    super("Commentaire", new BoxLayout(BoxLayout.Y_AXIS));
     getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
      getAllStyles().setBgColor(0xffffff);
        // viewretaing = new Container(BoxLayout.y());
      //  viewretaing.setScrollableY(true);
      Label l= new Label("\n\n\nMerci pour react c'est resto ");
      l.getAllStyles().setAlignment(Component.CENTER);
      add(l);
      
      Slider slaid = createStarRankSlider();
      add(FlowLayout.encloseCenter(slaid));
      
      int note= createStarRankSlider().getProgress();
      
      if ( (ServiceCommentaire.getInstance().ListeCommentairesIDDDDDDDDDDDDDDDDDDDDD(id))!=null)
      {
      coment=(ServiceCommentaire.getInstance().ListeCommentairesIDDDDDDDDDDDDDDDDDDDDD(id));
     
     
    
     Commentaires comtr=new Commentaires();
      TextField tfName = new TextField("","TaskName");
      
      
     
        Button btnValider = new Button("Add comm");
        
       btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                               
                if ((tfName.getText().length()==0)){
           
                     System.out.println(slaid.getProgress());
                     
                    Dialog.show("Erreur", "Pour ajoute un commentaire remplire valide le champe ", new Command("OK"));
                }
                
                else
                {
                    comtr.setMessage(tfName.getText().toString());
                    
                 comtr.getUser();
                    try {
                        if (true)
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                            if ( (ServiceCommentaire.getInstance().ListeCommentairesIDDDDDDDDDDDDDDDDDDDDD(id))!=null)
                           coment=(ServiceCommentaire.getInstance().ListeCommentairesIDDDDDDDDDDDDDDDDDDDDD(id));
                                refreshTheme(); 
                              refreshTheme();  
                                
                              
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
       addAll(tfName,btnValider);
     //
      Label re= new Label(String.valueOf("1")+"/5");
     re.getAllStyles().setFgColor(0x3e3f42);
      re.setTextPosition(RIGHT);
    //  re.getAllStyles().setBorder(RoundBorder.create().color(0xfaff00));
       
         add(FlowLayout.encloseCenter(re));
     //add(re);
    
       
      
          //add(CENTER,viewretaing);

    
        
    

           }
}
}