package com.service.impl;

import com.mapper.AnswersMapper;
import com.mapper.QuestionsMapper;
import com.pojo.*;
import com.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class QuestionsServiceImpl implements QuestionsService {
    @Autowired
    private QuestionsMapper mapper;
    @Autowired
    private AnswersMapper answersMapper;
    @Override
    public List<Questions> showQ(PageBean page) {
        QuestionsExample q = new QuestionsExample();
        QuestionsExample.Criteria c = q.createCriteria();
        int size = page.getSize();
        int count = (int) mapper.countByExample(q);
        int totalPage = (count%size==0)?(count/size):(count/size+1);
        page.setTotalPage(totalPage);
        page.setCount(count);
        int startRow = (page.getCurrPage()-1)*size;
        q.limit(startRow,size);
        List<Questions> que = mapper.selectByExample(q);

        return que;
    }

    @Override
    public int addQ(Questions questions) {
        return mapper.insertSelective(questions);
    }

    @Override
    public Questions showOne(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Answers> showQid(Integer id) {
        AnswersExample e = new AnswersExample();
        AnswersExample.Criteria c = e.createCriteria();
        c.andQidEqualTo(id);
        List<Answers> answers = answersMapper.selectByExample(e);
        return answers;
    }

    @Override
    public int addA(Answers answers) {
        QuestionsExample q = new QuestionsExample();
        QuestionsExample.Criteria c = q.createCriteria();
        c.andIdEqualTo(Integer.parseInt(answers.getQid()));
        Questions questions = mapper.selectByPrimaryKey(Integer.parseInt(answers.getQid()));
        Integer answercount = questions.getAnswercount()+1;
        questions.setAnswercount(answercount);
       /* Timestamp tiems = new Timestamp(new Date().getTime());
        questions.setLastmodified(tiems);*/
        questions.setLastmodified(answers.getAnsdate());
        mapper.updateByExample(questions,q);
       /* Timestamp tiem = new Timestamp(new Date().getTime());
        answers.setAnsdate(tiem);*/
        return answersMapper.insertSelective(answers);
    }
}
