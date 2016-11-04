package cn.gavinliu.glidestackblur;

import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import cn.gavinliu.android.lib.glide.stackblur.GlideStackBlurTransformation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.image);
        ImageView imageView2 = (ImageView) findViewById(R.id.image2);
        ImageView imageView3 = (ImageView) findViewById(R.id.image3);

        String url = "http://img.xiami.net/images/upload/10-27/186947296.jpg";
        int imageSize = getResources().getDimensionPixelSize(R.dimen.image_size);
        ColorDrawable placeHolder = new ColorDrawable(0xFFDEDEDE);

        Glide.with(this).load(url)
                .placeholder(placeHolder)
                .override(imageSize, imageSize)
                .centerCrop()
                .into(imageView);

        Glide.with(this).load(url)
                .placeholder(placeHolder)
                .override(imageSize, imageSize)
                .centerCrop()
                .transform(new GlideStackBlurTransformation(this))
                .into(imageView2);

        Glide.with(this).load(url)
                .placeholder(placeHolder)
                .override(imageSize, imageSize)
                .centerCrop()
                .transform(new GlideStackBlurTransformation(this))
                .transform(new GlideStackBlurTransformation(this, new GlideStackBlurTransformation.TintColorGenerator() {
                    @Override
                    public int generate(Bitmap bitmap) {
                        return 0x803F51B5;
                    }

                    @Override
                    public String getId() {
                        return ".TintColorGenerator";
                    }
                }))
                .into(imageView3);
    }
}
