package com.harambase.hackathon.server.service.impl;

import com.harambase.hackathon.common.Page;
import com.harambase.hackathon.common.ResultMap;
import com.harambase.hackathon.common.constant.SystemConst;
import com.harambase.hackathon.common.support.util.*;
import com.harambase.hackathon.server.dao.base.PersonDao;
import com.harambase.hackathon.server.dao.repository.*;
import com.harambase.hackathon.server.pojo.base.Person;
import com.harambase.hackathon.server.service.PersonServerService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonServerServiceImpl implements PersonServerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PersonRepository personRepository;
    //Only for search and paging functionality
    private final PersonDao personDao;

    @Autowired
    public PersonServerServiceImpl(PersonRepository personRepository, PersonDao personDao) {
        this.personRepository = personRepository;
        this.personDao = personDao;
    }

    @Override
    public ResultMap updateTrailPeriod(String studentId, String trial) {
        try {
            Optional<Person> p = personRepository.findById(studentId);
            Person person = p.get();
            person.setTrialPeriod(trial);
            person.setUpdateTime(DateUtil.DateToStr(new Date()));
            Person newPerson = personRepository.save(person);
            return newPerson != null ? ReturnMsgUtil.success(newPerson) : ReturnMsgUtil.fail();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap retrieveByKeyword(String keyword) {
        try {
            Person user = personRepository.findByQqOrUserIdOrUsername(keyword).get(0);
            return user != null ? ReturnMsgUtil.success(user) : ReturnMsgUtil.fail();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap login(Person person) {
        try {
            Person user = personRepository.findByUserIdAndPassword(person.getUserId(), person.getPassword());
            if (user != null) {
                String status = user.getStatus();
                if (status.equals("1")) {
                    user.setLastLoginTime(DateUtil.DateToStr(new Date()));
                    personRepository.save(user);
                    return ReturnMsgUtil.success(user);
                }

                return ReturnMsgUtil.custom(SystemConst.USER_DISABLED);
            }
            return ReturnMsgUtil.fail();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap create(Person person) {

        try {
            String userId, password;
            String info = person.getInfo();

            List<Person> people = personRepository.findByInfo(info);

            userId = IDUtil.genUserID(info);

            for (int i = 0; i < people.size(); i++) {
                Person p = people.get(i);
                if (userId.equals(p.getUserId())) {
                    userId = IDUtil.genUserID(info);
                    i = 0;
                }
            }
            person.setUserId(userId);

            if (StringUtils.isEmpty(person.getPassword())) {
                password = "Pioneer" + userId;
                person.setPassword(new BCryptPasswordEncoder().encode(password));
            }

            String firstPY = Pinyin4jUtil.converterToFirstSpell(person.getLastName());
            String lastPY = Pinyin4jUtil.converterToFirstSpell(person.getFirstName());
            String username = lastPY + firstPY + userId.substring(7, 10);

            person.setUsername(username);
            person.setCreateTime(DateUtil.DateToStr(new Date()));
            person.setUpdateTime(DateUtil.DateToStr(new Date()));

            if (StringUtils.isEmpty(person.getStatus()))
                person.setStatus("1");

            Person newPerson = personRepository.save(person);

            return newPerson != null ? ReturnMsgUtil.success(newPerson) : ReturnMsgUtil.fail();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }

    }

    @Override
    public ResultMap delete(String userId) {

        try {
            if (userId.equals(IDUtil.ROOT))
                return ReturnMsgUtil.custom(SystemConst.DELETE_BLOCK);

            Optional<Person> p = personRepository.findById(userId);
            //hibernate delete
            p.ifPresent(personRepository::delete);

            return !personRepository.existsById(userId) ? ReturnMsgUtil.success(null) : ReturnMsgUtil.fail();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap update(String userId, Person person) {
        try {
            person.setUserId(userId);
            person.setUpdateTime(DateUtil.DateToStr(new Date()));
            Person newPerson = personRepository.save(person);
            return newPerson != null ? ReturnMsgUtil.success(newPerson) : ReturnMsgUtil.fail();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap retrieve(String userId) {
        try {
            Optional<Person> person = personRepository.findById(userId);
            return ReturnMsgUtil.success(person.orElse(null));
        } catch (Exception e) {
            logger.error(e.toString());
            return ReturnMsgUtil.systemError();
        }
    }

    @Override
    public ResultMap list(String currentPage, String pageSize, String search, String order, String orderColumn,
                          String type, String status, String role) {
        ResultMap message = new ResultMap();
        try {

            long totalSize = personDao.getCountByMapPageSearchOrdered(search, type, status, role);

            Page page = new Page();
            page.setCurrentPage(PageUtil.getcPg(currentPage));
            page.setPageSize(PageUtil.getLimit(pageSize));
            page.setTotalRows(totalSize);

            List<Person> personList = personDao.getByMapPageSearchOrdered(page.getCurrentIndex(), page.getPageSize(), search, order, orderColumn, type, status, role);

            message.setData(personList);
            message.put("page", page);
            message.setMsg(SystemConst.SUCCESS.getMsg());
            message.setCode(SystemConst.SUCCESS.getCode());
            return message;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }


    @Override
    public ResultMap search(String search, String type, String status, String role, String maxLength) {
        try {
            List<Person> users = personDao.getPersonBySearch(search, type, status, role, maxLength);
            return ReturnMsgUtil.success(users);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
    }

}
