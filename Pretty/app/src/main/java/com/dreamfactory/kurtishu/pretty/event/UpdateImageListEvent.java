package com.dreamfactory.kurtishu.pretty.event;

import com.dreamfactory.kurtishu.pretty.api.entity.SearchEntity;

/**
 * Created by kurtishu on 12/1/15.
 */
public class UpdateImageListEvent {

    private final SearchEntity searchEntity;
    private boolean shouldReload = false;


    public UpdateImageListEvent(boolean shouldReload, SearchEntity searchEntity) {
        this.shouldReload = shouldReload;
        this.searchEntity = searchEntity;
    }

    public SearchEntity getSearchEntity() {
        return searchEntity;
    }

    public boolean isShouldReload() {
        return shouldReload;
    }
}
