package com.example.sdemo1.controller;

import com.example.sdemo1.pojo.Answer;
import com.example.sdemo1.pojo.Page;
import com.example.sdemo1.pojo.Question;
import com.example.sdemo1.pojo.Result;
import com.example.sdemo1.service.AnswerService;
import com.example.sdemo1.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@ResponseBody
@RestController
public class AnswerController {
    @Resource
    private AnswerService answerService;
    private QuestionService questionService;
    @RequestMapping("/answer/add")
    public Result<?> add(Answer answer,String title)//注意type属性
    {
        answer.setType(answer.type(title));
       answerService.add(answer);
        return Result.success("添加成功！");
    }
    @RequestMapping("/answer/delete")
    public Result<?> delete(Answer answer)
    {
       answerService.delete(answer);
        return Result.success("删除成功！");
    }
    @RequestMapping("/answer/update")
    public Result<?> update(Answer answer)
    {
       answerService.update(answer);
        return Result.success("更新成功！");
    }
    @RequestMapping("/answer/updateCount")
    public Result<?> update1(Answer answer)
    {
       answerService.updateCount(answer);
        return Result.success("更新点赞量成功!");
    }
    @RequestMapping("/answer/findMine")//"我的答案"按钮调用方法，需要问题的number还有本人phone
    public Result<?> find4(int currentPage,int pageSize,int number,String phone)
    {
        List<Answer> answers=new ArrayList<>();
        Question question=questionService.find(number);
        List<Answer> list1= answerService.findMine(phone,number);
        Page<Answer> page=new Page<>(list1.size(),pageSize,currentPage);
      //  page.setQuestion(question);
        int n=(currentPage-1)*pageSize;
        for(int i=1;i<=pageSize&&n<page.getTotal();i++)
        {
            answers.add(list1.get(n));
            n++;
        }
        page.setItem(answers);
        return Result.success(page);
    }

}
