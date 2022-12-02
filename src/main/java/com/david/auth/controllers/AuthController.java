package com.david.auth.controllers;

import com.david.auth.entities.Client;
import com.david.auth.dto.LoginDTO;
import com.david.auth.services.ClientServiceImpl;
import com.david.auth.services.JwtUserDetailsService;
import com.david.auth.services.RoleServiceImpl;
import com.david.auth.services.TokenService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    protected final Log logger = LogFactory.getLog(this.getClass());
    private final TokenService tokenService;
    private final ClientServiceImpl clientService;
    private final RoleServiceImpl roleService;
    private final JwtUserDetailsService jwtUserDetailsService;
    private final AuthenticationManager authenticationManager;

    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager, ClientServiceImpl clientService, JwtUserDetailsService jwtUserDetailsService, RoleServiceImpl roleService) {
        this.tokenService = tokenService;
        this.clientService = clientService;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.roleService = roleService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO login) {
        try {
            Authentication authentication = autenticarUsuario(login.getUsername(), login.getPassword());
            String token = tokenService.generateToken(authentication);
            return token;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping("/register")
    public String createClient(@RequestBody Client client) {
        try {
            Client client_save = this.clientService.createClient(client);
            return "El cliente " + client_save.getUsername() + ", ha sido creado con exito";
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping("/index")
    public String index(Principal principal){
        return "Hola " + principal.getName();
    }

    private Authentication autenticarUsuario(String username, String password) throws Exception {
        try{
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch(DisabledException e){
            throw new Exception("Usuario deshabilitado");
        }catch(BadCredentialsException e){
            throw new BadCredentialsException("El username o contraseña es incorrecto");
        }
    }

}
