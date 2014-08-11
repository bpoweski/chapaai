package com.derbysoft.chapaai.adapter.pusher.service;

import com.derbysoft.synchronizer.dto.ChangesDTO;

import java.util.List;

public interface FilterService {

     List<ChangesDTO> filter(List<ChangesDTO> changesDTOs);

}
