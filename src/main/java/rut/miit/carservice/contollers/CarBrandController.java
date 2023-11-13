package rut.miit.carservice.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import rut.miit.carservice.services.dtos.input.CarBrandDTO;
import rut.miit.carservice.services.interfaces.publicAPI.CarBrandService;
import rut.miit.carservice.util.contollerValidators.BrandValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * todo Document type CarBrandController
 */
@RestController
@RequestMapping("/brand")
public class CarBrandController {
    private CarBrandService<String> brandService;
    private BrandValidator brandValidator;

    @Autowired
    public void setBrandService(CarBrandService<String> brandService) {
        this.brandService = brandService;
    }

    @Autowired
    public void setBrandValidator(BrandValidator brandValidator) {
        this.brandValidator = brandValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(brandValidator);
    }

    @GetMapping("/find/all")
    public List<CarBrandDTO> findAll(){
        return brandService.getAllBrands();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBrand(@RequestParam String brandName) {
        // Применяем валидацию
        Errors errors = new BeanPropertyBindingResult(brandName, "brandName");
        brandValidator.validate(brandName, errors);

        if (errors.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("brandName", errors.getAllErrors().get(0).getDefaultMessage());
            return ResponseEntity.badRequest().body(errorMap);
        }
        return ResponseEntity.ok(brandService.addNewBrand(brandName));
    }


    //validation
    @GetMapping("/find/{brandName}")
    public CarBrandDTO findByName(@PathVariable String brandName){
        return brandService.getBrandByName(brandName);
    }

    //validation
    @PutMapping("/update/{brandName}")
    public CarBrandDTO updateBrandName(@PathVariable String brandName, @RequestParam String newBrandName){
        return brandService.updateBrandName(brandName, newBrandName);
    }

    //fix error and no more deleting
    @Deprecated
    @DeleteMapping("/delete/{brandName}")
    public void deleteBrand(@PathVariable String brandName){
        brandService.deleteBrandByName(brandName);
    }
}
