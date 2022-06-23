
package com.example.crudSpring.security.jwt;

import com.example.crudSpring.security.service.UserDetailsImpl;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;


//Se ejecuta en cada peticion, comprueba validez token a traves de clase provider,
//valida o lanza ecsepcion
public class JwtTokenFilter extends OncePerRequestFilter {
     
    private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);
    
    @Autowired
    JwtProvider jwtProvider;
    
    @Autowired
    UserDetailsImpl userDetailService;
            
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        try{
            String token = getToken(req);
            if(token != null && jwtProvider.validateToken(token)){
                //obtenemos usuario del token
                String nombreUsuario = jwtProvider.getNombreUsuarioFromToken(token);
                UserDetails userDetails = userDetailService.loadUserByUsername(nombreUsuario);
                //creamos autenticacio
              UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); 
              //pasamos usuario al contexto de autenticacion  
              SecurityContextHolder.getContext().setAuthentication(auth);
            }   
        }catch(Exception e){
            logger.error("fail en metodo doFilter");
        }
        filterChain.doFilter(req, res);
    }
    
    // metodo para extraer token(en el bearer llega con leyenda)
    //obtengo cabecera,valido y devuelvo con remplazo por cadena sin longitud
    private String getToken(HttpServletRequest request){
        String header = request.getHeader("Athorization");
        if(header !=null && header.startsWith("Bearer"))
            return header.replace("Bearer ", "");
        return null;
    }
    
    
    
}
