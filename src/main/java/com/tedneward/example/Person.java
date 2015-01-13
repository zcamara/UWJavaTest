//Zach Camara - 1124687
//Info 498 - Assignment 1
package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person>{
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  private int numPeople = 0;
  
  public static ArrayList<Person> getNewardFamily() {
    ArrayList result = new ArrayList<Person>();
    result.add(new Person("Ted", 41, 250000));
    result.add(new Person("Charlotte", 43, 150000));
    result.add(new Person("Michael", 22, 10000));
    result.add(new Person("Matthew", 15, 0));
    return result;
  }

  public Person() {
    this("", 0, 0.0d);
  }

  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    ssn = "";
    numPeople++;
  }

  public int count() {
    return numPeople;
  }

  public void setAge(int newAge) {
    if(newAge > -1)
      this.age = newAge;
    else
      throw new IllegalArgumentException(newAge + " is not a valid age");
  }

  public void setName(String newName) {
    if(newName != null && !newName.isEmpty())
      this.name = newName;
    else
      throw new IllegalArgumentException("Invalid or null name.");
  }

  public int getAge() {
    return age;
  }
  
  public String getName() {
    return name;
  }
  
  public double getSalary() {
    return salary;
  }
  
  public String getSSN() {
    return ssn;
  }

  public void setSalary(double s){
    this.salary = s;
  }

  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }
  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  @Override
  public boolean equals(Object other) { //might need a "weird" handler
    if (other instanceof Person) {
      Person p = (Person)other;
      return (this.name.equals(p.getName()) && this.age == p.getAge());
    }
    return false;
  }

  public String toString() {
    return "[Person name:"+name+" age:"+age+" salary:"+salary+"]";
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }

  public static class AgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
      return p1.getAge() - p2.getAge();
    }
  }

  @Override
  public int compareTo(Person p) {
    if(p.getSalary() == this.salary)//equal
      return 0;
    else if (p.getSalary() > this.salary) //p is richer than this, move p forward
      return 1;
    else  //p is not richer than this
      return -1; 
  }

}
