package cn.gavinliu.android.lib.glide.stackblur;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * 图片处理工具类
 * <p/>
 * Created by Gavin on 16/3/12.
 */
class BitmapUtils {

    /**
     * 裁剪图片，传入一个Bitmap对象，宽高和裁剪方式，返回一个新的Bitmap
     *
     * @param bitmap     Bitmap
     * @param width      int
     * @param height     int
     * @param scaleToFit ScaleToFit
     * @return Bitmap
     */
    public static Bitmap catBitmap(Bitmap bitmap, int width, int height, ScaleToFit scaleToFit, boolean recycle) {
        if (scaleToFit == ScaleToFit.FILL) {
            return bitmap;
        }

        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);

        int bmWidth = bitmap.getWidth();
        int bmHeight = bitmap.getHeight();

        int left = 0;
        int top = 0;
        int right = bmWidth;
        int bottom = bmHeight;

        int compareWidth = width * bmHeight;
        int compareHeight = height * bmWidth;

        int srcWidth = bmWidth;
        int srcHeight = bmHeight;

        if (compareHeight > compareWidth) { // 固定高，计算宽
            srcWidth = Math.round((float) compareWidth / height);

            switch (scaleToFit) {
                case START:
                    right = srcWidth;
                    break;
                case CENTER:
                    left = (bmWidth - srcWidth) / 2;
                    right = left + srcWidth;
                    break;
                case END:
                    left = srcWidth;
                    break;
            }

        } else { // 固定宽，计算高
            srcHeight = Math.round((float) compareHeight / width);

            switch (scaleToFit) {
                case START:
                    bottom = srcHeight;
                    break;
                case CENTER:
                    top = (bmHeight - srcHeight) / 2;
                    bottom = top + srcHeight;
                    break;
                case END:
                    top = bmHeight - srcHeight;
                    break;
            }
        }

        final Rect rect = new Rect(left, top, right, bottom);
        final RectF rectF = new RectF(0, 0, width, height);

        canvas.drawBitmap(bitmap, rect, rectF, paint);

        if (recycle) {
            bitmap.recycle();
            bitmap = null;
        }

        return result;
    }

}
