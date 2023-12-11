package rut.miit.carservice.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import rut.miit.carservice.services.dtos.input.CarBrandDTO;
import rut.miit.carservice.models.entities.CarBrand;
import rut.miit.carservice.repositories.CarBrandRepository;
import rut.miit.carservice.services.interfaces.internalAPI.CarBrandInternalService;
import rut.miit.carservice.services.interfaces.publicAPI.CarBrandService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableCaching
public class CarBrandServiceImpl implements CarBrandService<String>, CarBrandInternalService<String> {
    private CarBrandRepository brandRepository;
    private ModelMapper modelMapper;

    @Autowired
    public void setBrandRepository(CarBrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
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
    @Cacheable("brands")
    public List<CarBrandDTO> getAllBrands() {
        return brandRepository.findAll().stream()
                .map((c)-> modelMapper.map(c, CarBrandDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CarBrandDTO addNewBrand(String brandName) {
        CarBrandDTO brand = new CarBrandDTO(brandName);
        return modelMapper.map(brandRepository.saveAndFlush(modelMapper.map(brand,CarBrand.class)),CarBrandDTO.class);
    }

    @Override
    @CacheEvict(value = "brands", allEntries = true)
    public CarBrandDTO addNewBrandDTO(CarBrandDTO brand) {
        return modelMapper.map(brandRepository.saveAndFlush(modelMapper.map(brand,CarBrand.class)),CarBrandDTO.class);
    }

    @Override
    public CarBrandDTO updateBrandName(String brandName, String newBrandName) {
        CarBrand newCarBrand = brandRepository.findByName(brandName);
        newCarBrand.setName(newBrandName);
        brandRepository.save(newCarBrand);
        return modelMapper.map(newCarBrand, CarBrandDTO.class);
    }

    @Override
    @CacheEvict(value = "brands", allEntries = true)
    public CarBrandDTO updateBrandNameByID(String brandId, String newBrandName) {
        CarBrand newCarBrand = brandRepository.findById(brandId).orElse(null);
        newCarBrand.setName(newBrandName);
        brandRepository.save(newCarBrand);
        return modelMapper.map(newCarBrand, CarBrandDTO.class);
    }

    @Override
    public void deleteBrandById(String brandId) {
        brandRepository.deleteById(brandId);
    }
}
