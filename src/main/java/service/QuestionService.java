package service;

import entity.Question;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import static org.hibernate.loader.internal.AliasConstantsHelper.get;

@Service
public class QuestionService {
    @PersistenceContext
    public EntityManager em = Persistence.createEntityManagerFactory("TeleBot").createEntityManager();

    public Question addQuestion(Question question){
        em.getTransaction().begin();
        Question questionFromDB = em.merge(question);
        em.getTransaction().commit();
        return questionFromDB;
    }

    public void deleteQuestion(Integer id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Question getQuestion(Integer id){
        return em.find(Question.class, id);
    }

    public List<Question> getAll(){
        TypedQuery<Question> namedQuery = em.createNamedQuery("Question.getAll", Question.class);
        return  namedQuery.getResultList();
    }

}
