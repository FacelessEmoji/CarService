package rut.miit.carservice.services.interfaces.publicAPI;

import rut.miit.carservice.services.dtos.input.CarModelDTO;
import rut.miit.carservice.models.enums.EngineType;
import rut.miit.carservice.models.enums.ModelCategory;
import rut.miit.carservice.models.enums.TransmissionType;

import java.math.BigDecimal;
import java.util.List;

public interface CarModelService<ID>{
    CarModelDTO getModelByNameAndBrand(String modelName, String brandName);
    List<CarModelDTO> getAllModels();
    List<CarModelDTO> getModelsByBrandAndYears(String brandName, Integer startYear, Integer endYear);
    List<CarModelDTO> getModelsByCriteria(ModelCategory category, EngineType engine,
                                       TransmissionType transmission, Integer maxMileage, BigDecimal maxPrice);
    CarModelDTO addNewModel(CarModelDTO modelDTO);
    CarModelDTO updateModelName(ID modelId, String modelName);
    CarModelDTO updateModelImageUrl(ID modelId, String imageUrl);
    CarModelDTO updateModelStartYear(ID modelId, int startYear);
    CarModelDTO updateModelEndYear(ID modelId, int endYear);
}

