package spring.di;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.di.entity.Exam;
import spring.di.entity.NewlecExam;
import spring.di.ui.ExamConsole;
import spring.di.ui.GridExamConsole;
import spring.di.ui.InlineExamConsole;

public class Program {
  public static void main(String[] args) {

    // 스프링에게 지시하는 방법으로 코드를 변경
    // Exam exam = new NewlecExam();
    // ExamConsole console = new GridExamConsole();
    // console.setExam(exam);

    ApplicationContext context =
        // new ClassPathXmlApplicationContext("spring/di/setting.xml");
        new AnnotationConfigApplicationContext(NewlecDIConfig.class);

    // Exam exam = context.getBean(Exam.class);
    // System.out.println(exam.toString());

    ExamConsole console = (ExamConsole) context.getBean("console");
    // ExamConsole console = context.getBean(ExamConsole.class);

    console.print();

    // List<Exam> exams = new ArrayList<>();
    // List<Exam> exams = (List<Exam>) context.getBean("exams");
    // exams.add(new NewlecExam(1, 1, 1, 1));

    // for (Exam e : exams)
    // System.out.println(e);
  }
}
