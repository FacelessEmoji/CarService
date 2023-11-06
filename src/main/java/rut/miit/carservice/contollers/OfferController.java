package rut.miit.carservice.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rut.miit.carservice.services.dtos.complex.OfferWithDetailsDTO;
import rut.miit.carservice.services.dtos.input.OfferDTO;
import rut.miit.carservice.services.interfaces.publicAPI.OfferService;

import java.util.List;

/**
 * todo Document type OfferController
 */
@RestController
@RequestMapping("/offer")
public class OfferController {
    private final OfferService<String> offerService;

    @Autowired
    public OfferController(OfferService<String> offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/find/all")
    public List<OfferWithDetailsDTO> findAll(){
        return offerService.getAllOffers();
    }

    @GetMapping("/find/position/{brandName}/{modelName}")
    public List<OfferWithDetailsDTO> findByModelAndBrand(@PathVariable String brandName, @PathVariable String modelName){
        return offerService.getOffersByBrandAndModel(brandName, modelName);
    }

    @GetMapping("/find/seller/{username}")
    public List<OfferWithDetailsDTO> findBySellerUsername(@PathVariable String username){
        return offerService.getOffersBySellerUsername(username);
    }

    @GetMapping("/find/date/between/{startTime}/{endTime}")
    public List<OfferWithDetailsDTO> findByDateBetween(@PathVariable String startTime, @PathVariable String endTime){
        return offerService.getOffersCreatedBetweenDates(java.time.LocalDateTime.parse(startTime), java.time.LocalDateTime.parse(endTime));
    }

    @GetMapping("/find/price/between/{minPrice}/{maxPrice}")
    public List<OfferWithDetailsDTO> findByPriceBetween(@PathVariable String minPrice, @PathVariable String maxPrice){
        return offerService.getOffersByPriceBetween(new java.math.BigDecimal(minPrice), new java.math.BigDecimal(maxPrice));
    }

    @GetMapping("/find/position/{brandName}/{modelName}/price/between/{minPrice}/{maxPrice}")
    public List<OfferWithDetailsDTO> findByPriceBetweenAndName(@PathVariable String brandName, @PathVariable String modelName,
        @PathVariable String minPrice, @PathVariable String maxPrice){
        return offerService.getOffersByPriceBetweenAndName(brandName, modelName,
            new java.math.BigDecimal(minPrice), new java.math.BigDecimal(maxPrice));
    }

    @PostMapping("/add")
    public OfferDTO add(@RequestBody OfferDTO offer){
        return offerService.addNewOffer(offer);
    }

    @PutMapping("/update/description/{offerId}")
    public OfferDTO updateDescription(@PathVariable String offerId, @RequestParam String description){
        return offerService.updateOfferDescription(offerId, description);
    }

    @PutMapping("/update/image/{offerId}")
    public OfferDTO updateImageUrl(@PathVariable String offerId, @RequestParam String imageUrl){
        return offerService.updateOfferImageUrl(offerId, imageUrl);
    }

    @PutMapping("/update/price/{offerId}")
    public OfferDTO updatePrice(@PathVariable String offerId, @RequestParam String price){
        return offerService.updateOfferPrice(offerId, new java.math.BigDecimal(price));
    }
}
