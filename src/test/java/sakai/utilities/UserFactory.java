package sakai.utilities;

public class UserFactory {
    public static User getValidStudent(){
        User user = new User();
        user.withUsername(System.getenv("sakai_student_username")).withPassword(System.getenv("sakai_student_password"));
        return user;
    }

    public static User getValidInstructor(){
        User user = new User();
        user.withUsername(System.getenv("sakai_instructor_username")).withPassword(System.getenv("sakai_instructor_password"));
        return user;
    }

    public static User getValidTeachingAssistant(){
        User user = new User();
        user.withUsername(System.getenv("sakai_teaching_assistant_username")).withPassword(System.getenv("sakai_teaching_assistant_password"));
        return user;
    }
}