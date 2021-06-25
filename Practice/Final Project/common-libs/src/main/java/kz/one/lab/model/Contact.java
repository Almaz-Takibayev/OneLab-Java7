package kz.one.lab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Contact {
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String phoneNumber;

    @NonNull
    @JsonIgnore
    private Person person;
}
