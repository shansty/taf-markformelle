package by.itacademy.shirochina.anastasiya.utils;

import com.github.javafaker.Faker;

public class Util {
    Faker faker;
    public String generateEmail() {
        faker = new Faker();
        String email = faker.internet().emailAddress();
        return email;
    }
    public String generatePassword() {
        faker = new Faker();
        String password = faker.internet().password();
        return password;
    }
    public String correctEmail() {
        return "shirochina16@gmail.com";
    }
    public String correctPassword() {
        return "123456789";
    }
}
