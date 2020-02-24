package cat.ioc.sandwich_club_app.model;

import java.util.List;

public class Sandvitx {
    private String nomPrincipal;
    private List<String> nomConegut ;
    private String llocOrginen;
    private String descripcio;
    private String imatge;
    private List<String> ingredients=null;

    /*
    * Constructor sense arguments per a la serialitzacio
    * */

    public Sandvitx(){}

    public Sandvitx(String nomPrincipal,List<String> nomConegut,String llocOrginen,String descripcio,String imatge,List<String> ingredients){
        this.nomPrincipal=nomPrincipal;
        this.nomConegut=nomConegut;
        this.llocOrginen=llocOrginen;
        this.descripcio=descripcio;
        this.imatge=imatge;
        this.ingredients=ingredients;


    }

    public String getNomPrincipal() {
        return nomPrincipal;
    }

    public void setNomPrincipal(String nomPrincipal) {
        this.nomPrincipal = nomPrincipal;
    }

    public List<String> getNomConegut() {
        return nomConegut;
    }

    public void setNomConegut(List<String> nomConegut) {
        this.nomConegut = nomConegut;
    }

    public String getLlocOrginen() {
        return llocOrginen;
    }

    public void setLlocOrginen(String llocOrginen) {
        this.llocOrginen = llocOrginen;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getImatge() {
        return imatge;
    }

    public void setImatge(String imatge) {
        this.imatge = imatge;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
