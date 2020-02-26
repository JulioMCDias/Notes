package com.jlmcdeveloper.notes.data.network;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;

public class GsonRequest<T> extends JsonRequest<T> {
    private final Gson gson = new Gson();
    private final Class<T> myClass;

    public GsonRequest(String url, Class<T> myClass, Object body,
                        Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, new Gson().toJson(body), listener, errorListener);
        this.myClass = myClass;
    }




    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(
                    gson.fromJson(json, myClass), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

}
