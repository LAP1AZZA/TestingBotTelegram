package entity;

import entity.enumVariables.Difficulty;
import entity.enumVariables.Type;

import javax.persistence.*;

@Entity
@Table(name = "QUESTIONS")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "TYPE")
    private Type type;

    @Column(name = "AUTHOR_LOGIN")
    private String author_Login;

    @Column(name = "DIFFICULTY")
    private Difficulty difficulty;

    @Column(name = "QUESTION_TEXT")
    private String question_Text;

    @Column(name = "ANSWER")
    private String answer;



    public Question() {};
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getAnswer() { return answer; }

    public void setAnswer(String answer) { this.answer = answer; }
    public String getType() { return String.valueOf(type); }
    public void setType(String type) { this.type = Type.valueOf(type); }
    public String getAuthorLogin() { return author_Login; }
    public void setAuthorLogin(String authorLogin) { this.author_Login = authorLogin; }
    public String getDifficulty() { return String.valueOf(difficulty); }
    public void setDifficulty(String difficulty) { this.difficulty = Difficulty.valueOf(difficulty); }
    public String getQuestionText() { return question_Text; }
    public void setQuestionText(String questionText) { this.question_Text = questionText; }
}