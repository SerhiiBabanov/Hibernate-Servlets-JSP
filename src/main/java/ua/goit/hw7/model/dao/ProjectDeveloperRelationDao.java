package ua.goit.hw7.model.dao;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "project_developer_relation")
public class ProjectDeveloperRelationDao {
    private Long id;
    private Long projectId;
    private Long developerId;

    public ProjectDeveloperRelationDao() {

    }

    public ProjectDeveloperRelationDao(Long projectId, Long developerId) {
        this.projectId = projectId;
        this.developerId = developerId;
    }

    public ProjectDeveloperRelationDao(Long id, Long projectId, Long developerId) {
        this.id = id;
        this.projectId = projectId;
        this.developerId = developerId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "project_id")
    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    @Column(name = "developer_id")
    public Long getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Long developerId) {
        this.developerId = developerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectDeveloperRelationDao that = (ProjectDeveloperRelationDao) o;
        return Objects.equals(id, that.id) && Objects.equals(projectId, that.projectId) && Objects.equals(developerId, that.developerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectId, developerId);
    }
}
