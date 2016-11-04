# glide-stackblur

## Features

* stackblur for glide
* tint color on blur image

## Screenshots

![](/screenshots.png)

## Usage

### Dependencies

```
compile 'cn.gavinliu.android.lib:glide-stackblur:1.0'
```

### GlideStackBlur

```java
Glide.with(this).load(url)
        ...
        .transform(new GlideStackBlur(this))
        ...
        .into(imageView);
```

### GlideStackBlur with TintColor

```java
Glide.with(this).load(url)
        ...
        .transform(new GlideStackBlur(this, new TintColorGenerator() {
            @Override
            public int generate(Bitmap bitmap) {
                // You can use palette generate dynamic color
                return 0x803F51B5;
            }

            @Override
            public String getId() {
                return ".TintColorGenerator";
            }
        }))
        ...
        .into(imageView);
```

## License

MIT