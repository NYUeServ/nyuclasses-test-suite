package sakai.pages.tools.course;

import org.openqa.selenium.WebDriver;
import sakai.pages.CoursePage;
import sakai.pages.tools.Tool;

public class Syllabus extends Tool{

    private final CoursePage parent;

    public Syllabus (CoursePage parent, WebDriver driver)
    {
        super(driver);
        this.parent = parent;
    }

    public void navigate()
    {
        this.navigateToToolWithName("Syllabus");
    }
}
