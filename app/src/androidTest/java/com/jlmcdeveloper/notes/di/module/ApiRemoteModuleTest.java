package com.jlmcdeveloper.notes.di.module;

import android.content.Context;

import com.jlmcdeveloper.notes.data.network.ApiEndPoint;
import com.jlmcdeveloper.notes.data.network.ApiRestServer;
import com.jlmcdeveloper.notes.di.ContextApplication;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiRemoteModuleTest {

    private static final int CACHE_SIZE = 10 * 1024 * 1024;

    @Provides
    @Singleton
    ApiRestServer provideApiRestServer(Retrofit retrofit){
        return retrofit.create(ApiRestServer.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient, MockWebServer mockWebServer){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(mockWebServer.url("/"))
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    MockWebServer provideMockWebServer(){
        MockWebServer mockWebServer = new MockWebServer();
        try {
            mockWebServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mockWebServer;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache){
        return new OkHttpClient.Builder()
                .cache(cache)
                .build();
    }


    @Provides
    @Singleton
    Cache provideCache(@ContextApplication Context context){
        return new Cache(context.getCacheDir(), CACHE_SIZE);
    }
}
