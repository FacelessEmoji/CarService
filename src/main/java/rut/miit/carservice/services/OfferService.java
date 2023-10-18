package rut.miit.carservice.services;

import rut.miit.carservice.dtos.input.OfferDTO;

import java.math.BigDecimal;
import java.util.List;

public interface OfferService<ID>{
    OfferDTO getOfferById(ID offerId);
    List<OfferDTO> getAllOffers();
    OfferDTO addNewOffer(OfferDTO offerDTO);
    OfferDTO updateOfferDescription(ID offerId, String description);
    OfferDTO updateOfferImageUrl(ID offerId, String imageUrl);
    OfferDTO updateOfferPrice(ID offerId, BigDecimal price);
    void deleteOfferById(ID offerId);
}

