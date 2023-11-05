package rut.miit.carservice.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rut.miit.carservice.services.dtos.input.UserRoleDTO;
import rut.miit.carservice.models.entities.UserRole;
import rut.miit.carservice.models.enums.UserRoleType;
import rut.miit.carservice.repositories.UserRoleRepository;
import rut.miit.carservice.services.interfaces.internalAPI.UserRoleInternalService;
import rut.miit.carservice.services.interfaces.publicAPI.UserRoleService;
import rut.miit.carservice.util.ValidationUtilImpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService<UUID>, UserRoleInternalService<UUID> {
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
    public UserRole getRoleById(UUID roleId) {
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
    public UserRoleDTO addNewRole(UserRoleDTO roleDTO) {
        return modelMapper.map(roleRepository.save(modelMapper.map(roleDTO, UserRole.class)), UserRoleDTO.class);
    }

    @Override
    public UserRoleDTO updateRoleName(UUID roleId, UserRoleType roleName) {
        UserRole role = roleRepository.findById(roleId).orElseThrow();
        role.setRole(roleName);
        roleRepository.save(role);
        return modelMapper.map(role, UserRoleDTO.class);
    }

    @Override
    public void deleteRoleById(UUID roleId) {
        roleRepository.deleteById(roleId);
    }
}

