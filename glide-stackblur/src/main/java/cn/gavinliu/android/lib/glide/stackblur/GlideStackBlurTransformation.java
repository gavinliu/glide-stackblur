package cn.gavinliu.android.lib.glide.stackblur;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by Gavin on 2016/11/04.
 */

public class GlideStackBlurTransformation extends BitmapTransformation {

    private TintColorGenerator mTintColorGenerator;

    public GlideStackBlurTransformation(Context context) {
        super(context);
    }

    public GlideStackBlurTransformation(Context context, TintColorGenerator tintColorGenerator) {
        super(context);
        mTintColorGenerator = tintColorGenerator;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        if (toTransform != null) {
            toTransform = BitmapUtils.catBitmap(toTransform, outWidth, outHeight, Matrix.ScaleToFit.CENTER, true);

            BlurUtils blurManager = new BlurUtils(toTransform);
            Bitmap blur = blurManager.process(40);

            final Bitmap result = Bitmap.createBitmap(blur.getWidth(), blur.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setFilterBitmap(true);

            if (mTintColorGenerator != null) {
                int color = mTintColorGenerator.generate(toTransform);
                PorterDuffColorFilter colorFilter = new PorterDuffColorFilter((color), PorterDuff.Mode.SRC_OVER);
                paint.setColorFilter(colorFilter);
            }

            canvas.drawBitmap(blur, 0, 0, paint);

            blurManager.recycle();
            return result;
        }

        return null;
    }

    @Override
    public String getId() {
        String id = "cn.gavinliu.android.lib.glide.stackblur.GlideStackBlurTransformation";
        if (mTintColorGenerator != null) {
            id += mTintColorGenerator.getId();
        }
        return id;
    }

    public interface TintColorGenerator {
        int generate(Bitmap bitmap);

        String getId();
    }
}
