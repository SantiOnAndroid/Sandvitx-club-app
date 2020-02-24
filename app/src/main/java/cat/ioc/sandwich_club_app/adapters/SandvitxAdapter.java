package cat.ioc.sandwich_club_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cat.ioc.sandwich_club_app.DetailActivity;
import cat.ioc.sandwich_club_app.MainActivity;
import cat.ioc.sandwich_club_app.R;
import cat.ioc.sandwich_club_app.model.Sandvitx;

public class SandvitxAdapter extends RecyclerView.Adapter<SandvitxAdapter.ViewHolder>
{

    private final List<String> mValues;
    Context context;





    public SandvitxAdapter( Context context,List<String> items){
        this.mValues=items;
        this.context=context;


    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
     View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main,parent,false);
     ViewHolder vh=new ViewHolder(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

           holder.mIdView.setText(mValues.get(position));
           holder.mView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Context context = view.getContext();
                   Intent intent = new Intent(context, DetailActivity.class);
                   intent.putExtra(DetailActivity.EXTRA_POSITION,
                           holder.getAdapterPosition());
                   context.startActivity(intent);

               }
           });


    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        final View mView;
        final TextView mIdView;
        ViewHolder(View view){
            super(view);
            mView=view;
            mIdView=(TextView) view.findViewById(R.id.id_text);

        }
    }

}
