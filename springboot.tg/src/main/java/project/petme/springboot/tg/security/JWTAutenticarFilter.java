package project.petme.springboot.tg.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import project.petme.springboot.tg.data.DetalheUsuarioData;
import project.petme.springboot.tg.domain.Usuario;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAutenticarFilter extends UsernamePasswordAuthenticationFilter {

    public static final int TOKEN_EXPIRACAO = 600_000; // 10 minutos

    public static final String TOKEN_SENHA = "aa74311a-ffc1-4061-8c8b-bca8bfc05ff3"; //TROCAR DE LUGAR

    private final AuthenticationManager authenticationManager;

    public JWTAutenticarFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            Usuario usuario = new ObjectMapper()
                    .readValue(request.getInputStream(), Usuario.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    usuario.getUsername(),
                    usuario.getSenha(),
                    new ArrayList<>()
            ));

        } catch (IOException e) {
            throw new RuntimeException("Falha ao autenticar usuario", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        DetalheUsuarioData usuarioData = (DetalheUsuarioData) authResult.getPrincipal();

        String token = JWT.create()
                .withSubject(usuarioData.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRACAO)) //EXPIRAÇÃO DO TOKEN
                .sign(Algorithm.HMAC512(TOKEN_SENHA));

        response.getWriter().write(token);
        response.getWriter().flush();
    }
}
