package com.jlmcdeveloper.notes.data.network;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

public interface VolleyRequestQueue {

    RequestQueue getRequestQueue();

    ImageLoader getImageLoader();
}
