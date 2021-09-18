package dbweb.dao;

import dbweb.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext//(unitName=)
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> allUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByEmail(String email) {
        return entityManager.createQuery("select u from User u where u.email=:email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void removeUser(int id) {
        User usr = getUserById(id);
        entityManager.remove(usr);
    }
}
