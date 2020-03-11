package com.harambase.hackathon.server.service;

import com.harambase.hackathon.common.ResultMap;

public interface CitiesServerService {
    ResultMap get(int id);

    ResultMap getListByRegionId(int regionId);
}
