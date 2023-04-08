package com.example.myfirstproject.service;

import com.example.myfirstproject.model.DTOs.roles.RoleDTO;
import com.example.myfirstproject.model.DTOs.user.UsersDTO;
import com.example.myfirstproject.model.OfferEntity;
import com.example.myfirstproject.model.UserEntity;
import com.example.myfirstproject.model.RoleEntity;
import com.example.myfirstproject.model.enums.UserRoleEnum;
import com.example.myfirstproject.model.views.AllOffersView;
import com.example.myfirstproject.repository.OfferRepository;
import com.example.myfirstproject.repository.UserRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final OfferRepository offerRepository;


    public UserService(UserRepository userRepository, ModelMapper modelMapper, RoleService roleService, PasswordEncoder passwordEncoder, OfferRepository offerRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.offerRepository = offerRepository;
    }

    public void initFirstUser() {
        if (userRepository.count() == 0) {
            UserEntity adminUser = new UserEntity();

            RoleEntity userRoleEntity = roleService.findAdminRoleByName(UserRoleEnum.ADMIN);

            List<RoleEntity> roles = new ArrayList<>();
            roles.add(userRoleEntity);

            adminUser.setUsername("Kalin4");
            adminUser.setFirstName("Kalin");
            adminUser.setLastName("Krumov");
            adminUser.setEmail("kalin_krumov@gmail.com");
            adminUser.setPassword(passwordEncoder.encode("1234"));
            adminUser.setTelephoneNumber("0896464970");
            adminUser.setRoles(roles);

            this.userRepository.save(adminUser);
        }

    }


    public Optional<UserEntity> getByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public Optional<UserEntity> getByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public void register(UserEntity user) {

        RoleEntity roleByName = this.roleService.findUserRoleByName(UserRoleEnum.USER);
        user.setRoles(List.of(roleByName));

        this.userRepository.save(user);
    }

    public UserEntity getUserByUsername(String name) {
        return this.userRepository.findUserByUsername(name);
    }

    public void likeOffer(Principal principal, OfferEntity offer) {
        UserEntity currentUser = this.userRepository.findUserByUsername(principal.getName());

        List<OfferEntity> likedOffers = currentUser.getLikedOffers();

        Optional<OfferEntity> currentOffer = likedOffers.stream()
                .filter(o -> o.getId() == offer.getId())
                .findFirst();

        if (currentOffer.isPresent()) {
            return;
        }

        likedOffers.add(offer);

        currentUser.setLikedOffers(likedOffers);
        this.userRepository.save(currentUser);
    }


    public List<UsersDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(u -> this.modelMapper.map(u, UsersDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<UsersDTO> getUserDTOById(Long id) {
        return this.userRepository.findById(id)
                .map(u -> this.modelMapper.map(u, UsersDTO.class));
    }

    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }

    public List<AllOffersView> getLikedOffers(UserEntity currentUser) {
        return currentUser.getLikedOffers()
                .stream()
                .map(l -> new AllOffersView(
                        l.getId(),
                        l.getBrand().getName(),
                        l.getModel().getName(),
                        l.getPicture(),
                        l.getPrice(),
                        l.getDescription()
                )).collect(Collectors.toList());
    }


    public void disLikeOffer(Principal principal, OfferEntity offer) {
        UserEntity currentUser = this.userRepository.findUserByUsername(principal.getName());

        List<OfferEntity> likedOffers = currentUser.getLikedOffers();

        Optional<OfferEntity> currentOffer = likedOffers.stream()
                .filter(o -> o.getId() == offer.getId())
                .findFirst();

        if (currentOffer.isPresent()) {

            likedOffers.remove(likedOffers.stream().filter(o -> o.getId() == offer.getId()).findFirst().get());
            currentUser.setLikedOffers(likedOffers);

            this.userRepository.save(currentUser);
        }
    }

    public Optional<UsersDTO> getUserDTO(Long id) {
        return this.userRepository.findById(id)
                .map(u -> modelMapper.map(u, UsersDTO.class));
    }

    public void addRole(Long id, @Valid RoleDTO addRoleDTO) {

        UserEntity userById = this.userRepository.findById(id).get();
        List<RoleEntity> currentRoles = userById.getRoles();
        RoleEntity role = this.roleService.getRoleByName(UserRoleEnum.valueOf(addRoleDTO.getRole()));


            currentRoles.add(role);
            userById.setRoles(currentRoles);
            this.userRepository.save(userById);


    }


    public Optional<RoleDTO> getAddRoleDTO(Long id) {
        return this.userRepository.findById(id)
                .map(u -> modelMapper.map(u, RoleDTO.class));
    }

    public Optional<UserEntity> getUserById(Long id) {
        return this.userRepository.findById(id);
    }

    public boolean removeRole(Long id, @Valid RoleDTO userModel) {

        UserEntity user = this.userRepository.findById(id).get();

        if (user.getRoles().size() <= 1) {
            return false;
        }
        List<RoleEntity> userRoles = user.getRoles();
        RoleEntity toRemove = this.roleService.getRoleByName(UserRoleEnum.valueOf(userModel.getRole()));

        RoleEntity role = userRoles.stream()
                .filter(r -> r.getName().name().equals(toRemove.getName().name()))
                .findFirst()
                .get();

        userRoles.remove(role);
        user.setRoles(userRoles);
        this.userRepository.save(user);
        return true;
    }
}
