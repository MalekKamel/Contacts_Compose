package app.common.core.bitmap;

import android.graphics.Bitmap;

public class AppBitmap {

    public static Bitmap empty(){
        return Bitmap.createBitmap(
                1,
                1,
                Bitmap.Config.ARGB_8888
        );
    }



}
