/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tournament.legacy.services;
import com.codename1.db.Database;
import com.tournament.legacy.utils.Statics;
import com.tournament.legacy.entites.Commentaires;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;



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
           
}

