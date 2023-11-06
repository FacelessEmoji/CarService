package rut.miit.carservice.services.interfaces.publicAPI;

import rut.miit.carservice.services.dtos.output.OfferWithDetailsDTO;
import rut.miit.carservice.services.dtos.input.OfferDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface OfferService<ID>{
    List<OfferWithDetailsDTO> getAllOffers();
    List<OfferWithDetailsDTO> getOffersByBrandAndModel(String brandName, String modelName);
    List<OfferWithDetailsDTO> getOffersBySellerUsername(String username);
    List<OfferWithDetailsDTO> getOffersCreatedBetweenDates(LocalDateTime startTime, LocalDateTime endTime);
    List<OfferWithDetailsDTO> getOffersByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    List<OfferWithDetailsDTO> getOffersByPriceBetweenAndName(String brandName, String modelName, BigDecimal minPrice, BigDecimal maxPrice);
    OfferDTO addNewOffer(OfferDTO offerDTO);
    OfferDTO updateOfferDescription(ID offerId, String description);
    OfferDTO updateOfferImageUrl(ID offerId, String imageUrl);
    OfferDTO updateOfferPrice(ID offerId, BigDecimal price);

}

