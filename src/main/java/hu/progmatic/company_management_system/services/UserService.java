package hu.progmatic.company_management_system.services;

import hu.progmatic.company_management_system.models.Position;
import hu.progmatic.company_management_system.models.User;
import hu.progmatic.company_management_system.models.hr_accounting.Employee;
import hu.progmatic.company_management_system.repositories.EmployeeRepo;
import hu.progmatic.company_management_system.repositories.UserRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final EmployeeRepo employeeRepo;

    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, EmployeeRepo employeeRepo, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.employeeRepo = employeeRepo;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow();
    }

    public User getLoggedInUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Transactional
    public User saveUser(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAllUser() {

        return new ArrayList<>((Collection) userRepository.findAll());
    }

    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    public User findEmployee(Employee e){
        return userRepository.findByEmployee(e);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }

}
