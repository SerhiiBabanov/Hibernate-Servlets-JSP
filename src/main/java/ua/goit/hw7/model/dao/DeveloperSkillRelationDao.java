package ua.goit.hw7.model.dao;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "developer_skill_relation")
public class DeveloperSkillRelationDao {
    private Long id;
    private Long developerId;
    private Long skillId;

    public DeveloperSkillRelationDao() {
    }

    public DeveloperSkillRelationDao(Long developerId, Long skillId) {
        this.developerId = developerId;
        this.skillId = skillId;
    }

    public DeveloperSkillRelationDao(Long id, Long developerId, Long skillId) {
        this.id = id;
        this.developerId = developerId;
        this.skillId = skillId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "developer_id")
    public Long getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Long developerId) {
        this.developerId = developerId;
    }

    @Column(name = "skill_id")
    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeveloperSkillRelationDao that = (DeveloperSkillRelationDao) o;
        return Objects.equals(id, that.id) && Objects.equals(developerId, that.developerId) && Objects.equals(skillId, that.skillId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, developerId, skillId);
    }
}
