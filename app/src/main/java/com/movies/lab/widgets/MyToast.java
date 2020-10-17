package com.movies.lab.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.res.ResourcesCompat;

import com.movies.lab.R;

/**
 * Custom Toast
 * <p>
 * How To Use:
 * 1. MyToast myToast = new MyToast(context);
 * myToast.setTextAndShow(); Note: You can choose the method you need
 * myToast.close();
 * <p>
 * 2. MyToast.getInstance(context).setTextAndShow(); Note: You can choose the method you need
 * This method cannot cancel，If need to cancel toast, Please use method 1
 * PWToast.getInstance(context).close();
 */
public class MyToast extends Toast implements Handler.Callback {

    private static final int DISMISS_TOAST = 111;
    private Toast toast;
    private TextView textView;
    private Handler handler;//If you want to use the timer, it may cause a memory leak, you need a cancel timer. Therefore, the handler is used here
    private static volatile MyToast myToast;

    public static MyToast getInstance(Context context) {
        if (myToast == null) {
            synchronized (MyToast.class) {
                if (myToast == null) {
                    myToast = new MyToast(context);
                }
            }
        }
        return myToast;
    }

    private MyToast(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        toast = new Toast(context);
        handler = new Handler(this);
        //获取LayoutInflater对象
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Create a View object from the layout file
        textView = (TextView) inflater.inflate(R.layout.widget_toast, null);
        toast.setView(textView);
        toast.setGravity(Gravity.CENTER, 0, 0);
    }

    /**
     * Show toast with a string and given time
     *
     * @param str
     * @param time
     */
    public void setTextAndShow(String str, int time) {
        textView.setText(str);
        setTimeAndShow(time);
    }

    /**
     * Show toast with string res and give time
     *
     * @param stringId
     * @param time
     */
    public void setTextAndShow(int stringId, int time) {
        if (stringId == 0) {
            textView.setText("default toast");
        } else {
            textView.setText(stringId);
        }
        setTimeAndShow(time);
    }

    /**
     * Show toast, default LENGTH_SHORT time
     *
     * @param str
     */
    public void setTextAndShow(String str) {
        textView.setText(str);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * Show toast, default LENGTH_SHORT time
     *
     * @param stringId
     */
    public void setTextAndShow(int stringId) {
        if (stringId == 0) {
            textView.setText("default toast");
        } else {
            textView.setText(stringId);
        }
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * Show toast, default LENGTH_LONG time
     *
     * @param str
     */
    public void setLongToastText(String str) {
        if (str.length() == 0) {
            return;
        }
        textView.setText(str);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }

    /**
     * Show toast, default LENGTH_LONG time
     *
     * @param stringId
     */
    public void setLongToastText(int stringId) {
        if (stringId == 0) {
            textView.setText("default toast");
        } else {
            textView.setText(stringId);
        }
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }


    /**
     * Cancel toast
     */
    public void cancelShow() {
        if (toast != null) {
            toast.cancel();
        }
    }

    /**
     * Set time
     *
     * @param time
     */
    private void setTimeAndShow(int time) {
        if (time < 1000) {//At least 1000ms
            time = 1000;
        }
        toast.show();
        handler.sendEmptyMessageDelayed(DISMISS_TOAST, time);
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (toast != null) {
            toast.cancel();
        }
        return false;
    }
}
