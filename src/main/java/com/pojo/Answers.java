package com.pojo;

import java.sql.Timestamp;
import java.util.Date;

public class Answers {
    private Integer id;

    private String anscontent;

    private Timestamp ansdate;

    private String qid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnscontent() {
        return anscontent;
    }

    public void setAnscontent(String anscontent) {
        this.anscontent = anscontent == null ? null : anscontent.trim();
    }

    public Timestamp getAnsdate() {
        return ansdate;
    }

    public void setAnsdate(Timestamp ansdate) {
        this.ansdate = ansdate;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid == null ? null : qid.trim();
    }
}