package cat.ioc.sandwich_club_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import cat.ioc.sandwich_club_app.adapters.SandvitxAdapter;
import cat.ioc.sandwich_club_app.model.Sandvitx;
import cat.ioc.sandwich_club_app.utils.ParseSandvitxJson;

public class MainActivity extends AppCompatActivity {

    private boolean mTwoPane;
    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    ArrayList<String> mainName=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Uneix la vista amb ButterKnife
        ButterKnife.bind(this);

        String[] sandvitxos=getResources().getStringArray((R.array.noms_sandvitxos));
        List<String> list= Arrays.asList(sandvitxos);
        RecyclerView recyclerView=findViewById(R.id.sanditxos_recyclerview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        SandvitxAdapter adapter =new SandvitxAdapter(getApplicationContext(),list);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new VerticalSpacingDecoration(64));
        recyclerView.setNestedScrollingEnabled(true);

        recyclerView.setAdapter(adapter);




    }

    private void launchDetailActivity(int position) {
        Intent intent=new Intent(this,DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_POSITION,position);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void launchDetailActivityTransition(int position, View view) {
        Intent intent=new Intent(this,DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_POSITION,position);

        //crea el bundle que crea la animació transition. el recyclerview a activity_main
        // i el cordinateLayout a l'activity_detail son defines dins el android:transitionName="move"
        Bundle bundle= ActivityOptions.makeSceneTransitionAnimation(
                this,
                view,
                getString(R.string.transition_move)
        ).toBundle();
        //comença la nova activitat
        startActivity(intent,bundle);
    }

    class DividerItemDecoration extends RecyclerView.ItemDecoration {

        private Drawable mDivider;

        public DividerItemDecoration(Drawable divider) {
            this.mDivider = divider;
        }


    }

    static class VerticalSpacingDecoration extends RecyclerView.ItemDecoration {

        private int spacing;

        public VerticalSpacingDecoration(int spacing) {
            this.spacing = spacing;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent,
                                   RecyclerView.State state) {
            outRect.bottom = spacing;
        }
    }


}
