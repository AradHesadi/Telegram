package org.telegram.own.ui.cell;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.FrameLayout;

import com.google.android.exoplayer2.util.Log;

import org.telegram.own.ads.TapSellBannerAd;
import org.telegram.ui.Cells.BaseCell;
import org.telegram.ui.Components.LayoutHelper;

import ir.tapsell.sdk.bannerads.TapsellBannerView;

public class DialogAdCell extends FrameLayout {

    private TapsellBannerView tapsellBannerView;

    public DialogAdCell(Context context) {
        super(context);
        this.setLayoutParams(LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,150));
        tapsellBannerView = TapSellBannerAd.getAd(context);
        addView(tapsellBannerView, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT, Gravity.CENTER));
        this.setBackgroundColor(Color.WHITE);
        Log.d("tttt","on cell constructor"+this.getHeight()+""+tapsellBannerView.getHeight());
    }
}
