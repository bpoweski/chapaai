package com.derbysoft.chapaai.adapter.pusher.service;



import com.derbysoft.chapaai.adapter.pusher.domain.stay.StayAvailability;

import java.util.List;

public interface MergeService {

    List<StayAvailability> merge(List<StayAvailability> stayAvailabilities);

}
