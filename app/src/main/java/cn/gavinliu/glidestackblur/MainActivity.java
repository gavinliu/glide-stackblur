package cn.gavinliu.glidestackblur;

import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import cn.gavinliu.android.lib.glide.stackblur.GlideStackBlur;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.image);
        ImageView imageView2 = (ImageView) findViewById(R.id.image2);
        ImageView imageView3 = (ImageView) findViewById(R.id.image3);

        String url = "http://img.xiami.net/images/album/img48/3048/3714265921440496645.jpg";
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
                .transform(new CenterCrop(this), new GlideStackBlur(this))
                .into(imageView2);

        Glide.with(this).load(url)
                .placeholder(placeHolder)
                .override(imageSize, imageSize)
                .transform(new CenterCrop(this), new GlideStackBlur(this, new GlideStackBlur.TintColorGenerator() {
                    @Override
                    public int generate(Bitmap bitmap) {
                        // You can generate dynamic color
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
