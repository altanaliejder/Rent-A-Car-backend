package com.btkakademi.rentacar.ws.controllers;

import com.btkakademi.rentacar.business.abstracts.InvoiceService;
import com.btkakademi.rentacar.business.dtos.IndividualInvoiceListDto;
import com.btkakademi.rentacar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.Result;
import com.btkakademi.rentacar.core.utilities.results.SuccessDataResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    private InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }
    @PostMapping("add")
    public Result add(@RequestBody CreateInvoiceRequest createInvoiceRequest){
        return this.invoiceService.add(createInvoiceRequest);
    }

    @GetMapping("getinvoice")
    public DataResult<IndividualInvoiceListDto> getInvoice(@RequestParam int rentalId){
        return this.invoiceService.getIndividualList(rentalId);
    }
}
//promosyon kodu eklenebilmeli indirim oranı neyse kiralama o kadar indirimli