package com.example.sdemo1.controller;
import com.example.sdemo1.dao.AnswerDao;
import com.example.sdemo1.pojo.Answer;
import com.example.sdemo1.pojo.Collection;
import com.example.sdemo1.pojo.Page;
import com.example.sdemo1.pojo.Result;
import com.example.sdemo1.service.AnswerService;
import com.example.sdemo1.service.AnswerServiceimpl;
import com.example.sdemo1.service.QuestionService;
import com.example.sdemo1.pojo.Question;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@ResponseBody
@RestController
public class QuestionController {
    List<Question> list;
    @Resource
    private QuestionService questionService;
    private AnswerServiceimpl answerService;
    @RequestMapping("/question/add")
   public Result<?> add(Question question)
    {
        questionService.addQ(question);
        return Result.success("添加成功！");
    }
    @RequestMapping("/question/update")
    public Result<?> update(Question question)//医生处理问题，合格value=1，不合格value=2，不删除
    {
       questionService.updateQ(question);
        return Result.success("更新成功！");
    }
    @RequestMapping("/question/updateCount")
    public Result<?> update1(Question question)//需要原本的count和number
    {
        questionService.updateCount(question);
        return Result.success("更新点击量成功!");
    }
    @RequestMapping("/question/delete")
    public Result<?> delete(Question question){
        questionService.deleteQ(question);
        return Result.success("删除成功！");
    }
    @RequestMapping("/question/collect")//一个人收藏的所有问题
    public Result<?> collect(String phone,int currentPage,int pageSize)
    {
        list=new ArrayList<>();
        List<Collection> collections=questionService.collect(phone);//锁定这个人
        Question question;
        for(int i=0;i<collections.size();i++)
        {
          question=questionService.find(collections.get(i).getNumber());
          list.add(question);
        }
        Page<Question> page=new Page<>(list.size(),pageSize,currentPage);
        List<Question> list1=new ArrayList<>();
        int n=(currentPage-1)*pageSize;
        for(int i=1;i<=pageSize&&n<page.getTotal();i++)
        {
            list1.add(list.get(n));
            n++;
        }
        page.setItem(list1);
        return Result.success(page);
    }
    @RequestMapping("/question/submit1")//一个人发布的所有合格的问题
    public Result<?> find1(String phone,String search,int currentPage,int pageSize)
    {
        if(search==null||search.length()==0)
            list= questionService.findByPhone(phone,1);
        else
            list= questionService.search1(phone,search,1);
        Page<Question> page=new Page<>(list.size(),pageSize,currentPage);
        List<Question> list1=new ArrayList<>();
        int n=(currentPage-1)*pageSize;
        for(int i=1;i<=pageSize&&n<page.getTotal();i++)
        {
            list1.add(list.get(n));
            n++;
        }
        page.setItem(list1);
        return Result.success(page);
    }
    @RequestMapping("/question/submit2")//一个人发布所有不合格问题
    public Result<?> find6(String phone,String search,int currentPage,int pageSize)
    {
        if(search==null||search.length()==0)
            list= questionService.findByPhone(phone,2);
        else
            list= questionService.search1(phone,search,2);
        Page<Question> page=new Page<>(list.size(),pageSize,currentPage);
        List<Question> list1=new ArrayList<>();
        int n=(currentPage-1)*pageSize;
        for(int i=1;i<=pageSize&&n<page.getTotal();i++)
        {
            list1.add(list.get(n));
            n++;
        }
        page.setItem(list1);
        return Result.success(page);
    }

    @RequestMapping("/question/reply")//一个人回答的所有问题,不重复，从此链接点进去的详情界面有”我的回答按钮“，显示自己的回答，并且可进行编辑update
    public Result<?> find5(String phone,int currentPage,int pageSize)
    {
        System.out.println(currentPage+"  "+pageSize + "bbb"+phone);
        int number=0;
        Question question;
        List<Question> questions=new  ArrayList<>();
        List<Answer> list1= answerService.findByPhone(phone);
        System.out.println(list1.size());
        for(int i=0;i<list1.size();i++)
        {
            number=list1.get(i).number;
            System.out.println(number);
            question=questionService.find(number);
            System.out.println(question.content);
            if(!questions.contains(question))
            questions.add(question);
        }
        Page<Question> page=new Page<>(questions.size(),pageSize,currentPage);
        List<Question> list2=new ArrayList<>();
        int n=(currentPage-1)*pageSize;
        for(int i=1;i<=pageSize&&n<page.getTotal();i++)
        {
            list2.add(questions.get(n));
            System.out.println(questions.get(n).content);
            n++;
        }
        page.setItem(list2);
        return Result.success(page);
    }

    @RequestMapping("/question/findValued")//数据库中所有有价值的问题，展示在用户界面+医生已处理有价值问题界面
    public Result<?> find2(int currentPage,int pageSize,String search)
    {
        if(search==null||search.length()==0)
        list= questionService.findValued();
        else
         list= questionService.search(search,1);
          Collections.sort(list);
         Page<Question> page=new Page<>(list.size(),pageSize,currentPage);
         List<Question> list1=new ArrayList<>();
       int n=(currentPage-1)*pageSize;
       for(int i=1;i<=pageSize&&n<page.getTotal();i++)
       {
           list1.add(list.get(n));
           System.out.println(list1.get(n).count);
           n=(currentPage-1)*pageSize+i;
       }
       page.setItem(list1);

        return Result.success(page);
    }

    @RequestMapping("/question/top")//热议问题
    public Result<?> find8(String type,int number)    //type : week, month
    {
        System.out.println(type);
        System.out.println(number);
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String end=  formatter.format(date);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if(type.equals("week"))
        c.add(Calendar.DATE, - 7);
        else if(type.equals("month"))
        c.add(Calendar.MONTH, -1);
        Date d = c.getTime();
        String start = formatter.format(d);
        list= questionService.top(start,end);
        System.out.println(start+" "+end);
        Collections.sort(list);
        List<Question> list1=new ArrayList<>();
        for(int i=0;i<number&&i<list.size();i++){
            list1.add(list.get(i));
        System.out.println(list.get(i).content);
        }
        return Result.success(list1);
    }
    @RequestMapping("/question/findUnvalued")//数据库中所有已处理无价值的问题，展示在医生界面
    public Result<?> find7(int currentPage,int pageSize,String search)
    {
        if(search==null||search.length()==0)
            list= questionService.findUnvalued();
        else
            list= questionService.search(search,2);
        Page<Question> page=new Page<>(list.size(),pageSize,currentPage);
        List<Question> list1=new ArrayList<>();
        int n=(currentPage-1)*pageSize;
        for(int i=1;i<=pageSize&&n<page.getTotal();i++)
        {
            list1.add(list.get(n));
            n=(currentPage-1)*pageSize+i;
        }
        page.setItem(list1);
        return Result.success(page);
    }
    @RequestMapping("/question/findUndeal")//所有用户添加，但是医生还未处理的问题
    public Result<?> find3(int currentPage,int pageSize,String search)
    {
        if(search==null||search.length()==0)
            list= questionService.findUndeal();
        else
            list= questionService.search(search,0);
        Page<Question> page=new Page<>(list.size(),pageSize,currentPage);
        List<Question> list1=new ArrayList<>();
        int n=(currentPage-1)*pageSize;
        for(int i=1;i<=pageSize&&n<page.getTotal();i++)
        {
            list1.add(list.get(n));
            n=(currentPage-1)*pageSize+i;
        }
        page.setItem(list1);
        return Result.success(page);
    }
   /* @RequestMapping("/question/detail")//问题详情界面,每刷新一次，问题的点击量++
    public Result<?> find4(int currentPage,int pageSize,String search,int number)
    {
        Question question=questionService.find(number);
        questionService.updateQ(question);
        List<Answer> list1=new ArrayList<>();
        List<Answer> answers=new ArrayList<>();
        if(search==null||search.length()==0)
            list1=answerService.findByQuestion(number);
        else
            list1= answerService.search(search,number);
        Collections.sort(list1);
        Page<Answer> page=new Page<>(list1.size(),pageSize,currentPage);
        page.setQuestion(question);
        int n=(currentPage-1)*pageSize;
        for(int i=1;i<=pageSize&&n<page.getTotal();i++)
        {
            answers.add(list1.get(n));
            n++;
        }
        page.setItem(answers);
        return Result.success(page);
    }*/
   /* @RequestMapping("/question/findDeal1")//所有用户添加，但是医生已处理的问题,两部分：合格+不合格
    public Result<?> find5(int currentPage1,int pageSize1,int currentPage2,int pageSize2,String search)
    {
        List<Question> list1=new ArrayList<>();
        List<Question> list2=new ArrayList<>();
        if(search==null||search.length()==0)
        {
            list1=questionService.findValued();
            list2=questionService.findUnvalued();
        }
        else
        {
            list1=questionService.search(search,1);
            list2=questionService.search(search,2);
        }
        List<Page<Question>> pages=new ArrayList<>();
        Page<Question> page1=new Page<>(list1.size(),pageSize1,currentPage1);
        List<Question> list3=new ArrayList<>();
        int n=(currentPage1-1)*pageSize1;
        for(int i=1;i<=pageSize1&&n<page1.getTotal();i++)
        {
            list3.add(list1.get(n));
            n++;
        }
        page1.setItem(list3);
        pages.add(page1);
        Page<Question> page2=new Page<>(list2.size(),pageSize2,currentPage2);
        List<Question> list4=new ArrayList<>();
         n=(currentPage2-1)*pageSize2;
        for(int i=1;i<=pageSize2&&n<page2.getTotal();i++)
        {
            list4.add(list2.get(n));
            n++;
        }
        page2.setItem(list4);
        pages.add(page2);
        return Result.success(pages);
    }
     @RequestMapping("/question/submit")//所有用户提交的问题,两部分：合格+不合格//合格才可查看详情
    public Result<?> find6(int currentPage1,int pageSize1,int currentPage2,int pageSize2,String search,String phone)
    {
        List<Question> list1=new ArrayList<>();
        List<Question> list2=new ArrayList<>();
        if(search==null||search.length()==0)
        {
            list1=questionService.findByPhone(phone,1);
            list2=questionService.findByPhone(phone,2);
        }
        else
        {
            list1=questionService.search1(phone,search,1);
            list2=questionService.search1(phone,search,2);
        }
        List<Page<Question>> pages=new ArrayList<>();
        Page<Question> page1=new Page<>(list1.size(),pageSize1,currentPage1);
        List<Question> list3=new ArrayList<>();
        int n=(currentPage1-1)*pageSize1;
        for(int i=1;i<=pageSize1&&n<page1.getTotal();i++)
        {
            list3.add(list1.get(n));
            n++;
        }
        page1.setItem(list3);
        pages.add(page1);
        Page<Question> page2=new Page<>(list2.size(),pageSize2,currentPage2);
        List<Question> list4=new ArrayList<>();
        n=(currentPage2-1)*pageSize2;
        for(int i=1;i<=pageSize2&&n<page2.getTotal();i++)
        {
            list4.add(list2.get(n));
            n++;
        }
        page2.setItem(list4);
        pages.add(page2);
        return Result.success(pages);
    }*/
}
