package com.harambase.hackathon.controller;

import com.harambase.hackathon.common.Page;
import com.harambase.hackathon.common.ResultMap;
import com.harambase.hackathon.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/cities")
public class CitiesController {

    private final CitiesService citiesService;

    @Autowired
    public CitiesController(CitiesService citiesService) {
        this.citiesService = citiesService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable(value = "id") String id) {
        ResultMap resultMap = citiesService.get(Integer.parseInt(id));
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @RequestMapping(value = "/region/{id}", method = RequestMethod.GET)
    public ResponseEntity regionList(@PathVariable(value = "id") String id) {
        ResultMap resultMap = citiesService.getListByRegionId(Integer.parseInt(id));
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
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
        ResultMap message = citiesService.list(start * length - 1, length, search, order, orderCol, type, status, role);
        message.put("recordsTotal", ((Page) message.get("page")).getTotalRows());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
