/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tournament.legacy.services;
import com.codename1.db.Database;
import com.codename1.io.CharArrayReader;
import com.tournament.legacy.utils.Statics;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.tournament.legacy.entites.Produits;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Aymen Laroussi
 */
public class ServiceProduits {
    
    
    Database db;
    public ArrayList<Produits> produit;
    public static ServiceProduits instance;
    public boolean resultOK;
    private ConnectionRequest req;
    
    
    
    public ServiceProduits() {
        req = new ConnectionRequest();
    }
    
    public static ServiceProduits getInstance() {
    if (instance == null){
        instance = new ServiceProduits();
    }
    return instance;
    }
    
    public boolean AjoutProduits (Produits p){
        String url = Statics.BASE_URL + "/web/service/produit/ajout?titre="+p.getTitre()+"&description="+p.getDescription()+"&promo="+p.getPromo()+"&stock="+p.getStock()+"&ref="+p.getRef()+"&longdescription="+p.getLongdescription()+"&prix="+p.getPrix()+"&categorie="+p.getCategories();
        //127.0.0.1:8000/web/service/produits/ajout?titre=ka&description=test&promo=1&stock=10&flash=0&ref=10&longdescription=test&prix=1000.0&categories=3
            
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
    public ArrayList<Produits> ListeProduits() {
              String url = Statics.BASE_URL + "/web/service/produits";
              req.setUrl(url);
              req.addResponseListener(new ActionListener<NetworkEvent>() {
                  @Override
                  public void actionPerformed(NetworkEvent evt) {
                      try {
                          produit = parseProduits(new String(req.getResponseData()));
                          req.removeResponseListener(this);
                          
                      } catch (IOException ex) {

                      }
                  }
              });
              NetworkManager.getInstance().addToQueueAndWait(req);
              return produit;
          } 
    public ArrayList<Produits> ProfileProduits(String p) {
              String url = Statics.BASE_URL + "/web/service/produit?id=" +p;
              req.setUrl(url);
              req.addResponseListener(new ActionListener<NetworkEvent>() {
                  @Override
                  public void actionPerformed(NetworkEvent evt) {
                      try {
                          produit = parseProduits(new String(req.getResponseData()));
                          req.removeResponseListener(this);
                          
                      } catch (IOException ex) {

                      }
                  }
              });
              NetworkManager.getInstance().addToQueueAndWait(req);
              return produit;
          } 
    public ArrayList<Produits> parseProduits(String jsonText) throws IOException {
        try {
            
            produit = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            
            for (Map<String, Object> obj : list) {
                
                Produits p = new Produits();
                

                String s;
                int test=(int) Float.parseFloat(obj.get("id").toString());
                int nb =Math.round(test);
                s = String.valueOf(nb);
                
                p.setId((s.toString()));
                p.setTitre((obj.get("titre").toString()));
                p.setDescription((obj.get("description").toString()));
                p.setRef((obj.get("ref").toString()));
                p.setLongdescription((obj.get("longdescription").toString()));
                p.setPrix((obj.get("prix").toString()));
                
                
                produit.add(p);
                System.out.println(s.toString());
            }
        } catch (IOException ex) {
        }
        return produit;
    }
    
    public void supprimerProduits(String p){
        String url = Statics.BASE_URL + "/web/service/supprimer?id="+ p;
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

