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

package com.dreamfactory.kurtishu.pretty.widget.zoomable;

/**
 * Author：kurtishu on 2/15/16
 * Eevery one should have a dream, what if one day it comes true!
 */

import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.MotionEvent;

/**
 * Interface for implementing a controller that works with {@link ZoomableDraweeView}
 * to control the zoom.
 */
public interface ZoomableController {

    /**
     * Listener interface.
     */
    public interface Listener {

        /**
         * Notifies the view that the transform changed.
         *
         * @param transform the new matrix
         */
        void onTransformChanged(Matrix transform);
    }

    /**
     * Enables the controller. The controller is enabled when the image has been loaded.
     *
     * @param enabled whether to enable the controller
     */
    void setEnabled(boolean enabled);

    /**
     * Gets whether the controller is enabled. This should return the last value passed to
     * {@link #setEnabled}.
     *
     * @return whether the controller is enabled.
     */
    boolean isEnabled();

    /**
     * Sets the listener for the controller to call back when the matrix changes.
     *
     * @param listener the listener
     */
    void setListener(Listener listener);

    /**
     * Gets the current scale factor. A convenience method for calculating the scale from the
     * transform.
     *
     * @return the current scale factor
     */
    float getScaleFactor();

    /**
     * Gets the current transform.
     *
     * @return the transform
     */
    Matrix getTransform();

    /**
     * Sets the bounds of the image post transform prior to application of the zoomable
     * transformation.
     *
     * @param imageBounds the bounds of the image
     */
    void setImageBounds(RectF imageBounds);

    /**
     * Sets the bounds of the view.
     *
     * @param viewBounds the bounds of the view
     */
    void setViewBounds(RectF viewBounds);

    /**
     * Allows the controller to handle a touch event.
     *
     * @param event the touch event
     * @return whether the controller handled the event
     */
    boolean onTouchEvent(MotionEvent event);
}
