package rut.miit.carservice.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rut.miit.carservice.services.dtos.output.CarModelOutputDTO;
import rut.miit.carservice.services.interfaces.publicAPI.CarModelService;

import java.util.List;

/**
 * todo Document type CarModelController
 */
@RestController
@RequestMapping("/model")
public class CarModelController {
    CarModelService<String> modelService;

    @Autowired
    public CarModelController(CarModelService<String> modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/find/all")
    public List<CarModelOutputDTO> findAll(){
        return modelService.getAllModels();
    }

}
