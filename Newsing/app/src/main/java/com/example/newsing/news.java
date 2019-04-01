package com.example.newsing;

public class news {

    String headLine;
    String Description;

    public news(String HeadLine, String newsText){

        headLine = HeadLine;
        Description = newsText;
    }

    public String getDescription() {
        return Description;
    }

    public String getHeadLine() {
        return headLine;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }
}
