import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Student> list = new ArrayList<>();
    static final String FILE_NAME = "student_data.txt";

    public static void main(String[] args) throws Exception {
        loadData();
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("\n===== STUDENT RECORD SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch(ch) {
                case 1 -> addStudent(sc);
                case 2 -> viewStudents();
                case 3 -> searchStudent(sc);
                case 4 -> updateStudent(sc);
                case 5 -> deleteStudent(sc);
                case 6 -> {
                    saveData();
                    System.out.println("Data saved. Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option");
            }
        }
    }

    static void addStudent(Scanner sc) {
        System.out.print("Roll Number: ");
        int roll = sc.nextInt();
        sc.nextLine();

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Department: ");
        String dept = sc.nextLine();

        System.out.print("Marks: ");
        double marks = sc.nextDouble();

        list.add(new Student(roll, name, dept, marks));
        saveData();
        System.out.println("Student Added Successfully!");
    }

    static void viewStudents() {
        if(list.isEmpty()) System.out.println("No Records Found!");
        else list.forEach(s -> System.out.println(s.roll+" | "+s.name+" | "+s.dept+" | "+s.marks));
    }

    static void searchStudent(Scanner sc) {
        System.out.print("Enter Roll Number: ");
        int roll = sc.nextInt();

        for(Student s:list) {
            if(s.roll == roll){
                System.out.println("Found → "+s.roll+" | "+s.name+" | "+s.dept+" | "+s.marks);
                return;
            }
        }
        System.out.println("Student Not Found!");
    }

    static void updateStudent(Scanner sc) {
        System.out.print("Roll Number to Update: ");
        int roll = sc.nextInt();
        sc.nextLine();

        for(Student s:list){
            if(s.roll == roll){
                System.out.print("New Name: ");
                s.name = sc.nextLine();
                System.out.print("New Department: ");
                s.dept = sc.nextLine();
                System.out.print("New Marks: ");
                s.marks = sc.nextDouble();
                saveData();
                System.out.println("Updated Successfully!");
                return;
            }
        }
        System.out.println("Student Not Found!");
    }

    static void deleteStudent(Scanner sc) {
        System.out.print("Roll Number to Delete: ");
        int roll = sc.nextInt();

        Iterator<Student> it = list.iterator();
        while(it.hasNext()){
            if(it.next().roll == roll){
                it.remove();
                saveData();
                System.out.println("Deleted Successfully!");
                return;
            }
        }
        System.out.println("Student Not Found!");
    }

    static void saveData() {
        try(PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))){
            for(Student s:list) pw.println(s);
        } catch(Exception e){ System.out.println(e); }
    }

    static void loadData() {
        try(BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))){
            String line;
            while((line = br.readLine()) != null) list.add(Student.fromString(line));
        } catch(FileNotFoundException e){ }
        catch(Exception e){ System.out.println(e); }
    }
}