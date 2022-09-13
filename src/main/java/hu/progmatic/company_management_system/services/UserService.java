package hu.progmatic.company_management_system.services;

import hu.progmatic.company_management_system.models.User;
import hu.progmatic.company_management_system.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        User userData = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found."));


        if (userData.isAdmin()) {
            return new org.springframework.security.core.userdetails.User(
                    userData.getUsername(),
                    userData.getPassword(),
                    List.of(
                            new SimpleGrantedAuthority("ROLE_USER"),
                            new SimpleGrantedAuthority("ROLE_ADMIN")
                    )
            );
        } else {
            return new org.springframework.security.core.userdetails.User(
                    userData.getUsername(),
                    userData.getPassword(),
                    List.of(
                            new SimpleGrantedAuthority("ROLE_USER")
                    )
            );
        }
    }

    @Transactional
    public User saveUser(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAllUser() {

        return new ArrayList<>((Collection) userRepository.findAll());
    }


}
