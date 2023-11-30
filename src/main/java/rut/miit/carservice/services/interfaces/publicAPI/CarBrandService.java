package rut.miit.carservice.services.interfaces.publicAPI;

import rut.miit.carservice.services.dtos.input.CarBrandDTO;

import java.util.List;

public interface CarBrandService<ID>{
    CarBrandDTO getBrandByName(String brandName);
    List<CarBrandDTO> getAllBrands();
    CarBrandDTO addNewBrandDTO(CarBrandDTO brand);
    CarBrandDTO addNewBrand(String brandName);
    CarBrandDTO updateBrandName(String brandName, String newBrandName);
    CarBrandDTO updateBrandNameByID(ID brandId, String newBrandName);
}
