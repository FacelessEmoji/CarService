package rut.miit.carservice.services.impl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rut.miit.carservice.dtos.input.OfferDTO;
import rut.miit.carservice.models.entities.Offer;
import rut.miit.carservice.models.entities.UserRole;
import rut.miit.carservice.models.enums.UserRoleType;
import rut.miit.carservice.repositories.OfferRepository;
import rut.miit.carservice.services.OfferService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService<UUID> {

    @Autowired
    private final OfferRepository offerRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OfferDTO getOfferById(UUID offerId) {
        return modelMapper.map(offerRepository.findById(offerId).orElseThrow(), OfferDTO.class);
    }

    @Override
    public List<OfferDTO> getAllOffers() {
        return offerRepository.findAll().stream()
                .map(o -> modelMapper.map(o, OfferDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<OfferDTO> getOffersByModelAndBrand(String modelName, String brandName) {
        return offerRepository.findAllByModelNameAndModel_BrandName(modelName, brandName).stream()
                .map(o -> modelMapper.map(o, OfferDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<OfferDTO> getOffersBySellerUsername(String username) {
        return offerRepository.findAllBySeller_UsernameAndSeller_IsActiveAndSeller_RoleRole(username, true, UserRoleType.USER).stream()
                .map(o -> modelMapper.map(o, OfferDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<OfferDTO> getOffersCreatedAfterDate(LocalDateTime time) {
        return offerRepository.findAllByCreatedAfterOrderByCreated(time).stream()
                .map(o -> modelMapper.map(o, OfferDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<OfferDTO> getOffersCreatedBeforeDate(LocalDateTime time) {
        return offerRepository.findAllByCreatedBeforeOrderByCreated(time).stream()
                .map(o -> modelMapper.map(o, OfferDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<OfferDTO> getOffersCreatedBetweenDates(LocalDateTime startTime, LocalDateTime endTime) {
        return offerRepository.findAllByCreatedAfterAndCreatedBeforeOrderByCreated(startTime, endTime).stream()
                .map(o -> modelMapper.map(o, OfferDTO.class)).collect(Collectors.toList());
    }

    @Override
    public OfferDTO addNewOffer(OfferDTO offerDTO) {
        return modelMapper.map(offerRepository.save(modelMapper.map(offerDTO, Offer.class)), OfferDTO.class);
    }

    @Override
    public OfferDTO updateOfferDescription(UUID offerId, String description) {
        Offer offer = offerRepository.findById(offerId).orElseThrow();
        offer.setDescription(description);
        offerRepository.save(offer);
        return modelMapper.map(offer, OfferDTO.class);
    }

    @Override
    public OfferDTO updateOfferImageUrl(UUID offerId, String imageUrl) {
        Offer offer = offerRepository.findById(offerId).orElseThrow();
        offer.setImageUrl(imageUrl);
        offerRepository.save(offer);
        return modelMapper.map(offer, OfferDTO.class);
    }

    @Override
    public OfferDTO updateOfferPrice(UUID offerId, BigDecimal price) {
        Offer offer = offerRepository.findById(offerId).orElseThrow();
        offer.setPrice(price);
        offerRepository.save(offer);
        return modelMapper.map(offer, OfferDTO.class);
    }

    @Override
    public void deleteOfferById(UUID offerId) {
        offerRepository.deleteById(offerId);
    }
}
