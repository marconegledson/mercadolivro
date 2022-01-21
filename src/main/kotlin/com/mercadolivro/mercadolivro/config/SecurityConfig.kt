package com.mercadolivro.mercadolivro.config

import com.mercadolivro.mercadolivro.model.Role
import com.mercadolivro.mercadolivro.security.AutenticationFilter
import com.mercadolivro.mercadolivro.security.AutorizationFilter
import com.mercadolivro.mercadolivro.security.CustomAuthenticationEntryPoint
import com.mercadolivro.mercadolivro.service.UserDetailsCustomService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig() : WebSecurityConfigurerAdapter() {

    @Autowired
    private val userDetailsCustomService: UserDetailsCustomService? = null

    @Autowired
    private val customAuthenticationEntryPoint: CustomAuthenticationEntryPoint? = null

    private val endpoints = arrayOf("/customer", "/book")

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsCustomService).passwordEncoder(bCryptPasswordEncoder())
    }

    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()
        http.sessionManagement().sessionCreationPolicy((SessionCreationPolicy.STATELESS))
        http.addFilter(AutenticationFilter(authenticationManager()))
        http.authorizeRequests()
            .antMatchers("/admin/**").hasAuthority(Role.ADMIN.description)
            .antMatchers(HttpMethod.POST, *endpoints).permitAll() //varargs exploided
            .anyRequest().authenticated()
        http.addFilter(AutorizationFilter(authenticationManager()))
        http.exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint)
    }

    override fun configure(web: WebSecurity) {
        web.ignoring()
            .antMatchers(
                "/v2/apis-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/**",
                "/swagger-ui.html",
                "/webjars/**")
    }

    @Bean
    fun corsConfig(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.allowCredentials = true
        config.addAllowedOrigin("*")
        config.addAllowedHeader("*")
        config.addAllowedMethod("*")
        source.registerCorsConfiguration("/**", config)
        return CorsFilter(source)
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }


}
