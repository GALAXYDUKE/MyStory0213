package com.qf.story.entity;

/**
 * 我的说说
 * Created by Galaxy on 2017/2/10.
 */

public class MyStory {
    private String story_id,story_info,readCount,comment,story_time;//发表的该说说的序号，说说内容，点赞数，评论数，发表时间

    public String getId() {
        return story_id;
    }

    public void setId(String id) {
        this.story_id = id;
    }

    public String getStory_info() {
        return story_info;
    }

    public void setStory_info(String story_info) {
        this.story_info = story_info;
    }

    public String getReadCount() {
        return readCount;
    }

    public void setReadCount(String readCount) {
        this.readCount = readCount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStory_time() {
        return story_time;
    }

    public void setStory_time(String story_time) {
        this.story_time = story_time;
    }
}
