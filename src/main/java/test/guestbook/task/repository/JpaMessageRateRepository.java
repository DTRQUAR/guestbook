package test.guestbook.task.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import test.guestbook.task.model.MessageRate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Qouer on 05.07.2016.
 */

@Repository
@Transactional
public class JpaMessageRateRepository implements MessageRateRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void updateOrCreateMessageRate(MessageRate messageRate) {
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

    @Override
    public List<MessageRate> getAllMessageRate() {
        return em.createNamedQuery(MessageRate.GET_ALL, MessageRate.class).getResultList();
    }
}
