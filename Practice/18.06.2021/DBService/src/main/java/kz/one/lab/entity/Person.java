package kz.one.lab.entity;

import lombok.*;

import javax.persistence.*;

@SuppressWarnings("ALL")
@Entity
@Data
@Table(name = "persons")
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "age")
    private int age;
}
