package com.devteria.identity_service.service;

import com.devteria.identity_service.dto.request.AuthenticationRequest;
import com.devteria.identity_service.dto.request.IntrospectRequest;
import com.devteria.identity_service.dto.response.AuthenticationResponse;
import com.devteria.identity_service.dto.response.IntrospectResponse;
import com.devteria.identity_service.exception.AppException;
import com.devteria.identity_service.exception.ErrorCode;
import com.devteria.identity_service.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;

import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class AuthenticationService {
    UserRepository userRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected  String SIGNER_KEY;

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new AppException(ErrorCode.USER_DOESNT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated =  passwordEncoder.matches(request.getPassword(),user.getPassword());
        if(!authenticated)
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        var token = genarateToken(request.getUsername());

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        //Xác thực token và check token hết hạn hay chưa
        var token = request.getToken();

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expityTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        return  IntrospectResponse.builder()
                .valid(verified && expityTime.after(new Date()))
                .build();

    }

    private String genarateToken(String username) {
        //Để tạo token đầu tiên cần có:
        //header: chứa thuật toán sẽ sử dụng
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        //body: nội dung của token
        JWTClaimsSet jwtClaimSet = new  JWTClaimsSet.Builder()
                .subject(username)                      //Claim subject đại diện cho user login
                .issuer("devteria.com")                 //Xác định token được issue từ ai, định danh token
                .issueTime(new Date())                  //Thời gian issue token và thời gian hết hạn
                .expirationTime(new Date(
                        Instant.now().plus(1,ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("customClaim","Custom") //Dùng để custom giá trị vào body
                .build();

        Payload payload = new Payload(jwtClaimSet.toJSONObject());

        //Cách tạo toekn bằng nimbus
        JWSObject jwsObject = new JWSObject(header,payload); // Đến đoạn này là build xong infor của token

        //Tiếp theo đến ký token
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));        //Thuật toán ký
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token ",e);
            throw new RuntimeException(e);
        }
    }


}
