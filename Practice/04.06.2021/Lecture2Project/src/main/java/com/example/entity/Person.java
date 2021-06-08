package com.example.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "person_index")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Person {

    @Id
    private String id;
    @NonNull
    private String name;
    @NonNull
    private int age;


}
