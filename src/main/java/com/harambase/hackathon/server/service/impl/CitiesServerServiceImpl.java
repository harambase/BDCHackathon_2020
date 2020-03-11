package com.harambase.hackathon.server.service.impl;

import com.harambase.hackathon.common.ResultMap;
import com.harambase.hackathon.common.support.util.ReturnMsgUtil;
import com.harambase.hackathon.server.dao.repository.CitiesRepository;
import com.harambase.hackathon.server.pojo.base.Cities;
import com.harambase.hackathon.server.service.CitiesServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CitiesServerServiceImpl implements CitiesServerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CitiesRepository citiesRepository;

    @Autowired
    public CitiesServerServiceImpl(CitiesRepository citiesRepository) {
        this.citiesRepository = citiesRepository;
    }

    @Override
    public ResultMap get(int id) {
        try {
            Optional<Cities> opC = citiesRepository.findById(id);
            return opC.map(ReturnMsgUtil::success).orElseGet(ReturnMsgUtil::fail);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap getListByRegionId(int regionId) {
        try {
            List<Cities> cities = citiesRepository.findByRegionId(regionId);
            return ReturnMsgUtil.success(cities);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }
}
