package ua.goit.hw7.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.goit.hw7.config.HibernateProvider;
import ua.goit.hw7.model.dao.ProjectDao;

import java.util.List;
import java.util.Optional;

public class ProjectRepository implements Repository<ProjectDao> {
    private final HibernateProvider manager;

    public ProjectRepository(HibernateProvider manager) {
        this.manager = manager;
    }


    @Override
    public ProjectDao save(ProjectDao entity) {
        try (Session session = manager.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Save project failed");
        }
        return entity;
    }

    @Override
    public void delete(ProjectDao entity) {
        try (Session session = manager.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Delete project failed");
        }
    }

    @Override
    public Optional<ProjectDao> findById(Long id) {
        try (Session session = manager.openSession()) {
            return Optional.ofNullable(session.byId(ProjectDao.class).load(id));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Select project by id failed");
        }
    }

    @Override
    public ProjectDao update(ProjectDao entity) {
        try (Session session = manager.openSession()) {
            session.beginTransaction();
            ProjectDao updated = session.merge(entity);
            session.getTransaction().commit();
            return updated;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Project not updated");
        }
    }

    @Override
    public List<ProjectDao> findAll() {
        try (Session session = manager.openSession()) {
            return session.createQuery("from ProjectDao ", ProjectDao.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Select all projects failed");
        }
    }

    @Override
    public List<ProjectDao> findByListOfID(List<Long> idList) {
        try (Session session = manager.openSession()) {
            return session.byMultipleIds(ProjectDao.class).multiLoad(idList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Select projects by ids failed");
        }
    }


}
