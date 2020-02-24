package cat.ioc.sandwich_club_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cat.ioc.sandwich_club_app.model.Sandvitx;
import cat.ioc.sandwich_club_app.utils.ParseSandvitxJson;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION="extra_position";
    private static final int DEFAULT_POSITION=-1;

    /** TextView per mostrar tambe conegut com
     *
     * @param savedInstanceState
     */
    @BindView(R.id.also_known_tv)
    TextView mAlsoKnownTv;

    /**TextView per mostrar el lloc d'origen del Sandvitx
     *
     * @param savedInstanceState
     */
    @BindView(R.id.origin_tv) TextView mOriginTv;

    /**TextView per a mostrar la descripcio del Sandvitx */

    /**
     *
     * @param savedInstanceState
     */
    @BindView(R.id.description_tv) TextView mDescriptionTv;

    /**TextView per la etiqueta ingredients
     *
     * @param savedInstanceState
     */
    @BindView(R.id.ingredients_tv) TextView mIngredientsTv;

    /**TexView per a tambe conegut com **/
    @BindView(R.id.also_known_label_tv) TextView mAlsoKnownLabelTv;
    /**TexView per etiqueta lloc d'origen**/
    @BindView(R.id.origin_label_tv) TextView mOriginLabelTv;
    /**TextView per a la etiqueta descripcio**/
    @BindView(R.id.description_label_tv) TextView mDesscriptionLabelTv;
    /**TextView per a la etiqueta ingredients**/
    @BindView(R.id.ingredients_label_tv) TextView mIngredientsLabelTv;
    /**ImageView per a la imatge del Sandvitx**/
    @BindView(R.id.image_iv)ImageView mIngredientsIv;
    /**Collapsing Toolbar layout*/
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbar;
    /** Toolbar*/
    @BindView(R.id.app_bar)
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);
        //Bind les vistes amb ButterKnife
        ButterKnife.bind(this);
        Intent intent=getIntent();
        if(intent==null){
            closeOnError();
        }
        int position=intent.getIntExtra(EXTRA_POSITION,DEFAULT_POSITION);
        if(position==DEFAULT_POSITION){
            closeOnError();
            return;
        }

        String[] sandwitxes=getResources().getStringArray(R.array.sandvitx_details);
        String json=sandwitxes[position];
        Sandvitx sandvitx= ParseSandvitxJson.parseSandvitxJson(json);
        if(sandvitx==null){
            //les dades del Sandwitx no estan disponibles
            closeOnError();
            return;
        }

        // Estableix les fonts per als textViews
        setTypeFace();

        //Mostra el detalls del sandvitx en cada textview
        populateUI(sandvitx);
        Picasso.with(this).load(sandvitx.getImatge())
                .into(mIngredientsIv);
        mCollapsingToolbar.setTitle(sandvitx.getNomPrincipal());
        //Mostra el butto de retorn dins el collapsing Toolbar
        setSupportActionBar(mToolbar);
        ActionBar actionBar=getSupportActionBar();

        if(actionBar !=null){
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }



    }

    private void populateUI(Sandvitx sandvitx) {
        //Pren la llista tambeConegut
        List<String> tambeConegutLlista=sandvitx.getNomConegut();
        if(tambeConegutLlista !=null){
            //Si tambeConegut es null, fes servir el metode TextUtils.join que retorna una string amb els tokens
            //ajuntats per  mitja de delimitadors
            String tambeConegut= TextUtils.join(getString(R.string.new_line),tambeConegutLlista);
            //estableix tambe conegut com a el string per al textview
            mAlsoKnownTv.setText(tambeConegut);

        }else{
            // si la llista tambeConegutLlista es null, mostra un missatge que mostri que no hi ha dades
            mAlsoKnownTv.setText(getString(R.string.message_not_available));
        }
        //pren el string del lloc d'origen
        String originString=sandvitx.getLlocOrginen();
        //Si el lloc d'origen no es null, posa el text d'origen , del contrari amaga el textview
        if(originString!=null){
            mOriginTv.setText(originString);
        }else{
            //Si tambeConetutLLista es  null, mostra el missatge per a l'usuari indicant que no es disposen dades
            mOriginTv.setText(getString(R.string.message_not_available));
        }
        //estableix el string descripcio al texview

        mDescriptionTv.setText(sandvitx.getDescripcio());

        //pren els ingredients de la llista
        List<String> ingredientsList=sandvitx.getIngredients();
        if(ingredientsList!=null){
            //si la llista d'ingredients no es null, usa TextUtils.join per aretornar un string
            //amb els tokens ajuntats amb delimitadors
            String ingredients=TextUtils.join(getString(R.string.new_line),ingredientsList);
            //estableix el conjunt d'ingredients dins el textview
            mIngredientsTv.setText(ingredients);

        }
    }

    private void closeOnError(){
        finish();
        Toast.makeText(this,R.string.detail_error_message,Toast.LENGTH_SHORT).show();
    }

    private void setTypeFace(){
        //Retorna les fons amb el metode getFont.

        Typeface raleway= ResourcesCompat.getFont(this,R.font.raleway_regular);
        Typeface righteous=ResourcesCompat.getFont(this,R.font.righteous_regular);
        //estableix la familia de fonts per als textviews
        mAlsoKnownTv.setTypeface(raleway);
        mOriginTv.setTypeface(raleway);
        mIngredientsTv.setTypeface(raleway);
        mDescriptionTv.setTypeface(raleway);

        mAlsoKnownLabelTv.setTypeface(righteous);
        mOriginLabelTv.setTypeface(righteous);
        mIngredientsLabelTv.setTypeface(righteous);
        mDesscriptionLabelTv.setTypeface(righteous);
    }

    /**
     * Quan la fletxa del icone del app bar es clickat, acaba l'activitat detail
     *
     */
    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return  true;
    }
}
