package model;

public class Users {
    private String username, fullname;
    private String phoneNumber;
    private String email;
    private int gender;
    private String location, password, dob;
    private int roleId;
    private String image, address;

    public Users() {
    }

    public Users(String username, String fullname, String phoneNumber, String email, int gender, String location, String password, String dob, int roleId, String image, String address) {
        this.username = username;
        this.fullname = fullname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
        this.location = location;
        this.password = password;
        this.dob = dob;
        this.roleId = roleId;
        this.image = image;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Users{" + "username=" + username + ", fullname=" + fullname + ", phoneNumber=" + phoneNumber + ", email=" + email + ", gender=" + gender + ", location=" + location + ", password=" + password + ", dob=" + dob + ", roleID=" + roleId + ", image=" + image + ", address=" + address + '}';
    }
    
}