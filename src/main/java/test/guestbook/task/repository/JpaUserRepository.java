package test.guestbook.task.repository;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import test.guestbook.task.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Qouer on 02.07.2016.
 */

@Repository
@Transactional(readOnly = true)
public class JpaUserRepository implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User get(Integer user_id) {
        return em.find(User.class, user_id);
    }

    @Override
    public User getByEmail(String email) {
        List<User> userList = em.createNamedQuery(User.BY_EMAIL, User.class)
                .setParameter(1, email)
                .getResultList();
        return DataAccessUtils.singleResult(userList);
    }

    public List<User> getAll(){
        return em.createNamedQuery(User.ALL_SORTED, User.class)
                .getResultList();
    }

    @Transactional
    public User create(User user) {
        em.persist(user);
        return user;
    }

}
