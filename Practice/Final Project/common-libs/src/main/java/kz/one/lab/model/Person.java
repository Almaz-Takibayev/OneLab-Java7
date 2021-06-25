package kz.one.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String address;

    @NonNull
    private int age;

    @NonNull
    private String myPhoneNumber;

    @NonNull
    private String password;

    private List<Contact> contacts;
}
