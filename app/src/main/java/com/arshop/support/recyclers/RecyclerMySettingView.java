package com.arshop.support.recyclers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.arshop.controller.activities.ActivityMySettingOption;
import com.arshop.controller.R;

import java.io.Serializable;
import java.util.List;

public class RecyclerMySettingView extends RecyclerView.Adapter<RecyclerMySettingView.SettingeView>  {
    private Context context;
    private List<String> settingOptions;


    public RecyclerMySettingView (Context context, List<String> mySettings) {
        this.context = context;
        this.settingOptions = mySettings;
    }


    @NonNull
    @Override
    public RecyclerMySettingView.SettingeView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.card_item_view,parent,false);
        return new RecyclerMySettingView.SettingeView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerMySettingView.SettingeView holder, int position) {

        holder.settingOptionName.setText(settingOptions.get(position).toString());
        holder.settingsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityMySettingOption.class);

                // Passing the category name chose by the user to ProductList Activity
                intent.putExtra("SettingName", (Serializable) settingOptions.get(position));
                // start the activity
                context.startActivity(intent);

            }
        });

    }


    @Override
    public int getItemCount() {
        return settingOptions.size();
    }



    public static class SettingeView extends RecyclerView.ViewHolder {

        private CardView settingsCardView;
        private TextView settingOptionName;


        public SettingeView(View cartItem){
            super(cartItem);

            settingsCardView = (CardView) cartItem.findViewById(R.id.generic_item_view);
            settingOptionName = (TextView) cartItem.findViewById(R.id.itemName);


        }

    }
}
