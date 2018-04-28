package robot.boocax.com.android7zdemo01.cmd;

import android.text.TextUtils;

/**
 * Created by Administrator on 2018/2/5 0005.
 */

public class ZipCode {
    static {
        System.loadLibrary("native-lib");
    }

    //7zr a xxx.7z xx
    public native static int exec(String cmd);

}
