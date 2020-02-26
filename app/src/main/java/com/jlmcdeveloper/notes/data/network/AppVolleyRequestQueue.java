package com.jlmcdeveloper.notes.data.network;


import android.content.Context;
import android.graphics.Bitmap;

import androidx.collection.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.jlmcdeveloper.notes.di.ContextApplication;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppVolleyRequestQueue implements VolleyRequestQueue{
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    @Inject
    public AppVolleyRequestQueue(@ContextApplication Context context){
        this.requestQueue = Volley.newRequestQueue(context);

        imageLoader = new ImageLoader(requestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap> cache = new LruCache<>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }


    @Override
    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    @Override
    public ImageLoader getImageLoader() {
        return imageLoader;
    }



}
