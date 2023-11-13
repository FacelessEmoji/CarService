package rut.miit.carservice.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rut.miit.carservice.models.enums.EngineType;
import rut.miit.carservice.models.enums.ModelCategory;
import rut.miit.carservice.models.enums.TransmissionType;
import rut.miit.carservice.services.dtos.input.CarModelDTO;
import rut.miit.carservice.services.dtos.output.CarModelOutputDTO;
import rut.miit.carservice.services.interfaces.publicAPI.CarModelService;

import java.math.BigDecimal;
import java.util.List;

/**
 * todo Document type CarModelController
 */
@RestController
@RequestMapping("/model")
public class CarModelController {
    private CarModelService<String> modelService;

    @Autowired
    public void setModelService(CarModelService<String> modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/find/all")
    public List<CarModelOutputDTO> findAll(){
        return modelService.getAllModels();
    }

    @GetMapping("/find/position/{brandName}/{modelName}")
    public CarModelOutputDTO findByBrandAndName(@PathVariable String brandName, @PathVariable String modelName){
        return modelService.getModelByBrandAndName(brandName, modelName);
    }

    @GetMapping("/find/brand/{brandName}/years/between/{startYear}/{endYear}")
    public List<CarModelOutputDTO> findByBrandAndYears(@PathVariable String brandName, @PathVariable Integer startYear, @PathVariable Integer endYear){
        return modelService.getModelsByBrandAndYears(brandName, startYear, endYear);
    }

    @GetMapping("/find/criteria/{category}/{engine}/{transmission}/{maxMileage}/{maxPrice}")
    public List<CarModelOutputDTO> findByCriteria(@PathVariable ModelCategory category, @PathVariable EngineType engine,
        @PathVariable TransmissionType transmission, @PathVariable Integer maxMileage, @PathVariable BigDecimal maxPrice){
        return modelService.getModelsByCriteria(category, engine, transmission, maxMileage, maxPrice);
    }

    @PostMapping("/add")
    public CarModelDTO add(@RequestBody CarModelDTO modelDTO){
        return modelService.addNewModel(modelDTO);
    }

    @PutMapping("/update/name/{modelId}")
    public CarModelDTO updateName(@PathVariable String modelId, @RequestParam String modelName){
        return modelService.updateModelName(modelId, modelName);
    }

    @PutMapping("/update/image/{modelId}")
    public CarModelDTO updateImageUrl(@PathVariable String modelId, @RequestParam String imageUrl){
        return modelService.updateModelImageUrl(modelId, imageUrl);
    }

    @PutMapping("/update/endYear/{modelId}")
    public CarModelDTO updateEndYear(@PathVariable String modelId, @RequestParam int endYear){
        return modelService.updateModelEndYear(modelId, endYear);
    }
}
