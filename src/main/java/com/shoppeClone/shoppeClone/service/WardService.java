package com.shoppeClone.shoppeClone.service;

import java.util.List;
import java.util.Map;

import com.shoppeClone.shoppeClone.dto.PageDTO;
import com.shoppeClone.shoppeClone.dto.ward.WardDTO;

public interface WardService {
    WardDTO createWard(WardDTO dto);

    WardDTO updateWard(Long wardId, WardDTO dto);

    void deleteWard(Long wardId);

    List<WardDTO> getAll();

    PageDTO<WardDTO> getWards(Map<String, String> params);

    WardDTO getWardByWardId(Long wardId);
}
