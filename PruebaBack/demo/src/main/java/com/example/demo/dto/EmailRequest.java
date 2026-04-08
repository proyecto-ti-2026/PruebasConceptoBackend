package com.example.demo.dto;

public class EmailRequest {
    private String to;
    private String subject;
    private String html;

    // Getters y Setters
    public String getTo() { return to; }
    public void setTo(String to) { this.to = to; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getHtml() { return html; }
    public void setHtml(String html) { this.html = html; }
}