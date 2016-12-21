package koti.placesnearby;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import koti.placesnearby.Model.Places.Places;
import koti.placesnearby.Network.GoogleAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = "AIzaSyBX_wG070n1huAexYOdUIr65cUXO3HOn4w";
    private static final String TAG = "kkkk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kotifunction();
    }
    public  void  kotifunction(){

        Map<String, String> opt = new HashMap<>();
        opt.put("location", "-33.8670522,151.1957362");
        opt.put("radius", "1000");
        opt.put("types", "food");
        opt.put("key", API_KEY);

GoogleAPI.Factory.getInstance().getPlacesNearby(opt)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Places>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Places places) {
                for (int i = 0; i < places.getResults().size(); i++) {
                    Log.d(TAG, "onNext: "+places.getResults().get(i).getGeometry().getLocation().getLat());
                }

            }
        });
    }
}
