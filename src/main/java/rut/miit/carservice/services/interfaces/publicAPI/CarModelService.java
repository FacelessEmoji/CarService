package rut.miit.carservice.services.interfaces.publicAPI;

import rut.miit.carservice.services.dtos.input.CarModelDTO;
import rut.miit.carservice.models.enums.EngineType;
import rut.miit.carservice.models.enums.ModelCategory;
import rut.miit.carservice.models.enums.TransmissionType;
import rut.miit.carservice.services.dtos.output.CarModelOutputDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CarModelService<ID>{
    CarModelOutputDTO getModelByBrandAndName(String brandName, String modelName);
    List<CarModelOutputDTO> getAllModels();
    List<CarModelOutputDTO> getAllModelsByBrand(String brandName);
    List<CarModelOutputDTO> getModelsByBrandAndYears(String brandName, Integer startYear, Integer endYear);
    List<CarModelOutputDTO> getModelsByCriteria(ModelCategory category, EngineType engine,
                                       TransmissionType transmission, Integer maxMileage, BigDecimal maxPrice);
//    CarModelDTO addNewModel(String carBrandName, CarModelDTO modelDTO);
    CarModelDTO addNewModel(CarModelDTO modelDTO);
    CarModelDTO updateModel(ID modelId, CarModelDTO modelDTO);
    CarModelDTO updateModelName(ID modelId, String modelName);
    CarModelDTO updateModelImageUrl(ID modelId, String imageUrl);
    CarModelDTO updateModelEndYear(ID modelId, int endYear);
}

