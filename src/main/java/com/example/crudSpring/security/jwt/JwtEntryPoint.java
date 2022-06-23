
package com.example.crudSpring.security.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


//Comprueba si hay token  autorizado

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {

    //lanza error en desarrollo/no usar en produccion
    private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
    
    //rechaza petciones no autorizadas
    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) 
            throws IOException, ServletException {
            logger.error("fail en el metodo commence");
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
       
    }
    
}
