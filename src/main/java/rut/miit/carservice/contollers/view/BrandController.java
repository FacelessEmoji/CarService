package rut.miit.carservice.contollers.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rut.miit.carservice.services.dtos.input.CarBrandDTO;
import rut.miit.carservice.services.implementations.CarBrandServiceImpl;
import rut.miit.carservice.util.contollerValidators.BrandValidator;

import java.util.Collections;
import java.util.List;

/**
 * todo Document type CarBrandController
 */
@Controller
@RequestMapping("/brands")
public class BrandController {

    private  CarBrandServiceImpl brandService;
    private  BrandValidator brandValidator;

    @Autowired
    public void setBrandService(CarBrandServiceImpl brandService) {
        this.brandService = brandService;
    }

    @Autowired
    public void setBrandValidator(BrandValidator brandValidator) {
        this.brandValidator = brandValidator;
    }

    @GetMapping
    public String showBrands(@RequestParam(name = "search", required = false) String search, Model model) {
        List<CarBrandDTO> brands = (search == null || search.isEmpty())
            ? brandService.getAllBrands()
            : Collections.singletonList(brandService.getBrandByName(search));
        model.addAttribute("brands", brands);
        model.addAttribute("newBrand", new CarBrandDTO());
        return "test/brands"; // Чистый и ясный код
    }

    @PostMapping("/add")
    public String addBrand(@ModelAttribute("newBrand") CarBrandDTO newBrand, BindingResult bindingResult, Model model) {
        brandValidator.validate(newBrand.getName(), bindingResult);
        if (!bindingResult.hasErrors()) {
            brandService.addNewBrand(newBrand.getName());
            return "redirect:/brands";
        }
        model.addAttribute("brands", brandService.getAllBrands());
        return "test/brands"; // Обработка ошибок
    }

    @GetMapping("/edit/{name}")
    public String showEditForm(@PathVariable("name") String name, Model model) {
        CarBrandDTO brand = brandService.getBrandByName(name);
        if (brand != null) {
            model.addAttribute("brand", brand);
            return "test/edit_brand"; // Чистый и ясный код
        } else {
            return "redirect:/brands"; // Обработка отсутствующего бренда
        }
    }

    @PostMapping("/update")
    public String updateBrand(@RequestParam("oldName") String oldName, @RequestParam("newName") String newName, Model model) {
        if (oldName.equals(newName)) {
            model.addAttribute("error", "New brand name can't be the same as the old one");
            return "test/edit_brand"; // Проверка на ошибку: новое имя не должно совпадать со старым
        }

        // Проверка на уникальность нового имени
        if (brandService.getBrandByName(newName) != null) {
            model.addAttribute("error", "Brand name already exists");
            return "test/edit_brand"; // Проверка на ошибку: новое имя уже существует
        }

        CarBrandDTO updatedBrand = brandService.updateBrandName(oldName, newName);
        if (updatedBrand == null) {
            model.addAttribute("error", "Unable to update brand or brand not found");
            return "test/edit_brand"; // Обработка ошибки обновления
        }
        return "redirect:/brands"; // Чистый и ясный код
    }

}


