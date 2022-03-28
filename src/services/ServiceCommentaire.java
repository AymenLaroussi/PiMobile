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
                
                
                c.setId((obj.get("id").toString()));
                c.setMessage((obj.get("message").toString()));
                System.out.println(c);
                
                coment.add(c);
            }
        } catch (IOException ex) {
        }
        return coment;
    }
    
           
}

