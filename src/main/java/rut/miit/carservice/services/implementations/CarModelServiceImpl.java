package rut.miit.carservice.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rut.miit.carservice.repositories.CarBrandRepository;
import rut.miit.carservice.services.dtos.input.CarModelDTO;
import rut.miit.carservice.models.entities.CarModel;
import rut.miit.carservice.models.enums.*;
import rut.miit.carservice.repositories.CarModelRepository;
import rut.miit.carservice.services.dtos.output.CarModelOutputDTO;
import rut.miit.carservice.services.interfaces.internalAPI.CarModelInternalService;
import rut.miit.carservice.services.interfaces.publicAPI.CarModelService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarModelServiceImpl implements CarModelService<String>, CarModelInternalService<String>{
    private CarModelRepository modelRepository;
    private CarBrandRepository brandRepository;
    private ModelMapper modelMapper;

    @Autowired
    public void setBrandRepository(CarBrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }
    @Autowired
    public void setModelRepository(CarModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }
    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CarModel getModelById(String modelId) {
        return modelRepository.findById(modelId).orElse(null);
    }

    @Override
    public CarModelOutputDTO getModelByBrandAndName(String brandName, String modelName) {
        CarModel carModel = modelRepository.findByBrand_NameAndName(brandName, modelName);
        if (carModel == null) {
            return null;
        }
        return modelMapper.map(carModel, CarModelOutputDTO.class);
    }

    @Override
    public List<CarModelOutputDTO> getAllModels() {
        return modelRepository.findAll().stream()
                .map(m -> modelMapper.map(m, CarModelOutputDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<CarModelOutputDTO> getModelsByCriteria(ModelCategory category, EngineType engine, TransmissionType transmission, Integer maxMileage, BigDecimal maxPrice) {
        return modelRepository.findModelsByCriteria(category, engine, transmission, maxMileage, maxPrice).stream()
                .map(m -> modelMapper.map(m, CarModelOutputDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<CarModelOutputDTO> getModelsByBrandAndYears(String brandName, Integer startYear, Integer endYear) {
        return modelRepository.findAllByBrand_NameAndStartYearGreaterThanEqualAndEndYearLessThanEqualOrderByEndYearDesc(brandName, startYear, endYear).stream()
                .map(m -> modelMapper.map(m, CarModelOutputDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CarModelDTO addNewModel(CarModelDTO carModelDTO) {
        CarModel carModel = modelMapper.map(carModelDTO, CarModel.class);
        carModel.setBrand(brandRepository.findById(carModelDTO.getBrand()).orElse(null));
        return modelMapper.map(modelRepository.saveAndFlush(modelMapper.map(carModel, CarModel.class)), CarModelDTO.class);
    }

    @Override
    public CarModelDTO updateModel(String modelId, CarModelDTO modelDTO) {
        CarModel carModel = modelRepository.findById(modelId).orElseThrow();
        carModel.setName(modelDTO.getName());
        carModel.setStartYear(modelDTO.getStartYear());
        carModel.setEndYear(modelDTO.getEndYear());
        carModel.setCategory(modelDTO.getCategory());
        carModel.setBrand(brandRepository.findById(modelDTO.getBrand()).orElse(null));
        return modelMapper.map(modelRepository.saveAndFlush(carModel), CarModelDTO.class);
    }

    @Override
    public CarModelDTO updateModelName(String modelId, String modelName) {
        CarModel carModel = modelRepository.findById(modelId).orElseThrow();
        carModel.setName(modelName);
        modelRepository.save(carModel);
        return modelMapper.map(carModel, CarModelDTO.class);
    }

    @Override
    public CarModelDTO updateModelImageUrl(String modelId, String imageUrl) {
        CarModel carModel = modelRepository.findById(modelId).orElseThrow();
        carModel.setImageUrl(imageUrl);
        modelRepository.save(carModel);
        return modelMapper.map(carModel, CarModelDTO.class);
    }

    @Override
    public CarModelDTO updateModelEndYear(String modelId, int endYear) {
        CarModel carModel = modelRepository.findById(modelId).orElseThrow();
        carModel.setEndYear(endYear);
        modelRepository.save(carModel);
        return modelMapper.map(carModel, CarModelDTO.class);
    }

    @Override
    public void deleteModelById(String modelId) {
        modelRepository.deleteById(modelId);
    }
}


