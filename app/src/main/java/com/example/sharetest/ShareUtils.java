package com.example.sharetest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;

import androidx.core.app.ShareCompat;

public class ShareUtils {

    public final static String FACEBOOK_PKG = "com.facebook.katana";
    public final static String TWITTER_PKG = "com.twitter.android";
    public final static String PINTEREST_PKG = "com.pinterest";
    public final static String INSTAGRAM_PKG = "com.instagram.android";
    public final static String LINE_PKG = "jp.naver.line.android";
    public final static String WHATSAPP_PKG = "com.whatsapp";
    public final static String TELEGRAM_PKG = "org.telegram.messenger";
    public final static String MESSAGER_PKG = "com.facebook.orca";

    /**
     * Intent to send a telegram message
     *
     * @param msg
     */
    static void intentMessageTelegram(Activity activity, String msg, Uri uri) {
        final String appName = "org.telegram.messenger";
        Intent myIntent =  ShareCompat.IntentBuilder.from(activity)
                .setType("text/html")
                .setHtmlText("<a href='https://t.me/WebAuthTestBot'>邀请文案</a>")
                .getIntent();

        myIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("<a href='https://t.me/WebAuthTestBot'>邀请文案</a>"));

        myIntent.setPackage(appName);

//        myIntent.putExtra(Intent.EXTRA_STREAM,uri);
//        myIntent.setType("image/*");

//        myIntent.putExtra(Intent.EXTRA_TITLE, "title test");
//        myIntent.putExtra(Intent.EXTRA_SUBJECT, "subject test");
//        myIntent.putExtra(Intent.EXTRA_HTML_TEXT, "<a href='https://t.me/WebAuthTestBot'>邀请文案</a>");
//        myIntent.setType("text/plain");
        activity.startActivity(Intent.createChooser(myIntent, "Share with"));
    }


    static void intentMessageWithText(Activity activity, String msg, String pkg){
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        shareIntent.setType("text/plain");
        shareIntent.setPackage(pkg);
        shareIntent.putExtra(Intent.EXTRA_TEXT, msg);
        activity.startActivity(Intent.createChooser(shareIntent, "Share with"));
    }

    static void intentMessageWithPicture(Activity activity, String msg, String pkg, Uri uri){
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        shareIntent.putExtra(Intent.EXTRA_STREAM,uri);
        shareIntent.setType("image/*");
        shareIntent.setPackage(pkg);
        shareIntent.putExtra(Intent.EXTRA_TEXT, msg);
        shareIntent.setType("text/plain");
        activity.startActivity(Intent.createChooser(shareIntent, "Share with"));
    }



}
