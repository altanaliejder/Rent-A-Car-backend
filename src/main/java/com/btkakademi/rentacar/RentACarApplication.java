package com.btkakademi.rentacar;

import com.btkakademi.rentacar.adapters.corporateFindex.CorporateFindexServiceAdapter;
import com.btkakademi.rentacar.adapters.individualCorporate.IndividualFindexServiceAdapter;
import com.btkakademi.rentacar.adapters.pos.PosServiceAdapter;
import com.btkakademi.rentacar.business.abstracts.InvoiceService;
import com.btkakademi.rentacar.business.concretes.InvoiceManager;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RentACarApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentACarApplication.class, args);
    }
    @Bean
    public ModelMapper getModelModelMapper(){
        return new ModelMapper();
    }

    @Bean
    public IndividualFindexServiceAdapter getFindexServiceAdapter(){return new IndividualFindexServiceAdapter();}
    @Bean
    public CorporateFindexServiceAdapter getFindexServiceCorporateAdapter(){return new CorporateFindexServiceAdapter();}
    @Bean
    public PosServiceAdapter getPosServiceAdapter(){return  new PosServiceAdapter();}
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/**").allowedMethods("*");
            }
        };
    }
}
//kiralama için fatura bireysel kurumsal ayrı alınan hizmetler listele hangi araba total fiyat şu kadar gün invoice
//invoice için rental id tarih