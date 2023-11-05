package rut.miit.carservice.services.interfaces.internalAPI;

import rut.miit.carservice.models.entities.Offer;


/**
 * todo Document type Offer
 */
public interface OfferInternalService<ID>{
    Offer getOfferById(ID offerId);
    void deleteOfferById(ID offerId);
}
