package test.guestbook.task.repository;

import org.springframework.dao.support.DataAccessUtils;
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
    public MessageRate updateOrCreate(MessageRate messageRate) {
        if (messageRate.getId() == null){
            em.persist(messageRate);
            return messageRate;
        }else{
            return get(messageRate.getId()) == null ? null : em.merge(messageRate);
        }
    }

    @Override
    public boolean delete(Integer messageRate_id) {
        return em.createNamedQuery(MessageRate.DELETE_BY_ID)
                .setParameter("id", messageRate_id)
                .executeUpdate() != 0;
    }

    @Override
    public List<MessageRate> getAll() {
        return em.createNamedQuery(MessageRate.GET_ALL, MessageRate.class)
                .getResultList();
    }

    @Override
    public MessageRate get(Integer messageRate_id) {
        return em.find(MessageRate.class, messageRate_id);
    }

}
