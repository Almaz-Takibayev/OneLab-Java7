package kz.one.lab.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@SuppressWarnings("ALL")
@Entity
@Data
@Table(name = "contact")
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @NonNull
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonIgnore
    private Person person;
}
