package rut.miit.carservice.services.implementations;

import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rut.miit.carservice.models.entities.CarBrand;
import rut.miit.carservice.repositories.CarBrandRepository;
import rut.miit.carservice.services.dtos.input.CarBrandDTO;
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
    private CarModelRepository modelRepository;
    private CarBrandRepository brandRepository;
    private ModelMapper modelMapper;
    private ValidationUtilImpl validationUtil;

    @Autowired
    public void setModelRepository(CarModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }
    @Autowired
    public void setBrandRepository(CarBrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }
    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Autowired
    public void setValidationUtil(ValidationUtilImpl validationUtil) {
        this.validationUtil = validationUtil;
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


//    @Override
//    public CarModelDTO addNewModel(String carBrandName, CarModelDTO model) {
//        if (!this.validationUtil.isValid(model)) {
//            this.validationUtil
//                .violations(model)
//                .stream()
//                .map(ConstraintViolation::getMessage)
//                .forEach(System.out::println);
//        } else {
//            try {
//                CarBrandDTO brandDTO = modelMapper.map(brandRepository.findByName(carBrandName), CarBrandDTO.class);
//                model.setBrandDTO(brandDTO);
//                return modelMapper.map(modelRepository.saveAndFlush(modelMapper.map(model, CarModel.class)), CarModelDTO.class);
//            } catch (Exception e) {
//                System.out.println("Some thing went wrong!");
//            }
//        }
//
//        return null;
//    }

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


