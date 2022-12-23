package com.idev4.gateway.config;

import com.idev4.gateway.security.AuthoritiesConstants;
import com.idev4.gateway.security.jwt.JWTConfigurer;
import com.idev4.gateway.security.jwt.TokenProvider;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Configuration
@Import(SecurityProblemSupport.class)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final UserDetailsService userDetailsService;

    private final TokenProvider tokenProvider;

    private final CorsFilter corsFilter;

    private final SecurityProblemSupport problemSupport;

    public SecurityConfiguration(AuthenticationManagerBuilder authenticationManagerBuilder, UserDetailsService userDetailsService,
                                 TokenProvider tokenProvider, CorsFilter corsFilter, SecurityProblemSupport problemSupport) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDetailsService = userDetailsService;
        this.tokenProvider = tokenProvider;
        this.corsFilter = corsFilter;
        this.problemSupport = problemSupport;
    }

    @PostConstruct
    public void init() {
        try {
            authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        } catch (Exception e) {
            throw new BeanInitializationException("Security configuration failed", e);
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**").antMatchers("/app/**/*.{js,html}").antMatchers("/bower_components/**")
                .antMatchers("/i18n/**").antMatchers("/content/**").antMatchers("/swagger-ui/index.html").antMatchers("/test/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class).exceptionHandling()
                .authenticationEntryPoint(problemSupport).accessDeniedHandler(problemSupport).and().csrf().disable().headers()
                .frameOptions().disable().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                /*.antMatchers("/api/register").permitAll()
                .antMatchers("/api/activate").permitAll()
                .antMatchers("/api/authenticate").permitAll()
                .antMatchers("/api/account/reset-password/init").permitAll()
                .antMatchers("/api/account/reset-password/finish").permitAll()
                .antMatchers("/api/profile-info").permitAll()
                .antMatchers("/api/**").authenticated()*/
                .antMatchers("/api/**").permitAll().antMatchers("/management/health").permitAll().antMatchers("/management/**")
                .hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/v2/api-docs/**").permitAll()
                .antMatchers("/swagger-resources/configuration/ui").permitAll().antMatchers("/swagger-ui/index.html")
                .hasAuthority(AuthoritiesConstants.ADMIN).and().apply(securityConfigurerAdapter());

    }

    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider);
    }

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }

    // PROD AD
    @Inject
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.ldapAuthentication().userSearchBase("DC=kashf,DC=org,DC=pk") // don't add the base
                .userSearchFilter("(userPrincipalName={0})").groupSearchBase("DC=kashf,DC=org,DC=pk") // don't add the base
                .groupSearchFilter("userPrincipalName={0}").contextSource(getContextSource());
    }

    @Bean
    public LdapContextSource getContextSource() {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl("ldap://192.168.7.15:389");
        contextSource.setBase("DC=kashf,DC=org,DC=pk");
        contextSource.setUserDn("CN=mwx,OU=IT,DC=kashf,DC=org,DC=pk");
        contextSource.setPassword("miracle@mwx");
        contextSource.afterPropertiesSet(); // needed otherwise you will have a NullPointerException in spring
        return contextSource;
    }

    // TEST AD

    // @Inject
    // public void configureGlobal( AuthenticationManagerBuilder auth ) throws Exception {
    // auth.ldapAuthentication().userSearchBase( "o=myO,ou=NewMW" ) // don't add the base
    // .userSearchFilter( "(uid={0})" ).groupSearchBase( "ou=Groups" ) // don't add the base
    // .groupSearchFilter( "member={0}" ).contextSource( getContextSource() );
    // }
    //
    // @Bean
    // public LdapContextSource getContextSource() {
    // LdapContextSource contextSource = new LdapContextSource();
    // contextSource.setUrl( "ldap://172.16.42.1:389" );
    // contextSource.setBase( "DC=NewMW,DC=com" );
    // contextSource.setUserDn( "CN=mbasheer255,OU=Lahore,OU=BM,OU=Branches,DC=NewMW,DC=com" );
    // contextSource.setPassword( "abubakar@1" );
    // contextSource.afterPropertiesSet(); // needed otherwise you will have a NullPointerException in spring
    // return contextSource;
    // }
}
