package rut.miit.carservice.services;

import rut.miit.carservice.dtos.input.CarModelDTO;

import java.util.List;

public interface CarModelService<ID>{
    CarModelDTO getModelById(ID modelId);
    List<CarModelDTO> getAllModels();
    CarModelDTO addNewModel(CarModelDTO modelDTO);
    CarModelDTO updateModelName(ID modelId, String modelName);
    CarModelDTO updateModelImageUrl(ID modelId, String imageUrl);
    CarModelDTO updateModelStartYear(ID modelId, int startYear);
    CarModelDTO updateModelEndYear(ID modelId, int endYear);
    void deleteModelById(ID modelId);
}

