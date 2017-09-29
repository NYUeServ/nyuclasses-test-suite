package sakai.utilities;

public class User {
    private String netid;
    private String password;

    public User withUsername(String netid){
        this.netid = netid;
        return this;
    }

    public String getUsername(){
        return netid;
    }

    public void withPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

}