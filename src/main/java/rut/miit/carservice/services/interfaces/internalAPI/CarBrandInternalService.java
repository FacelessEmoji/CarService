package rut.miit.carservice.services.interfaces.internalAPI;

import rut.miit.carservice.models.entities.CarBrand;

/**
 * todo Document type BrandInternalService
 */
public interface CarBrandInternalService<ID> {
    CarBrand getBrandById(ID brandId);
    void deleteBrandById(ID brandId);
}
