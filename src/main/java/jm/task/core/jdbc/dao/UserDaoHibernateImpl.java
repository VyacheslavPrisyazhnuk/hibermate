package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = null;
        Transaction tx = null;
        try {
            session = Util.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (Id INT PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(20), lastName VARCHAR(20), age INT)")
                    .addEntity(User.class)
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("ролбэк");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        Transaction tx = null;
        try {
            session = Util.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users")
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("ролбэк");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        Transaction tx = null;
        try {
            SessionFactory fg = Util.getSessionFactory();
            session = fg.openSession();
            System.out.println(fg.isOpen());
            System.out.println("Добавление записи в таблицу БД");
            tx = session.beginTransaction();
            User person = new User(name, lastName, age);
            session.save(person);
            tx.commit();
            System.out.println("Записи добавлены");

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = Util.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.createQuery("delete from User where id = :id").setParameter("id", id).executeUpdate();
            tx.commit();
        } catch (IllegalArgumentException e) {
            System.out.println("нет пользователя с таким ID");
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = null;
        Transaction tx = null;
        List<User> users = null;
        try {
            session = Util.getSessionFactory().openSession();
            tx = session.beginTransaction();
            users = session.createQuery("FROM User").list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        } return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        Transaction tx = null;
        try {
            session = Util.getSessionFactory().openSession();
            tx = session.beginTransaction();
            List<User> users = session.createQuery("FROM User").list();
            if (users.size() > 0) {
                for (User obj : users) {
                    session.delete(obj);
                }
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}

