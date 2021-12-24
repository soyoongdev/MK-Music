package com.example.miku_project.screens;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.media.session.MediaSessionCompat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.miku_project.R;
import com.example.miku_project.models.Product;

public class MediaNotification {

    public static final String CHANNEL_ID = "channel1";

    public static final String ACTIONPREV = "actionprev";
    public static final String CHANNEL_PLAY = "actionplay";
    public static final String CHANNEL_NEXT = "actionnext";

    public static Notification notification;

    public static void createNotification(Context context, Product product, int playbutton, int pos, int size){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
            MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(context, "tag");

//            Bitmap icon = BitmapFactory.decodeResource(context.getResources(), product.getProduct_image());
//
//            Bitmap bitmap = null;
//            bitmap = Glide.with(context)
//                    .asBitmap()
//                    .load(product.getProduct_image())
//                    .into();

            notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_music)
                    .setContentTitle(product.getProduct_name())
                    .setContentText(product.getProduct_singer())
                    .setOnlyAlertOnce(true)
                    .setShowWhen(false)
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .build();

            notificationManagerCompat.notify(1, notification);
        }
    }
}
