package com.example.rorydemo.generi;

/**
 * Author by roryLin, Email xx@xx.com, Date on 2020/6/12.
 */
public class GenericTest {
    class Fruit{
        @Override
        public String toString() {
            return "fruit";
        }
    }

    class Apple extends Fruit{
        @Override
        public String toString() {
            return "apple";
        }
    }
    class Person{
        @Override
        public String toString() {
            return "Person";
        }
    }

    class GenerateTest<T>{

        //不是泛型方法
        public void show_1(T t){
            System.out.println(t.toString());
        }

        //在泛型类中声明了一个泛型方法，使用泛型E，这种泛型E可以为任意类型。可以类型与T相同，也可以不同。
        //由于泛型方法在声明的时候会声明泛型<E>，因此即使在泛型类中并未声明泛型，编译器也能够正确识别泛型方法中识别的泛型。
        public <E> void show_3(E t){
            System.out.println(t.toString());
        }

        //在泛型类中声明了一个泛型方法，使用泛型T，注意这个T是一种全新的类型，可以与泛型类中声明的T不是同一种类型。
        public <T> void show_2(T t){
            System.out.println(t.toString());
        }
    }

    public void main(){
         Apple apple = new Apple();
        Person person = new Person();
        GenerateTest<Fruit> generateTest=new GenerateTest<Fruit>();
        generateTest.show_1(apple);
        //person 不可以调用
        //generateTest.show_1(person)


        generateTest.show_2(apple);
        generateTest.show_2(person);

        generateTest.show_3(apple);
        generateTest.show_3(person);
    }

    //泛型接口
    interface Test<T>{
      public void setData(T t);
    }

    //泛型类
    class TestGG<T> implements Test<T>{

        @Override
        public void setData(T t) {

        }
    }

    //泛型
    class TestGG1 implements Test<String>{

        @Override
        public void setData(String s) {

        }
    }
}

