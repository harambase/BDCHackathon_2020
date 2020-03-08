package com.harambase.hackathon.service;

import com.harambase.hackathon.common.ResultMap;
import com.harambase.hackathon.common.support.util.ReturnMsgUtil;
import com.harambase.hackathon.server.pojo.base.Person;
import com.harambase.hackathon.server.service.PersonServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PersonServerService personServerService;

    @Autowired
    public PersonService(PersonServerService personServerService) {
        this.personServerService = personServerService;
    }


    public ResultMap login(Person person) {
        try {
            return personServerService.login(person);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }


    public ResultMap createPerson(Person person) {
        try {
            return personServerService.create(person);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }


    public ResultMap deletePerson(String userId) {
        try {
            return personServerService.delete(userId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }


    public ResultMap updatePerson(String userId, Person person) {
        try {
            return personServerService.update(userId, person);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }


    public ResultMap get(String userId) {
        try {
            return personServerService.retrieve(userId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }


    public ResultMap list(int start, int length, String search, String order, String orderColumn,
                          String type, String status, String role) {
        try {
            return personServerService.list(String.valueOf(start / length + 1), String.valueOf(length), search, order, orderColumn, type, status, role);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }


    public ResultMap search(String search, String type, String status, String role) {
        try {
            return personServerService.search(search, type, status, role, "5");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }
}
