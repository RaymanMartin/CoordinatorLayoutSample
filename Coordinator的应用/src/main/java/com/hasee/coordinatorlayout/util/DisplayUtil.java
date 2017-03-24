package com.hasee.coordinatorlayout.util;

import android.app.Activity;
import android.content.Context;

/**
 * Created by Rayman on 2016/10/10.
 */
public class DisplayUtil {

    private static final String TAG = "DisplayUtil";
    private Activity activity;

    public DisplayUtil() {
    }

    public DisplayUtil(Activity activity) {
        this.activity = activity;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px
     */
    public static int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (scale * dipValue + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
