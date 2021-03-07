package org.telegram.own.ads;

import android.content.Context;
import android.util.Log;

import ir.tapsell.sdk.bannerads.TapsellBannerType;
import ir.tapsell.sdk.bannerads.TapsellBannerView;
import ir.tapsell.sdk.bannerads.TapsellBannerViewEventListener;

public class TapSellBannerAd {

    public static final String LIST_ZONE_ID = "60336999d5eec2000138bfb6";

    public static TapsellBannerView getAd(Context context) {

        TapsellBannerView tapsellBannerView = new TapsellBannerView(context, TapsellBannerType.BANNER_320x50, LIST_ZONE_ID);
        tapsellBannerView.loadAd(context, LIST_ZONE_ID, TapsellBannerType.BANNER_320x50);
        tapsellBannerView.setEventListener(new TapsellBannerViewEventListener() {
            @Override
            public void onNoAdAvailable() {
                Log.d("tttt", "onNoAdAvailable");
            }

            @Override
            public void onNoNetwork() {
                Log.d("tttt", "onNoNetwork");
            }

            @Override
            public void onError(String s) {
                Log.d("tttt", "0" + s);
            }

            @Override
            public void onRequestFilled() {
                Log.d("tttt", "onRequestFilled");
            }

            @Override
            public void onHideBannerView() {
                Log.d("tttt", "onHideBannerView");
            }
        });
        tapsellBannerView.showBannerView();
        return tapsellBannerView;
    }
}
