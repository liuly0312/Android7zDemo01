package robot.boocax.com.android7zdemo01.cmd;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;

import robot.boocax.com.android7zdemo01.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //加载
    public void load(View view) {
        boolean result = ZipHelper.loadBinary(this, "7zr");
        Toast.makeText(this, "加载7zr 结果:" + result, Toast.LENGTH_SHORT).show();
    }

    /**
     * 压缩
     * 7z a  [输出文件] [待压缩文件/目录] -mx=9
     * 7z a /sdcard/7-Zip.7z /sdcard/7-Zip -mx=9
     *
     * @param view
     */
    public void pack(View view) {
        File src = new File(Environment.getExternalStorageDirectory() + File.separator + "somethings", "7-ZIP");
        File out = new File(Environment.getExternalStorageDirectory() + File.separator + "somethings", "7-Zip.7z");
        ZipHelper.execute(this, "7zr a " + out.getAbsolutePath() + " " + src.getAbsolutePath() +
                        " -mx=9",
                new ZipHelper.OnResultListener() {
                    @Override
                    public void onSuccess(String msg) {
                        Log.e(TAG, "执行成功");
                    }

                    @Override
                    public void onFailure(int errorno, String msg) {
                        Log.e(TAG, "执行失败:");
                        Log.e(TAG, " 错误代码:" + errorno);
                        Log.e(TAG, " 错误输出:" + msg);
                    }

                    @Override
                    public void onProgress(String msg) {
                        Log.e(TAG, "正在执行:" + msg);
                    }
                });
    }

    //解压
    public void unpack(View view) {
        File out = new File(Environment.getExternalStorageDirectory() + File.separator + "somethings", "7-Zip-unpack");
        File src = new File(Environment.getExternalStorageDirectory() + File.separator + "somethings", "7-Zip.7z");
        ZipHelper.execute(this, "7zr x " + src.getAbsolutePath() + " -o" + out.getAbsolutePath(),
                new ZipHelper.OnResultListener() {
                    @Override
                    public void onSuccess(String msg) {
                        Log.e(TAG, "执行成功");
                    }

                    @Override
                    public void onFailure(int errorno, String msg) {
                        Log.e(TAG, "执行失败:");
                        Log.e(TAG, " 错误代码:" + errorno);
                        Log.e(TAG, " 错误输出:" + msg);
                    }

                    @Override
                    public void onProgress(String msg) {
                        Log.e(TAG, "正在执行:" + msg);
                    }
                });
    }
}
