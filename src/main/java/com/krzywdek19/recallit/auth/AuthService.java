package com.krzywdek19.recallit.auth;

import com.krzywdek19.recallit.dto.AuthResponse;
import com.krzywdek19.recallit.exception.AuthError;
import com.krzywdek19.recallit.exception.AuthException;
import com.krzywdek19.recallit.request.LoginRequest;
import com.krzywdek19.recallit.request.RegisterRequest;
import com.krzywdek19.recallit.user.USER_ROLE;
import com.krzywdek19.recallit.user.User;
import com.krzywdek19.recallit.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthResponse signup(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AuthException(AuthError.USERNAME_TAKEN);
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AuthException(AuthError.EMAIL_TAKEN);
        }

        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(USER_ROLE.USER)
                .build();

        userRepository.save(user);
        var token = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse authenticate(LoginRequest request) {
        try {
            var authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            var user = (User) authentication.getPrincipal();
            var token = jwtService.generateToken(user);

            return AuthResponse.builder()
                    .token(token)
                    .build();

        } catch (BadCredentialsException e) {
            log.warn("Failed login attempt for user: {}", request.getUsername());
            throw new AuthException(AuthError.INVALID_CREDENTIALS);
        }
    }
}
