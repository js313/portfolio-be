package com.jeenit.portfolio.audit;

import com.jeenit.portfolio.model.Admin;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("baseEntityAuditAware")
public class BaseEntityAuditAware implements AuditorAware<Admin> {
    @Override
    public Optional<Admin> getCurrentAuditor() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(Admin.class::cast);
    }
}
