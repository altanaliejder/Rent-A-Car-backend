package com.btkakademi.rentacar.business.requests.brandRequests;

import com.btkakademi.rentacar.business.constants.Messages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Required;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBrandRequest {

    private int id;
    @NotBlank
    @Size(min=3,max=20,message = Messages.INVALID_BRAND_NAME)
    private String name;
}
