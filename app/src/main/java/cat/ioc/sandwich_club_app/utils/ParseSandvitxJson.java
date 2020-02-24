package cat.ioc.sandwich_club_app.utils;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cat.ioc.sandwich_club_app.model.Sandvitx;

public class ParseSandvitxJson {
    private static final String TAG=ParseSandvitxJson.class.getSimpleName();
    /**Strings per la clau associada amb el Json
     *
     */

    private static final String KEY_NAME="name";
    private static final String KEY_MAIN_NAME="mainName";
    private static final String KEY_ALSO_KNOWN_AS="alsoKnownAs";
    private static final String KEY_PLACE_OF_ORIGIN="placeOfOrigin";
    private static final String KEY_DESCRIPTION ="description";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_INGREDIENTS = "ingredients";

    /**
     * Retorna a {@link Sandvitx} Object que pot ser utilitzat per a emplenar la UI parsejant el Json
     */

    public static Sandvitx parseSandvitxJson(String json){
        //Si el String de JSON es buit o null, llavors retorna
        if(TextUtils.isEmpty(json)) return null;

    //Intenta parsejar el JSON string. Si hi ha algun problema amb com es formatejat
    //Una JSONException es llençada

        try {
            //crea un JSONObject del JSON string
            JSONObject baseJson=new JSONObject(json) ;

            //Pren el JSONObject associada amb la clau anomenada nom
            JSONObject name=baseJson.getJSONObject(KEY_NAME);
            //per a un nom donat, pren el valor fro amb clau anomenada "nomPrincipal"
            String mainName=name.getString(KEY_MAIN_NAME);
            //per a un nom donat, extrau el JSOnArray associat amb tambeconegutCom
            JSONArray tambeConegutArray=name.getJSONArray(KEY_ALSO_KNOWN_AS);
            //Crea un arraylist buit
            List<String> tambeConegutCom=new ArrayList<>();
            //si el JSONArray no es buit ,afageix cada element dins el JSONArray dins el ArrayList
            if(tambeConegutArray.length()!=0){
                for(int i=0;i<tambeConegutArray.length();i++){
                    tambeConegutCom.add(tambeConegutArray.get(i).toString());
                }
            }else{
                tambeConegutCom=null;
            }
         //pren el valor per a la clau "llocOrigen"

            String llocOrigen=baseJson.getString(KEY_PLACE_OF_ORIGIN);
            if(llocOrigen.isEmpty())llocOrigen=null;
            //pren el valor per a la clau imatge
            String imageUrl=baseJson.getString(KEY_IMAGE);
            //pren el valor per la clau desciption
            String descripcio=baseJson.getString(KEY_DESCRIPTION);

            //pren el JSONArray associat amb la clau ingredients

            JSONArray ingredientsArray=baseJson.getJSONArray(KEY_INGREDIENTS);
            List<String> ingredients=new ArrayList<>();
            //Si el JSONArray no es buit , cada elemnt dins el JSON Array  cap el Arraylist
            if(ingredientsArray.length()!=0){
                for(int j=0;j<ingredientsArray.length();j++){
                    ingredients.add(ingredientsArray.get(j).toString());
                }
            }else ingredients = null;

          return new Sandvitx(mainName,tambeConegutCom,llocOrigen,descripcio,imageUrl,ingredients);

        } catch (JSONException e) {
            Log.e(TAG,"Problemes parsing el JSON",e);
            return null;
        }

    }

}
