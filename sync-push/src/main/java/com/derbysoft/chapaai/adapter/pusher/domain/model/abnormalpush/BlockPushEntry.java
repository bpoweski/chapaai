package com.derbysoft.chapaai.adapter.pusher.domain.model.abnormalpush;

import com.derbysoft.ccs.core.MappingEntry;

public class BlockPushEntry extends AbnormalPushEntry  implements MappingEntry {

    public final static String CCS_HOTEL_CODE_NAME = "HOTEL_CODE";

    private boolean disabled;

    public boolean isDisabled() {
        return disabled;
    }

    @Override
    public void setDisabled(boolean disabled) {

    }
}
