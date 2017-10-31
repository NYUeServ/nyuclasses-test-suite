package sakai.utilities.api;

import org.yaml.snakeyaml.Yaml;
import sakai.utilities.SakaiLogger;
import sakai.utilities.UserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * This is the class used by cucumber picocontainer
 * An instance of this class will be passed to all step definitions so they are using the same page resources
 *
 * The setters of this class will also return a copy of what is set, for the sake of easier chaining commands
 *
 * This class manages the environment variables of the test suite
 */
public class Config {

    private Map<String, String> settings;

    public void loadSettings(){
        File settingFile = new File("settings.yml");
        try{
            if(settingFile.exists()){
                Yaml yaml = new Yaml();
                settings = yaml.load(new FileInputStream(settingFile));
            }
            else{
                SakaiLogger.logErr("Configuration file not found, exiting system status 0");
                System.exit(0);
            }
        }catch(FileNotFoundException e){
            SakaiLogger.logErr("Configuration file not found, exiting system status 0");
            System.exit(0);
        }

        new UserFactory(this);
    }

    public String getLoginPageUrl(){
        return settings.get("login_page");
    }

    public String getCourseSite(){
        return settings.get("course_site");
    }

    public String getInstructorUsername(){
        return settings.get("instructor_account_username");
    }

    public String getInstructorPassword(){
        return settings.get("instructor_account_password");
    }

    public String getStudentUsername(){
        return settings.get("student_account_username");
    }

    public String getStudentPassword(){
        return settings.get("student_account_password");
    }

    public String getTeachingAssistantUsername(){
        return settings.get("ta_account_username");
    }

    public String getTeachingAssistantPassword(){
        return settings.get("ta_account_password");
    }

    @Override
    public String toString() {
        String output = "==================\n";
        output += "Login Information Retrieved\n";
        output += "==================\n";
        output += "Instructor: " + getInstructorUsername() + " : " + getInstructorPassword() + "\n";
        output += "Student: " + getStudentUsername() + " : " + getStudentPassword() + "\n";
        output += "TA: " + getTeachingAssistantUsername() + " : " + getTeachingAssistantPassword() + "\n";
        return output;
    }
}
