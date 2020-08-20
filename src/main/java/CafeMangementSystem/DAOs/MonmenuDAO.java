package CafeMangementSystem.DAOs;

import CafeMangementSystem.Entities.Monmenu;
import CafeMangementSystem.Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class MonmenuDAO implements DAO<Monmenu> {
    private static MonmenuDAO instance;
    private MonmenuDAO() {
    }
    public static MonmenuDAO getInstance() {
        if (instance == null) {
            return (instance = new MonmenuDAO());
        }
        return instance;
    }

    @Override
    public int getMaxId() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Integer returnValue = -1;
        try{
            String sql = "Select count(m.mamon) from Monmenu m";
            Query query = session.createQuery(sql);
            returnValue = query.uniqueResult() == null ? -1 : (Integer) query.uniqueResult();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return returnValue;
    }

    @Override
    public List<Monmenu> getAll() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<Monmenu> result = null;
        try{
            String sql = "Select m from Monmenu m";
            Query query = session.createQuery(sql);
            result = query.list();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean insert(Monmenu obj) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction t = null;
        try{
            t = session.beginTransaction();
            session.save(obj);
            t.commit();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            if(t != null) t.rollback();
        }
        return false;
    }

    @Override
    public Monmenu get(int id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Monmenu result = null;
        try{
            String sql = "Select m from Monmenu m where m.mamon = :id";
            Query query = session.createQuery(sql);
            query.setParameter("id", id);
            result = (Monmenu) query.uniqueResult();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(int id, Monmenu newObj) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction t = null;
        try{
            String sql = "Select m from Monmenu m where m.mamon = :id";
            Query query = session.createQuery(sql);
            query.setParameter("id", id);
            Monmenu result = (Monmenu) query.uniqueResult();

            if(result == null) throw new Exception("id of Monmenu not Available");
            t = session.beginTransaction();
            session.update(newObj);
            t.commit();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            if(t != null) t.rollback();
        }
        return false;
    }

    @Override
    public boolean delete(Monmenu obj) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction t = null;
        try{
            t = session.beginTransaction();
            session.delete(obj);
            t.commit();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            if(t != null) t.rollback();
        }
        return false;
    }
}
