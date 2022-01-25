package com.btkakademi.rentacar.business.concretes;

import com.btkakademi.rentacar.business.abstracts.ColorService;
import com.btkakademi.rentacar.business.dtos.ColorListDto;
import com.btkakademi.rentacar.business.requests.colorRequests.CreateColorRequest;
import com.btkakademi.rentacar.business.requests.colorRequests.UpdateColorRequest;
import com.btkakademi.rentacar.core.utilities.business.BusinessRules;
import com.btkakademi.rentacar.core.utilities.mapping.ModelMapperService;
import com.btkakademi.rentacar.core.utilities.results.*;
import com.btkakademi.rentacar.dataAccess.abstratcs.ColorDao;
import com.btkakademi.rentacar.entities.concretes.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ColorManager implements ColorService {

    private ColorDao colorDao;
    private ModelMapperService modelMapperService;
    @Autowired
    public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService) {
        this.colorDao = colorDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<ColorListDto>> getAll() {
        List<Color> colorList= this.colorDao.findAll();
        List<ColorListDto> response= colorList.stream().map(color -> modelMapperService.forDto().map(color,ColorListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<ColorListDto>>(response);
    }

    @Override
    public Result add(CreateColorRequest createColorRequest) {
        Result result=BusinessRules.run(checkIfNameExist(createColorRequest.getName()));
        if (!result.isSuccess()){
            return new ErrorResult("Tekrarlı Kayıt");
        }
        Color color= this.modelMapperService.forRequest().map(createColorRequest,Color.class);
        this.colorDao.save(color);
        return new SuccessResult("Başarılı");
    }

    @Override
    public Result update(UpdateColorRequest updateColorRequest) {
        Color color= modelMapperService.forRequest().map(updateColorRequest,Color.class);
        var response=BusinessRules.run(checkIfColorIdExists(updateColorRequest.getId()));
        if(response==null){
            this.colorDao.save(color);
            return new SuccessResult();
        }return new ErrorResult();
    }


    private Result checkIfNameExist(String colorName){
        var brand= this.colorDao.getColorByName(colorName);
        if(brand!=null){
            return new ErrorResult("Tekrarlı kayıt");
        }return new SuccessResult();
    }
    private Result checkIfColorIdExists(int colorId){
        var color= this.colorDao.findById(colorId);
        if(color!=null){
            return new SuccessResult();
        }return new ErrorResult("Böyle bir kullanıcı yok");
    }
}
