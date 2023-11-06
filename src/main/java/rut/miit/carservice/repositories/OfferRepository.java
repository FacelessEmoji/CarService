package rut.miit.carservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;;
import rut.miit.carservice.models.entities.Offer;
import rut.miit.carservice.models.enums.UserRoleType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, String> {

    List<Offer> findAllBySeller_UsernameAndSeller_IsActiveAndSeller_RoleRole
            (String username, boolean isActive, UserRoleType roleType);

    List<Offer> findAllByCreatedAfterAndCreatedBeforeOrderByCreated(LocalDateTime startTime, LocalDateTime endTime);

    List<Offer> findAllByModel_Brand_NameAndModel_Name(String brandName, String modelName);

    List<Offer> findAllByPriceGreaterThanEqualAndPriceLessThanEqual(BigDecimal minPrice, BigDecimal maxPrice);

    List<Offer> findAllByModel_Brand_NameAndModel_NameAndPriceGreaterThanEqualAndPriceLessThanEqual
            (String brandName, String modelName, BigDecimal minPrice, BigDecimal maxPrice);
 }
