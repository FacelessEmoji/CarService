package rut.miit.carservice.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rut.miit.carservice.services.dtos.input.CarBrandDTO;
import rut.miit.carservice.services.interfaces.publicAPI.CarBrandService;

import java.util.List;

/**
 * todo Document type CarBrandController
 */
@RestController
@RequestMapping("/brand")
public class CarBrandController {
    private final CarBrandService<String> brandService;

    @Autowired
    public CarBrandController(CarBrandService<String> brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/find/all")
    public List<CarBrandDTO> findAll(){
        return brandService.getAllBrands();
    }

    @PostMapping("/add")
    public CarBrandDTO add(@RequestParam String brandName){
        return brandService.addNewBrand(brandName);
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
