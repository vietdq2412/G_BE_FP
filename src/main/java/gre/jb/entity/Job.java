package gre.jb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
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

    @Column(columnDefinition = "MEDIUMTEXT")
    private String description;
    private String requiredExperience;
    private String requiredEducation;
    private String address;
    private Date createdDate;
    private Date expiredDate;

    @ManyToMany
    private Set<JobCategory> jobCategory;
    @ManyToMany
    private Set<Skill> skills;

    @ManyToOne
    private JobType jobType;
    @ManyToOne
    private Location location;
    @ManyToOne
    private Company company;
}
