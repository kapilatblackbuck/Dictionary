package com.kapil.sample;

import android.support.annotation.IntDef;

/**
 * Created by kapilsharma on 12/07/17.
 */

@IntDef({
        ViewType.LOADER,
        ViewType.PRODUCT

})
public @interface ViewType {
    int LOADER = 0;
    int PRODUCT = 1;

}
