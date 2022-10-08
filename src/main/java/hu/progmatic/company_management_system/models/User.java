package hu.progmatic.company_management_system.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
public class User implements UserDetails{
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private boolean alreadyLoggedIn;

    private boolean isEnabled;
    @Enumerated(EnumType.STRING)
    private Position position;

    private boolean admin;

    public User() {
        isEnabled = true;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        isEnabled = true;
    }

    public User(String username, String password, Position position, boolean admin) {
        this(username, password);
        this.position = position;
        this.admin = admin;
        isEnabled = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isEnabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isEnabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isEnabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> auths = new ArrayList<>();
        if (Position.WAREHOUSEWORKER == position){
            SimpleGrantedAuthority auth = new SimpleGrantedAuthority("ROLE_WAREHOUSEWORKER");
            auths.add(auth);

        } else if (Position.PRODUCTIONMANAGER == position) {
            SimpleGrantedAuthority auth2 = new SimpleGrantedAuthority("ROLE_PRODUCTIONMANAGER");
            auths.add(auth2);

        } else if (Position.ADMIN == position) {
            SimpleGrantedAuthority auth3 = new SimpleGrantedAuthority("ROLE_ADMIN");
            auths.add(auth3);

        } else if (Position.CEO == position) {
            SimpleGrantedAuthority auth4 = new SimpleGrantedAuthority("ROLE_CEO");
            auths.add(auth4);

        } else if (Position.FINANCE_STAFF == position) {
            SimpleGrantedAuthority auth5 = new SimpleGrantedAuthority("ROLE_FINANCE_STAFF");
            auths.add(auth5);

        } else if (Position.TRADE_MANAGER == position) {
            SimpleGrantedAuthority auth6 = new SimpleGrantedAuthority("ROLE_TRADE_MANAGER");
            auths.add(auth6);
        }

        else if (Position.MACHINE_OPERATOR == position) {
            SimpleGrantedAuthority auth7 = new SimpleGrantedAuthority("ROLE_MACHINE_OPERATOR");
            auths.add(auth7);
        }

        return auths;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAlreadyLoggedIn() {
        return alreadyLoggedIn;
    }

    public void setAlreadyLoggedIn(boolean alreadyLoggedIn) {
        this.alreadyLoggedIn = alreadyLoggedIn;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
