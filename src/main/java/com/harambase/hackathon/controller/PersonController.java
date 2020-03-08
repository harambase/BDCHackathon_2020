package com.harambase.hackathon.controller;

import com.alibaba.fastjson.JSONObject;
import com.harambase.hackathon.common.Config;
import com.harambase.hackathon.common.Page;
import com.harambase.hackathon.common.ResultMap;
import com.harambase.hackathon.common.support.util.FileUtil;
import com.harambase.hackathon.helper.TokenHelper;
import com.harambase.hackathon.server.pojo.base.Person;
import com.harambase.hackathon.service.PersonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
@RequestMapping(value = "/user")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //@PreAuthorize("hasAnyRole('SYSTEM','ADMIN')")
    @RequestMapping(produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Person person) {
        person.setPassword(new BCryptPasswordEncoder().encode(person.getPassword()));
        ResultMap message = personService.createPerson(person);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("userId") String userid) {
        ResultMap message = personService.deletePerson(userid);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}", produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable("userId") String userId, @RequestBody Person person) {
        ResultMap message = personService.updatePerson(userId, person);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable(value = "userId") String userId) {
        ResultMap resultMap = personService.get(userId);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public ResponseEntity getCurrentUser(@RequestHeader String token) {
        String userId = TokenHelper.getUserIdFromToken(token);
        ResultMap message = personService.get(userId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity search(@RequestParam(value = "search", required = false) String search,
                                 @RequestParam(value = "type", required = false) String type,
                                 @RequestParam(value = "role", required = false) String role,
                                 @RequestParam(value = "status", required = false) String status) {
        ResultMap message = personService.search(search, type, status, role);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity list(@RequestParam(value = "start", required = false, defaultValue = "0") Integer start,
                               @RequestParam(value = "length", required = false, defaultValue = "100") Integer length,
                               @RequestParam(value = "search", required = false, defaultValue = "") String search,
                               @RequestParam(value = "order", required = false, defaultValue = "desc") String order,
                               @RequestParam(value = "orderCol", required = false, defaultValue = "user_id") String orderCol,
                               @RequestParam(value = "type", required = false) String type,
                               @RequestParam(value = "role", required = false) String role,
                               @RequestParam(value = "status", required = false) String status) {
        search = search.replace("'", "");
        ResultMap message = personService.list(start * length - 1, length, search, order, orderCol, type, status, role);
        message.put("recordsTotal", ((Page) message.get("page")).getTotalRows());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @RequestMapping(value = "/info/{userId}", method = RequestMethod.GET)
    public void downloadUserInfo(@PathVariable(value = "userId") String userId, HttpServletResponse response) {
        ResultMap message = personService.get(userId);
        String userInfo = ((Person) message.getData()).getUserInfo();
        if (StringUtils.isNotEmpty(userInfo)) {
            JSONObject info = JSONObject.parseObject(userInfo);
            try {
                FileUtil.downloadFileFromFtpServer(response, info.getString("name"), info.getString("path"), Config.FTP_SERVER, Config.FTP_USERNAME, Config.FTP_PASSWORD);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
