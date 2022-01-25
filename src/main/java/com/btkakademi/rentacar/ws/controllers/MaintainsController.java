package com.btkakademi.rentacar.ws.controllers;

import com.btkakademi.rentacar.business.abstracts.MaintainService;
import com.btkakademi.rentacar.business.dtos.MaintainListDto;
import com.btkakademi.rentacar.business.requests.maintainRequests.CreateMaintainRequest;
import com.btkakademi.rentacar.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/maintains")
public class MaintainsController {
    private MaintainService maintainService;
@Autowired
    public MaintainsController(MaintainService maintainService) {
        this.maintainService = maintainService;
    }

    @PostMapping("add")
    public Result add(@RequestBody CreateMaintainRequest createMaintainRequest){
        return this.maintainService.add(createMaintainRequest);
    }

    @GetMapping("getall")
    public Result getAll(@RequestBody MaintainListDto maintainListDto){
        return this.maintainService.getAll(maintainListDto);
    }
}
