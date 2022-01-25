package com.btkakademi.rentacar.ws.controllers;

import com.btkakademi.rentacar.business.abstracts.ColorService;
import com.btkakademi.rentacar.business.dtos.BrandListDto;
import com.btkakademi.rentacar.business.dtos.ColorListDto;
import com.btkakademi.rentacar.business.requests.carRequests.UpdateCarRequest;
import com.btkakademi.rentacar.business.requests.colorRequests.CreateColorRequest;
import com.btkakademi.rentacar.business.requests.colorRequests.UpdateColorRequest;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.Result;
import com.btkakademi.rentacar.core.utilities.results.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/colors")
public class ColorsController {
    private ColorService colorService;

@Autowired
    public ColorsController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping("getall")
    public DataResult<List<ColorListDto>> getAll(){
        return this.colorService.getAll();
    }

    @PostMapping("add")
    public Result add(@RequestBody CreateColorRequest createColorRequest){
        return this.colorService.add(createColorRequest);
    }

    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateColorRequest updateColorRequest){
        return this.colorService.update(updateColorRequest);
    }
}
