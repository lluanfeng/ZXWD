package com.service;

import com.pojo.Answers;
import com.pojo.PageBean;
import com.pojo.Questions;

import java.util.List;

public interface QuestionsService {

    List<Questions> showQ(PageBean page);

    int addQ(Questions questions);

    Questions showOne(Integer id);


    List<Answers> showQid(Integer id);

    int addA(Answers answers);
}
