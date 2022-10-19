package hu.progmatic.company_management_system.models;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public enum Position implements GrantedAuthority{

    WAREHOUSEWORKER,
    PRODUCTIONMANAGER,
    ADMIN,
    CEO,
    HR_MANAGER,
    TRADE_MANAGER,
    MACHINE_OPERATOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
