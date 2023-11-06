package rut.miit.carservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rut.miit.carservice.models.entities.CarBrand;

//todo fix deleteCarBrandByName

@Repository
public interface CarBrandRepository extends JpaRepository<CarBrand, String> {
    CarBrand findByName(String brandName);
    //???
    @Deprecated
    void deleteByName(String brandName);
}
