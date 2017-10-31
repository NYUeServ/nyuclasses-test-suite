package sakai.utilities;

import sakai.utilities.api.Config;

public class UserFactory {

    private static Config config;
    public static User instructor;
    public static User student;
    public static User ta;

    public UserFactory(Config config){
        this.config = config;
        instructor = getValidInstructor();
        student = getValidStudent();
        ta = getValidTeachingAssistant();
    }

    public static User getValidStudent(){
        User user = new User();
        user.withUsername(config.getStudentUsername()).withPassword(config.getStudentPassword())
                .withRole("Student");
        return user;
    }

    public static User getValidInstructor(){
        User user = new User();
        user.withUsername(config.getInstructorUsername()).withPassword(config.getInstructorPassword())
                .withRole("Instructor");
        return user;
    }

    public static User getValidTeachingAssistant(){
        User user = new User();
        user.withUsername(config.getTeachingAssistantUsername()).withPassword(config.getTeachingAssistantPassword())
                .withRole("Teaching Assistant");
        return user;
    }

}