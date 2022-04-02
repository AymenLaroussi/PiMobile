/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tournament.legacy.services;
import com.codename1.db.Database;
import com.codename1.io.CharArrayReader;
import com.tournament.legacy.utils.Statics;
import com.tournament.legacy.entites.Commentaires;
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
public class ServiceCommentaire {
    
    
    Database db;
    public ArrayList<Commentaires> coment;
    public static ServiceCommentaire instance;
    public boolean resultOK;
    private ConnectionRequest req;
    
    
    
    public ServiceCommentaire() {
        req = new ConnectionRequest();
    }
    
    public static ServiceCommentaire getInstance() {
    if (instance == null){
        instance = new ServiceCommentaire();
    }
    return instance;
    }
    
    public boolean AjoutCommentaire (Commentaires c){
        String url = Statics.BASE_URL + "/web/service/commentaires/ajout?id="+c.getId()+"&message="+c.getMessage();
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
    
    public ArrayList<Commentaires> ListeCommentairesIDDDDDDDDDDDDDDDDDDDDD(String p) {
          int test=(int) Float.parseFloat(p);
                int nb =Math.round(test);
                String s = String.valueOf(nb);
              String url = Statics.BASE_URL + "/web/service/commentaireid?id="+s;
              req.setUrl(url);
              req.addResponseListener(new ActionListener<NetworkEvent>() {
                  @Override
                  public void actionPerformed(NetworkEvent evt) {
                      try {
                          coment = parseCommentaires(new String(req.getResponseData()));
                          req.removeResponseListener(this);
                          
                      } catch (IOException ex) {

                      }
                  }
              });
              NetworkManager.getInstance().addToQueueAndWait(req);
              return coment;
          } 
    
    
    
    
    
    public ArrayList<Commentaires> ListeCommentaires() {
              String url = Statics.BASE_URL + "/web/service/commentaires";
              req.setUrl(url);
              req.addResponseListener(new ActionListener<NetworkEvent>() {
                  @Override
                  public void actionPerformed(NetworkEvent evt) {
                      try {
                          coment = parseCommentaires(new String(req.getResponseData()));
                          req.removeResponseListener(this);
                          
                      } catch (IOException ex) {

                      }
                  }
              });
              NetworkManager.getInstance().addToQueueAndWait(req);
              return coment;
          } 
    public ArrayList<Commentaires> parseCommentaires(String jsonText) throws IOException {
        try {
            
            coment = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            
            for (Map<String, Object> obj : list) {
                
                Commentaires c = new Commentaires();
                
                String s;
                int test=(int) Float.parseFloat(obj.get("id").toString());
                int nb =Math.round(test);
                s = String.valueOf(nb);
                
                c.setId((s.toString()));
                String test1 = obj.get("user").toString();
                String test2 = (test1.substring((test1).indexOf("username=")+9 ,(test1).indexOf("}"))).toString();
                c.setUser(test2.toString());
                
                String test3 = obj.get("produit").toString();
                String test4 = (test3.substring((test3).indexOf("titre=")+6 ,(test3).indexOf("}"))).toString();
                
                
                c.setProduit(test4.toString());
                c.setMessage((obj.get("message").toString()));
                c.setDate((obj.get("date").toString()));
               
                coment.add(c);
            }
        } catch (IOException ex) {
        }
        return coment;
    }
    
    public void supprimerCommentaire(String p){
        String url = Statics.BASE_URL + "/web/service/commentaire/supprimer?id="+ p;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    
    
        
}

