package rut.miit.carservice.contollers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import rut.miit.carservice.services.dtos.output.OfferWithDetailsDTO;
import rut.miit.carservice.services.dtos.input.OfferDTO;
import rut.miit.carservice.services.implementations.OfferServiceImpl;
import rut.miit.carservice.services.interfaces.publicAPI.OfferService;
import rut.miit.carservice.util.contollerValidators.ModelValidator;
import rut.miit.carservice.util.contollerValidators.OfferValidator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * todo Document type OfferController
 */
@RestController
@RequestMapping("/offer")
public class OfferController {
    private OfferServiceImpl offerService;

    @Autowired
    public void setOfferService(OfferServiceImpl offerService) {
        this.offerService = offerService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new OfferValidator(offerService));
    }
    @GetMapping("/find/position")
    public ResponseEntity<?> findByModelAndBrand(
        @RequestParam(name = "brand", defaultValue = "null") String brandName,
        @RequestParam(name = "model", defaultValue = "null") String modelName){
        if (brandName.equals("null") && modelName.equals("null")){
            return ResponseEntity.badRequest().body("Brand and model names must be specified");
        } else if (brandName.equals("all") && modelName.equals("null")){
            return ResponseEntity.ok(offerService.getAllOffers());
        } else return ResponseEntity.ok(offerService.getOffersByBrandAndModel(brandName, modelName));
    }

    @GetMapping("/find/seller")
    public ResponseEntity<?> findBySellerUsername(
        @RequestParam(name = "username", defaultValue = "null") String username){
        if (username.equals("null")){
            return ResponseEntity.badRequest().body("Username must be specified");
        } else return ResponseEntity.ok(offerService.getOffersBySellerUsername(username));
    }

    @GetMapping("/find/date/between")
    public ResponseEntity<?> findByDateBetween(
        @RequestParam(name = "startTime", required = false, defaultValue = "2020-01-01T00:00:00") String startTime,
        @RequestParam(name = "endTime", required = false, defaultValue = "2030-12-31T23:59:59") String endTime){
        return ResponseEntity.ok(offerService.getOffersCreatedBetweenDates(LocalDateTime.parse(startTime), LocalDateTime.parse(endTime)));
    }

    @GetMapping("/find/price/between")
    public ResponseEntity<?> findByPriceBetween(
        @RequestParam(name = "minPrice", required = false, defaultValue = "0") String minPrice,
        @RequestParam(name = "maxPrice", required = false, defaultValue = "100000000") String maxPrice){
        return ResponseEntity.ok(offerService.getOffersByPriceBetween(new BigDecimal(minPrice), new BigDecimal(maxPrice)));
    }

    @GetMapping("/find/position/price/between")
    public ResponseEntity<?> findByPriceBetweenAndName(
        @RequestParam(name = "brand", defaultValue = "null") String brandName,
        @RequestParam(name = "model", defaultValue = "null") String modelName,
        @RequestParam(name = "minPrice", required = false, defaultValue = "0") String minPrice,
        @RequestParam(name = "maxPrice", required = false, defaultValue = "100000000") String maxPrice){
        if (brandName.equals("null") && modelName.equals("null")){
            return ResponseEntity.badRequest().body("Brand and model names must be specified");
        } else return ResponseEntity.ok(offerService.getOffersByPriceBetweenAndName(brandName, modelName, new BigDecimal(minPrice), new BigDecimal(maxPrice)));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody OfferDTO offer, BindingResult result){
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : result.getFieldErrors()) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        else return ResponseEntity.ok(offerService.addNewOffer(offer));
    }

    @PutMapping("/update/description")
    public ResponseEntity<?> updateDescription(
        @RequestParam(name = "id", defaultValue = "null")  String offerId,
        @RequestParam(name = "description", defaultValue = "null")  String description){
        if (offerId.equals("null") || description.equals("null")){
            return ResponseEntity.badRequest().body("Offer id and description must be specified");
        } else return ResponseEntity.ok(offerService.updateOfferDescription(offerId, description));
        
    }

    @PutMapping("/update/image")
    public ResponseEntity<?> updateImageUrl(
        @RequestParam(name = "id", defaultValue = "null") String offerId,
        @RequestParam(name = "imageUrl", defaultValue = "null") String imageUrl){
        if (offerId.equals("null") || imageUrl.equals("null")){
            return ResponseEntity.badRequest().body("Offer id and image url must be specified");
        } else return ResponseEntity.ok(offerService.updateOfferImageUrl(offerId, imageUrl));
    }

    @PutMapping("/update/price")
    public ResponseEntity<?> updatePrice(
        @RequestParam(name = "id", defaultValue = "null") String offerId,
        @RequestParam(name = "price", defaultValue = "null") String price){
        if (offerId.equals("null") || price.equals("null")){
            return ResponseEntity.badRequest().body("Offer id and price must be specified");
        } else return ResponseEntity.ok(offerService.updateOfferPrice(offerId, new BigDecimal(price)));
    }
}
