package com.abs.springauthprovider.component.mobile;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import javax.security.auth.Subject;
import java.util.Collection;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 17:39 2018/7/6
 * 手机号登录令牌
 **/
public class MobileAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final Object principal;

    public MobileAuthenticationToken(String mobile) {
        super(null);
        this.principal=mobile;
        setAuthenticated(false);
    }

    public MobileAuthenticationToken(Collection<? extends GrantedAuthority> authorities, Object principal) {
        super(authorities);
        this.principal = principal;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) {
        if (isAuthenticated){
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(isAuthenticated);
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}
