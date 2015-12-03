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

package com.dreamfactory.kurtishu.pretty.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created by kurtishu on 12/3/15.
 */
public class  UniqueListArray <T extends Comparable<? super T>> extends ArrayList<T> {

    public UniqueListArray() {

    }

    public UniqueListArray(Collection<? extends T> collection) {
        this.addAll(collection);
    }

    @Override
    public boolean add(T object) {

        if (!contains(object)) {
            return super.add(object);
        }

        return false;
    }

    public boolean addAllWithSort(Collection<? extends T> collection) {

        boolean result = false;
        Iterator<? extends T> it = collection.iterator();
        while (it.hasNext()) {
            if (add(it.next())) {
                result = true;
            }
        }
        return result;
    }

    public void sort() {
        Collections.sort(this);
    }
}
