/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tournament.legacy.services;
import com.codename1.io.CharArrayReader;
import com.tournament.legacy.utils.Statics;
import com.tournament.legacy.entites.Categories;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Aymen Laroussi
 */
public class ServiceCategorie {
    
    public ArrayList<Categories> categories;
    public static ServiceCategorie instance;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceCategorie() {
        req = new ConnectionRequest();
    }
    
    public static ServiceCategorie getInstance() {
    if (instance == null){
        instance = new ServiceCategorie();
    }
    return instance;
    }
    
    public boolean AjoutCategorie (Categories c){
        String url = Statics.BASE_URL + "/web/service/categorie/ajout?id="+c.getId()+"&nom="+c.getNom();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK=req.getResponseCode()==200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;        
    }
    
  public ArrayList<Categories> parseCategories(String jsonText){
        
        try {
            categories = new ArrayList<>();
            JSONParser j= new JSONParser();
            Map<String,Object> CategoriesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)CategoriesListJson.get("root");
            
            for (Map<String,Object> obj : list){
                Categories c = new Categories();
                String s;
                int test=(int) Float.parseFloat(obj.get("id").toString());
                int nb =Math.round(test);
                s = String.valueOf(nb);
                
                c.setId((s.toString()));
                c.setNom(obj.get("nom").toString());
                categories.add(c);
            }
        } catch (IOException ex) {
        }
        
        return categories;
    }
   public void supprimerCategories(String p){
        String url = Statics.BASE_URL + "/web/service/categorie/supprimer?id="+ p;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    public ArrayList<Categories> getAllCategories(){
        String url  = Statics.BASE_URL+"/web/service/categories";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                categories = parseCategories(new String(req.getResponseData()));
                req.removeExceptionListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categories;
        
                
  
   }


}


