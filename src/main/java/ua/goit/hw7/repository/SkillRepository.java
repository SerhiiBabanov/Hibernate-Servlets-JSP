package ua.goit.hw7.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.goit.hw7.config.HibernateProvider;
import ua.goit.hw7.model.SkillLevel;
import ua.goit.hw7.model.dao.SkillDao;

import java.util.List;
import java.util.Optional;

public class SkillRepository implements Repository<SkillDao> {
    private final HibernateProvider manager;

    public SkillRepository(HibernateProvider manager) {
        this.manager = manager;
    }


    @Override
    public SkillDao save(SkillDao entity) {
        try (Session session = manager.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Save skill failed");
        }
        return entity;
    }

    @Override
    public void delete(SkillDao entity) {
        try (Session session = manager.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Delete skill failed");
        }
    }

    @Override
    public Optional<SkillDao> findById(Long id) {
        try (Session session = manager.openSession()) {
            return Optional.ofNullable(session.byId(SkillDao.class).load(id));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Select skill by id failed");
        }
    }

    @Override
    public SkillDao update(SkillDao entity) {
        try (Session session = manager.openSession()) {
            session.beginTransaction();
            SkillDao updated = session.merge(entity);
            session.getTransaction().commit();
            return updated;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Skill not updated");
        }
    }

    @Override
    public List<SkillDao> findAll() {
        try (Session session = manager.openSession()) {
            return session.createQuery("from SkillDao ", SkillDao.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Select all skills failed");
        }
    }

    @Override
    public List<SkillDao> findByListOfID(List<Long> idList) {
        try (Session session = manager.openSession()) {
            return session.byMultipleIds(SkillDao.class).multiLoad(idList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Select skills by ids failed");
        }
    }

    public List<SkillDao> findByLanguage(String language) {
        try (Session session = manager.openSession()) {
            return session.createQuery("from SkillDao where language = :language", SkillDao.class)
                    .setParameter("language", language)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Select skills with language failed");
        }
    }

    public List<SkillDao> findByLevel(SkillLevel level) {
        try (Session session = manager.openSession()) {
            return session.createQuery("from SkillDao where level = :level", SkillDao.class)
                    .setParameter("level", level)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Select skills with level failed");
        }
    }

}
