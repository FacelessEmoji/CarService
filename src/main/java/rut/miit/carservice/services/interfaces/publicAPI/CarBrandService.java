package rut.miit.carservice.services.interfaces.publicAPI;

import rut.miit.carservice.services.dtos.input.CarBrandDTO;

import java.util.List;

public interface CarBrandService<ID>{
    CarBrandDTO getBrandByName(String brandName);
    List<CarBrandDTO> getAllBrands();
    CarBrandDTO addNewBrand(String brandName);
    CarBrandDTO updateBrandName(String brandName, String newBrandName);
    @Deprecated
    void deleteBrandByName(String brandName);
}
