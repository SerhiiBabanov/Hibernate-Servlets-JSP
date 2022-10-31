package ua.goit.hw7.model.dao;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "developers")
public class DeveloperDao {
    private Long id;
    private String name;
    private String username;
    private Integer salary;
    private Set<SkillDao> skills;
    private Set<ProjectDao> projects;

    public DeveloperDao() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "username", length = 30)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "salary")
    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeveloperDao that = (DeveloperDao) o;
        return id.equals(that.id) && salary.equals(that.salary) && Objects.equals(name, that.name) && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username, salary);
    }

    @ManyToMany
    @JoinTable(
            name = "developer_skill_relation",
            joinColumns = {@JoinColumn(name = "developer_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")}
    )
    public Set<SkillDao> getSkills() {
        return skills;
    }

    public void setSkills(Set<SkillDao> skills) {
        this.skills = skills;
    }

    @ManyToMany
    @JoinTable(
            name = "project_developer_relation",
            joinColumns = {@JoinColumn(name = "developer_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")}
    )
    public Set<ProjectDao> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectDao> projects) {
        this.projects = projects;
    }
}
