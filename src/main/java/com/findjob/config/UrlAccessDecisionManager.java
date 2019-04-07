package com.findjob.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author Chichiu Yeung
 * Created in 2018/12/14 9:49
 */
@Component
public class UrlAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        // 开发初期，暂时有角色都需要登录
        if (!configAttributes.isEmpty() && authentication instanceof AnonymousAuthenticationToken) {
            throw new BadCredentialsException("请先登录");
        }
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (ConfigAttribute attribute : configAttributes) {
            String requireRole = attribute.getAttribute();
            Iterator<? extends GrantedAuthority> it = authorities.iterator();
            while (it.hasNext()) {
                GrantedAuthority authority = it.next();
                if (!it.hasNext() && !authority.getAuthority().equals(requireRole)) {
                    throw new AccessDeniedException("权限不足!");
                }
                if (authority.getAuthority().equals(requireRole)) {
                    break;
                }
            }
        }
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

}
