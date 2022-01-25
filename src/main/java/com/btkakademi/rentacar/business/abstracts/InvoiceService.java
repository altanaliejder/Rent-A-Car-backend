package com.btkakademi.rentacar.business.abstracts;

import com.btkakademi.rentacar.business.dtos.IndividualInvoiceListDto;
import com.btkakademi.rentacar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.Result;

public interface InvoiceService {
    Result add(CreateInvoiceRequest createInvoiceRequest);
    DataResult<IndividualInvoiceListDto> getIndividualList(int rentalId);

}
