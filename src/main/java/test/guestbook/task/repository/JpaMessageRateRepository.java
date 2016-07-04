package test.guestbook.task.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import test.guestbook.task.model.MessageRate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Qouer on 04.07.2016.
 */

@Repository
@Transactional
public class JpaMessageRateRepository implements MessageRateRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void updateMessageRate(MessageRate messageRate) {
        if (messageRate.getId() == null){
            em.persist(messageRate);
        }else{
            em.merge(messageRate);
        }
    }

    @Override
    public void deleteMessageRate(Integer messageRate_id) {
        em.createNamedQuery(MessageRate.DELETE_BY_ID)
                .setParameter("id", messageRate_id).executeUpdate();
    }
}
