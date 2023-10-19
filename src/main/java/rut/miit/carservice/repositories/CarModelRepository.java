package rut.miit.carservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rut.miit.carservice.models.entities.CarModel;

import java.util.UUID;

@Repository
public interface CarModelRepository extends JpaRepository<CarModel, UUID>{
    CarModel findByNameAndBrand_Name(String modelName, String brandName);
}
