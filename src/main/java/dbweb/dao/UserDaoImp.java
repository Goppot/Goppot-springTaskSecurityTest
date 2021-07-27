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
    public User showUserById(int id) {
        return entityManager.createQuery("select u from User u where u.id =:id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public User getUserByEmail(String email) {
        return entityManager.createQuery("select u from User u where u.email=:email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public void updateUser(int id, User usr) {
        entityManager.createQuery("update User u set u.name=:name, u.age=:age, u.email=:email, u.password=:password where u.id=:id")
                .setParameter("name", usr.getName())
                .setParameter("age", usr.getAge())
                .setParameter("email", usr.getEmail())
                .setParameter("password", usr.getPassword())
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void deleteUser(int id) {
        entityManager.createQuery("delete from User u where u.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void removeUser(User user) {
        User usr = entityManager.find(User.class, user.getId());
        entityManager.remove(usr);
    }
}
