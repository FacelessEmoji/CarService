package rut.miit.carservice.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rut.miit.carservice.dtos.input.CarModelDTO;
import rut.miit.carservice.models.entities.CarModel;
import rut.miit.carservice.models.enums.EngineType;
import rut.miit.carservice.models.enums.ModelCategory;
import rut.miit.carservice.models.enums.TransmissionType;
import rut.miit.carservice.repositories.CarModelRepository;
import rut.miit.carservice.services.CarModelService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarModelServiceImpl implements CarModelService<UUID> {

    @Autowired
    private final CarModelRepository modelRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public CarModelServiceImpl(CarModelRepository modelRepository, ModelMapper modelMapper) {
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CarModelDTO getModelById(UUID modelId) {
        return modelMapper.map(modelRepository.findById(modelId).orElseThrow(), CarModelDTO.class);
    }

    @Override
    public CarModelDTO getModelByNameAndBrand(String modelName, String brandName) {
        return modelMapper.map(modelRepository.findByNameAndBrand_Name(modelName, brandName), CarModelDTO.class);
    }

    @Override
    public List<CarModelDTO> getAllModels() {
        return modelRepository.findAll().stream()
                .map(m -> modelMapper.map(m, CarModelDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<CarModelDTO> getModelsByCriteria(ModelCategory category, EngineType engine, TransmissionType transmission, Integer maxMileage, BigDecimal maxPrice) {
        return modelRepository.findModelsByCriteria(category, engine, transmission, maxMileage, maxPrice).stream()
                .map(m -> modelMapper.map(m, CarModelDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<CarModelDTO> getModelsByBrandAndYears(String brandName, Integer startYear, Integer endYear) {
        return modelRepository.findAllByBrand_NameAndStartYearGreaterThanEqualAndEndYearGreaterThanEqualOrderByEndYearDesc(brandName, startYear, endYear).stream()
                .map(m -> modelMapper.map(m, CarModelDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CarModelDTO addNewModel(CarModelDTO modelDTO) {
        return modelMapper.map(modelRepository.save(modelMapper.map(modelDTO, CarModel.class)), CarModelDTO.class);
    }

    @Override
    public CarModelDTO updateModelName(UUID modelId, String modelName) {
        CarModel carModel = modelRepository.findById(modelId).orElseThrow();
        carModel.setName(modelName);
        modelRepository.save(carModel);
        return modelMapper.map(carModel, CarModelDTO.class);
    }

    @Override
    public CarModelDTO updateModelImageUrl(UUID modelId, String imageUrl) {
        CarModel carModel = modelRepository.findById(modelId).orElseThrow();
        carModel.setImageUrl(imageUrl);
        modelRepository.save(carModel);
        return modelMapper.map(carModel, CarModelDTO.class);
    }

    @Override
    public CarModelDTO updateModelStartYear(UUID modelId, int startYear) {
        CarModel carModel = modelRepository.findById(modelId).orElseThrow();
        carModel.setStartYear(startYear);
        modelRepository.save(carModel);
        return modelMapper.map(carModel, CarModelDTO.class);
    }

    @Override
    public CarModelDTO updateModelEndYear(UUID modelId, int endYear) {
        CarModel carModel = modelRepository.findById(modelId).orElseThrow();
        carModel.setEndYear(endYear);
        modelRepository.save(carModel);
        return modelMapper.map(carModel, CarModelDTO.class);
    }

    @Override
    public void deleteModelById(UUID modelId) {
        modelRepository.deleteById(modelId);
    }
}


