package com.example.testemployes1.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//данный клвсс будет для нас создавать объект ретрофит
public class ApiFactory {
    private static ApiFactory apiFactory;
    private static Retrofit retrofit;

    //все URL в ретрофит необходимо что бы заканчивались слэшом
    private static final String BASE_URL = "http://gitlab.65apps.com/65gb/static/raw/master/";

    //Что бы нельзя было создать обьъект этого класса создаем приватный конструктор без параметров
    //в приватном конструкторе создаем объект retrofit
    private ApiFactory(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                //CallAdapterFactory что бы в процессе полуения данных-> смотрим преобразование произошло успешно или не успешно,слушатель событий
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //этот конвертер преобразовывает полученный GSON в объекты
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiFactory getInstance (){
        if (apiFactory == null){
            apiFactory = new ApiFactory();
        }
        return apiFactory;
    }

    public ApiService getApiService(){

        //возвращаем Apiservice c помощью ретрофита, все методы ApiService реализовывает retrofit
        return retrofit.create(ApiService.class);
    }
}
