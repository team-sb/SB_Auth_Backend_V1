package team.sb.authorizationserver.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team.sb.authorizationserver.domain.user.entity.User;
import team.sb.authorizationserver.domain.user.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

    @Override
    public UserDetails loadUserByUsername(String name) {
        User user = userRepository.findByUid(name)
                .orElseThrow(() -> new UsernameNotFoundException("user is not exists"));

        detailsChecker.check(user);

        return user;
    }

}
