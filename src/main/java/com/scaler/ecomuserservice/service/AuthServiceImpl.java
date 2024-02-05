package com.scaler.ecomuserservice.service;

import com.scaler.ecomuserservice.dto.UserDto;
import com.scaler.ecomuserservice.exceptions.InvalidCredentialException;
import com.scaler.ecomuserservice.exceptions.SessionNotFoundException;
import com.scaler.ecomuserservice.exceptions.UserNotFoundException;
import com.scaler.ecomuserservice.mapper.UserEntityDtoMapper;
import com.scaler.ecomuserservice.model.Session;
import com.scaler.ecomuserservice.model.SessionStatus;
import com.scaler.ecomuserservice.model.User;
import com.scaler.ecomuserservice.repository.SessionRepository;
import com.scaler.ecomuserservice.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.MultiValueMapAdapter;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.*;

@Service
public class AuthServiceImpl implements AuthService{
    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder; //comes with spring maven security dependency

    public AuthServiceImpl(UserRepository userRepository, SessionRepository sessionRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public ResponseEntity <List<Session>> getAllSession(){
        List<Session> sessions=sessionRepository.findAll();
        return ResponseEntity.ok(sessions);
    }

    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    public ResponseEntity <UserDto> login(String email,String password){
        //get user details from DB
        Optional<User> userOptional=userRepository.findByEmail(email);
        if (userOptional.isEmpty()){
            throw new UserNotFoundException("user not found with email"+email);
        }

        User user=userOptional.get();
        //verify the user password given at athe time of login
       // if (!user.getPassword().equals(password)){ will use bCrypt password
         if (!bCryptPasswordEncoder.matches(password,user.getPassword())){
            throw new InvalidCredentialException("invalid credentials");
        }
       // token generation...token should be part of header
        //String token=RandomStringUtils.randomAlphanumeric(30);lets generate token by jwt
        MacAlgorithm algo= Jwts.SIG.HS256; //HS25 algo added.
        SecretKey key=algo.key().build();

        //add claims(attributes) for jwt
        Map<String,Object> jsonforJwt=new HashMap<>();

        jsonforJwt.put("userId",user.getId());
        jsonforJwt.put("roles",user.getRolls());
        jsonforJwt.put("createdAt",new Date());
        jsonforJwt.put("expiryAt",new Date(LocalDate.now().plusDays(3).toEpochDay()));

        String token=Jwts.builder()
                .claims(jsonforJwt)//added the claims
                .signWith(key,algo) //added the algo and key
                .compact();         //building the token


        //session creation
        Session session=new Session();
        session.setLoginAt(new Date());
        session.setExpiringAt(new Date(LocalDate.now().plusDays(3).toEpochDay()));
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setUser(user);
        session.setToken(token);
        sessionRepository.save(session);

        //generating the response

        UserDto userDto= UserEntityDtoMapper.getUserDtofromUserEntity(user);
        //setting up the headers
        MultiValueMapAdapter<String,String> headers=new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE,token);
        ResponseEntity<UserDto> response=new ResponseEntity<>(userDto,headers, HttpStatus.OK);
        return response;
    }

    public ResponseEntity<Void> logout(String token,Long userId){
        Optional<Session> optionalSession=sessionRepository.findByTokenAndUser_Id(token,userId);
        if (optionalSession.isEmpty()){
            throw new SessionNotFoundException("Session not found ");
        }
        Session session= optionalSession.get();
        session.setSessionStatus(SessionStatus.EXPIRED);
        sessionRepository.save(session);
        return ResponseEntity.ok().build();
    }

    public UserDto signUp(String email,String password,String profilename){
        User user=new User();
        user.setEmail(email);
        user.setProfileName(profilename);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        User user1=userRepository.save(user);
        return UserDto.from(user1);

    }
    public SessionStatus validate(String token,Long userId){
        //TODO check expiry // Jwts Parser -> parse(means decode) the encoded JWT token to read the claims
        Optional<Session> optionalSession=sessionRepository.findByTokenAndUser_Id(token,userId);
        if(optionalSession.isEmpty() || optionalSession.get().getSessionStatus().equals(SessionStatus.EXPIRED) ){
            throw new SessionNotFoundException("session not found --");
        }
        Session session= optionalSession.get();
      return SessionStatus.ACTIVE;
    }
}
