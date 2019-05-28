package com.rifaikuci.yerelseyahatrotalama;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    Activity activity;
    List<UserModel> users;
    LayoutInflater inflater;

    public CustomAdapter(Activity activity) {
        this.activity = activity;
    }

    public CustomAdapter(Activity activity, List<UserModel> users) {
        this.activity   = activity;
        this.users      = users;

        inflater        = activity.getLayoutInflater();
    }


    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;

        if (view == null){

            view = inflater.inflate(R.layout.custom_view, viewGroup, false);

            holder = new ViewHolder();
            holder.tvUserName = (TextView)view.findViewById(R.id.basliklar);
            holder.ivCheckBox = (ImageView) view.findViewById(R.id.checkler);
            holder.resim =(ImageView)view.findViewById(R.id.resimler);

            view.setTag(holder);
        }else
            holder = (ViewHolder)view.getTag();

        UserModel model = users.get(i);

        holder.tvUserName.setText(model.getUserName());

        if (model.isSelected())
            holder.ivCheckBox.setBackgroundResource(R.drawable.checked);

        else
            holder.ivCheckBox.setBackgroundResource(R.drawable.check);


        String icecekIsim=model.getUserName().toString();
        if(icecekIsim.equals("Ulu Cami")){
            holder.resim.setImageResource(R.drawable.ulucami);
        }else if(icecekIsim.equals("Hacılar Höyük")){
            holder.resim.setImageResource(R.drawable.hacilarhoyuk);
        }else if(icecekIsim.equals("İncir  Han")){
            holder.resim.setImageResource(R.drawable.incirhan);
        }else if(icecekIsim.equals("Burdur Müzesi")) {
            holder.resim.setImageResource(R.drawable.burdurmuzesi);
        }else if(icecekIsim.equals("Doğa Tarihi Müzesi")) {
            holder.resim.setImageResource(R.drawable.dogatarihimuzesi);
        }else if(icecekIsim.equals("Sagalassos")) {
            holder.resim.setImageResource(R.drawable.sagalassos);
        }else if(icecekIsim.equals("Kibyra")) {
            holder.resim.setImageResource(R.drawable.kibyra);
        }else if(icecekIsim.equals("Kremna")) {
            holder.resim.setImageResource(R.drawable.kremna);
        }else if(icecekIsim.equals("Baki Bey Konağı")) {
            holder.resim.setImageResource(R.drawable.bakibey);
        }else if(icecekIsim.equals("Taş Oda")) {
            holder.resim.setImageResource(R.drawable.tasoda);
        }else if(icecekIsim.equals("Mısırlılar Evi")) {
            holder.resim.setImageResource(R.drawable.misirlilar);
        }else if(icecekIsim.equals("Burdur Gölü")) {
            holder.resim.setImageResource(R.drawable.burdurgolu);
        }else if(icecekIsim.equals("Gölhisar Gölü")) {
            holder.resim.setImageResource(R.drawable.golhisar);
        }else if(icecekIsim.equals("Salda Gölü")) {
            holder.resim.setImageResource(R.drawable.saldagolu);
        }else if(icecekIsim.equals("İnsuyu Mağarası")) {
            holder.resim.setImageResource(R.drawable.insuyu);
        }

        return view;
    }

    public void updateRecords(List<UserModel> users){
        this.users = users;
        notifyDataSetChanged();
    }

    class ViewHolder{

        TextView tvUserName;
        ImageView ivCheckBox;
        ImageView resim;

    }
}