package com.ichoice.egan.eganview.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.maychoo.meifou.R;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by 刘大军 on 2015/12/10.
 */
public class Bimp {
	public static int max = 0;
	public static boolean act_bool = true;
	public static List<Bitmap> bmp = new ArrayList<Bitmap>();	
	
	//图片sd地址  上传服务器时把图片调用下面方法压缩后 保存到临时文件夹 图片压缩后小于100KB，失真度不明显
	public static List<String> drr = new ArrayList<String>();
	

	public static Bitmap revitionImageSize(String path) throws IOException {
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				new File(path)));
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeStream(in, null, options);
		in.close();
		int i = 0;
		Bitmap bitmap = null;
		while (true) {
			if ((options.outWidth >> i <= 1000)
					&& (options.outHeight >> i <= 1000)) {
				in = new BufferedInputStream(
						new FileInputStream(new File(path)));
				options.inSampleSize = (int) Math.pow(2.0D, i);
				options.inJustDecodeBounds = false;
				bitmap = BitmapFactory.decodeStream(in, null, options);
				break;
			}
			i += 1;
		}
		return bitmap;
	}
	// 下载图片
	private static void downAndSetImage(ImageView v, String imagePath) {
		ImageOptions.Builder builder = new ImageOptions.Builder();
		builder.setLoadingDrawableId(R.mipmap.mflogo_gray);
		builder.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
		builder.setFailureDrawableId(R.mipmap.mflogo_gray);
		x.image().bind(v, imagePath, builder.build());
	}

	// 下载图片设置为圆角图片
	private static void downAndSetCircleImage(ImageView v, String imagePath) {
		ImageOptions.Builder builder = new ImageOptions.Builder();
		builder.setCircular(true);
		builder.setLoadingDrawableId(R.mipmap.mflogo_gray);
		builder.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
		builder.setFailureDrawableId(R.mipmap.mflogo_gray);
		x.image().bind(v, imagePath, builder.build());
	}
}
