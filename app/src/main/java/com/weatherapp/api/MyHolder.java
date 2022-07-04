package com.weatherapp.api;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Hp on 3/17/2016.
 */
public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener{

    ImageView img;
    TextView nameTxt, woeidTxt,woeTxt2,temp,min,max,id;
    ItemClickListener itemClickListener;
    Context context;

    public MyHolder(View itemView) {
        super(itemView);

        //ASSIGN
        img= (ImageView) itemView.findViewById(R.id.Image);
        nameTxt= (TextView) itemView.findViewById(R.id.nameTxt);
        woeidTxt = (TextView) itemView.findViewById(R.id.woeTxt);
        woeTxt2 = (TextView) itemView.findViewById(R.id.woeTxt2);


        temp = (TextView) itemView.findViewById(R.id.temp);
        min = (TextView) itemView.findViewById(R.id.min);
        max = (TextView) itemView.findViewById(R.id.max);
        id = (TextView) itemView.findViewById(R.id.id);


        context = itemView.getContext();
        itemView.setOnClickListener(this);
        itemView.setOnCreateContextMenuListener(this);





    }


    @Override
    public void onClick(View v) {
       // this.itemClickListener.onItemClick(v,getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListener ic)
    {
        this.itemClickListener=ic;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Select Action");
        MenuItem edit = menu.add(Menu.NONE,1,1,"Forecast");
        MenuItem delete = menu.add(Menu.NONE,2,2,"Delete");
        edit.setOnMenuItemClickListener(onChange);
        delete.setOnMenuItemClickListener(onChange);





    }


    private final MenuItem.OnMenuItemClickListener onChange = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case 1:
                    getAdapterPosition();
//                    Intent intent = new Intent(context,ThirdActivity.class);
//                    context.startActivity(intent);

                    Intent weatherActivityIntent = new Intent(context, ThirdActivity.class);
                    weatherActivityIntent.putExtra("woeid",woeTxt2.getText().toString());
                    context.startActivity(weatherActivityIntent);
                    Toast.makeText(context,woeTxt2.getText().toString(),Toast.LENGTH_LONG).show();

                    return true;
                case 2:

                    delete(Integer.valueOf(id.getText().toString()));

                    //Toast.makeText(context,nameTxt.getText().toString(),Toast.LENGTH_LONG).show();

                    return true;
            }
            return false;
        }
    };

    private void delete(int id)
    {
        DBAdapter db=new DBAdapter(context);
        db.openDB();
        long result=db.Delete(id);

        if(result>0)
        {
            //this.finish();
        }else
        {

            //Snackbar.make(nameTxt,"Unable to Update",Snackbar.LENGTH_SHORT).show();

        }

        db.close();
    }



}
