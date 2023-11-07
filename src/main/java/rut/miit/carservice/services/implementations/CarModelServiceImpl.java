package rut.miit.carservice.services.implementations;

import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rut.miit.carservice.services.dtos.input.CarModelDTO;
import rut.miit.carservice.models.entities.CarModel;
import rut.miit.carservice.models.enums.*;
import rut.miit.carservice.repositories.CarModelRepository;
import rut.miit.carservice.services.dtos.output.CarModelOutputDTO;
import rut.miit.carservice.services.interfaces.internalAPI.CarModelInternalService;
import rut.miit.carservice.services.interfaces.publicAPI.CarModelService;
import rut.miit.carservice.util.serviceValidators.ValidationUtilImpl;

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
    public CarModelOutputDTO getModelByBrandAndName(String brandName, String modelName) {
        return modelMapper.map(modelRepository.findByBrand_NameAndName(brandName, modelName), CarModelOutputDTO.class);
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
    public CarModelDTO addNewModel(CarModelDTO model) {
        if (!this.validationUtil.isValid(model)) {
            this.validationUtil
                .violations(model)
                .stream()
                .map(ConstraintViolation::getMessage)
                .forEach(System.out::println);
        } else {
            try {
                return modelMapper.map(modelRepository.saveAndFlush(modelMapper.map(model, CarModel.class)), CarModelDTO.class);
            } catch (Exception e) {
                System.out.println("Some thing went wrong!");
            }
        }

        return null;

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


