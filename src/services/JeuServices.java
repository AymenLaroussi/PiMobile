package services;

import com.codename1.db.Database;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.tournament.legacy.entites.Jeu;
import com.tournament.legacy.entites.Tournoi;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.tournament.legacy.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JeuServices {
    public ArrayList<Jeu> jeux;

    public static JeuServices instance = null;
    public boolean resultOK;
    Database db;
    private ConnectionRequest req1;

    private JeuServices() {
        req1 = new ConnectionRequest();
    }

    public static JeuServices getInstance() {
        if (instance == null) {
            instance = new JeuServices();
        }
        return instance;
    }

    public ArrayList<Jeu> parseJeu(String jsonText) {

        try {
            jeux = new ArrayList<Jeu>();
            JSONParser j = new JSONParser();
            Map<String, Object> jeuListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) jeuListJson.get("root");
            System.out.println("success");
            for (Map<String, Object> obj : list) {
                Jeu jeu = new Jeu();
                jeu.setId((int) Float.parseFloat(obj.get("id").toString()));
                jeu.setNom(obj.get("nom").toString());


                System.out.println(jeu);
                jeux.add(jeu);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
return  jeux;
    }
    public ArrayList<Jeu> getAllJeu() {
        req1 = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL + "/allJeuAPI";
        System.out.println("===>" + url);
        req1.setUrl(url);
        req1.setPost(false);
        req1.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                jeux = parseJeu(new String(req1.getResponseData()));
                req1.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req1);
        System.out.println("*******"+jeux);
        return jeux;
    }
}