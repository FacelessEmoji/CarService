package rut.miit.carservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rut.miit.carservice.models.entities.CarModel;
import rut.miit.carservice.models.enums.EngineType;
import rut.miit.carservice.models.enums.ModelCategory;
import rut.miit.carservice.models.enums.TransmissionType;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CarModelRepository extends JpaRepository<CarModel, String>{
    @Query("SELECT o.model FROM Offer o " +
            "WHERE o.model.category = :category " +
            "AND o.engine = :engine " +
            "AND o.transmission = :transmission " +
            "AND o.mileage <= :maxMileage " +
            "AND o.price <= :maxPrice")
    List<CarModel> findModelsByCriteria(@Param("category") ModelCategory category,
                                        @Param("engine") EngineType engine,
                                        @Param("transmission") TransmissionType transmission,
                                        @Param("maxMileage") Integer maxMileage,
                                        @Param("maxPrice") BigDecimal maxPrice);

    CarModel findByBrand_NameAndName(String brandName, String modelName);

    List<CarModel> findAllByBrand_NameAndStartYearGreaterThanEqualAndEndYearLessThanEqualOrderByEndYearDesc
            (String brandName, Integer startYear, Integer endYear);
}
