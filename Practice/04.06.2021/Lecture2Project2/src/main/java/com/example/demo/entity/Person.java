package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@SuppressWarnings("ALL")
@Entity
@Data
@Table(name = "persons")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
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

    @OneToMany(mappedBy = "person")
    private List<Phone> phones;
}
