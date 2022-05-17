/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tournament.legacy.services;

import com.codename1.components.SpanLabel;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.tournament.legacy.entites.User;
import com.tournament.legacy.utils.Statics;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author mind
 */
public class ServiceUser {
    public boolean resultOK;
    public static ServiceUser instance = null;
    private ConnectionRequest con,req;
    public ArrayList<User> Users;
    String json;
    
    public ServiceUser() {
        con = new ConnectionRequest();
    }
    
    public static ServiceUser getInstance() {
        if(instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }
    
   
    boolean result;
    
    public boolean addUser(User t) {
        String url = Statics.BASE_URL+"/addUser/new?username=" + t.getUsername() + "&email=" + t.getEmail() + "&password=" + t.getPassword();
        ConnectionRequest req = new ConnectionRequest(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode()==200; // C'est OK
            }
        });
        NetworkManager.getInstance().addToQueueAndWait((req));
                
        return resultOK;
    }
    
    public void connexionUser(TextField username,TextField password){
            String url = Statics.BASE_URL+"/verifierUserJSON?username="+username.getText().toString()+"&password="+password.getText().toString();
            req = new ConnectionRequest(url,false);
            req.setUrl(url);
            req.addResponseListener((e) ->{
                
                JSONParser j = new JSONParser();
                
                 json = new String(req.getResponseData()) + "";
                
                try{
                    if(json.equals("Wrong")) {
                        Dialog.show("Erreur","Vos informations de connexion sont incorrects.","OK",null);
                    }
                    else {
                        // System.out.println("data =="+json);
                        Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                        // Dialog.show("Succès","Vous êtes à présent connecté: "+username.getText(),"OK",null);
                        
                        
                        // if(user.size() > 0)
                    }
                    
                }catch(Exception ex){
                    ex.printStackTrace();
                }
           
            });
        NetworkManager.getInstance().addToQueueAndWait(req);
        }
    
        public String printallUsers(){
            String url = Statics.BASE_URL+"/allUsers";
            req = new ConnectionRequest(url);
            req.setUrl(url);
            req.addResponseListener((e) ->{
                
                JSONParser j = new JSONParser();
                
                 json = new String(req.getResponseData()) + "";
                
                try{
                    //System.out.println("data =="+json);    
                    Dialog.show("Liste users",json,"OK",null);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
           
            });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return json;
        }
}