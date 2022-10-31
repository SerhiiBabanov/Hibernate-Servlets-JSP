package ua.goit.hw7.model.dao;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "projects")
public class ProjectDao {
    private Long id;
    private String name;
    private String git_url;
    private Integer cost;
    private Long date;
    private Set<DeveloperDao> developers;

    public ProjectDao() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "git_url", length = 100)
    public String getGit_url() {
        return git_url;
    }

    public void setGit_url(String git_url) {
        this.git_url = git_url;
    }

    @Column(name = "cost")
    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @Column(name = "date")
    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectDao that = (ProjectDao) o;
        return id.equals(that.id) && cost.equals(that.cost) && Objects.equals(name, that.name) && Objects.equals(git_url, that.git_url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, git_url, cost);
    }

    @ManyToMany(mappedBy = "projects")
    public Set<DeveloperDao> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<DeveloperDao> developers) {
        this.developers = developers;
    }
}
