package org.telegram.own.ui.cell;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

import ir.tapsell.sdk.TapsellAdRequestListener;
import ir.tapsell.sdk.nativeads.TapsellNativeBanner;
import ir.tapsell.sdk.nativeads.TapsellNativeBannerManager;
import ir.tapsell.sdk.nativeads.TapsellNativeBannerViewManager;

public class NativeDialogAdCell extends FrameLayout {

    public static final String LIST_NATIVE_ZONE_ID = "603a3611dbf667000160319f";
    private TapsellNativeBannerViewManager nativeBannerViewManager;
    private String adId;

    public NativeDialogAdCell(@NonNull Context context) {
        super(context);
        this.setLayoutParams(LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, LayoutHelper.WRAP_CONTENT, Gravity.CENTER));
        this.setPadding(0, 16, 16, 0);
        setAdView(context);
        requestAd(context);
    }

    private void setAdView(Context context) {
        nativeBannerViewManager = new TapsellNativeBannerManager
                .Builder()
                .setParentView(this)
                .setContentViewTemplate(R.layout.test)
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
                        initUI();
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

    private void initUI() {
        TextView titleTextView = findViewById(R.id.tapsell_nativead_title);
        titleTextView.setTextColor(Theme.getColor(Theme.key_chats_message));
        TextView descriptionTextView = findViewById(R.id.tapsell_nativead_description);
        descriptionTextView.setTextColor(Theme.getColor(Theme.key_chats_message));
        FrameLayout divider = findViewById(R.id.ad_divider);
        divider.setBackgroundColor(Theme.getColor(Theme.key_divider));
        TextView sponsoredTextView = findViewById(R.id.tapsell_nativead_sponsored);
        sponsoredTextView.setBackgroundColor(Theme.getColor(Theme.key_dialogRoundCheckBox));
        sponsoredTextView.setTextColor(Theme.getColor(Theme.key_dialogRoundCheckBoxCheck));
        Log.d("tttt", "configureUI: ");
    }
}
