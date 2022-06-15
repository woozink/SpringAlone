//package com.example.springex.controller;
//
//import lombok.Getter;
//import lombok.Setter;
//
//public class Test {
//    public static void main(String[] args) {
//        // Person: class, p1,p2: 변수, Person의 인스턴스
//        // 인스턴스만 멤버변수와 멤버 함수를 가질 수 있다
//        // 클래스는 멤버변수와 멤버함수를 가질 수 없다
//        Person p1 = new Person(); // 인스턴스화 객체화 실체화
//        Person p2 = new Person();
//        p1.setAge(10);
//
//        System.out.println(p1.getAge());
//        System.out.println(p1.age);
//
//        System.out.println(Person.MAX_AGE);
//        System.out.println(Person.staticHello());
//    }
//}
//
//@Getter @Setter
//class Person {
//    // 멤버 변수, 인스턴스 변수
//    public int age;
//    // 멤버 함수
//    private void hello() {
//        System.out.println("hello");
//    }
//
//    // static 변수, class 변수
//    public static int MAX_AGE = 100;
//    // static 메서드, class 메서드
//    public static void staticHello() {
//        System.out.println("staticHello");
//    }
//}
//
//
//
