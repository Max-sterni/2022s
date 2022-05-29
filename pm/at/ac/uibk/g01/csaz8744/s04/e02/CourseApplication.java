package at.ac.uibk.pm.g01.csaz8744.s04.e02;

public class CourseApplication {

    public static void main(String[] args) {
        Course course = new Course();
        String[] students = {"Donald Duck", "Donald Duck", "Uncle Scrooge", "Gyro Gearloose"};

        for (String student : students) {
            try{
                course.addStudent(student);
                System.out.println(student + " succesfully enrolled");
            }
            catch(StudentAlreadyEnrolledException e) {
                System.out.println(student + " was already enrolled in the course");
            }
            catch(CourseFullException e) {
                System.out.println("The course is full");
            }
        }
    }
}