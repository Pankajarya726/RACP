package com.tekzee.racp.api;

import com.tekzee.racp.BuildConfig;
import com.tekzee.racp.RacpApplication;
import com.tekzee.racp.constant.Constant;
import com.tekzee.racp.utils.Utility;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit mRetrofit;

    public static Retrofit retrofit()
    {
        if(mRetrofit==null)
        {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if(BuildConfig.DEBUG)
            {
                //log information interceptor
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                //Set the Debug Log mode
                builder.addInterceptor(loggingInterceptor);
            }

            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .header("appversion", String.valueOf(RacpApplication.versionCode))
                            .header("fcmtoken", Utility.getSharedPreferences(RacpApplication.context, Constant.F_TOKEN))
                            .header("languagecode",Utility.getSharedPreferences(RacpApplication.context,Constant.language_code))
                            .method(original.method(), original.body())
                            .build();

                    return chain.proceed(request);
                }
            });

            OkHttpClient okHttpClient =  builder
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .build();




            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return mRetrofit;
    }


}
