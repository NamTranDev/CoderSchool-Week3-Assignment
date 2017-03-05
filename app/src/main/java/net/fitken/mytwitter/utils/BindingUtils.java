package net.fitken.mytwitter.utils;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import net.fitken.mytwitter.R;
import net.fitken.mytwitter.models.TweetModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;


/**
 * Created by Ken on 2/17/2017.
 */

public class BindingUtils {
    @BindingAdapter("imgUrlProfile")
    public static void loadImageProfile(RoundedImageView imageView, String imgUrl) {
        if (imgUrl.isEmpty()) {
            imageView.setImageResource(R.drawable.image_not_found);
            return;
        }
        if (imgUrl.contains("_normal")) {
            imgUrl = imgUrl.replace("_normal", "");
        }
        Glide.with(imageView.getContext()).load(imgUrl)
                .asBitmap()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.image_not_found)
                .into(imageView);
    }

    @BindingAdapter("imgUrlContent")
    public static void loadImageUrlContent(RoundedImageView imageView, TweetModel tweetModel) {
        if (tweetModel.getEntities().getMedia() == null || tweetModel.getEntities().getMedia().isEmpty()) {
            imageView.setVisibility(View.GONE);
            return;
        }
        imageView.setVisibility(View.VISIBLE);
        String imgUrl;
        List<TweetModel.EntitiesModel.MediaModel> listMedia = tweetModel.getEntities().getMedia();
        imgUrl = listMedia.get(0).getMediaUrl();
        Glide.with(imageView.getContext()).load(imgUrl)
                .asBitmap()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.image_not_found)
                .into(imageView);
    }

    @BindingAdapter("relativeTimeAgo")
    public static void getRelativeTimeAgo(TextView textView, String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getTimeAgo(dateMillis);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        textView.setText(relativeDate);
    }
}

