package service;

import entity.User;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.sql.ResultSet;


@Service
public class UserService {
    @PersistenceContext
    public EntityManager em = Persistence.createEntityManagerFactory("TeleBot").createEntityManager();

    public User addUser(User user){
        em.getTransaction().begin();
        User userFromDB = em.merge(user);
        em.getTransaction().commit();
        return userFromDB;
    }

    public User getUser(Integer id){
        ResultSet rs = null;
        return em.find(User.class, id);
    }
}
