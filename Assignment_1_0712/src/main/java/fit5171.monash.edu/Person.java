package fit5171.monash.edu;

import java.util.Arrays;
import java.util.regex.Pattern;

public abstract class Person {
    private String firstName;
    private String secondName;
    private int age;
    private String gender;

    private static final String[] VALID_GENDERS = {
            "Woman", "Man", "Non-binary | gender diverse", "Prefer not to say", "Other"
    };

    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z]+$");


    public String Person(String firstName, String secondName, int age, String gender) {
        if (firstName == null || firstName.isEmpty() || secondName == null || secondName.isEmpty() || gender == null || gender.isEmpty()) {
            return "All fields are required to create a person";
        }

        if (!Arrays.asList(VALID_GENDERS).contains(gender)) {
            return "Invalid gender provided";
        }

        if (!NAME_PATTERN.matcher(firstName).matches()) {
            return "First name must only contain alphabet letters and not start with a number or symbol";
        }

        if (!NAME_PATTERN.matcher(secondName).matches()) {
            return "Second name must only contain alphabet letters and not start with a number or symbol";
        }

        this.age = age;
        this.firstName = firstName;
        this.secondName = secondName;
        this.gender = gender;
        return "Successfully created a person";
    }



    public int getAge() {
        return age;
    }

    public String setAge(int age) {
        if(0< age && age <150) {
            this.age = age;
            return "Successfully Set Age";
        }
        else return "Invalid Age provided";
    }

    public String getGender() {
        return gender;
    }

    public String setGender(String gender) {
        if (Arrays.asList(VALID_GENDERS).contains(gender)) {
            this.gender = gender;
            return "Successfully Set Gender";
        }
        else return "Invalid gender provided";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String setFirstName(String firstName) {
        if (NAME_PATTERN.matcher(firstName).matches()) {
            this.firstName = firstName;
            return "Successfully Set FirstName";
        }
        else return "First name must only contain alphabet letters and not start with a number or symbol";
    }

    public String setSecondName(String secondName) {
        if (NAME_PATTERN.matcher(secondName).matches()) {
            this.secondName = secondName;
            return "Successfully Set SecondName";
        }
        else return"Second name must only contain alphabet letters and not start with a number or symbol";

    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
