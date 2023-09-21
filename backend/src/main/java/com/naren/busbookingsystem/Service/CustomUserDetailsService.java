package com.naren.busbookingsystem.Service;

import com.naren.busbookingsystem.Dao.UserDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    public CustomUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {
        return userDao.findUserByUserName(userName)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Username %s not found".
                                        formatted(userName)
                        ));
    }
}
