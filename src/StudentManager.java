import java.util.ArrayList;
public class StudentManager{
    //list to store all studens
    private ArrayList<Student> students;
    //constructor
    public StudentManager(){
        students=new ArrayList<>();  
    }
    public boolean addStudent(Student student){
        //check for duplicate ID
        for(Student s:students){
            if(s.getId()==student.getId()){
                return false;
            }
        }
        students.add(student);
        return true;
    }
    //return
    public ArrayList<Student>getAllStudents(){
        return students;
    }
    //search
    public Student searchStudent(int id){
        for(Student s: students){
         if(s.getId()==id){
            return s;
         }
        }
        return null;
    }
    //update
    public boolean updateStudent(int id,String name,int age,String course){
        Student student=searchStudent(id);
        if(student!=null){
            student.setName(name);
            student.setAge(age);
            student.setCourse(course);
            return true;
        }
        return false;
    }
    //delete
    public boolean deleteStudent(int id){
        Student student=searchStudent(id);
        if(student!=null){
            students.remove(student);
            return true;
        }
        return false;
    }
}