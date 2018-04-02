package myapps.jherrera.sonidosapp.myutil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import myapps.jherrera.sonidosapp.R;

public class FileByPath
{
    private static Bitmap getImageBitmap(Context context){

        Bitmap resourceAsBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.descarga); // here use R.drawable.your_resource
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        if (resourceAsBitmap != null) {
            resourceAsBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        }

        return resourceAsBitmap;
    }

    private static String getResoursePath(Context context){


        return "android.resource:///" +
                context.getPackageName() +
                "/" +
               // "drawable" +
               // "/" +
                "descarga.jpg";
    }

    public static File getFile(Context context) {

        Bitmap bitmap = getImageBitmap(context);

        File imageFile = new File(getResoursePath(context));

        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (Exception e) {
            Log.e(context.getClass().getSimpleName(), "Error writing bitmap", e);
        }

        return imageFile;
    }
}
