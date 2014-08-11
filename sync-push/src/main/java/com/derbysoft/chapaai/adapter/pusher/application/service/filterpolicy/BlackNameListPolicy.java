package com.derbysoft.chapaai.adapter.pusher.application.service.filterpolicy;

import com.derbysoft.chapaai.adapter.pusher.application.service.FilterPolicy;
import com.derbysoft.chapaai.adapter.pusher.domain.repository.AbnormalPushEntryRepository;
import com.derbysoft.synchronizer.dto.ChangesDTO;

public class BlackNameListPolicy implements FilterPolicy {

    private AbnormalPushEntryRepository abnormalPushEntryRepository;

    @Override
    public boolean accept(ChangesDTO changesDTO) {

        return false;
    }
}
