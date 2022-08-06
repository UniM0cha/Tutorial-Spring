package spring.di.ui;

import spring.di.entity.Exam;

public class GridExamConsole implements ExamConsole {

  private Exam exam;

  public GridExamConsole(Exam exam) {
    this.exam = exam;
  }

  public GridExamConsole() {
  }

  @Override
  public void print() {
    System.out.printf("그리드다 %d 싯팔아 %f\n", exam.total(), exam.avg());
  }

  @Override
  public void setExam(Exam exam) {
    this.exam = exam;
  }

}
