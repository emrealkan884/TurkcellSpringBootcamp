package com.turkcell.spring.business.concretes;

import com.turkcell.spring.business.abstracts.AuthService;
import com.turkcell.spring.business.abstracts.RoleService;
import com.turkcell.spring.core.exceptions.types.BusinessException;
import com.turkcell.spring.core.jwt.JwtService;
import com.turkcell.spring.entities.concretes.Role;
import com.turkcell.spring.entities.concretes.User;
import com.turkcell.spring.entities.dtos.auth.AuthenticationResponse;
import com.turkcell.spring.entities.dtos.auth.LoginRequest;
import com.turkcell.spring.entities.dtos.auth.RegisterRequest;
import com.turkcell.spring.entities.dtos.auth.UpdateUserRolesRequest;
import com.turkcell.spring.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthManager implements AuthService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        Set<Role> roles = new HashSet<>();

        Role role = roleService.getByRoleName("USER");
        roles.add(role);

        User user = User.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .roles(roles)
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

    @Override
    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }
    public void updateRolesForUser(Integer userId, UpdateUserRolesRequest updateUserRolesRequest) {

        checkIfAdmin();

        User user = userRepository.findById(userId).orElseThrow(() -> new BusinessException("Kullanıcı bulunamadı"));
        String roleName = updateUserRolesRequest.getRoleName();
        Set<Role> userRoles = user.getRoles();
        Role newRole = roleService.getByRoleName(roleName);
        //Eger veritabaninda bizim ekledigimiz rol yoksa veritabanina yeni bir rol ekle.
        if (newRole == null) {
            newRole = new Role();
            newRole.setRoleName(roleName);
        }
        // Eğer kullanıcının rolleri bu rolü zaten içeriyorsa ekleme.(Yinelemeyi engellemek icin)
        if (!userRoles.contains(newRole)) {
            userRoles.add(newRole);
            user.setRoles(userRoles);
            userRepository.save(user);
        }
    }

    //Giris yapmis kullanicinin Rolu Admin mi, degil mi?
    private void checkIfAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {

        } else {
            throw new AccessDeniedException("Yetkisiz erişim");
        }
    }
}
