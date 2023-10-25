package gre.jb.security;

//import gre.jb.controller.AccountController;

import gre.jb.entity.Account;
import gre.jb.service.accountService.AccountService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import java.io.IOException;

public class JwtAuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {
    private final static String TOKEN_HEADER = "authorization";
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AccountService accountService;

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authToken = httpRequest.getHeader(TOKEN_HEADER);
        if (authToken != null) {
            authToken = authToken.replace("Bearer ", "");
        }
        int check = jwtService.validateTokenLogin(authToken);
        if (check == JwtService.TOKEN_VALID) {
            String username = jwtService.getUsernameFromToken(authToken);
            Account user = accountService.findByUserName(username);
            String pw = user.getPassword();
            boolean enabled = true;
            boolean accountNonExpired = true;
            boolean credentialsNonExpired = true;
            boolean accountNonLocked = true;
            UserDetails userDetail = new User(username, pw, enabled, accountNonExpired,
                    credentialsNonExpired, accountNonLocked, accountService.getAuthorities(user.getId()));
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail,
                    null, userDetail.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
//            AccountController accountController = new AccountController();
//            accountController.logout(check);
        }
        chain.doFilter(request, response);
    }
}