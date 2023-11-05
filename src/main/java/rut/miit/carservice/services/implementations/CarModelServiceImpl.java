package rut.miit.carservice.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rut.miit.carservice.services.dtos.input.CarModelDTO;
import rut.miit.carservice.models.entities.CarModel;
import rut.miit.carservice.models.enums.EngineType;
import rut.miit.carservice.models.enums.ModelCategory;
import rut.miit.carservice.models.enums.TransmissionType;
import rut.miit.carservice.repositories.CarModelRepository;
import rut.miit.carservice.services.interfaces.internalAPI.CarModelInternalService;
import rut.miit.carservice.services.interfaces.publicAPI.CarModelService;
import rut.miit.carservice.util.ValidationUtilImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarModelServiceImpl implements CarModelService<String>, CarModelInternalService<String>{
    private final CarModelRepository modelRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtilImpl validationUtil;

    @Autowired
    public CarModelServiceImpl(CarModelRepository modelRepository, ModelMapper modelMapper, ValidationUtilImpl validationUtil) {
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public CarModel getModelById(String modelId) {
        return modelRepository.findById(modelId).orElse(null);
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
    public CarModelDTO updateModelStartYear(String modelId, int startYear) {
        CarModel carModel = modelRepository.findById(modelId).orElseThrow();
        carModel.setStartYear(startYear);
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


