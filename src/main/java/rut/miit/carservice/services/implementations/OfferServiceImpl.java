package rut.miit.carservice.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rut.miit.carservice.repositories.CarModelRepository;
import rut.miit.carservice.repositories.UserRepository;
import rut.miit.carservice.services.dtos.output.OfferWithDetailsDTO;
import rut.miit.carservice.services.dtos.input.OfferDTO;
import rut.miit.carservice.models.entities.Offer;
import rut.miit.carservice.models.enums.UserRoleType;
import rut.miit.carservice.repositories.OfferRepository;
import rut.miit.carservice.services.interfaces.internalAPI.OfferInternalService;
import rut.miit.carservice.services.interfaces.publicAPI.OfferService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService<String>, OfferInternalService<String> {
    private CarModelRepository modelRepository;
    private UserRepository userRepository;
    private OfferRepository offerRepository;
    private ModelMapper modelMapper;

    @Autowired
    public void setModelRepository(CarModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setOfferRepository(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Offer getOfferById(String offerId) {
        return offerRepository.findById(offerId).orElse(null);
    }

    @Override
    public List<OfferWithDetailsDTO> getAllOffers() {
        return offerRepository.findAll().stream()
                .map(o -> modelMapper.map(o, OfferWithDetailsDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<OfferWithDetailsDTO> getOffersByBrandAndModel(String brandName, String modelName) {
        return offerRepository.findAllByModel_Brand_NameAndModel_Name(brandName, modelName).stream()
                .map(o -> modelMapper.map(o, OfferWithDetailsDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<OfferWithDetailsDTO> getOffersBySellerUsername(String username) {
        return offerRepository.findAllBySeller_UsernameAndSeller_IsActive(username, true).stream()
                .map(o -> modelMapper.map(o, OfferWithDetailsDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<OfferWithDetailsDTO> getOffersCreatedBetweenDates(LocalDateTime startTime, LocalDateTime endTime) {
        return offerRepository.findAllByCreatedAfterAndCreatedBeforeOrderByCreated(startTime, endTime).stream()
                .map(o -> modelMapper.map(o, OfferWithDetailsDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<OfferWithDetailsDTO> getOffersByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return offerRepository.findAllByPriceGreaterThanEqualAndPriceLessThanEqual(minPrice, maxPrice) .stream()
            .map(o -> modelMapper.map(o, OfferWithDetailsDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<OfferWithDetailsDTO> getOffersByPriceBetweenAndName(String brandName, String modelName, BigDecimal minPrice, BigDecimal maxPrice) {
        return offerRepository.
            findAllByModel_Brand_NameAndModel_NameAndPriceGreaterThanEqualAndPriceLessThanEqual(brandName, modelName, minPrice, maxPrice).stream()
            .map(o -> modelMapper.map(o, OfferWithDetailsDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<OfferWithDetailsDTO> getOffersByPriceBetweenAndBrand(String brandName, BigDecimal minPrice, BigDecimal maxPrice) {
        return offerRepository.
            findAllByModel_Brand_NameAndPriceGreaterThanEqualAndPriceLessThanEqual(brandName, minPrice, maxPrice).stream()
            .map(o -> modelMapper.map(o, OfferWithDetailsDTO.class)).collect(Collectors.toList());
    }

    @Override
    public OfferDTO addNewOffer(OfferDTO offerDTO) {
        Offer offer = modelMapper.map(offerDTO, Offer.class);
        offer.setModel(modelRepository.findById(offerDTO.getModel()).orElse(null));
        offer.setSeller(userRepository.findById(offerDTO.getSeller()).orElse(null));
        return modelMapper.map(offerRepository.saveAndFlush(modelMapper.map(offer, Offer.class)), OfferDTO.class);
    }

    @Override
    public OfferDTO updateOffer(String offerId, OfferDTO offerDTO) {
        Offer offer = offerRepository.findById(offerId).orElseThrow();
        offer.setModel(modelRepository.findById(offerDTO.getModel()).orElse(null));
        offer.setDescription(offerDTO.getDescription());
        offer.setImageUrl(offerDTO.getImageUrl());
        offer.setPrice(offerDTO.getPrice());
        offer.setEngine(offerDTO.getEngine());
        offer.setTransmission(offerDTO.getTransmission());
        offer.setMileage(offerDTO.getMileage());
        offer.setYear(offerDTO.getYear());
        return modelMapper.map(offerRepository.saveAndFlush(modelMapper.map(offer, Offer.class)), OfferDTO.class);
    }

    @Override
    public OfferDTO updateOfferDescription(String offerId, String description) {
        Offer offer = offerRepository.findById(offerId).orElseThrow();
        offer.setDescription(description);
        offerRepository.save(offer);
        return modelMapper.map(offer, OfferDTO.class);
    }

    @Override
    public OfferDTO updateOfferImageUrl(String offerId, String imageUrl) {
        Offer offer = offerRepository.findById(offerId).orElseThrow();
        offer.setImageUrl(imageUrl);
        offerRepository.save(offer);
        return modelMapper.map(offer, OfferDTO.class);
    }

    @Override
    public OfferDTO updateOfferPrice(String offerId, BigDecimal price) {
        Offer offer = offerRepository.findById(offerId).orElseThrow();
        offer.setPrice(price);
        offerRepository.save(offer);
        return modelMapper.map(offer, OfferDTO.class);
    }

    @Override
    public void deleteOfferById(String offerId) {
        offerRepository.deleteById(offerId);
    }
}

