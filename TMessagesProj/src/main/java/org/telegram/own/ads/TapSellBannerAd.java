package org.telegram.own.ads;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import org.telegram.messenger.R;
import org.telegram.ui.Components.LayoutHelper;

import ir.tapsell.sdk.bannerads.TapsellBannerType;
import ir.tapsell.sdk.bannerads.TapsellBannerView;
import ir.tapsell.sdk.bannerads.TapsellBannerViewEventListener;

public class TapSellBannerAd {

    public static final String VIDEO_BANNER_ZONE_ID = "60336999d5eec2000138bfb6";
    private final TapsellBannerView tapsellBannerView;
    private final ImageView closeButtonImageView;

    public TapSellBannerAd(Context context) {
        Log.d("tttt", "TapSellBannerAd: ");
        tapsellBannerView = new TapsellBannerView(context, TapsellBannerType.BANNER_320x50, VIDEO_BANNER_ZONE_ID);
        closeButtonImageView = new ImageView(context);
        closeButtonImageView.setImageResource(R.drawable.ic_close_white);
        tapsellBannerView.addView(closeButtonImageView, LayoutHelper.createFrame(20, 20, Gravity.TOP | Gravity.END));
        closeButtonImageView.bringToFront();
        closeButtonImageView.setVisibility(View.INVISIBLE);
        setListeners();
    }

    public TapsellBannerView getBannerAd() {
        return tapsellBannerView;
    }

    private void setListeners() {

        tapsellBannerView.setEventListener(new TapsellBannerViewEventListener() {
            @Override
            public void onNoAdAvailable() {
            }

            @Override
            public void onNoNetwork() {
            }

            @Override
            public void onError(String s) {
            }

            @Override
            public void onRequestFilled() {
                closeButtonImageView.setVisibility(View.VISIBLE);
                tapsellBannerView.showBannerView();
                Log.d("tttt", "onRequestFilled");
            }

            @Override
            public void onHideBannerView() {
                closeButtonImageView.setVisibility(View.GONE);
                Log.d("tttt", "onHideBannerView");
            }
        });

        closeButtonImageView.setOnClickListener(v -> {
            Log.d("tttt", "onClick: ");
            tapsellBannerView.setVisibility(View.GONE);
        });
    }
}
