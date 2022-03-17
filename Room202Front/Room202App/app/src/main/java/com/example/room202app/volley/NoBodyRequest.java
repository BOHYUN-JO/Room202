package com.example.room202app.volley;

import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class NoBodyRequest extends JsonRequest<String> {
    private final Object mLock = new Object();

    @Nullable
    @GuardedBy("mLock")
    private Response.Listener<String> mListener;

    public NoBodyRequest(
            int method,
            String url,
            @Nullable JSONObject jsonRequest,
            Response.Listener<String> listener,
            @Nullable Response.ErrorListener errorListener) {
        super(method, url, (jsonRequest == null) ? null : jsonRequest.toString(), listener, errorListener);
        mListener = listener;
    }

    public NoBodyRequest(
            String url, @Nullable JSONObject jsonRequest, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        this(Method.GET, url, (jsonRequest == null) ? null : jsonRequest, listener, errorListener);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            // Since minSdkVersion = 8, we can't call
            // new String(response.data, Charset.defaultCharset())
            // So suppress the warning instead.
            return Response.error(new ParseError(e));
        }

        if(parsed.equals("성공"))
            return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
        else
            return Response.error(new ParseError(new Exception("실패")));
    }

    @Override
    protected void deliverResponse(String response) {
        Response.Listener<String> listener;
        synchronized (mLock) {
            listener = mListener;
        }
        if (listener != null) {
            listener.onResponse(response);
        }
    }


        @Override
        public void cancel() {
            super.cancel();
            synchronized (mLock) {
                mListener = null;
            }
        }



}
