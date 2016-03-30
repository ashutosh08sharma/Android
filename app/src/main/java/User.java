/**
 * Created by Ashutosh on 3/15/2016.
 */
public class User {

    int _id;
    String name;
    String email_id;
    String password;
    int age;
    String phoneNo;


public User(){
}

    public User(int _id, String name, String email_id,String password, int age, String phoneNo) {
        this._id = _id;
        this.name = name;
        this.email_id = email_id;
        this.password =password;
        this.age = age;
        this.phoneNo = phoneNo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}

