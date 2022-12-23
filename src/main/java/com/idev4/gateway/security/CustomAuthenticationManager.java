package com.idev4.gateway.security;

import com.idev4.gateway.repository.UserRepository;
import com.idev4.gateway.service.dto.LdapUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.logging.Level;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationManager.class);
    private final UserRepository userRepository;
    @Autowired
    private final LdapContextSource ldapContextSource;
    LdapAuthenticationProvider provider = null;

    public CustomAuthenticationManager(UserRepository userRepository, LdapContextSource ldapContextSource) {
        this.userRepository = userRepository;
        this.ldapContextSource = ldapContextSource;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {

        BindAuthenticator bindAuth = new BindAuthenticator(ldapContextSource);
        FilterBasedLdapUserSearch userSearch = new FilterBasedLdapUserSearch("", "(sAMAccountName={0})", ldapContextSource);
        try {
            bindAuth.setUserSearch(userSearch);
            bindAuth.afterPropertiesSet();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(CustomAuthenticationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        provider = new LdapAuthenticationProvider(bindAuth);
        provider.setUserDetailsContextMapper(new UserDetailsContextMapper() {

            @Override
            public void mapUserToContext(UserDetails arg0, DirContextAdapter arg1) {
                // TODO Auto-generated method stub

            }

            @Override
            public UserDetails mapUserFromContext(DirContextOperations ctx, String username,
                                                  Collection<? extends GrantedAuthority> clctn) {
                // TODO Auto-generated method stub
                LdapUser ldapUser = new LdapUser(username, "1", clctn);
                ldapUser.cn = ctx.getStringAttribute("cn");
                ldapUser.sn = ctx.getStringAttribute("sn");
                ldapUser.role = ctx.getStringAttribute("distinguishedName");
                ldapUser.CN = ctx.getStringAttribute("originalAttrs");
                return ldapUser;
            }
        });
        return provider.authenticate(authentication);
    }
}
