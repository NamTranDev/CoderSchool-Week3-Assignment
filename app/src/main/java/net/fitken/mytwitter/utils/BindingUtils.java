package net.fitken.mytwitter.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import net.fitken.mytwitter.R;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by Ken on 2/17/2017.
 */

public class BindingUtils {
    @BindingAdapter("imgUrlWithTransform")
    public static void loadImageUrlWithTransform(ImageView imageView, String imgUrl) {
        if (imgUrl.isEmpty()) {
            imageView.setImageResource(R.drawable.image_not_found);
            return;
        }
        Picasso.with(imageView.getContext()).load(imgUrl)
                .transform(new RoundedCornersTransformation(10, 0))
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.image_not_found)
                .into(imageView);
    }

    @BindingAdapter("imgUrl")
    public static void loadImageUrl(ImageView imageView, String imgUrl) {
        if (imgUrl.isEmpty()) {
            imageView.setImageResource(R.drawable.image_not_found);
            return;
        }
        Picasso.with(imageView.getContext()).load(imgUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.image_not_found)
                .into(imageView);
    }
}

