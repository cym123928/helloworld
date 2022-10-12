package com.example.sdemo1.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question implements Comparable<Question> {
  public String content,name,phone,time;
  public int number,value,count;

  @Override
  public int compareTo(Question o) {
    return o.count-this.count;
  }
}
