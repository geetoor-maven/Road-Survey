package com.shegi.surveyour;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingeton {
    private static MySingeton mInstance;
    private RequestQueue requestQueue;
    private static Context mCtx;

    private MySingeton(Context context)
    {
        mCtx = context;
        requestQueue = getRequest();
    }
    private RequestQueue getRequest() {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
            return requestQueue;
        }

    public static synchronized MySingeton getInstance (Context context){
        if (mInstance == null)
        {
            mInstance = new MySingeton(context);
        }
        return mInstance;
    }
        public <T> void addToRequestQuee(Request<T> request){
            getRequest().add(request);
        }
}
