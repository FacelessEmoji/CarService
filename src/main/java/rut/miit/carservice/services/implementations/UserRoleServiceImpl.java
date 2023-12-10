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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService<String>, UserRoleInternalService<String> {
    private UserRoleRepository roleRepository;
    private ModelMapper modelMapper;

    @Autowired
    public void setRoleRepository(UserRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserRole getRoleById(String roleId) {
        return roleRepository.findById(roleId).orElse(null);
    }

    @Override
    public UserRoleDTO getRoleByName(UserRoleType roleName) {
        UserRole role = roleRepository.findByRole(roleName);
        if (role == null) {
            throw new IllegalArgumentException("Role not found: " + roleName);
        }
        return modelMapper.map(role, UserRoleDTO.class);
    }


    @Override
    public List<UserRoleDTO> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(r -> modelMapper.map(r, UserRoleDTO.class)).collect(Collectors.toList());
    }

    @Override
    public UserRoleDTO addNewRole(UserRoleDTO userRole) {
        return modelMapper.map(roleRepository.saveAndFlush(modelMapper.map(userRole, UserRole.class)), UserRoleDTO.class);
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

