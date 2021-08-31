package com.example.taskmaster;

public class Task {

    public String title;
    public String body;
    // TODO: state should be one of “new”, “assigned”, “in progress”, or “complete”.
    public String state;

    public Task(String title, String body, String state) {
        this.title = title;
        this.body = body;
        this.state = state;
    }
}
