package org.telegram.own.ui.cell;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import org.telegram.messenger.R;
import org.telegram.ui.Components.LayoutHelper;

import ir.tapsell.sdk.TapsellAdRequestListener;
import ir.tapsell.sdk.nativeads.TapsellNativeBannerManager;
import ir.tapsell.sdk.nativeads.TapsellNativeBannerViewManager;

public class NativeDialogAdCell extends FrameLayout {

    public static final String LIST_NATIVE_ZONE_ID = "603a3611dbf667000160319f";
    private TapsellNativeBannerViewManager nativeBannerViewManager;
    private String adId;

    public NativeDialogAdCell(@NonNull Context context) {
        super(context);
        this.setLayoutParams(LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, LayoutHelper.WRAP_CONTENT, Gravity.CENTER));
        this.setPadding(0,16,16,0);
        setAdView(context);
        requestAd(context);
    }

    private void setAdView(Context context) {
        nativeBannerViewManager = new TapsellNativeBannerManager
                .Builder()
                .setParentView(this)
                .setContentViewTemplate(R.layout.test2)
                .inflateTemplate(context);
    }

    private void requestAd(Context context) {
        TapsellNativeBannerManager.getAd(context, LIST_NATIVE_ZONE_ID,
                new TapsellAdRequestListener() {
                    @Override
                    public void onAdAvailable(String s) {
                        super.onAdAvailable(s);
                        Log.d("tttt", s);
                        adId = s;
                        showAd(context);
                    }

                    @Override
                    public void onError(String s) {
                        super.onError(s);
                    }
                });
    }

    private void showAd(Context context) {
        TapsellNativeBannerManager.bindAd(
                context,
                nativeBannerViewManager,
                LIST_NATIVE_ZONE_ID,
                adId);
        Log.d("tttt", "showAd: ");
    }
}
