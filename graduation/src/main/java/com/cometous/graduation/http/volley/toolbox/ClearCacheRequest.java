package com.cometous.graduation.http.volley.toolbox;


import android.os.Handler;
import android.os.Looper;

import com.cometous.graduation.http.volley.Cache;
import com.cometous.graduation.http.volley.NetworkResponse;
import com.cometous.graduation.http.volley.Request;
import com.cometous.graduation.http.volley.Request.Method;
import com.cometous.graduation.http.volley.Request.Priority;
import com.cometous.graduation.http.volley.Response;

/**
 * A synthetic request used for clearing the cache.
 */
public class ClearCacheRequest extends Request<Object> {
    private final Cache mCache;
    private final Runnable mCallback;

    /**
     * Creates a synthetic request for clearing the cache.
     * @param cache Cache to clear
     * @param callback Callback to make on the main thread once the cache is clear,
     * or null for none
     */
    public ClearCacheRequest(Cache cache, Runnable callback) {
        super(Method.GET, null, null);
        mCache = cache;
        mCallback = callback;
    }

    @Override
    public boolean isCanceled() {
        // This is a little bit of a hack, but hey, why not.
        mCache.clear();
        if (mCallback != null) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postAtFrontOfQueue(mCallback);
        }
        return true;
    }

    @Override
    public Priority getPriority() {
        return Priority.IMMEDIATE;
    }

    @Override
    protected Response<Object> parseNetworkResponse(NetworkResponse response) {
        return null;
    }

    @Override
    protected void deliverResponse(Object response) {
    }
}
