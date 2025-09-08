package com.example.DI;

public class Student
{
    private int id;
    private String studentName;

    // constructor injection

    public Student(int id)
    {
        this.id = id;
        System.out.println("Student constructor called for ashish");
    }

    public Student(int id, String studentName)
    {
        this.id = id;
        this.studentName = studentName;
        System.out.println("Student constructor called for abhilash");
    }
   /* setter injection
    public void setId(int id) {
        this.id = id;
        System.out.println("setter method called : " + ":setid");
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
        System.out.println("setter method called : " + ":setstudentname");
    }
    */

    public void displayStudentInfo()
    {
        System.out.println("Student Name: " + studentName + " ID: " + id );
    }
}
