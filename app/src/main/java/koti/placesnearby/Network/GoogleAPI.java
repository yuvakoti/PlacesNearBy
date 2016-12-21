package koti.placesnearby.Network;

import java.util.Map;

import koti.placesnearby.Model.Places.Places;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by User on 12/21/2016.
 */

public interface GoogleAPI {
    @GET("place/nearbysearch/json")
    Observable<Places> getPlacesNearby(@QueryMap Map<String, String> options);
    @GET("place/nearbysearch/json")
    Call<Places> getPlacesNearbyF(@QueryMap Map<String, String> options);

    class Factory{
        private static GoogleAPI service;

        public static GoogleAPI getInstance(){
            if (service == null){
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .baseUrl("https://maps.googleapis.com/maps/api/")
                        .build();

                service = retrofit.create(GoogleAPI.class);
                return service;
            }else {
                return service;
            }

        }
    }
}
