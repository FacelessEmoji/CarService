package rut.miit.carservice.contollers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import rut.miit.carservice.models.enums.EngineType;
import rut.miit.carservice.models.enums.ModelCategory;
import rut.miit.carservice.models.enums.TransmissionType;
import rut.miit.carservice.services.dtos.input.CarModelDTO;
import rut.miit.carservice.services.dtos.output.CarModelOutputDTO;
import rut.miit.carservice.services.implementations.CarBrandServiceImpl;
import rut.miit.carservice.services.implementations.CarModelServiceImpl;
import rut.miit.carservice.services.implementations.UserServiceImpl;
import rut.miit.carservice.services.interfaces.publicAPI.CarModelService;
import rut.miit.carservice.util.contollerValidators.BrandValidator;
import rut.miit.carservice.util.contollerValidators.ModelValidator;
import rut.miit.carservice.util.contollerValidators.UserValidator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * todo Document type CarModelController
 */
@RestController
@RequestMapping("/model")
public class CarModelController {
    private CarModelServiceImpl modelService;
    private CarBrandServiceImpl brandService;

    @Autowired
    public void setBrandService(CarBrandServiceImpl brandService) {
        this.brandService = brandService;
    }

    @Autowired
    public void setModelService(CarModelServiceImpl modelService) {
        this.modelService = modelService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new ModelValidator(modelService));
    }

    @GetMapping("/find/position")
    public ResponseEntity<?> findByBrandAndName(
        @RequestParam(name = "brand", defaultValue = "null") String brandName,
        @RequestParam(name = "model", defaultValue = "null") String modelName){
        if (brandName.equals("null") && modelName.equals("null")){
            return ResponseEntity.badRequest().body("Brand and model names must be specified");
            //if brandName equals all and modelName equals null return all models
        } else if (brandName.equals("all") && modelName.equals("null")){
            return ResponseEntity.ok(modelService.getAllModels());
            //if brandName equals all and modelName not equals null return all models with modelName
        } else if (brandService.getBrandByName(brandName) != null && modelName.equals("all")){
            return ResponseEntity.ok(modelService.getModelsByBrandAndYears(brandName, 1900, 2100));
            //if brandName not equals all and modelName equals null return all models with brandName
        } else return ResponseEntity.ok(modelService.getModelByBrandAndName(brandName, modelName));
    }

    @GetMapping("/find/brand/years/between")
    public ResponseEntity<?> findByBrandAndYears(
        @RequestParam(name = "brand") String brandName,
        @RequestParam(name = "startYear", required = false, defaultValue = "1900") Integer startYear,
        @RequestParam(name = "endYear", required = false, defaultValue = "2100") Integer endYear){
        if (brandName.equals("null"))
            return ResponseEntity.badRequest().body("Brand name must be specified");
        else return ResponseEntity.ok(modelService.getModelsByBrandAndYears(brandName, startYear, endYear));
    }

    @GetMapping("/find/criteria")
    public ResponseEntity<?> findByCriteria(
        @RequestParam(name = "category", required = false, defaultValue = "CAR") ModelCategory category,
        @RequestParam(name = "engine", required = false, defaultValue = "GASOLINE") EngineType engine,
        @RequestParam(name = "transmission", required = false, defaultValue = "AUTOMATIC") TransmissionType transmission,
        @RequestParam(name = "mileage", required = false, defaultValue = "100000") Integer maxMileage,
        @RequestParam(name = "price", required = false, defaultValue = "100000") BigDecimal maxPrice){

        return ResponseEntity.ok(modelService.getModelsByCriteria(category, engine, transmission, maxMileage, maxPrice));
    }


    //todo: add validation
    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody CarModelDTO modelDTO, BindingResult result){
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : result.getFieldErrors()) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok(modelService.addNewModel(modelDTO));
    }

    @PutMapping("/update/name")
    public ResponseEntity<?> updateName(
        @RequestParam(name = "id", defaultValue = "null") String modelId,
        @RequestParam(name = "name", defaultValue = "null") String modelName){
        if (modelId.equals("null") || modelName.equals("null"))
            return ResponseEntity.badRequest().body("Model id and name must be specified");
        else return ResponseEntity.ok(modelService.updateModelName(modelId, modelName));
    }

    @PutMapping("/update/image")
    public ResponseEntity<?> updateImageUrl(
        @RequestParam(name = "id", defaultValue = "null") String modelId,
        @RequestParam(name = "imageUrl", defaultValue = "null") String imageUrl){
        if (modelId.equals("null") || imageUrl.equals("null"))
            return ResponseEntity.badRequest().body("Model id and image url must be specified") ;
        else return ResponseEntity.ok(modelService.updateModelImageUrl(modelId, imageUrl));
    }

    @PutMapping("/update/endYear")
    public ResponseEntity<?> updateEndYear(
        @RequestParam(name = "id", defaultValue = "null") String modelId,
        @RequestParam(name = "endYear", defaultValue = "0") int endYear){
        if (modelId.equals("null") || endYear == 0)
            return ResponseEntity.badRequest().body("Model id and end year must be specified");
        else return ResponseEntity.ok(modelService.updateModelEndYear(modelId, endYear));
    }
}
