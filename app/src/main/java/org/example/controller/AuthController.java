package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.Service.JwtService;
import org.example.Service.RefreshTokenService;
import org.example.Service.UserDetailsServiceImpl;
import org.example.auth.JwtAuthFilter;
import org.example.entities.RefreshToken;
import org.example.entities.UserInfo;
import org.example.model.UserInfoDto;
import org.example.response.JwtResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@AllArgsConstructor
@RestController
public class AuthController {

    @Autowired
    private final JwtAuthFilter jwtAuthFilter;

    @Autowired
    private final RefreshTokenService refreshTokenService;

    @Autowired
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("auth/v1/signup")
    public ResponseEntity SignUp(@RequestBody UserInfoDto userInfoDto){
        try{
            Boolean isSignUp = userDetailsService.signupUser(userInfoDto);
            if(Boolean.FALSE.equals(isSignUp)){
                return new ResponseEntity<>("Already Exist" , HttpStatus.BAD_REQUEST);

            }
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userInfoDto.getUserName());
            String jwtToken = jwtService.GenerateToken(userInfoDto.getUserName());
            return new ResponseEntity<>(JwtResponseDTO.builder().accessToken(jwtToken).
                    token(refreshToken.getToken()).build(), HttpStatus.OK;
        }
        catch (Exception e){
            return new ResponseEntity("Exception in User Service", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}
