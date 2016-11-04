package cn.gavinliu.android.lib.glide.stackblur;

import android.graphics.Bitmap;

/**
 * Created by Gavin on 16/3/12.
 */
class BlurUtils {

    private Bitmap _image;

    private Bitmap _result;

    private int width, height;

    public BlurUtils(Bitmap image) {
        width = image.getWidth();
        height = image.getHeight();

        _image = scaleBitmap(image);
    }

    public Bitmap process(int radius) {
        _result = blur(_image, radius);
        return _result;
    }

    public Bitmap getBitmap() {
        return _image;
    }

    public Bitmap getBluredBitmap() {
        return _result;
    }

    public void recycle() {
        if (_image != null) {
            _image.recycle();
            _image = null;
        }
        if (_result != null) {
            _result.recycle();
            _result = null;
        }
    }

    private Bitmap scaleBitmap(Bitmap bitmap) {
        float ratio = 0;
        int dstWidth = 0, dstHeight = 0;
        if (width > height) {
            ratio = height / (float) width;
            dstWidth = 200;
            dstHeight = (int) (dstWidth * ratio);
        } else {
            ratio = width / (float) height;
            dstHeight = 200;
            dstWidth = (int) (dstHeight * ratio);
        }
        return Bitmap.createScaledBitmap(bitmap, dstWidth, dstHeight, false);
    }

    static {
        System.loadLibrary("StackBlur");
    }

    private static native void bitmapToBlur(Bitmap bitmapOut, int radius, int threadCount, int threadIndex, int round);

    private Bitmap blur(Bitmap original, float radius) {
        Bitmap bitmapOut = original.copy(Bitmap.Config.ARGB_8888, true);

        bitmapToBlur(bitmapOut, (int) radius, 1, 0, 1);
        bitmapToBlur(bitmapOut, (int) radius, 1, 0, 2);

        return bitmapOut;
    }
}
