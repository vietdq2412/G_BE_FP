package gre.jb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "job")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String jobLevel;
    private String requiredExperience;
    private String requiredEducation;
    private String location;
    @OneToMany
    private Set<JobCategory> jobCategory;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
