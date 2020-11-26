package entity;

import entity.enumVariables.QuestionDifficulty;
import entity.enumVariables.QuestionType;
import org.springframework.stereotype.Component;


import javax.persistence.*;
import java.util.Collection;

@Component
@Entity
@Table(name = "QUESTION")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "TYPE")
    private QuestionType type;

    @Column(name = "AUTHOR_LOGIN")
    private String authorLogin;

    @Column(name = "DIFFICULTY")
    private QuestionDifficulty difficulty;

    @Column(name = "QUESTION_TEXT")
    private String questionText;

    @Column(name = "ANSWER")
    private String answer;

    @ManyToMany
    @JoinTable(name = "QUESTION_TEST",
    joinColumns = @JoinColumn(name = "QUESTION_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TEST_ID", referencedColumnName = "ID")
    )
    private Collection<Test> bir;

    public Question() {};

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getAnswer() { return answer; }


    public void setAnswer(String answer) { this.answer = answer; }
    public String getType() { return String.valueOf(type); }
    public void setType(String type) { this.type = QuestionType.valueOf(type); }
    public String getAuthorLogin() { return authorLogin; }
    public void setAuthorLogin(String authorLogin) { this.authorLogin = authorLogin; }
    public String getDifficulty() { return String.valueOf(difficulty); }
    public void setDifficulty(String difficulty) { this.difficulty = QuestionDifficulty.valueOf(difficulty); }
    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }
}