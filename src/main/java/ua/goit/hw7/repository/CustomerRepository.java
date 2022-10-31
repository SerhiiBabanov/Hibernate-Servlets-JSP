package ua.goit.hw7.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.goit.hw7.config.HibernateProvider;
import ua.goit.hw7.model.dao.CustomerDao;

import java.util.List;
import java.util.Optional;

public class CustomerRepository implements Repository<CustomerDao> {
    private final HibernateProvider manager;

    public CustomerRepository(HibernateProvider manager) {
        this.manager = manager;
    }


    @Override
    public CustomerDao save(CustomerDao entity) {
        try (Session session = manager.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Save customer failed");
        }
        return entity;
    }

    @Override
    public void delete(CustomerDao entity) {
        try (Session session = manager.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Delete customer failed");
        }
    }

    @Override
    public Optional<CustomerDao> findById(Long id) {
        try (Session session = manager.openSession()) {
            return Optional.ofNullable(session.byId(CustomerDao.class).load(id));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Select customer by id failed");
        }
    }

    @Override
    public CustomerDao update(CustomerDao entity) {
        try (Session session = manager.openSession()) {
            session.beginTransaction();
            CustomerDao updated = session.merge(entity);
            session.getTransaction().commit();
            return updated;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Customer not updated");
        }
    }

    @Override
    public List<CustomerDao> findAll() {
        try (Session session = manager.openSession()) {
            return session.createQuery("from CustomerDao", CustomerDao.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Select all customers failed");
        }
    }

    @Override
    public List<CustomerDao> findByListOfID(List<Long> idList) {
        try (Session session = manager.openSession()) {
            return session.byMultipleIds(CustomerDao.class).multiLoad(idList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Select customers by ids failed");
        }
    }
}
