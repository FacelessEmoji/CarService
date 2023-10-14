package rut.miit.carservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rut.miit.carservice.models.entities.Offer;

import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository<Offer, UUID> {
}