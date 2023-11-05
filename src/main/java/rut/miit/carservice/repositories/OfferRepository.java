package rut.miit.carservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;;
import rut.miit.carservice.models.entities.Offer;
import rut.miit.carservice.models.enums.UserRoleType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository<Offer, UUID> {


    List<Offer> findAllBySeller_UsernameAndSeller_IsActiveAndSeller_RoleRole
            (String username, boolean isActive, UserRoleType roleType);

    List<Offer> findAllByModelNameAndModel_BrandName(String modelName, String brandName);

    List<Offer> findAllByCreatedAfterOrderByCreated(LocalDateTime time);

    List<Offer> findAllByCreatedBeforeOrderByCreated(LocalDateTime time);

    List<Offer> findAllByCreatedAfterAndCreatedBeforeOrderByCreated(LocalDateTime startTime, LocalDateTime endTime);
 }
