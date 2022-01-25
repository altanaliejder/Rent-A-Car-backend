package com.btkakademi.rentacar.ws.controllers;
import com.btkakademi.rentacar.business.abstracts.BrandService;
import com.btkakademi.rentacar.business.dtos.BrandListDto;
import com.btkakademi.rentacar.business.requests.brandRequests.CreateBrandRequest;
import com.btkakademi.rentacar.business.requests.brandRequests.UpdateBrandRequest;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandsController {
    private BrandService brandService;

    @Autowired
    public BrandsController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("getall")
    public DataResult<List<BrandListDto>> getAll(){
        return this.brandService.getAll();
    }

    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateBrandRequest createBrandRequest){
        return this.brandService.add(createBrandRequest);
    }

    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateBrandRequest updateBrandRequest){
        return this.brandService.update(updateBrandRequest);
    }
}