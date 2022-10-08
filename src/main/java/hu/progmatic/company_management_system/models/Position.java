package hu.progmatic.company_management_system.models;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public enum Position implements GrantedAuthority{

    WAREHOUSEWORKER,
    PRODUCTIONMANAGER,
    ACCOUNTANT,
    ADMIN,
    CEO,
    FINANCE_STAFF,
    TRADE_MANAGER,
    MACHINE_OPERATOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
