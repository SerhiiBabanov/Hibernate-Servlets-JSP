package ua.goit.hw7.model.dao;

import jakarta.persistence.*;
import ua.goit.hw7.model.SkillLevel;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "skills")
public class SkillDao {
    private Long id;
    private String language;
    private SkillLevel level;
    private Set<DeveloperDao> developers;

    public SkillDao() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "language", length = 30)
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    public SkillLevel getLevel() {
        return level;
    }

    public void setLevel(SkillLevel level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillDao skillDao = (SkillDao) o;
        return id.equals(skillDao.id) && Objects.equals(language, skillDao.language) && Objects.equals(level, skillDao.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, language, level);
    }

    @ManyToMany(mappedBy = "skills")
    public Set<DeveloperDao> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<DeveloperDao> developers) {
        this.developers = developers;
    }
}
