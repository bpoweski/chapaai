package com.derbysoft.chapaai.adapter.pusher.application.service.mergeservice;


import com.derbysoft.chapaai.adapter.pusher.application.service.MergeService;
import com.derbysoft.chapaai.adapter.pusher.domain.stay.StayAvailability;

import java.util.List;

public abstract class AbstractMergeService implements MergeService{

    private MergeService next;

    @Override
    public final List<StayAvailability> merge(List<StayAvailability> stayAvailabilities) {
        if(hasNext()){
            return next.merge(doMerge(stayAvailabilities));
        }
        return doMerge(stayAvailabilities);
    }

    public abstract List<StayAvailability> doMerge(List<StayAvailability> stayAvailabilities);

    public boolean hasNext(){
        return next == null;
    }


    public MergeService getNext() {
        return next;
    }

    public void setNext(MergeService next) {
        this.next = next;
    }
}
