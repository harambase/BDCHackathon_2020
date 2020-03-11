package com.harambase.hackathon.service;

import com.harambase.hackathon.common.ResultMap;
import com.harambase.hackathon.common.support.util.ReturnMsgUtil;
import com.harambase.hackathon.server.service.CitiesServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitiesService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final CitiesServerService citiesServerService;

    @Autowired
    public CitiesService(CitiesServerService citiesServerService) {
        this.citiesServerService = citiesServerService;
    }

    public ResultMap get(int id) {
        try {
            return citiesServerService.get(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    public ResultMap getListByRegionId(int regionId) {
        try {
            return citiesServerService.getListByRegionId(regionId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    public ResultMap list(int i, Integer length, String search, String order, String orderCol, String type, String status, String role) {
        try {
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }
}
