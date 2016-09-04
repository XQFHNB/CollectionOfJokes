package practice.software.xqf.collectionofjokes.net;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by dell on 2016/9/4.
 */
public class Loader_AsyncImage {
    private HashMap<String, SoftReference<Drawable>> imageCacheHashMap;

    public Loader_AsyncImage() {
        // TODO Auto-generated constructor stub
        imageCacheHashMap = new HashMap<String, SoftReference<Drawable>>();
    }

    /**
     * 同样是回调，看看人家是怎么用的，怎么在具体的非构造方法中使用回调
     *
     * @param url 传入图片地址
     * @param sucessCallback 回调
     * @return
     */
    public Drawable load_From_Url(final String url,
                                  final OnSucessCallback sucessCallback) {
        if (imageCacheHashMap.containsKey(url)) {
            SoftReference<Drawable> softReference = imageCacheHashMap.get(url);
            Drawable drawable = softReference.get();
            if (drawable != null) {
                return drawable;
            }

        }
        final Handler handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                Drawable drawable = (Drawable) msg.obj;
                sucessCallback.OnSuccess(url, drawable);

            }

            ;
        };
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    InputStream inputStream = new URL(url).openStream();
                    Drawable drawable = Drawable.createFromStream(inputStream,
                            null);
                    imageCacheHashMap.put(url, new SoftReference<Drawable>(
                            drawable));
                    Message message = handler.obtainMessage(0, drawable);
                    handler.sendMessage(message);
                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
        return null;
    }

    public static interface OnSucessCallback {
        void OnSuccess(String url, Drawable drawable);
    }
}
