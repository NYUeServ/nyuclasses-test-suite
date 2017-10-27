package sakai.utilities;

public class User {
    private String netid;
    private String password;
    private String role;

    public User withUsername(String netid){
        this.netid = netid;
        return this;
    }

    public String getUsername(){
        return netid;
    }

    public User withPassword(String password){
        this.password = password;
        return this;
    }

    public String getPassword(){
        return password;
    }

    public User withRole(String role){
        this.role = role;
        return this;
    }

    public String getRole(){
        return role;
    }

}