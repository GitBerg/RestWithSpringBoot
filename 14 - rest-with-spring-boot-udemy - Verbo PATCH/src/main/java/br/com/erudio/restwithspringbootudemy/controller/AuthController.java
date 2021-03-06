package br.com.erudio.restwithspringbootudemy.controller;

import static org.springframework.http.ResponseEntity.ok;

import br.com.erudio.restwithspringbootudemy.repository.UserRepository;
import br.com.erudio.restwithspringbootudemy.security.AccountCredentialsVO;
import br.com.erudio.restwithspringbootudemy.security.jwt.JwtTokenProvider;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserRepository repository;

    @ApiOperation(value = "Authenticate a user by credentials")
    @SuppressWarnings("rawtypes")
    @PostMapping(value = "/signin", produces = {"application/json", "application/xml","application/x-yaml"},
            consumes = {"application/json", "application/xml","application/x-yaml"})
    public ResponseEntity signin(@RequestBody AccountCredentialsVO data) {
            try{
                var username = data.getUsername();
                var password = data.getPassword();

                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

                var user = repository.findByUsername(username);
                var token = "";
                if(user  != null){
                    token = tokenProvider.createToken(username, user.getRoles());
                }else{
                    throw new UsernameNotFoundException("Username " + username + "not found!" );
                }

                Map<Object, Object> model = new HashMap<Object, Object>();
                model.put("username", username);
                model.put("token", token);
                return ok(model);
            }catch(Exception e){
                throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

}
