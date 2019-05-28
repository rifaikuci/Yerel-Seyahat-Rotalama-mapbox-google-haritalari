package com.rifaikuci.yerelseyahatrotalama;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button rotaOlustur;
    UserModel model;
   public static ArrayList<String> gidilecekYerler ;

    public static RelativeLayout layout;
    public static List<UserModel> users;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gidilecekYerler = new ArrayList<>();
       listView=(ListView) findViewById(R.id.liste);
        rotaOlustur=(Button) findViewById(R.id.rotaOlustur);
         layout=(RelativeLayout) findViewById(R.id.customListe);

        users = new ArrayList<>();
        users.add(new UserModel(false, "Ulu Cami"));
        users.add(new UserModel(false, "Hacılar Höyük"));
        users.add(new UserModel(false, "İncir Han"));
        users.add(new UserModel(false, "Burdur Müzesi"));
        users.add(new UserModel(false, "Doğa Tarihi Müzesi"));
        users.add(new UserModel(false, "Sagalassos"));
        users.add(new UserModel(false, "Kibyra"));
        users.add(new UserModel(false, "Kremna"));
        users.add(new UserModel(false, "Baki Bey Konağı"));
        users.add(new UserModel(false, "Taş Oda"));
        users.add(new UserModel(false, "Mısırlılar Evi"));
        users.add(new UserModel(false, "Burdur Gölü"));
        users.add(new UserModel(false, "Gölhisar Gölü"));
        users.add(new UserModel(false, "Salda Gölü"));
        users.add(new UserModel(false, "İnsuyu Mağarası"));


        final CustomAdapter adapter = new CustomAdapter(this, users);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                 model = users.get(i);


                if (model.isSelected()) {
                    model.setSelected(false);
                    Toast.makeText(getApplicationContext(), "Rotadan " + model.getUserName() + " Çıkarıldı.", Toast.LENGTH_SHORT).show();
                    gidilecekYerler.remove(model.getUserName());
                } else {
                    if(gidilecekYerler.size()<12) {
                        model.setSelected(true);
                        Toast.makeText(getApplicationContext(), "Rotaya " + model.getUserName() + " Eklendi.", Toast.LENGTH_SHORT).show();
                        gidilecekYerler.add(model.getUserName());
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Rotaya en fazla 12 yere gidebilirsiniz.", Toast.LENGTH_SHORT).show();

                    }
                    }
                users.set(i, model);

                //now update adapter
                adapter.updateRecords(users);

            }


        });


        if(Build.VERSION.SDK_INT>=19)
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        else
        {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }



    }

    public void geri(View view) {
        Intent intent = new Intent(getApplicationContext(),yerlerinGosterimi.class);

        startActivity(intent);
    }
    public void rota(View view) {

        if(gidilecekYerler.size()<=12 && gidilecekYerler.size()>=2)
        {
            Intent intent = new Intent(getApplicationContext(),optimizasyon.class);



            startActivity(intent);
        }else
        {
            Toast.makeText(this,"En Az 2 yer Seçmeniz Gerekir",Toast.LENGTH_SHORT).show();
        }



    }
}
