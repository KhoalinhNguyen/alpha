package Linh.Alpha.Dto;

import Linh.Alpha.Modell.Roles;
import Linh.Alpha.Modell.User;
import lombok.Getter;

@Getter
public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String currentPosition;
    private String department;
    private Roles role;

    public UserDto(long id, String firstName, String lastName, String email, String phoneNumber, String currentPosition, String department, Roles role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.currentPosition = currentPosition;
        this.department = department;
        this.role = role;
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.currentPosition = user.getCurrentPosition();
        this.phoneNumber = user.getPhoneNumber();
        this.department = user.getDepartment();
        this.role = user.getRole();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
