package com.ichoice.egan.eganview.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 图片下载工具类
 *
 * @author yanshaokun@ichoice.cc
 * @data 2015/11/4.
 */
public class ImageLoaderUtil {

    /*************************************************************************
     *********************  获取单例  ******************************************
     *************************************************************************/

    private static ImageLoaderUtil imageLoaderUtil = new ImageLoaderUtil();

    /**
     * 获取单例
     *
     * @return
     */
    public static ImageLoaderUtil getSingleImageLoaderutil() {
        return imageLoaderUtil;
    }

    /*************************************************************************
     **********************  缓存  ********************************************
     *************************************************************************/
    /**
     * 取手机空闲内存的1/8来用做缓存图片
     */
    private LruCache<String, Bitmap> bitmapLruCache = new LruCache<String, Bitmap>(((int) (Runtime.getRuntime().freeMemory()/1024)) / 10) {
        @Override
        protected int sizeOf(String key, Bitmap value) {
            // 重写此方法来衡量每张图片的大小，默认返回图片数量。
//            return super.sizeOf(key, value);
            return value.getRowBytes() * value.getHeight() / 1024;
        }
    };

    /*************************************************************************
     ********************* 异步开启线程进行图片下载  ******************************
     *************************************************************************/

    private ImageView imageView;
    private String imageUrl;

    /**
     * 为控件设置图片
     *
     * @param imageUrl
     * @param imageView
     */
    public void setBitmap(String imageUrl, ImageView imageView) {
        this.imageView = imageView;
        this.imageUrl = imageUrl;
        downLoadBitmap();
        //先从缓存中去取，如果没有再启动异步去下载
        Bitmap bitmap = bitmapLruCache.get(imageUrl);
        if (bitmap == null)
            downLoadBitmap();
        else
            imageView.setImageBitmap(bitmap);
    }

    /**
     * 启动异步任务，进行下载图片
     */
    private void downLoadBitmap() {
        new BitmapAsynTask().execute(imageUrl);
    }

    class BitmapAsynTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            try {
//                URL url = new URL(params[0]);
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                if (connection.getResponseCode() == 200) {
//                    bitmap = BitmapFactory.decodeStream(connection.getInputStream());
//                }
                bitmap = BitmapFactory.decodeStream(new URL(params[0]).openStream());
                //将下载好的图片，放入到缓存中，以便于下一次使用
                bitmapLruCache.put(imageUrl, bitmap);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }

}
