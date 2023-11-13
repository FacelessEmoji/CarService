package rut.miit.carservice.services.implementations;

import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rut.miit.carservice.services.dtos.input.CarBrandDTO;
import rut.miit.carservice.models.entities.CarBrand;
import rut.miit.carservice.repositories.CarBrandRepository;
import rut.miit.carservice.services.interfaces.internalAPI.CarBrandInternalService;
import rut.miit.carservice.services.interfaces.publicAPI.CarBrandService;
import rut.miit.carservice.util.serviceValidators.ValidationUtilImpl;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarBrandServiceImpl implements CarBrandService<String>, CarBrandInternalService<String> {
    private CarBrandRepository brandRepository;
    private ModelMapper modelMapper;
    private ValidationUtilImpl validationUtil;

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
    public CarBrand getBrandById(String brandId) {
        return brandRepository.findById(brandId).orElse(null);
    }

    @Override
    public CarBrandDTO getBrandByName(String brandName) {
        CarBrand carBrand = brandRepository.findByName(brandName);
        if (carBrand == null) {
            return null;
        }
        return modelMapper.map(brandRepository.findByName(brandName), CarBrandDTO.class);
    }

    @Override
    public List<CarBrandDTO> getAllBrands() {
        return brandRepository.findAll().stream()
                .map((c)-> modelMapper.map(c, CarBrandDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CarBrandDTO addNewBrand(String brandName) {
        CarBrandDTO brand = new CarBrandDTO(brandName);
        if (!this.validationUtil.isValid(brand)) {
            this.validationUtil
                .violations(brand)
                .stream()
                .map(ConstraintViolation::getMessage)
                .forEach(System.out::println);
        } else {
            try {
                return modelMapper.map(brandRepository.saveAndFlush(modelMapper.map(brand,CarBrand.class)),CarBrandDTO.class);
            } catch (Exception e) {
                System.out.println("Some thing went wrong!");
            }
        }
        return null;
    }

    @Override
    public CarBrandDTO updateBrandName(String brandName, String newBrandName) {
        CarBrand newCarBrand = brandRepository.findByName(brandName);
        newCarBrand.setName(newBrandName);
        brandRepository.save(newCarBrand);
        return modelMapper.map(newCarBrand, CarBrandDTO.class);
    }

    @Deprecated
    @Override
    public void deleteBrandByName(String brandName) {
        brandRepository.deleteByName(brandName);
    }

    @Override
    public void deleteBrandById(String brandId) {
        brandRepository.deleteById(brandId);
    }
}
