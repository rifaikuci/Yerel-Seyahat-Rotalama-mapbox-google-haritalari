package com.rifaikuci.yerelseyahatrotalama;

// Dahil Edilen Kütüphaneler Bunları Eklerken Gradle Yapılması Gereken Ayarlar Vardır...

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.google.android.gms.maps.model.Marker;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.api.directions.v5.DirectionsCriteria;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.api.optimization.v1.MapboxOptimization;
import com.mapbox.api.optimization.v1.models.OptimizationResponse;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.LineString;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.markerview.MarkerView;
import com.mapbox.mapboxsdk.plugins.markerview.MarkerViewManager;
import com.mapbox.mapboxsdk.style.layers.LineLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.mapbox.core.constants.Constants.PRECISION_6;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.lineColor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.lineWidth;
import static com.rifaikuci.yerelseyahatrotalama.MainActivity.gidilecekYerler;


public class optimizasyon extends AppCompatActivity implements OnMapReadyCallback,PermissionsListener  {

    private static final String ANY = "any";
    private static final String TEAL_COLOR = "#3358FF";
    private static final float POLYLINE_WIDTH = 5;
    private MapView mapView;
    private MapboxMap mapboxMap;
    private DirectionsRoute optimizedRoute;
    private MapboxOptimization optimizedClient;
    private List<Point> stops = new ArrayList<>();
    private MarkerView markerView;
    private MarkerViewManager markerViewManager;
    private PermissionsManager permissionsManager;
    private Marker marker;
    // Rotalara Ekleyebilmek için pointer türünde değişkenler tanımlandı
    Point ulucamiPoint,hacilarPoint,incirhanPoint,burdurmuzeisPoint,dogaPoint,sagalassosPoint,kibyraPoint;
    Point kremnaPoint,bakibeyPoint,tasPoint,misirlilarPoint,burdurgoluPoint,golhisarPoint,saldaPoint,insuyuPoint;

    //Marker ve point alırken Enlem ve boylama ihtiyacımız olacktır başlangıç
    com.google.android.gms.maps.model.LatLng ulucami = new com.google.android.gms.maps.model.LatLng(37.717430, 30.286363);
    com.google.android.gms.maps.model.LatLng hacilar = new com.google.android.gms.maps.model.LatLng(37.584867, 30.084486);
    com.google.android.gms.maps.model.LatLng incirhan= new com.google.android.gms.maps.model.LatLng(37.478628, 30.533486);
    com.google.android.gms.maps.model.LatLng burdurmuzesi = new com.google.android.gms.maps.model.LatLng(37.719492, 30.286229);
    com.google.android.gms.maps.model.LatLng doga = new com.google.android.gms.maps.model.LatLng(37.714039, 30.281065);
    com.google.android.gms.maps.model.LatLng sagalassos = new com.google.android.gms.maps.model.LatLng(37.677311, 30.516952);
    com.google.android.gms.maps.model.LatLng kibyra = new com.google.android.gms.maps.model.LatLng(37.156866, 29.499676);
    com.google.android.gms.maps.model.LatLng kremna = new com.google.android.gms.maps.model.LatLng(37.497568, 30.688048);
    com.google.android.gms.maps.model.LatLng bakibey = new com.google.android.gms.maps.model.LatLng(     37.714896, 30.287548);
    com.google.android.gms.maps.model.LatLng tas = new com.google.android.gms.maps.model.LatLng(     37.717124, 30.288768);
    com.google.android.gms.maps.model.LatLng misirlilar = new com.google.android.gms.maps.model.LatLng(     37.715267, 30.283913);
    com.google.android.gms.maps.model.LatLng burdurgolu = new com.google.android.gms.maps.model.LatLng(     37.735082, 30.170443);
    com.google.android.gms.maps.model.LatLng golhisar = new com.google.android.gms.maps.model.LatLng(     37.115486, 29.600524);
    com.google.android.gms.maps.model.LatLng salda = new com.google.android.gms.maps.model.LatLng(     37.552650, 29.672964);
    com.google.android.gms.maps.model.LatLng insuyu = new com.google.android.gms.maps.model.LatLng(     37.659385, 30.374563);
    //Marker ve point alırken Enlem ve boylama ihtiyacımız olacktır Bitiş


    //Otel Yerleri Başlangıç
    com.google.android.gms.maps.model.LatLng özeren = new com.google.android.gms.maps.model.LatLng(37.719063,30.280882);
    com.google.android.gms.maps.model.LatLng muratSezgin = new com.google.android.gms.maps.model.LatLng(37.719604,30.281196);
    com.google.android.gms.maps.model.LatLng LodgeSpa = new com.google.android.gms.maps.model.LatLng(37.659201,30.525999);
    com.google.android.gms.maps.model.LatLng kibyraOtel = new com.google.android.gms.maps.model.LatLng(37.151595,29.510836);
    com.google.android.gms.maps.model.LatLng mecikoglu = new com.google.android.gms.maps.model.LatLng(37.456467,30.590120);
    com.google.android.gms.maps.model.LatLng alya = new com.google.android.gms.maps.model.LatLng(37.717585,30.280218);
    com.google.android.gms.maps.model.LatLng lagoDisalda = new com.google.android.gms.maps.model.LatLng(37.514729,29.686925);
    com.google.android.gms.maps.model.LatLng saldaLake = new com.google.android.gms.maps.model.LatLng(37.525844,29.728538);
    com.mapbox.mapboxsdk.annotations.Marker özerenMarkerView,muratSezginMarkerView,LodgeSpaMarkerView,kibyraOtelMarkerView,mecikogluMarkerView;
    com.mapbox.mapboxsdk.annotations.Marker alyaMarkerView,lagoDisaldaMarkerView,saldaLakeMarkerView;
    //Otel Yerleri Bitis


    //Yemek Yerleri Başlangıç
    com.google.android.gms.maps.model.LatLng sisciVeli = new com.google.android.gms.maps.model.LatLng(37.7176629,30.2859775);
    com.google.android.gms.maps.model.LatLng özenirPide = new com.google.android.gms.maps.model.LatLng(37.7176578,30.2858244);
    com.google.android.gms.maps.model.LatLng toros = new com.google.android.gms.maps.model.LatLng(37.7188393,30.2839443);
    com.google.android.gms.maps.model.LatLng özdemir = new com.google.android.gms.maps.model.LatLng(37.476807,30.5344546);
    com.google.android.gms.maps.model.LatLng teziHasan = new com.google.android.gms.maps.model.LatLng(37.476785,30.534859);
    com.google.android.gms.maps.model.LatLng dönerDünyasi = new com.google.android.gms.maps.model.LatLng(37.719177,30.286529);
    com.google.android.gms.maps.model.LatLng gülistan = new com.google.android.gms.maps.model.LatLng(37.716256,30.282490);
    com.google.android.gms.maps.model.LatLng karPide = new com.google.android.gms.maps.model.LatLng(37.715974,30.283138);
    com.google.android.gms.maps.model.LatLng sehriZiyaret= new com.google.android.gms.maps.model.LatLng(37.718993,30.287096);
    com.google.android.gms.maps.model.LatLng yilmazPide = new com.google.android.gms.maps.model.LatLng(37.715366,30.283338);
    com.google.android.gms.maps.model.LatLng serenlerTepesi = new com.google.android.gms.maps.model.LatLng(37.706048,30.231590);
    com.google.android.gms.maps.model.LatLng lanzhou=new com.google.android.gms.maps.model.LatLng(37.708444,30.241787);
    com.google.android.gms.maps.model.LatLng aksoy = new com.google.android.gms.maps.model.LatLng(37.110471,29.624448);
    com.google.android.gms.maps.model.LatLng insuyuCafe=new com.google.android.gms.maps.model.LatLng(37.659243,30.373991);
    com.google.android.gms.maps.model.LatLng yörük = new com.google.android.gms.maps.model.LatLng(37.656890,30.361513);
    com.google.android.gms.maps.model.LatLng bulgaroglu = new com.google.android.gms.maps.model.LatLng(37.506898, 29.710016);
    com.google.android.gms.maps.model.LatLng venus= new com.google.android.gms.maps.model.LatLng(37.514229,29.679785);
    com.google.android.gms.maps.model.LatLng maldivler = new com.google.android.gms.maps.model.LatLng(37.534653,29.644536);
    com.mapbox.mapboxsdk.annotations.Marker sisciVeliMarkerView,özenirPideMarkerView,torosMarkerView,özdemirMarkerView,teziHasanMarkerView;
    com.mapbox.mapboxsdk.annotations.Marker DönerDunyasiView,gülistanMarkerView,karpideMarkerView,sehriZiyaretMarkerView,yilmazPideMarkerView;
    com.mapbox.mapboxsdk.annotations.Marker serenlerTepesiView,lanzhouMarkerView,aksoyMarkerView,insuyuCafeMarkerView,yorukMarkerView;
    com.mapbox.mapboxsdk.annotations.Marker bulgarogluMarkerView,venusMarkerView,maldivlerMarkerView;

    //Yemek Yerleri Bitis

    IconFactory iconhotelFactory;
    FloatingActionButton btnYemek,btnHotel;
    boolean yemekDurum=false;
    boolean otelDurum=false;
    private SwipeRefreshLayout swipeRefreshLayout;


    // Marker Sıralaması için böyle bir yola başvuruldu
    MarkerOptions incirhanMarker;
    MarkerOptions ulucamiMarker;
    MarkerOptions hacilarMarker;
    MarkerOptions kibyraMarker;
    MarkerOptions kremnaMarker;
    MarkerOptions bakiMarker;
    MarkerOptions tasMarker;
    MarkerOptions misirlilarMarker;
    MarkerOptions burduGoluMarker;
    MarkerOptions golhisarMarkker;
    MarkerOptions saldaMarker;
    MarkerOptions insuMarker;
    MarkerOptions burdurmuzesiMarker;
    MarkerOptions dogaMarker;
    MarkerOptions sagalassosMarker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.access_token));
        setContentView(R.layout.activity_optimizasyon);
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        btnHotel=(FloatingActionButton) findViewById(R.id.hotel);
        btnYemek=(FloatingActionButton) findViewById(R.id.yemek);
        btnHotel.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.Error)));
        btnYemek.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.Error)));



// Aşağıdaki Kod Ekranımızın üst tarafını transparan yapmamızı sağlar
        if(Build.VERSION.SDK_INT>=19)
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        else
        {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        //Refresh Butonu
  /*      swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        Style style =mapboxMap.getStyle();

                        getOptimizedRoute(style,stops);

                Toast.makeText(getApplicationContext(),"1.İNDEx:"+stops.get(0) , Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(),"2.İNDEx:"+stops.get(1) , Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(),"3.İNDEx:"+stops.get(2) , Toast.LENGTH_SHORT).show();

                    }
                },100);
            }
        });
*/


    }


    @Override
    public void onMapReady(@NonNull final MapboxMap mapboxMap) {
        this.mapboxMap = mapboxMap;



        btnYemek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(yemekDurum==false)
                {
                    btnYemek.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.Success)));
                    yemekDurum=true;

                    //Yemek Yenilecek Yer Baslangıc
                    if(gidilecekYerler.contains("Ulu Cami"))
                    {
                        MarkerOptions sisciVeliMarker= new MarkerOptions();
                        sisciVeliMarker.title("Şişçi Veli'nin Yeri").snippet("Telefon : 0505 203 51 59\nPuan:\t5.0/5.0");
                        sisciVeliMarker.position(new LatLng(sisciVeli.latitude,sisciVeli.longitude)).icon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.blue_marker));
                        sisciVeliMarkerView=mapboxMap.addMarker(sisciVeliMarker);


                        MarkerOptions özenirPideMarker= new MarkerOptions();
                        özenirPideMarker.title("Özenir Pide").snippet("Telefon : (0248) 232 24 84\nPuan:\t5.0/5.0");
                        özenirPideMarker.position(new LatLng(özenirPide.latitude,özenirPide.longitude)).icon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.blue_marker));
                        özenirPideMarkerView=mapboxMap.addMarker(özenirPideMarker);
                    }

                    if(gidilecekYerler.contains("Ulu Cami") || gidilecekYerler.contains("Burdur Müzesi")) {


                        MarkerOptions torosMarker= new MarkerOptions();
                        torosMarker.title("Toros Lokantası").snippet("Telefon : (0248) 233 57 52\nPuan:\t5.0/4.1");
                        torosMarker.position(new LatLng(toros.latitude,toros.longitude)).icon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.blue_marker));
                        torosMarkerView=mapboxMap.addMarker(torosMarker);
                    }

                    if(gidilecekYerler.contains("İncir Han"))
                    {
                        MarkerOptions özdemirCanliMarker= new MarkerOptions();
                        özdemirCanliMarker.title("Özdemir Canlı Balık Restaurant").snippet("Telefon : -\nPuan:\t5.0/3.0");
                        özdemirCanliMarker.position(new LatLng(özdemir.latitude,özdemir.longitude)).icon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.blue_marker));
                        özdemirMarkerView=mapboxMap.addMarker(özdemirCanliMarker);

                        MarkerOptions hasanMarker= new MarkerOptions();
                        hasanMarker.title("Terzi Hasan Hüseyinin Yeri\n").snippet("Telefon : -\nPuan:\t5.0/4.9");
                        hasanMarker.position(new LatLng(teziHasan.latitude,teziHasan.longitude)).icon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.blue_marker));
                        teziHasanMarkerView=mapboxMap.addMarker(hasanMarker);
                    }

                    if(gidilecekYerler.contains("Taş Oda") || gidilecekYerler.contains("Burdur Müzesi")) {


                        MarkerOptions DonerMaker= new MarkerOptions();
                        DonerMaker.title("Döner Dünyası").snippet("Telefon : (0248) 234 10 10\nPuan:\t5.0/5.0");
                        DonerMaker.position(new LatLng(dönerDünyasi.latitude,dönerDünyasi.longitude)).icon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.blue_marker));
                        DönerDunyasiView=mapboxMap.addMarker(DonerMaker);
                    }

                    if(gidilecekYerler.contains("Doğa Tarihi Müzesi")) {

                        MarkerOptions gulistamarker= new MarkerOptions();
                        gulistamarker.title("Gülistan Restaurant").snippet("Telefon : (0248) 233 43 47\nPuan:\t5.0/4.8");
                        gulistamarker.position(new LatLng(gülistan.latitude,gülistan.longitude)).icon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.blue_marker));
                        gülistanMarkerView=mapboxMap.addMarker(gulistamarker);

                    }

                    if(gidilecekYerler.contains("Baki Bey Konağı") || gidilecekYerler.contains("Mısırlılar Evi")) {

                        MarkerOptions KarMarker= new MarkerOptions();
                        KarMarker.title("Kar Pide Fırını").snippet("Telefon : (0248) 233 93 52\nPuan:\t5.0/4.5");
                        KarMarker.position(new LatLng(karPide.latitude,karPide.longitude)).icon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.blue_marker));
                        karpideMarkerView=mapboxMap.addMarker(KarMarker);


                    }
                    if(gidilecekYerler.contains("Taş Oda")){
                        MarkerOptions SehriMarker= new MarkerOptions();
                        SehriMarker.title("Şehri Ziyafet Restaurant").snippet("Telefon : (0248) 234 11 92\nPuan:\t5.0/5.0");
                        SehriMarker.position(new LatLng(sehriZiyaret.latitude,sehriZiyaret.longitude)).icon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.blue_marker));
                        sehriZiyaretMarkerView=mapboxMap.addMarker(SehriMarker);

                    }

                    if(gidilecekYerler.contains("Mısırlılar Evi")){
                        MarkerOptions yilmazMarker= new MarkerOptions();
                        yilmazMarker.title("Yılmaz Pide Salonu").snippet("Telefon : (0248) 252 83 37\nPuan:\t5.0/3.0");
                        yilmazMarker.position(new LatLng(yilmazPide.latitude,yilmazPide.longitude)).icon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.blue_marker));
                        yilmazPideMarkerView=mapboxMap.addMarker(yilmazMarker);
                    }

                    if(gidilecekYerler.contains("Burdur Gölü")){
                        MarkerOptions serenlerMarker= new MarkerOptions();
                        serenlerMarker.title("Serenler Tepesi Restaurant").snippet("Telefon : 0530 612 15 85\nPuan:\t5.0/4.0");
                        serenlerMarker.position(new LatLng(serenlerTepesi.latitude,serenlerTepesi.longitude)).icon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.blue_marker));
                        serenlerTepesiView=mapboxMap.addMarker(serenlerMarker);

                        MarkerOptions lanzhouMarker= new MarkerOptions();
                        lanzhouMarker.title("Lanzhou Restaurant").snippet("Telefon : -\nPuan:\t5.0/3.0");
                        lanzhouMarker.position(new LatLng(lanzhou.latitude,lanzhou.longitude)).icon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.blue_marker));
                        lanzhouMarkerView=mapboxMap.addMarker(lanzhouMarker);
                    }

                    if(gidilecekYerler.contains("Gölhisar Gölü")){
                        MarkerOptions aksoyMarker= new MarkerOptions();
                        aksoyMarker.title("Aksoy Aile Balık Restaurant").snippet("Telefon : 0544 411 56 47\nPuan:\t5.0/4.5");
                        aksoyMarker.position(new LatLng(aksoy.latitude,aksoy.longitude)).icon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.blue_marker));
                        aksoyMarkerView=mapboxMap.addMarker(aksoyMarker);
                    }

                    if(gidilecekYerler.contains("İnsuyu Mağarası")){
                        MarkerOptions insuyuCafeMarker= new MarkerOptions();
                        insuyuCafeMarker.title("İnsuyu Cafe Restaurant").snippet("Telefon : -\nPuan:\t5.0/4.0");
                        insuyuCafeMarker.position(new LatLng(insuyuCafe.latitude,insuyuCafe.longitude)).icon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.blue_marker));
                        insuyuCafeMarkerView=mapboxMap.addMarker(insuyuCafeMarker);

                        MarkerOptions yorukMarker= new MarkerOptions();
                        yorukMarker.title("Yörük Sofrası ").snippet("Telefon : 0554 585 68 07\nPuan:\t5.0/4.0");
                        yorukMarker.position(new LatLng(yörük.latitude,yörük.longitude)).icon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.blue_marker));
                        yorukMarkerView=mapboxMap.addMarker(yorukMarker);
                    }

                    if(gidilecekYerler.contains("Salda Gölü")){
                        MarkerOptions bulgarogluMarker= new MarkerOptions();
                        bulgarogluMarker.title("Bulgaroğlu Restaurant").snippet("Telefon : -\nPuan:\t5.0/1.0");
                        bulgarogluMarker.position(new LatLng(bulgaroglu.latitude,bulgaroglu.longitude)).icon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.blue_marker));
                        bulgarogluMarkerView=mapboxMap.addMarker(bulgarogluMarker);

                        MarkerOptions venüsMarker= new MarkerOptions();
                        venüsMarker.title("Venüs Pansiyon&Restaurant").snippet("Telefon : 0536 374 33 92\nPuan:\t5.0/3.4");
                        venüsMarker.position(new LatLng(venus.latitude,venus.longitude)).icon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.blue_marker));
                        venusMarkerView=mapboxMap.addMarker(venüsMarker);

                        MarkerOptions maldivlerMarker= new MarkerOptions();
                        maldivlerMarker.title("Maldivler Cafe Fast Food").snippet("Telefon : 0553 868 74 64\nPuan:\t5.0/3.8");
                        maldivlerMarker.position(new LatLng(maldivler.latitude,maldivler.longitude)).icon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.blue_marker));
                        maldivlerMarkerView=mapboxMap.addMarker(maldivlerMarker);
                    }





//Yemek Yenilecek Yer Bitis

                }
                else
                {
                    btnYemek.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.Error)));
                    yemekDurum=false;
                    if(gidilecekYerler.contains("Ulu Cami"))
                    {
                        sisciVeliMarkerView.remove();
                        özenirPideMarkerView.remove();
                    }

                    if(gidilecekYerler.contains("Ulu Cami") || gidilecekYerler.contains("Burdur Müzesi")) {

                        torosMarkerView.remove();
                    }

                    if(gidilecekYerler.contains("İncir Han"))
                    {
                        özdemirMarkerView.remove();
                        teziHasanMarkerView.remove();
                    }

                    if(gidilecekYerler.contains("Taş Oda") || gidilecekYerler.contains("Burdur Müzesi")) {

                        DönerDunyasiView.remove();
                    }

                    if(gidilecekYerler.contains("Doğa Tarihi Müzesi")) {

                        gülistanMarkerView.remove();

                    }

                    if(gidilecekYerler.contains("Baki Bey Konağı") || gidilecekYerler.contains("Mısırlılar Evi")) {

                       karpideMarkerView.remove();
                    }
                    if(gidilecekYerler.contains("Taş Oda")){
                        sehriZiyaretMarkerView.remove();
                    }

                    if(gidilecekYerler.contains("Mısırlılar Evi")){
                        yilmazPideMarkerView.remove();
                    }

                    if(gidilecekYerler.contains("Burdur Gölü")){
                        serenlerTepesiView.remove();
                        lanzhouMarkerView.remove();
                    }

                    if(gidilecekYerler.contains("Gölhisar Gölü")){
                        aksoyMarkerView.remove();
                    }

                    if(gidilecekYerler.contains("İnsuyu Mağarası")){
                        insuyuCafeMarkerView.remove();
                        yorukMarkerView.remove();                    }

                    if(gidilecekYerler.contains("Salda Gölü")){
                        bulgarogluMarkerView.remove();
                        venusMarkerView.remove();
                       maldivlerMarkerView.remove();
                    }



                }
            }
        });

        btnHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (otelDurum == false) {
                    btnHotel.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.Success)));
                    otelDurum = true;


                    //Otel Kalacak Yerler Başlabgıç
                    if (gidilecekYerler.contains("Ulu Cami") || gidilecekYerler.contains("Burdur Müzesi") || gidilecekYerler.contains("Doğa Tarihi Müzesi") ||
                            gidilecekYerler.contains("Baki Bey Konağı") || gidilecekYerler.contains("Taş Oda") || gidilecekYerler.contains("Mısırlılar Evi")
                    ) {

                        MarkerOptions özerenMarker = new MarkerOptions();
                        özerenMarker.title("Özeren Grand Hotel & Spa").snippet("Telefon : (0248) 233 77 53\nPuan:\t5.0/4.2");
                        özerenMarker.position(new LatLng(özeren.latitude, özeren.longitude)).setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.green_marker));
                        özerenMarkerView = mapboxMap.addMarker(özerenMarker);


                        MarkerOptions muratSezginMarker = new MarkerOptions();
                        muratSezginMarker.title("Murat Sezgin Park Prestij Hotel").snippet("Telefon : (0248) 233 88 44\nPuan:\t5.0/4.2");
                        muratSezginMarker.position(new LatLng(muratSezgin.latitude, muratSezgin.longitude)).icon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.green_marker));
                        muratSezginMarkerView = mapboxMap.addMarker(muratSezginMarker);


                    }
                    if (gidilecekYerler.contains("Sagalassos")) {
                        MarkerOptions sagalasosMarkerLodge = new MarkerOptions();
                        sagalasosMarkerLodge.title("Sagalassos Lodge and Spa").snippet("Telefon : (0248) 731 32 32\nPuan:\t5.0/4.5");
                        sagalasosMarkerLodge.position(new LatLng(LodgeSpa.latitude, LodgeSpa.longitude)).icon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.green_marker));
                        LodgeSpaMarkerView = mapboxMap.addMarker(sagalasosMarkerLodge);
                    }

                    if (gidilecekYerler.contains("Kibyra") || gidilecekYerler.contains("Gölhisar Gölü")) {
                        MarkerOptions KibyraMarkerOtelMarker = new MarkerOptions();
                        KibyraMarkerOtelMarker.title("Kibyra Hotel").snippet("Telefon : (0248) 411 39 39\nPuan:\t5.0/4.4");
                        KibyraMarkerOtelMarker.position(new LatLng(kibyraOtel.latitude, kibyra.longitude)).icon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.green_marker));
                        kibyraOtelMarkerView = mapboxMap.addMarker(KibyraMarkerOtelMarker);
                    }


                    if (gidilecekYerler.contains("Kremna")) {
                        MarkerOptions KremnaOtelMarker = new MarkerOptions();
                        KremnaOtelMarker.title("Meçikoğlu Hotel").snippet("Telefon : (0248) 325 55 10\nPuan:\t5.0/4.0");
                        KremnaOtelMarker.position(new LatLng(mecikoglu.latitude, mecikoglu.longitude)).icon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.green_marker));
                        mecikogluMarkerView = mapboxMap.addMarker(KremnaOtelMarker);
                    }

                    if (gidilecekYerler.contains("Burdur Gölü")) {
                        MarkerOptions alyaMarker = new MarkerOptions();
                        alyaMarker.title("Alya Hotel").snippet("Telefon : (0248) 234 30 67\nPuan:\t5.0/4.3");
                        alyaMarker.position(new LatLng(alya.latitude, alya.longitude)).icon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.green_marker));
                        alyaMarkerView = mapboxMap.addMarker(alyaMarker);
                    }

                    if (gidilecekYerler.contains("Salda Gölü")) {
                        MarkerOptions hotelSaldaMarker = new MarkerOptions();
                        hotelSaldaMarker.title("Hotel Lago Di Salda").snippet("Telefon : (0248) 618 04 80\nPuan:\t5.0/4.1");
                        hotelSaldaMarker.position(new LatLng(lagoDisalda.latitude, lagoDisalda.longitude)).icon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.green_marker));
                        lagoDisaldaMarkerView = mapboxMap.addMarker(hotelSaldaMarker);

                        MarkerOptions saldaLakeMarker = new MarkerOptions();
                        saldaLakeMarker.title("Salda Lake Hotel").snippet("Telefon : (0248) 618 25 86\nPuan:\t5.0/4.2");
                        saldaLakeMarker.position(new LatLng(saldaLake.latitude, saldaLake.longitude)).icon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.green_marker));
                        saldaLakeMarkerView = mapboxMap.addMarker(saldaLakeMarker);
                    }
//Otel Kalacak Yer Bitis
                } else {
                    btnHotel.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.Error)));
                    otelDurum = false;

                    if (gidilecekYerler.contains("Ulu Cami") || gidilecekYerler.contains("Burdur Müzesi") || gidilecekYerler.contains("Doğa Tarihi Müzesi") ||
                            gidilecekYerler.contains("Baki Bey Konağı") || gidilecekYerler.contains("Taş Oda") || gidilecekYerler.contains("Mısırlılar Evi")
                    ) {

                        özerenMarkerView.remove();
                        muratSezginMarkerView.remove();


                    }
                    if (gidilecekYerler.contains("Sagalassos")) {
                        LodgeSpaMarkerView.remove();

                    }

                    if (gidilecekYerler.contains("Kibyra") || gidilecekYerler.contains("Gölhisar Gölü")) {
                        kibyraOtelMarkerView.remove();

                    }


                    if (gidilecekYerler.contains("Kremna")) {
                        mecikogluMarkerView.remove();
                    }

                    if (gidilecekYerler.contains("Burdur Gölü")) {
                        alyaMarkerView.remove();

                    }

                    if (gidilecekYerler.contains("Salda Gölü")) {
                        lagoDisaldaMarkerView.remove();
                        saldaLakeMarkerView.remove();
                    }
                }
            }
        }    );

        if(gidilecekYerler.contains("Ulu Cami"))
        {
            ulucamiMarker= new MarkerOptions();
            ulucamiMarker.title("Ulu Cami");
            ulucamiMarker.position(new LatLng(ulucami.latitude,ulucami.longitude));
            ulucamiPoint =Point.fromLngLat(ulucami.longitude,ulucami.latitude);
            stops.add(ulucamiPoint);
        }


        if(gidilecekYerler.contains("Hacılar Höyük"))
        {
            hacilarMarker= new MarkerOptions();
            hacilarMarker.title("Hacılar Höyük");
            hacilarMarker.position(new LatLng(hacilar.latitude,hacilar.longitude));
            hacilarPoint =Point.fromLngLat(hacilar.longitude,hacilar.latitude);
            stops.add(hacilarPoint);
        }

        if(gidilecekYerler.contains("İncir Han"))
        {
            incirhanMarker= new MarkerOptions();
            incirhanMarker.title("İncir Han");
            incirhanMarker.position(new LatLng(incirhan.latitude,incirhan.longitude));
            incirhanPoint =Point.fromLngLat(incirhan.longitude,incirhan.latitude);
            stops.add(incirhanPoint);
        }

        if(gidilecekYerler.contains("Burdur Müzesi"))
        {
            burdurmuzesiMarker= new MarkerOptions();
            burdurmuzesiMarker.title("Burdur Müzesi");
            burdurmuzesiMarker.position(new LatLng(burdurmuzesi.latitude,burdurmuzesi.longitude));
            burdurmuzeisPoint =Point.fromLngLat(burdurmuzesi.longitude,burdurmuzesi.latitude);
            stops.add(burdurmuzeisPoint);
        }



        if(gidilecekYerler.contains("Doğa Tarihi Müzesi"))
        {
            dogaMarker= new MarkerOptions();
            dogaMarker.title("Doğa Tarihi Müzesi");
            dogaMarker.position(new LatLng(doga.latitude,doga.longitude));
            dogaPoint =Point.fromLngLat(doga.longitude,doga.latitude);
            stops.add(dogaPoint);
        }

        if(gidilecekYerler.contains("Sagalassos"))
        {
            sagalassosMarker= new MarkerOptions();
            sagalassosMarker.title("Sagalassos");
            sagalassosMarker.position(new LatLng(sagalassos.latitude,sagalassos.longitude));
            sagalassosPoint=Point.fromLngLat(sagalassos.longitude,sagalassos.latitude);
            stops.add(sagalassosPoint);
        }

        if(gidilecekYerler.contains("Kibyra"))
        {
            kibyraMarker= new MarkerOptions();
            kibyraMarker.title("Kibyra");
            kibyraMarker.position(new LatLng(kibyra.latitude,kibyra.longitude));
            kibyraPoint=Point.fromLngLat(kibyra.longitude,kibyra.latitude);
            stops.add(kibyraPoint);
        }

        if(gidilecekYerler.contains("Kremna"))
        {
            kremnaMarker= new MarkerOptions();
            kremnaMarker.title("Kremna");
            kremnaMarker.position(new LatLng(kremna.latitude,kremna.longitude));
            kremnaPoint=Point.fromLngLat(kremna.longitude,kremna.latitude);
            stops.add(kremnaPoint);
        }

        if(gidilecekYerler.contains("Baki Bey Konağı"))
        {
            bakiMarker= new MarkerOptions();
            bakiMarker.title("Baki Bey Konağı");
            bakiMarker.position(new LatLng(bakibey.latitude,bakibey.longitude));
            bakibeyPoint=Point.fromLngLat(bakibey.longitude,bakibey.latitude);
            stops.add(bakibeyPoint);
        }

        if(gidilecekYerler.contains("Taş Oda"))
        {
            tasMarker= new MarkerOptions();
            tasMarker.title("Taş Oda");
            tasMarker.position(new LatLng(bakibey.latitude,bakibey.longitude));
            tasPoint=Point.fromLngLat(tas.longitude,tas.latitude);
            stops.add(tasPoint);
        }

        if(gidilecekYerler.contains("Mısırlılar Evi"))
        {
            misirlilarMarker= new MarkerOptions();
            misirlilarMarker.title("Mısırlılar Evi");
            misirlilarMarker.position(new LatLng(misirlilar.latitude,misirlilar.longitude));
            misirlilarPoint=Point.fromLngLat(misirlilar.longitude,misirlilar.latitude);
            stops.add(misirlilarPoint);
        }

        if(gidilecekYerler.contains("Burdur Gölü"))
        {
            burduGoluMarker= new MarkerOptions();
            burduGoluMarker.title("Burdur Gölü");
            burduGoluMarker.position(new LatLng(burdurgolu.latitude,burdurgolu.longitude));
            burdurgoluPoint=Point.fromLngLat(burdurgolu.longitude,burdurgolu.latitude);
            stops.add(burdurgoluPoint);
        }

        if(gidilecekYerler.contains("Gölhisar Gölü"))
        {
            golhisarMarkker= new MarkerOptions();
            golhisarMarkker.title("Gölhisar Gölü");
            golhisarMarkker.position(new LatLng(golhisar.latitude,golhisar.longitude));
            golhisarPoint=Point.fromLngLat(golhisar.longitude,golhisar.latitude);
            stops.add(golhisarPoint);
        }

        if(gidilecekYerler.contains("Salda Gölü"))
        {
            saldaMarker= new MarkerOptions();
            saldaMarker.title("Salda Gölü");
            saldaMarker.position(new LatLng(salda.latitude,salda.longitude));
            saldaPoint=Point.fromLngLat(salda.longitude,salda.latitude);
            stops.add(saldaPoint);
        }

        if(gidilecekYerler.contains("İnsuyu Mağarası"))
        {
            insuMarker= new MarkerOptions();
            insuMarker.title("İnsuyu Mağarası");
            insuMarker.position(new LatLng(insuyu.latitude,insuyu.longitude));
            insuyuPoint=Point.fromLngLat(insuyu.longitude,insuyu.latitude);
            stops.add(insuyuPoint);
        }

            if(stops.get(0).latitude()==ulucami.latitude )

            {
                ulucamiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker1));
                mapboxMap.addMarker(ulucamiMarker);
            }

            if(stops.get(1).latitude()==ulucami.latitude  )

            {
                ulucamiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker2));
                mapboxMap.addMarker(ulucamiMarker);
            }

        if(stops.get(0).latitude()==hacilar.latitude )

        {
            hacilarMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker1));
            mapboxMap.addMarker(hacilarMarker);
        }

        if(stops.get(1).latitude()==hacilar.latitude  )

        {
            hacilarMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker2));
            mapboxMap.addMarker(hacilarMarker);
        }


        if(stops.get(0).latitude()==incirhan.latitude )

        {
            incirhanMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker1));
            mapboxMap.addMarker(incirhanMarker);
        }

        if(stops.get(1).latitude()==incirhan.latitude  )

        {
            incirhanMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker2));
            mapboxMap.addMarker(incirhanMarker);
        }


        if(stops.get(0).latitude()==burdurmuzesi.latitude )

        {
            burdurmuzesiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker1));
            mapboxMap.addMarker(burdurmuzesiMarker);
        }

        if(stops.get(1).latitude()==burdurmuzesi.latitude  )

        {
            burdurmuzesiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker2));
            mapboxMap.addMarker(burdurmuzesiMarker);
        }

        if(stops.get(0).latitude()==doga.latitude )

        {
            dogaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker1));
            mapboxMap.addMarker(dogaMarker);
        }

        if(stops.get(1).latitude()==doga.latitude  )

        {
            dogaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker2));
            mapboxMap.addMarker(dogaMarker);
        }

        if(stops.get(0).latitude()==sagalassos.latitude )

        {
            sagalassosMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker1));
            mapboxMap.addMarker(sagalassosMarker);
        }

        if(stops.get(1).latitude()==sagalassos.latitude  )

        {
            sagalassosMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker2));
            mapboxMap.addMarker(sagalassosMarker);
        }


        if(stops.get(0).latitude()==kibyra.latitude )

        {
            kibyraMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker1));
            mapboxMap.addMarker(kibyraMarker);
        }

        if(stops.get(1).latitude()==kibyra.latitude  )

        {
            kibyraMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker2));
            mapboxMap.addMarker(kibyraMarker);
        }

        if(stops.get(0).latitude()==kremna.latitude )

        {
            kremnaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker1));
            mapboxMap.addMarker(kremnaMarker);
        }

        if(stops.get(1).latitude()==kremna.latitude  )

        {
            kremnaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker2));
            mapboxMap.addMarker(kremnaMarker);
        }

        if(stops.get(0).latitude()==bakibey.latitude )

        {
            bakiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker1));
            mapboxMap.addMarker(bakiMarker);
        }

        if(stops.get(1).latitude()==bakibey.latitude  )

        {
            bakiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker2));
            mapboxMap.addMarker(bakiMarker);
        }

        if(stops.get(0).latitude()==tas.latitude )

        {
            tasMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker1));
            mapboxMap.addMarker(tasMarker);
        }

        if(stops.get(1).latitude()==tas.latitude  )

        {
            tasMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker2));
            mapboxMap.addMarker(tasMarker);
        }

        if(stops.get(0).latitude()==misirlilar.latitude )

        {
            misirlilarMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker1));
            mapboxMap.addMarker(misirlilarMarker);
        }

        if(stops.get(1).latitude()==misirlilar.latitude  )

        {
            misirlilarMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker2));
            mapboxMap.addMarker(misirlilarMarker);
        }

        if(stops.get(0).latitude()==burdurgolu.latitude )

        {
            burduGoluMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker1));
            mapboxMap.addMarker(burduGoluMarker);
        }

        if(stops.get(1).latitude()==burdurgolu.latitude  )

        {
            burduGoluMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker2));
            mapboxMap.addMarker(burduGoluMarker);
        }

        if(stops.get(0).latitude()==golhisar.latitude )

        {
            golhisarMarkker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker1));
            mapboxMap.addMarker(golhisarMarkker);
        }

        if(stops.get(1).latitude()==golhisar.latitude  )

        {
            golhisarMarkker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker2));
            mapboxMap.addMarker(golhisarMarkker);
        }

        if(stops.get(0).latitude()==salda.latitude )

        {
            saldaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker1));
            mapboxMap.addMarker(saldaMarker);
        }

        if(stops.get(1).latitude()==salda.latitude  )

        {
            saldaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker2));
            mapboxMap.addMarker(saldaMarker);
        }

        if(stops.get(0).latitude()==insuyu.latitude )

        {
            insuMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker1));
            mapboxMap.addMarker(insuMarker);
        }

        if(stops.get(1).latitude()==insuyu.latitude  )

        {
            insuMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker2));
            mapboxMap.addMarker(insuMarker);
        }


        if(gidilecekYerler.size()>2)
        {
            if (stops.get(2).latitude() == ulucami.latitude) {
                ulucamiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker3));
                mapboxMap.addMarker(ulucamiMarker);
            }

            if(stops.get(2).latitude()==hacilar.latitude )

            {
                hacilarMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker3));
                mapboxMap.addMarker(hacilarMarker);
            }

            if(stops.get(2).latitude()==incirhan.latitude )

            {
                incirhanMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker3));
                mapboxMap.addMarker(incirhanMarker);
            }

            if(stops.get(2).latitude()==burdurmuzesi.latitude )

            {
                burdurmuzesiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker3));
                mapboxMap.addMarker(burdurmuzesiMarker);
            }

            if(stops.get(2).latitude()==doga.latitude )

            {
                dogaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker3));
                mapboxMap.addMarker(dogaMarker);
            }

            if(stops.get(2).latitude()==sagalassos.latitude )

            {
                sagalassosMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker3));
                mapboxMap.addMarker(sagalassosMarker);
            }
            if(stops.get(2).latitude()==kibyra.latitude )

            {
                kibyraMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker3));
                mapboxMap.addMarker(kibyraMarker);
            }
            if(stops.get(2).latitude()==kremna.latitude )

            {
                kremnaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker3));
                mapboxMap.addMarker(kremnaMarker);
            }

            if(stops.get(2).latitude()==bakibey.latitude )

            {
                bakiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker3));
                mapboxMap.addMarker(bakiMarker);
            }
            if(stops.get(2).latitude()==tas.latitude )

            {
                tasMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker3));
                mapboxMap.addMarker(tasMarker);
            }

            if(stops.get(2).latitude()==misirlilar.latitude )

            {
                misirlilarMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker3));
                mapboxMap.addMarker(misirlilarMarker);
            }

            if(stops.get(2).latitude()==burdurgolu.latitude )

            {
                burduGoluMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker3));
                mapboxMap.addMarker(burduGoluMarker);
            }
            if(stops.get(2).latitude()==golhisar.latitude )

            {
                golhisarMarkker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker3));
                mapboxMap.addMarker(golhisarMarkker);
            }
            if(stops.get(2).latitude()==salda.latitude )

            {
                saldaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker3));
                mapboxMap.addMarker(saldaMarker);
            }

            if(stops.get(2).latitude()==insuyu.latitude )

            {
                insuMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker3));
                mapboxMap.addMarker(insuMarker);
            }
        }
        if(gidilecekYerler.size()>3)
        {

            if (stops.get(3).latitude() == ulucami.latitude) {
                ulucamiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker4));
                mapboxMap.addMarker(ulucamiMarker);
            }

            if(stops.get(3).latitude()==hacilar.latitude )

            {
                hacilarMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker4));
                mapboxMap.addMarker(hacilarMarker);
            }
            if(stops.get(3).latitude()==incirhan.latitude )

            {
                incirhanMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker4));
                mapboxMap.addMarker(incirhanMarker);
            }
            if(stops.get(3).latitude()==burdurmuzesi.latitude )

            {
                burdurmuzesiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker4));
                mapboxMap.addMarker(burdurmuzesiMarker);
            }
            if(stops.get(3).latitude()==doga.latitude )

            {
                dogaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker4));
                mapboxMap.addMarker(dogaMarker);
            }
            if(stops.get(3).latitude()==sagalassos.latitude )

            {
                sagalassosMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker4));
                mapboxMap.addMarker(sagalassosMarker);
            }
            if(stops.get(3).latitude()==kibyra.latitude )

            {
                kibyraMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker4));
                mapboxMap.addMarker(kibyraMarker);
            }
            if(stops.get(3).latitude()==kremna.latitude )

            {
                kremnaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker4));
                mapboxMap.addMarker(kremnaMarker);
            }
            if(stops.get(3).latitude()==bakibey.latitude )

            {
                bakiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker4));
                mapboxMap.addMarker(bakiMarker);
            }
            if(stops.get(3).latitude()==tas.latitude )

            {
                tasMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker4));
                mapboxMap.addMarker(tasMarker);
            }

            if(stops.get(3).latitude()==misirlilar.latitude )

            {
                misirlilarMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker4));
                mapboxMap.addMarker(misirlilarMarker);
            }

            if(stops.get(3).latitude()==burdurgolu.latitude )

            {
                burduGoluMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker4));
                mapboxMap.addMarker(burduGoluMarker);
            }
            if(stops.get(3).latitude()==golhisar.latitude )

            {
                golhisarMarkker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker4));
                mapboxMap.addMarker(golhisarMarkker);
            }
            if(stops.get(3).latitude()==salda.latitude )

            {
                saldaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker4));
                mapboxMap.addMarker(saldaMarker);
            }
            if(stops.get(3).latitude()==insuyu.latitude )

            {
                insuMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker4));
                mapboxMap.addMarker(insuMarker);
            }

        }
        if(gidilecekYerler.size()>4)
        {

            if(stops.get(4).latitude()==ulucami.latitude  )

            {
                ulucamiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker5));
                mapboxMap.addMarker(ulucamiMarker);
            }


            if(stops.get(4).latitude()==hacilar.latitude  )

            {
                hacilarMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker5));
                mapboxMap.addMarker(hacilarMarker);
            }

            if(stops.get(4).latitude()==incirhan.latitude  )

            {
                incirhanMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker5));
                mapboxMap.addMarker(incirhanMarker);
            }
            if(stops.get(4).latitude()==burdurmuzesi.latitude  )

            {
                burdurmuzesiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker5));
                mapboxMap.addMarker(burdurmuzesiMarker);
            }


            if(stops.get(4).latitude()==doga.latitude  )

            {
                dogaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker5));
                mapboxMap.addMarker(dogaMarker);
            }

            if(stops.get(4).latitude()==sagalassos.latitude  )

            {
                sagalassosMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker5));
                mapboxMap.addMarker(sagalassosMarker);
            }


            if(stops.get(4).latitude()==kibyra.latitude  )

            {
                kibyraMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker5));
                mapboxMap.addMarker(kibyraMarker);
            }

            if(stops.get(4).latitude()==kremna.latitude  )

            {
                kremnaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker5));
                mapboxMap.addMarker(kremnaMarker);
            }

            if(stops.get(4).latitude()==bakibey.latitude  )

            {
                bakiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker5));
                mapboxMap.addMarker(bakiMarker);
            }

            if(stops.get(4).latitude()==tas.latitude  )

            {
                tasMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker5));
                mapboxMap.addMarker(tasMarker);
            }
            if(stops.get(4).latitude()==misirlilar.latitude  )

            {
                misirlilarMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker5));
                mapboxMap.addMarker(misirlilarMarker);
            }
            if(stops.get(4).latitude()==burdurgolu.latitude  )

            {
                burduGoluMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker5));
                mapboxMap.addMarker(burduGoluMarker);
            }


            if(stops.get(4).latitude()==golhisar.latitude  )

            {
                golhisarMarkker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker5));
                mapboxMap.addMarker(golhisarMarkker);
            }

            if(stops.get(4).latitude()==salda.latitude  )

            {
                saldaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker5));
                mapboxMap.addMarker(saldaMarker);
            }
            if(stops.get(4).latitude()==insuyu.latitude  )

            {
                insuMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker5));
                mapboxMap.addMarker(insuMarker);
            }

        }
        if(gidilecekYerler.size()>5)
        {
            if(stops.get(5).latitude()==ulucami.latitude )

            {
                ulucamiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker6));
                mapboxMap.addMarker(ulucamiMarker);
            }


            if(stops.get(5).latitude()==hacilar.latitude )

            {
                hacilarMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker6));
                mapboxMap.addMarker(hacilarMarker);
            }


            if(stops.get(5).latitude()==incirhan.latitude )

            {
                incirhanMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker6));
                mapboxMap.addMarker(incirhanMarker);
            }


            if(stops.get(5).latitude()==burdurmuzesi.latitude )

            {
                burdurmuzesiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker6));
                mapboxMap.addMarker(burdurmuzesiMarker);
            }

            if(stops.get(5).latitude()==doga.latitude )

            {
                dogaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker6));
                mapboxMap.addMarker(dogaMarker);
            }

            if(stops.get(5).latitude()==sagalassos.latitude )

            {
                sagalassosMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker6));
                mapboxMap.addMarker(sagalassosMarker);
            }

            if(stops.get(5).latitude()==kibyra.latitude )

            {
                kibyraMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker6));
                mapboxMap.addMarker(kibyraMarker);
            }

            if(stops.get(5).latitude()==kremna.latitude )

            {
                kremnaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker6));
                mapboxMap.addMarker(kremnaMarker);
            }

            if(stops.get(5).latitude()==bakibey.latitude )

            {
                bakiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker6));
                mapboxMap.addMarker(bakiMarker);
            }
            if(stops.get(5).latitude()==tas.latitude )

            {
                tasMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker6));
                mapboxMap.addMarker(tasMarker);
            }
            if(stops.get(5).latitude()==misirlilar.latitude )

            {
                misirlilarMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker6));
                mapboxMap.addMarker(misirlilarMarker);
            }
            if(stops.get(5).latitude()==burdurgolu.latitude )

            {
                burduGoluMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker6));
                mapboxMap.addMarker(burduGoluMarker);
            }

            if(stops.get(5).latitude()==golhisar.latitude )

            {
                golhisarMarkker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker6));
                mapboxMap.addMarker(golhisarMarkker);
            }

            if(stops.get(5).latitude()==salda.latitude )

            {
                saldaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker6));
                mapboxMap.addMarker(saldaMarker);
            }

            if(stops.get(5).latitude()==insuyu.latitude )

            {
                insuMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker6));
                mapboxMap.addMarker(insuMarker);
            }
        }
        if(gidilecekYerler.size()>6)
        {
            if(stops.get(6).latitude()==ulucami.latitude )

            {
                ulucamiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker7));
                mapboxMap.addMarker(ulucamiMarker);
            }

            if(stops.get(6).latitude()==hacilar.latitude )

            {
                hacilarMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker7));
                mapboxMap.addMarker(hacilarMarker);
            }

            if(stops.get(6).latitude()==incirhan.latitude )

            {
                incirhanMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker7));
                mapboxMap.addMarker(incirhanMarker);
            }

            if(stops.get(6).latitude()==burdurmuzesi.latitude )

            {
                burdurmuzesiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker7));
                mapboxMap.addMarker(burdurmuzesiMarker);
            }
            if(stops.get(6).latitude()==doga.latitude )

            {
                dogaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker7));
                mapboxMap.addMarker(dogaMarker);
            }

            if(stops.get(6).latitude()==sagalassos.latitude )

            {
                sagalassosMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker7));
                mapboxMap.addMarker(sagalassosMarker);
            }

            if(stops.get(6).latitude()==kibyra.latitude )

            {
                kibyraMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker7));
                mapboxMap.addMarker(kibyraMarker);
            }
            if(stops.get(6).latitude()==kremna.latitude )

            {
                kremnaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker7));
                mapboxMap.addMarker(kremnaMarker);
            }

            if(stops.get(6).latitude()==bakibey.latitude )

            {
                bakiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker7));
                mapboxMap.addMarker(bakiMarker);
            }
            if(stops.get(6).latitude()==tas.latitude )

            {
                tasMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker7));
                mapboxMap.addMarker(tasMarker);
            }

            if(stops.get(6).latitude()==misirlilar.latitude )

            {
                misirlilarMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker7));
                mapboxMap.addMarker(misirlilarMarker);
            }

            if(stops.get(6).latitude()==burdurgolu.latitude )

            {
                burduGoluMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker7));
                mapboxMap.addMarker(burduGoluMarker);
            }

            if(stops.get(6).latitude()==golhisar.latitude )

            {
                golhisarMarkker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker7));
                mapboxMap.addMarker(golhisarMarkker);
            }
            if(stops.get(6).latitude()==salda.latitude )

            {
                saldaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker7));
                mapboxMap.addMarker(saldaMarker);
            }
            if(stops.get(6).latitude()==insuyu.latitude )

            {
                insuMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker7));
                mapboxMap.addMarker(insuMarker);
            }
        }
        if(gidilecekYerler.size()>7)
        {

            if(stops.get(7).latitude()==ulucami.latitude  )

            {
                ulucamiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker8));
                mapboxMap.addMarker(ulucamiMarker);
            }
            if(stops.get(7).latitude()==hacilar.latitude  )

            {
                hacilarMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker8));
                mapboxMap.addMarker(hacilarMarker);
            }
            if(stops.get(7).latitude()==incirhan.latitude  )

            {
                incirhanMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker8));
                mapboxMap.addMarker(incirhanMarker);
            }
            if(stops.get(7).latitude()==burdurmuzesi.latitude  )

            {
                burdurmuzesiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker8));
                mapboxMap.addMarker(burdurmuzesiMarker);
            }
            if(stops.get(7).latitude()==doga.latitude  )

            {
                dogaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker8));
                mapboxMap.addMarker(dogaMarker);
            }
            if(stops.get(7).latitude()==sagalassos.latitude  )

            {
                sagalassosMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker8));
                mapboxMap.addMarker(sagalassosMarker);
            }

            if(stops.get(7).latitude()==kibyra.latitude  )

            {
                kibyraMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker8));
                mapboxMap.addMarker(kibyraMarker);
            }
            if(stops.get(7).latitude()==kremna.latitude  )

            {
                kremnaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker8));
                mapboxMap.addMarker(kremnaMarker);
            }
            if(stops.get(7).latitude()==bakibey.latitude  )

            {
                bakiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker8));
                mapboxMap.addMarker(bakiMarker);
            }
            if(stops.get(7).latitude()==tas.latitude  )

            {
                tasMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker8));
                mapboxMap.addMarker(tasMarker);
            }
            if(stops.get(7).latitude()==misirlilar.latitude  )

            {
                misirlilarMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker8));
                mapboxMap.addMarker(misirlilarMarker);
            }
            if(stops.get(7).latitude()==burdurgolu.latitude  )

            {
                burduGoluMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker8));
                mapboxMap.addMarker(burduGoluMarker);
            }

            if(stops.get(7).latitude()==golhisar.latitude  )

            {
                golhisarMarkker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker8));
                mapboxMap.addMarker(golhisarMarkker);
            }

            if(stops.get(7).latitude()==salda.latitude  )

            {
                saldaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker8));
                mapboxMap.addMarker(saldaMarker);
            }

            if(stops.get(7).latitude()==insuyu.latitude  )

            {
                insuMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker8));
                mapboxMap.addMarker(insuMarker);
            }
        }
        if(gidilecekYerler.size()>8)
        {

            if(stops.get(8).latitude()==ulucami.latitude )

            {
                ulucamiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker9));
                mapboxMap.addMarker(ulucamiMarker);
            }

            if(stops.get(8).latitude()==hacilar.latitude )

            {
                hacilarMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker9));
                mapboxMap.addMarker(hacilarMarker);
            }
            if(stops.get(8).latitude()==incirhan.latitude )

            {
                incirhanMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker9));
                mapboxMap.addMarker(incirhanMarker);
            }


            if(stops.get(8).latitude()==burdurmuzesi.latitude )

            {
                burdurmuzesiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker9));
                mapboxMap.addMarker(burdurmuzesiMarker);
            }


            if(stops.get(8).latitude()==doga.latitude )

            {
                dogaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker9));
                mapboxMap.addMarker(dogaMarker);
            }

            if(stops.get(8).latitude()==sagalassos.latitude )

            {
                sagalassosMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker9));
                mapboxMap.addMarker(sagalassosMarker);
            }

            if(stops.get(8).latitude()==kibyra.latitude )

            {
                kibyraMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker9));
                mapboxMap.addMarker(kibyraMarker);
            }

            if(stops.get(8).latitude()==kremna.latitude )

            {
                kremnaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker9));
                mapboxMap.addMarker(kremnaMarker);
            }


            if(stops.get(8).latitude()==bakibey.latitude )

            {
                bakiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker9));
                mapboxMap.addMarker(bakiMarker);
            }

            if(stops.get(8).latitude()==tas.latitude )

            {
                tasMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker9));
                mapboxMap.addMarker(tasMarker);
            }

            if(stops.get(8).latitude()==misirlilar.latitude )

            {
                misirlilarMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker9));
                mapboxMap.addMarker(misirlilarMarker);
            }
            if(stops.get(8).latitude()==burdurgolu.latitude )

            {
                burduGoluMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker9));
                mapboxMap.addMarker(burduGoluMarker);
            }
            if(stops.get(8).latitude()==golhisar.latitude )

            {
                golhisarMarkker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker9));
                mapboxMap.addMarker(golhisarMarkker);
            }

            if(stops.get(8).latitude()==salda.latitude )

            {
                saldaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker9));
                mapboxMap.addMarker(saldaMarker);
            }

            if(stops.get(8).latitude()==insuyu.latitude )

            {
                insuMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker9));
                mapboxMap.addMarker(insuMarker);
            }

        }
        if(gidilecekYerler.size()>9)
        {


            if(stops.get(9).latitude()==ulucami.latitude )

            {
                ulucamiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker10));
                mapboxMap.addMarker(ulucamiMarker);
            }




            if(stops.get(9).latitude()==hacilar.latitude )

            {
                hacilarMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker10));
                mapboxMap.addMarker(hacilarMarker);
            }



            if(stops.get(9).latitude()==incirhan.latitude )

            {
                incirhanMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker10));
                mapboxMap.addMarker(incirhanMarker);
            }


            if(stops.get(9).latitude()==burdurmuzesi.latitude )

            {
                burdurmuzesiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker10));
                mapboxMap.addMarker(burdurmuzesiMarker);
            }

            if(stops.get(9).latitude()==doga.latitude )

            {
                dogaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker10));
                mapboxMap.addMarker(dogaMarker);
            }
            if(stops.get(9).latitude()==sagalassos.latitude )

            {
                sagalassosMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker10));
                mapboxMap.addMarker(sagalassosMarker);
            }

            if(stops.get(9).latitude()==kibyra.latitude )

            {
                kibyraMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker10));
                mapboxMap.addMarker(kibyraMarker);
            }

            if(stops.get(9).latitude()==kremna.latitude )

            {
                kremnaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker10));
                mapboxMap.addMarker(kremnaMarker);
            }


            if(stops.get(9).latitude()==bakibey.latitude )

            {
                bakiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker10));
                mapboxMap.addMarker(bakiMarker);
            }


            if(stops.get(9).latitude()==tas.latitude )

            {
                tasMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker10));
                mapboxMap.addMarker(tasMarker);
            }


            if(stops.get(9).latitude()==misirlilar.latitude )

            {
                misirlilarMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker10));
                mapboxMap.addMarker(misirlilarMarker);
            }

            if(stops.get(9).latitude()==burdurgolu.latitude )

            {
                burduGoluMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker10));
                mapboxMap.addMarker(burduGoluMarker);
            }

            if(stops.get(9).latitude()==golhisar.latitude )

            {
                golhisarMarkker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker10));
                mapboxMap.addMarker(golhisarMarkker);
            }



            if(stops.get(9).latitude()==salda.latitude )

            {
                saldaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker10));
                mapboxMap.addMarker(saldaMarker);
            }


            if(stops.get(9).latitude()==insuyu.latitude )

            {
                insuMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker10));
                mapboxMap.addMarker(insuMarker);
            }

        }
        if(gidilecekYerler.size()>10)
        {

            if(stops.get(10).latitude()==ulucami.latitude  )

            {
                ulucamiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker11));
                mapboxMap.addMarker(ulucamiMarker);
            }

            if(stops.get(10).latitude()==hacilar.latitude  )

            {
                hacilarMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker11));
                mapboxMap.addMarker(hacilarMarker);
            }

            if(stops.get(10).latitude()==incirhan.latitude  )

            {
                incirhanMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker11));
                mapboxMap.addMarker(incirhanMarker);
            }

            if(stops.get(10).latitude()==burdurmuzesi.latitude  )

            {
                burdurmuzesiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker11));
                mapboxMap.addMarker(burdurmuzesiMarker);
            }


            if(stops.get(10).latitude()==doga.latitude  )

            {
                dogaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker11));
                mapboxMap.addMarker(dogaMarker);
            }
            if(stops.get(10).latitude()==kibyra.latitude  )

            {
                kibyraMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker11));
                mapboxMap.addMarker(kibyraMarker);
            }
            if(stops.get(10).latitude()==kremna.latitude  )

            {
                kremnaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker11));
                mapboxMap.addMarker(kremnaMarker);
            }

            if(stops.get(10).latitude()==bakibey.latitude  )

            {
                bakiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker11));
                mapboxMap.addMarker(bakiMarker);
            }
            if(stops.get(10).latitude()==tas.latitude  )

            {
                tasMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker11));
                mapboxMap.addMarker(tasMarker);
            }
            if(stops.get(10).latitude()==misirlilar.latitude  )

            {
                misirlilarMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker11));
                mapboxMap.addMarker(misirlilarMarker);
            }
            if(stops.get(10).latitude()==burdurgolu.latitude  )

            {
                burduGoluMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker11));
                mapboxMap.addMarker(burduGoluMarker);
            }
            if(stops.get(10).latitude()==golhisar.latitude  )

            {
                golhisarMarkker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker11));
                mapboxMap.addMarker(golhisarMarkker);
            }
            if(stops.get(10).latitude()==sagalassos.latitude  )

            {
                sagalassosMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker11));
                mapboxMap.addMarker(sagalassosMarker);
            }



            if(stops.get(10).latitude()==salda.latitude  )

            {
                saldaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker11));
                mapboxMap.addMarker(saldaMarker);
            }
            if(stops.get(10).latitude()==insuyu.latitude  )

            {
                insuMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker11));
                mapboxMap.addMarker(insuMarker);
            }
        }
        if(gidilecekYerler.size()>11)
        {


            if(stops.get(11).latitude()==ulucami.latitude )

            {
                ulucamiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker12));
                mapboxMap.addMarker(ulucamiMarker);
            }

            if(stops.get(11).latitude()==hacilar.latitude )

            {
                hacilarMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker12));
                mapboxMap.addMarker(hacilarMarker);
            }
            if(stops.get(11).latitude()==incirhan.latitude )

            {
                incirhanMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker12));
                mapboxMap.addMarker(incirhanMarker);
            }
            if(stops.get(11).latitude()==burdurmuzesi.latitude )

            {
                burdurmuzesiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker12));
                mapboxMap.addMarker(burdurmuzesiMarker);
            }



            if(stops.get(11).latitude()==doga.latitude )

            {
                dogaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker12));
                mapboxMap.addMarker(dogaMarker);
            }


            if(stops.get(11).latitude()==kibyra.latitude )

            {
                kibyraMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker12));
                mapboxMap.addMarker(kibyraMarker);
            }

            if(stops.get(11).latitude()==sagalassos.latitude )

            {
                sagalassosMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker12));
                mapboxMap.addMarker(sagalassosMarker);
            }


            if(stops.get(11).latitude()==kremna.latitude )

            {
                kremnaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker12));
                mapboxMap.addMarker(kremnaMarker);
            }

            if(stops.get(11).latitude()==bakibey.latitude )

            {
                bakiMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker12));
                mapboxMap.addMarker(bakiMarker);
            }
            if(stops.get(11).latitude()==tas.latitude )

            {
                tasMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker12));
                mapboxMap.addMarker(tasMarker);
            }

            if(stops.get(11).latitude()==misirlilar.latitude )

            {
                misirlilarMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker12));
                mapboxMap.addMarker(misirlilarMarker);
            }


            if(stops.get(11).latitude()==burdurgolu.latitude )

            {
                burduGoluMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker12));
                mapboxMap.addMarker(burduGoluMarker);
            }
            if(stops.get(11).latitude()==golhisar.latitude )

            {
                golhisarMarkker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker12));
                mapboxMap.addMarker(golhisarMarkker);
            }

            if(stops.get(11).latitude()==salda.latitude )

            {
                saldaMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker12));
                mapboxMap.addMarker(saldaMarker);
            }

            if(stops.get(11).latitude()==insuyu.latitude )

            {
                insuMarker.setIcon(IconFactory.getInstance(optimizasyon.this).fromResource(R.drawable.marker12));
                mapboxMap.addMarker(insuMarker);
            }

        }

        mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {



                enableLocationComponent(style);
                initOptimizedRouteLineLayer(style);
                getOptimizedRoute(style,stops);



            }
        });


    }

    @SuppressWarnings( {"MissingPermission"})
    private void enableLocationComponent(@NonNull Style loadedMapStyle) {

        if (PermissionsManager.areLocationPermissionsGranted(this)) {


            LocationComponent locationComponent = mapboxMap.getLocationComponent();


            locationComponent.activateLocationComponent(
                    LocationComponentActivationOptions.builder(this, loadedMapStyle).build());

            locationComponent.setLocationComponentEnabled(true);

            locationComponent.setCameraMode(CameraMode.TRACKING);


            locationComponent.setRenderMode(RenderMode.NORMAL);
        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }
    }



    private void initOptimizedRouteLineLayer(@NonNull Style loadedMapStyle) {
        loadedMapStyle.addSource(new GeoJsonSource("optimized-route-source-id"));
        loadedMapStyle.addLayerBelow(new LineLayer("optimized-route-layer-id", "optimized-route-source-id")
                .withProperties(
                        lineColor(Color.parseColor(TEAL_COLOR)),
                        lineWidth(POLYLINE_WIDTH)
                ), "icon-layer-id");
    }


    private void getOptimizedRoute(@NonNull final Style style, List<Point> coordinates) {
        optimizedClient = MapboxOptimization.builder()
                .source(ANY)
                .destination(ANY)
                .coordinates(coordinates)
                .overview(DirectionsCriteria.OVERVIEW_FULL)
                .profile(DirectionsCriteria.PROFILE_DRIVING)
                .accessToken(Mapbox.getAccessToken())
                .build();

        optimizedClient.enqueueCall(new Callback<OptimizationResponse>() {
            @Override
            public void onResponse(Call<OptimizationResponse> call, Response<OptimizationResponse> response) {
                if (!response.isSuccessful()) {

                    return;
                } else {
                    if (response.body().trips().isEmpty()) {

                        return;
                    }
                }

                optimizedRoute = response.body().trips().get(0);
                drawOptimizedRoute(style, optimizedRoute);
            }

            @Override
            public void onFailure(Call<OptimizationResponse> call, Throwable throwable) {
                Timber.d("Error: %s", throwable.getMessage());
            }
        });


    }

    private void drawOptimizedRoute( Style style, DirectionsRoute route) {
        GeoJsonSource optimizedLineSource = style.getSourceAs("optimized-route-source-id");
        if (optimizedLineSource != null) {
            optimizedLineSource.setGeoJson(FeatureCollection.fromFeature(Feature.fromGeometry(
                    LineString.fromPolyline(route.geometry(), PRECISION_6))));
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
// Cancel the directions API request

        if (optimizedClient != null) {
            optimizedClient.cancelCall();
        }
        if (mapboxMap != null) {
            //  mapboxMap.removeOnMapClickListener(this);
        }
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        // Toast.makeText(this, R.string.user_location_permission_explanation, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            mapboxMap.getStyle(new Style.OnStyleLoaded() {
                @Override
                public void onStyleLoaded(@NonNull Style style) {
                    enableLocationComponent(style);
                }
            });
        } else {
            finish();
        }
    }

}