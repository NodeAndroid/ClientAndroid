package com.cometous.graduation.exception;

import com.cometous.graduation.http.volley.VolleyError;

/**
 * Created by Devilsen on 2015/4/15.
 */
public class BusinessError extends VolleyError {

    private String error;

    public BusinessError() {
        super();
    }

    public BusinessError(String error) {
        this.error = error;
    }

    @Override
    public String getMessage() {
        return error;
    }
}
