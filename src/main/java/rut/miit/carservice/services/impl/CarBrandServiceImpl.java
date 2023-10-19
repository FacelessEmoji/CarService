package rut.miit.carservice.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rut.miit.carservice.dtos.input.CarBrandDTO;
import rut.miit.carservice.models.entities.CarBrand;
import rut.miit.carservice.repositories.CarBrandRepository;
import rut.miit.carservice.services.CarBrandService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarBrandServiceImpl implements CarBrandService<UUID> {
    @Autowired
    private final CarBrandRepository brandRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public CarBrandServiceImpl(CarBrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CarBrandDTO getBrandById(UUID brandId) {
        return modelMapper.map(brandRepository.findById(brandId).orElseThrow(), CarBrandDTO.class);
    }

    @Override
    public CarBrandDTO getBrandByName(String brandName) {
        return modelMapper.map(brandRepository.findByName(brandName), CarBrandDTO.class);
    }

    @Override
    public List<CarBrandDTO> getAllBrands() {
        return brandRepository.findAll().stream()
                .map((c)-> modelMapper.map(c, CarBrandDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CarBrandDTO addNewBrand(CarBrandDTO brandDTO) {
        return modelMapper.map(brandRepository.save(modelMapper.map(brandDTO, CarBrand.class)), CarBrandDTO.class);
    }

    @Override
    public CarBrandDTO updateBrandName(UUID brandId, String brandName) {
        CarBrand newCarBrand = brandRepository.findById(brandId).orElseThrow();
        newCarBrand.setName(brandName);
        brandRepository.save(newCarBrand);
        return modelMapper.map(newCarBrand, CarBrandDTO.class);
    }

    @Override
    public void deleteBrandById(UUID brandId) {
        brandRepository.deleteById(brandId);
    }
}
