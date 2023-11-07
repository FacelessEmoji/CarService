package rut.miit.carservice.contollers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import rut.miit.carservice.services.dtos.input.CarBrandDTO;
import rut.miit.carservice.services.implementations.CarBrandServiceImpl;
import rut.miit.carservice.services.implementations.UserServiceImpl;
import rut.miit.carservice.services.interfaces.publicAPI.CarBrandService;
import rut.miit.carservice.util.contollerValidators.BrandValidator;
import rut.miit.carservice.util.contollerValidators.UserValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * todo Document type CarBrandController
 */
@RestController
@RequestMapping("/brand")
public class CarBrandController {
    private final CarBrandService<String> brandService;
    private final  BrandValidator brandValidator;

    @Autowired
    public CarBrandController(CarBrandService<String> brandService, BrandValidator brandValidator) {
        this.brandService = brandService;
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
