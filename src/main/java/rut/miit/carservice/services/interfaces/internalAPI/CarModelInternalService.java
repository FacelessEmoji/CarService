package rut.miit.carservice.services.interfaces.internalAPI;

import rut.miit.carservice.models.entities.CarModel;

/**
 * todo Document type CarModelInternalService
 */
public interface CarModelInternalService<ID> {
    CarModel getModelById(ID modelId);
    void deleteModelById(ID modelId);
}
