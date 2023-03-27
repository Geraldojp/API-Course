package com.example.belajar_spring.service;

import com.example.belajar_spring.exception.ExistedDataException;
import com.example.belajar_spring.model.Auth;
import com.example.belajar_spring.model.User;
import com.example.belajar_spring.model.request.LoginRequest;
import com.example.belajar_spring.model.request.RegisterRequest;
import com.example.belajar_spring.repository.AuthRepository;
import com.example.belajar_spring.utils.validation.JwtUtil;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements IAuthService{
    private AuthRepository authRepository;
    private IUserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    public AuthService(AuthRepository authRepository, IUserService userService) {
        this.authRepository = authRepository;
        this.userService = userService;
    }

    @Transactional
    @Override
    public String register(RegisterRequest registerRequest) {
        try {
            Auth auth = modelMapper.map(registerRequest, Auth.class);
            User user = modelMapper.map(registerRequest, User.class);
            if(authRepository.existsByEmailIgnoreCase(registerRequest.getEmail())){
                throw new ExistedDataException("Email already exist");
            }
            Auth authResult = authRepository.save(auth);
            user.setAuth(authResult);
            userService.create(user);
            return user.getName();
        }catch (ExistedDataException e){
            throw e;
        }
        catch (DataIntegrityViolationException e) {
            throw new EntityExistsException();
        }
    }

    @Override
    public String login(LoginRequest loginRequest) {
        try {
            Optional<Auth> auth = authRepository.findById(loginRequest.getEmail());
            if (auth.isEmpty()) throw new RuntimeException("User is not found");
            if (!auth.get().getPassword().equals(loginRequest.getPassword())) {
                throw new RuntimeException("Password is not match");
            }

            String token = jwtUtil.generateToken(loginRequest.getEmail());
            return token;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
