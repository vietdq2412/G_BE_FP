package gre.jb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "company")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column( columnDefinition = "MEDIUMTEXT")
    private String about;
    private String email;
    private String address;
    private String logo;
    private String status;
    @OneToOne
    @JoinColumn(name = "accountId", unique = true,
            nullable = false, updatable = false)
    private Account account;
}
