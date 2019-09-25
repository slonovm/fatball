package com.fartball.zag;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GiantPillar extends Pillar {
    static Bitmap giantPillar;

    public GiantPillar(Context context) {
        super(context);
        // DisplayScoreAndShare topPillar image ressource to R.drawable.top
        this.setImageResource(R.drawable.giantpillar);
        // Generating the bitmap of GiantPillar
        giantPillar = BitmapFactory.decodeResource(getResources(), R.drawable.giantpillar);
    }

    public int getGiantPillarWidth() {
        return giantPillar.getWidth();
    }

    public int getGiantPillarHeight() {
        return giantPillar.getHeight();
    }
}
