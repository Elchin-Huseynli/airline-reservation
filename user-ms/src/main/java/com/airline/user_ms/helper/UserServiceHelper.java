package com.airline.user_ms.helper;

import com.airline.common_exception.exception.ApplicationException;
import com.airline.common_security.helper.SecurityHelper;
import com.airline.common_security.model.dto.response.AuthenticationResponse;
import com.airline.common_security.service.IJwtService;
import com.airline.user_ms.model.dto.response.JwtResponse;
import com.airline.user_ms.model.entity.Token;
import com.airline.user_ms.model.entity.User;
import com.airline.user_ms.repository.TokenRepository;
import com.airline.user_ms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class UserServiceHelper {

    private final TokenRepository tokenRepository;
    private final IJwtService jwtService;
    private final SecurityHelper securityHelper;
    private final UserRepository userRepository;

    public void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .revoked(false)
                .expired(false)
                .build();

        tokenRepository.save(token);
    }

    public void revokedAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokensByUser(user.getId());

        if (validUserTokens.isEmpty()) return;

        validUserTokens
                .forEach(t -> {t.setExpired(true); t.setRevoked(true);});

        tokenRepository.saveAll(validUserTokens);
    }

    public AuthenticationResponse getAuthenticationResponseGlobalResponse(User user) {
        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        revokedAllUserTokens(user);
        saveUserToken(user, accessToken);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public String generateOtp(){
        Random random = new Random();
        int otp = random.nextInt(0,9999);
        StringBuilder output = new StringBuilder(Integer.toString(otp));
        while (output.length() < 4 ) output.append("0").append(output);

        return output.toString();
    }

    public Map<User,String> checkToken(String authHeader, Long id) {
        if (!securityHelper.authHeaderIsValid(authHeader)) {
            throw new ApplicationException("TOKEN_IS_INVALID_EXCEPTION");
        }
        String jwt = authHeader.substring(7);
        String username = jwtService.extractUsername(jwt);

        User userBase = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("USERNAME_NOT_FOUND"));

        boolean equals = userBase.getUsername().equals(username);
        if (username != null && equals) {
            User user = userRepository.findByUsernameIgnoreCaseAndIsEnable(username, true)
                    .orElseThrow(() -> new UsernameNotFoundException("Username doesn't exist: " + username));
            Map<User,String> map = new HashMap<>();
            map.put(user,jwt);
            return map;
        }
        throw new ApplicationException("TOKEN_IS_INVALID_EXCEPTION");
    }

    public JwtResponse checkAuth(String authHeader, Long id){
        Map<User, String> userAndJwt = checkToken(authHeader, id);

        User user = userAndJwt.keySet().stream()
                .findAny()
                .orElseThrow(()->new ApplicationException("USERNAME_NOT_FOUND"));

        String jwt = userAndJwt.values().stream()
                .findAny()
                .orElseThrow(() -> new ApplicationException("TOKEN_IS_INVALID_EXCEPTION"));

        return JwtResponse.builder()
                .jwt(jwt)
                .user(user)
                .build();
    }

}
