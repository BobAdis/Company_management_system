package hu.progmatic.company_management_system;

import hu.progmatic.company_management_system.models.User;
import hu.progmatic.company_management_system.repositories.UserRepository;
import hu.progmatic.company_management_system.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestUserService
{

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /*@Test
    public void testLoadUserByUsername_NonAdminUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("1234");
        user.setAdmin(false);
        user.setId(1L);
        user.setAlreadyLoggedIn(false);

        Optional<User> userOptional = Optional.of(user);

        when(userRepository.findByUsername(eq("testuser"))).thenReturn(userOptional);

        UserDetails result = userService.loadUserByUsername("testuser");

        assertEquals(result.getUsername(), "testuser");
        assertEquals(result.getPassword(), "1234");
        assertEquals(result.getAuthorities().size(), 1);

        for (GrantedAuthority authority : result.getAuthorities()) {
            assertEquals(authority.getAuthority(), "ROLE_USER");
        }
    }*/

    /*@Test
    public void testLoadUserByUsername_AdminUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("1234");
        user.setAdmin(true);
        user.setId(1L);
        user.setAlreadyLoggedIn(false);

        Optional<User> userOptional = Optional.of(user);

        when(userRepository.findByUsername(eq("testuser"))).thenReturn(userOptional);

        UserDetails result = userService.loadUserByUsername("testuser");

        assertEquals(result.getUsername(), "testuser");
        assertEquals(result.getPassword(), "1234");
        assertEquals(result.getAuthorities().size(), 2);

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>(result.getAuthorities());

        assertEquals(grantedAuthorityList.get(0).getAuthority(), "ROLE_ADMIN");
        assertEquals(grantedAuthorityList.get(1).getAuthority(), "ROLE_USER");

    }*/

}
