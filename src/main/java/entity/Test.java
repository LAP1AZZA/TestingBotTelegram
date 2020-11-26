package entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Component
@Entity
@Table(name = "TEST")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "QUESTIONS_LIST")
    private String questionsList;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "bir")
    private Set<Question> questions;

    public Test(String name) {
        this.name = name;
    }
    public Test() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getQuestions_list() {
        return questionsList;
    }
    public void setQuestions_list(String questions_list) {
        this.questionsList = questions_list;
    }

}