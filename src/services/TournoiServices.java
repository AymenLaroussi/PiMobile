package services;

import com.codename1.db.Database;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.tournament.legacy.entites.Tournoi;
import com.tournament.legacy.utils.Statics;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TournoiServices {
    public ArrayList<Tournoi> tournoisl;

    public static TournoiServices instance = null;
    public boolean resultOK;
    Database db;
    private ConnectionRequest req;

    private TournoiServices() {
        req = new ConnectionRequest();
    }

    public static TournoiServices getInstance() {
        if (instance == null) {
            instance = new TournoiServices();
        }
        return instance;
    }


    public ArrayList<Tournoi> parseTournois(String jsonText) {

        try {
            tournoisl = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tournoiListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tournoiListJson.get("root");
            System.out.println("success");
            for (Map<String, Object> obj : list) {
                Tournoi t = new Tournoi();
                t.setId((int) Float.parseFloat(obj.get("id").toString()));
                t.setNom(obj.get("nom").toString());

                Float tmp = Float.parseFloat(obj.get("nbr_equipes").toString());
                int nb =Math.round(tmp);
                System.out.println(nb);

                t.setNbr_equipes(nb);
                tmp = Float.parseFloat(obj.get("nbr_joueur_eq").toString());
                nb =Math.round(tmp);
                t.setNbr_joueur_eq(nb);
                t.setPrix(Float.parseFloat(obj.get("prix").toString()));
//                t.setImage((obj.get("image").toString()));
                t.setDiscord_channel(obj.get("discord_channel").toString());
//                t.setTime(obj.get("time").toString());
//                t.setTimeEnd(obj.get("timeEnd").toString());
                System.out.println(t);
                tournoisl.add(t);

            }
        } catch (IOException ex) {
            System.out.println(ex);

        }
        return tournoisl;
    }
//

    public ArrayList<Tournoi> getAllTournoi() {
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL + "/allTournoiAPI";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tournoisl = parseTournois(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tournoisl;
    }

    public boolean ajoutTournoi(Tournoi t,String jeu) throws IOException {
        System.out.println(t);
        System.out.println("********");
        String url = Statics.BASE_URL + "/addtournoiAPI";
//             "/addReclamation?objet=" + tournoi.getNom() + "&nom" + tournoi.getNbr_equipes() + "$nbr_equipes" +
//                tournoi.getNbr_joueur_eq() + "&nbr_joueur_eq" + tournoi.getPrix() + "$prix" + tournoi.getImage() + "&image" +
//                tournoi.getDiscord_channel() + "$discord_channel";
        req.setUrl(url);
        req.setPost(false);
        req.addArgument("nom", t.getNom());
        req.addArgument("nbr_equipes", String.valueOf(t.getNbr_equipes()));
        req.addArgument("nbr_joueur_eq", String.valueOf(t.getNbr_joueur_eq()));
        req.addArgument("prix", String.valueOf(t.getPrix()));
        req.addArgument("discord_channel", String.valueOf(t.getDiscord_channel()));
        req.addArgument("jeu", String.valueOf(jeu));
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        Map<String,Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(req.getResponseData()), "UTF-8"));
        System.out.println(result);
        return resultOK;
    }

    public boolean updateTournoi(Tournoi t) throws IOException {
        System.out.println(t);
        System.out.println("********");
        String url = Statics.BASE_URL + "/updatetournoiAPI/"+(t.getId());
//             "/addReclamation?objet=" + tournoi.getNom() + "&nom" + tournoi.getNbr_equipes() + "$nbr_equipes" +
//                tournoi.getNbr_joueur_eq() + "&nbr_joueur_eq" + tournoi.getPrix() + "$prix" + tournoi.getImage() + "&image" +
//                tournoi.getDiscord_channel() + "$discord_channel";
        req.setUrl(url);
        req.setPost(false);
        req.addArgument("nom", t.getNom());
        req.addArgument("nbr_equipes", String.valueOf(t.getNbr_equipes()));
        req.addArgument("nbr_joueur_eq", String.valueOf(t.getNbr_joueur_eq()));
        req.addArgument("prix", String.valueOf(t.getPrix()));
        req.addArgument("discord_channel", String.valueOf(t.getDiscord_channel()));
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        Map<String,Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(req.getResponseData()), "UTF-8"));
        System.out.println(result);
        return resultOK;
    }



    public void supprimerTournoi(Tournoi t){
        String url = Statics.BASE_URL + "/removeTournoiAPI?id="+(t.getId()) ;
        System.out.println("************");
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
