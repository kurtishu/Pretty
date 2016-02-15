/*
 *
 *  * Copyright 2016 Kurtis <kurtis_hu@hotmail.com>
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.dreamfactory.kurtishu.pretty.widget;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.dreamfactory.kurtishu.pretty.widget.zoomable.ZoomableDraweeView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;

/**
 * Author：kurtishu on 2/15/16
 * Eevery one should have a dream, what if one day it comes true!
 */
public class PhotoImageView extends ZoomableDraweeView {


    public PhotoImageView(Context context) {
        super(context);
    }

    public PhotoImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PhotoImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Displays an image given by the uri.
     *
     * @param uri uri of the image
     * @undeprecate
     */
    @Override
    public void setImageURI(Uri uri) {
        setImageURI(uri, null);
    }

    /**
     * Displays an image given by the uri.
     *
     * @param uri uri of the image
     * @param callerContext caller context
     */
    public void setImageURI(Uri uri, @Nullable Object callerContext) {

        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(getResources())
                .setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER)
                .setProgressBarImage(new ProgressBarDrawable())
                .build();

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setCallerContext(callerContext)
                .setTapToRetryEnabled(false)
                .setUri(uri)
                .setOldController(getController())
                .build();
        setController(controller);
        setHierarchy(hierarchy);
    }
}
