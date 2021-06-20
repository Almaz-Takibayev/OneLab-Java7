package kz.one.lab.model;

import lombok.*;

@SuppressWarnings("ALL")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Person {
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private int age;
}
