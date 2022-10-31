package ua.goit.hw7.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.goit.hw7.config.HibernateProvider;
import ua.goit.hw7.model.dao.CompanyDao;

import java.util.List;
import java.util.Optional;

public class CompanyRepository implements Repository<CompanyDao> {
    private final HibernateProvider manager;

    public CompanyRepository(HibernateProvider manager) {
        this.manager = manager;
    }

    @Override
    public CompanyDao save(CompanyDao entity) {
        try (Session session = manager.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Save company failed");
        }
        return entity;
    }

    @Override
    public void delete(CompanyDao entity) {
        try (Session session = manager.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Delete company failed");
        }
    }

    @Override
    public Optional<CompanyDao> findById(Long id) {
        try (Session session = manager.openSession()) {
            return Optional.ofNullable(session.byId(CompanyDao.class).load(id));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Select company by id failed");
        }
    }

    @Override
    public CompanyDao update(CompanyDao entity) {
        try (Session session = manager.openSession()) {
            session.beginTransaction();
            CompanyDao updated = session.merge(entity);
            session.getTransaction().commit();
            return updated;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Company not updated");
        }
    }

    @Override
    public List<CompanyDao> findAll() {
        try (Session session = manager.openSession()) {
            return session.createQuery("from CompanyDao", CompanyDao.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Select all companies failed");
        }
    }

    @Override
    public List<CompanyDao> findByListOfID(List<Long> idList) {
        try (Session session = manager.openSession()) {
            return session.byMultipleIds(CompanyDao.class).multiLoad(idList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Select all companies failed");
        }
    }

}
