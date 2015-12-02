package com.dreamfactory.kurtishu.pretty.utils;

import java.util.HashSet;
import java.util.List;

/**
 * Created by kurtishu on 12/1/15.
 */
public class ArrayUtil {

    public static<T> void removeDuplicate(List<T> list)   {
        if (null == list || list.size() == 0) {
            return;
        }
        HashSet<T> h  =   new HashSet<T>(list);
        list.clear();
        list.addAll(h);
    }
}
