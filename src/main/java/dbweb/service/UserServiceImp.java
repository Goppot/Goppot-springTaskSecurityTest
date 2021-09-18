package dbweb.service;

import dbweb.dao.UserDao;
import dbweb.model.Role;
import dbweb.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService, UserDetailsService {

    final UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void addUser(String name, int age, String email, String password, String role) {
        User user = new User();
        Set<Role>roles = new HashSet<>();
        if (role.equals("ADMIN") | role.equals("USER,ADMIN")){
            roles.add(new Role(1, "ROLE_ADMIN"));
            roles.add(new Role(2, "ROLE_USER"));
        } else {
            roles.add(new Role(2, "ROLE_USER"));
        }
        user.setName(name);
        user.setAge(age);
        user.setEmail(email);
        user.setPassword(passwordEncoder().encode(password));
        user.setRoles(roles);
        userDao.addUser(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> allUsers() {
        return userDao.allUsers();
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getShowId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user;
    }

    @Transactional
    @Override
    public void updateUser(int id, String name, int age, String email,  String password, String role) {
        User user = new User();
        Set<Role>roles = new HashSet<>();
        if (role.equals("ADMIN") | role.equals("USER,ADMIN")){
            roles.add(new Role(1, "ROLE_ADMIN"));
            roles.add(new Role(2, "ROLE_USER"));
        } else {
            roles.add(new Role(2, "ROLE_USER"));
        }
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        user.setEmail(email);
        user.setPassword(passwordEncoder().encode(password));
        user.setRoles(roles);
        userDao.updateUser(user);
    }

    @Transactional
    @Override
    public void removeUser(int id) {
        userDao.removeUser(id);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDao.getUserByEmail(email);
    }

    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

