package rut.miit.carservice.services;

import rut.miit.carservice.dtos.input.CarBrandDTO;

import java.util.List;

public interface CarBrandService<ID>{
    CarBrandDTO getBrandById(ID brandId);
    List<CarBrandDTO> getAllBrands();
    CarBrandDTO addNewBrand(CarBrandDTO brandDTO);
    CarBrandDTO updateBrandName(ID brandId, String brandName);
    void deleteBrandById(ID brandId);
}
