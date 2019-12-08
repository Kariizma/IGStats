package com.f4csci380.mylist;

public class Memo
{

    private String mtitle;
    private String mbody;
    public Memo(String title, String body)
    {
        mtitle = title;
        mbody = body;
    }

    public String getTitle()
    {
        return mtitle;
    }

    public void setTitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public String getBody() {
        return mbody;
    }

    public void setBody(String mbody) {
        this.mbody = mbody;
    }
}
