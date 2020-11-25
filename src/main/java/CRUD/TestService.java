package CRUD;

import entity.Test;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import static org.hibernate.loader.internal.AliasConstantsHelper.get;

@Service
public class TestService {

    @PersistenceContext
    public EntityManager em = Persistence.createEntityManagerFactory("TeleBot").createEntityManager();

    public Test addTest(Test test){
        em.getTransaction().begin();
        Test testFromBD = em.merge(test);
        em.getTransaction().commit();
        return testFromBD;
    }

    public Test getTest(Integer id){
        return em.find(Test.class, id);
    }

    public void deleteTest(Integer id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }
}
