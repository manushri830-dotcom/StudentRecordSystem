import java.io.Serializable;
public class Student implements Serializable{
int roll;
String name;
String dept;
double marks;
public Student(int roll,String name,String dept,double marks){
this.roll=roll;
this.name=name;
this.dept=dept;
this.marks=marks;
}
public String toString(){
return roll+","+name+","+dept+","+marks;
}
public static Student fromString(String data){
String[]arr=data.split(",");
return new Student(Integer.parseInt(arr[0]),arr[1],arr[2],Double.parseDouble(arr[3]));
}
}