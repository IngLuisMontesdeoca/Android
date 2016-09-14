package io.github.ingluismontesdeoca.fragment.admin;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Aministrador on 31/08/2016.
 */
public abstract class Debug {

    public static boolean debug = false;

    public Debug(){

    }

    public static void mkToast(String message, Context context){
        if( debug )
            Toast.makeText(context,message,Toast.LENGTH_LONG).show();

    }
}
