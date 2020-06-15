package uz.marokand.market.dto;

import uz.marokand.market.validate.FieldMatch;
import uz.marokand.market.validate.UserValidity;

import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@FieldMatch(
        first = "password",second = "confirmPassword",message = "Password and confirmpassword not match"
)

public class UserDto {
    @UserValidity
    @NotNull
    @Size(min = 4,max = 12)
    private String username;
    @NotNull
    @NotBlank(message = "Password is required!")
    private String password;
    @NotNull
    @Size(min = 4,max = 12)
    private String firstname;
    @NotNull
    @NotBlank
    private String phone;
    @NotNull
    @NotBlank
    @Email
    @UserValidity(message = "This email already exist!")
    private String email;

    private String country;
    @NotNull
    @Transient
    private String confirmPassword;

    public UserDto() {
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
