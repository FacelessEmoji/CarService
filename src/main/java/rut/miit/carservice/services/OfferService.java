package rut.miit.carservice.services;

import rut.miit.carservice.dtos.input.OfferDTO;
import rut.miit.carservice.models.entities.Offer;
import rut.miit.carservice.models.entities.UserRole;
import rut.miit.carservice.models.enums.UserRoleType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface OfferService<ID>{
    OfferDTO getOfferById(ID offerId);
    List<OfferDTO> getAllOffers();
    List<OfferDTO> getOffersByModelAndBrand(String modelName, String brandName);
    List<OfferDTO> getOffersBySellerUsername(String username);
    List<OfferDTO> getOffersCreatedAfterDate(LocalDateTime time);
    List<OfferDTO> getOffersCreatedBeforeDate(LocalDateTime time);
    List<OfferDTO> getOffersCreatedBetweenDates(LocalDateTime startTime, LocalDateTime endTime);
    OfferDTO addNewOffer(OfferDTO offerDTO);
    OfferDTO updateOfferDescription(ID offerId, String description);
    OfferDTO updateOfferImageUrl(ID offerId, String imageUrl);
    OfferDTO updateOfferPrice(ID offerId, BigDecimal price);
    void deleteOfferById(ID offerId);
}

