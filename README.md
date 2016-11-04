# glide-stackblur

## Features

* stackblur for glide
* tint color on blur image

## Screenshot

![](/screenshots.png)

## Usage

### dependencies

### GlideStackBlurTransformation

```java
Glide.with(this).load(url)
        ...
        .transform(new GlideStackBlurTransformation(this))
        ...
        .into(imageView);
```

### GlideStackBlurTransformation with TintColor

```java
Glide.with(this).load(url)
        ...
        .transform(new GlideStackBlurTransformation(this, new GlideStackBlurTransformation.TintColorGenerator() {
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