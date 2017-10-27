package sakai.utilities;

import sakai.utilities.api.SettingsAPI;

public class UserFactory {

    private static SettingsAPI settings;
    public static User instructor;
    public static User student;
    public static User ta;

    public UserFactory(SettingsAPI settings){
        this.settings = settings;
        instructor = getValidInstructor();
        student = getValidStudent();
        ta = getValidTeachingAssistant();
    }

    public static User getValidStudent(){
        User user = new User();
        user.withUsername(settings.getStudentUsername()).withPassword(settings.getStudentPassword())
                .withRole("Student");
        return user;
    }

    public static User getValidInstructor(){
        User user = new User();
        user.withUsername(settings.getInstructorUsername()).withPassword(settings.getInstructorPassword())
                .withRole("Instructor");
        return user;
    }

    public static User getValidTeachingAssistant(){
        User user = new User();
        user.withUsername(settings.getTeachingAssistantUsername()).withPassword(settings.getTeachingAssistantPassword())
                .withRole("Teaching Assistant");
        return user;
    }

}