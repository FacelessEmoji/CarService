package rut.miit.carservice.services.implementations;

import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rut.miit.carservice.services.dtos.input.UserRoleDTO;
import rut.miit.carservice.models.entities.UserRole;
import rut.miit.carservice.models.enums.UserRoleType;
import rut.miit.carservice.repositories.UserRoleRepository;
import rut.miit.carservice.services.interfaces.internalAPI.UserRoleInternalService;
import rut.miit.carservice.services.interfaces.publicAPI.UserRoleService;
import rut.miit.carservice.util.serviceValidators.ValidationUtilImpl;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService<String>, UserRoleInternalService<String> {
    private final UserRoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtilImpl validationUtil;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository roleRepository, ModelMapper modelMapper, ValidationUtilImpl validationUtil) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public UserRole getRoleById(String roleId) {
        return roleRepository.findById(roleId).orElse(null);
    }

    @Override
    public UserRoleDTO getRoleByName(UserRoleType roleName) {
        return modelMapper.map(roleRepository.findByRole(roleName), UserRoleDTO.class);
    }

    @Override
    public List<UserRoleDTO> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(r -> modelMapper.map(r, UserRoleDTO.class)).collect(Collectors.toList());
    }

    @Override
    public UserRoleDTO addNewRole(UserRoleDTO userRole) {
        if (!this.validationUtil.isValid(userRole)) {
            this.validationUtil
                .violations(userRole)
                .stream()
                .map(ConstraintViolation::getMessage)
                .forEach(System.out::println);
        } else {
            try {
                return modelMapper.map(roleRepository.saveAndFlush(modelMapper.map(userRole, UserRole.class)), UserRoleDTO.class);
            } catch (Exception e) {
                System.out.println("Some thing went wrong!");
            }
        }
        return null;
    }

    @Override
    public UserRoleDTO updateRoleName(String roleId, UserRoleType roleName) {
        UserRole role = roleRepository.findById(roleId).orElseThrow();
        role.setRole(roleName);
        roleRepository.save(role);
        return modelMapper.map(role, UserRoleDTO.class);
    }

    @Override
    public void deleteRoleById(String roleId) {
        roleRepository.deleteById(roleId);
    }
}

