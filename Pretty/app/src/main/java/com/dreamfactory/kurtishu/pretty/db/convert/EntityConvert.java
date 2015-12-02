package com.dreamfactory.kurtishu.pretty.db.convert;

import com.dreamfactory.kurtishu.pretty.db.entity.ClasslfyEntity;
import com.dreamfactory.kurtishu.pretty.model.Galleryclass;

/**
 * Created by kurtishu on 12/1/15.
 */
public class EntityConvert {

    public static void toDBEntity(ClasslfyEntity classlfyEntity, Galleryclass galleryclass) {
        classlfyEntity.setId(galleryclass.id);
        classlfyEntity.setName(galleryclass.name);
        classlfyEntity.setTitle(galleryclass.title);
        classlfyEntity.setDescription(galleryclass.description);
        classlfyEntity.setSeq(galleryclass.seq);
    }
}
