package kz.one.lab.model;

import lombok.*;

@SuppressWarnings("ALL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private int age;
}
