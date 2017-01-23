package demo.techtime.com.fcmdemo;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

/**
 * Created by Ashwin Ukey on 7/01/17.
 */
public class MyApp extends MultiDexApplication{

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private static MyApp instance = null;

    public MyApp()
    {
        instance = this;
    }

    public static Context getInstance()
    {
        if (null == instance)
        {
            instance = new MyApp();
        }

        return instance;
    }
}
