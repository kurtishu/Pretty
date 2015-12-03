/*
 *
 *  * Copyright 2015 Kurtis <kurtis_hu@hotmail.com>
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

package com.dreamfactory.kurtishu.pretty.view.activity;

import com.dreamfactory.kurtishu.pretty.event.NavigatorEvent;
import com.dreamfactory.kurtishu.pretty.view.base.ActivityPresenter;
import com.dreamfactory.kurtishu.pretty.view.delegate.AboutDelegate;

import de.greenrobot.event.EventBus;

public class AboutActivity extends ActivityPresenter<AboutDelegate> {


    @Override
    protected Class<AboutDelegate> getDeletageClass() {
        return AboutDelegate.class;
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(NavigatorEvent event) {
        finish();
    }
}
