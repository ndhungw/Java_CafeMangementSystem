package CafeMangementSystem.DAOs;

import CafeMangementSystem.Entities.Monmenu;
import CafeMangementSystem.Utils.HibernateUtils;
import CafeMangementSystem.Utils.ThreadPool;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class MonmenuDAO implements DAO<Monmenu> {
    private static MonmenuDAO instance;
    private MonmenuDAO() {
    }
    private static class InnerDAO{
        private static MonmenuDAO DAO = new MonmenuDAO();
    }

    public static MonmenuDAO getInstance(){
        return InnerDAO.DAO;
    }

    @Override
    public int getMaxId() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Integer returnValue = -1;
        try{
            session.getTransaction().begin();
            String sql = "Select count(m.mamon) from Monmenu m";
            Query query = session.createQuery(sql);
            returnValue = query.uniqueResult() == null ? -1 : (Integer) query.uniqueResult();
            session.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return returnValue;
    }

    @Override
    public List<Monmenu> getAll() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<Monmenu> result = null;
        try{
            session.getTransaction().begin();
            String sql = "Select m from Monmenu m";
            Query query = session.createQuery(sql);
            result = query.list();
            session.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
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
            session.close();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return false;
    }

    @Override
    public Monmenu get(int id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Monmenu result = null;
        try{
            session.getTransaction().begin();
            String sql = "Select m from Monmenu m where m.mamon = :id";
            Query query = session.createQuery(sql);
            query.setParameter("id", id);
            result = (Monmenu) query.uniqueResult();
            session.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return result;
    }

    @Override
    public boolean update(int id, Monmenu newObj) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try{
            session.getTransaction().begin();
            session.update(newObj);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
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
            session.close();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return false;
    }

    public static class MonmenuDAOException extends Exception{
        private String details;
        public MonmenuDAOException(String message, String details){
            super(message);
            this.details = details;
        }
        public String getDetails() {
            return details;
        }
    }

    public void checkCurrentTrangThai_ThenUpdateTrangThai(Monmenu obj) throws MonmenuDAOException {
        if(obj == null || obj.getMamon() == null) return;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try{
            session.getTransaction().begin();
            System.out.println("cccccccccccccccccccccccccccccc");
            boolean currentTT = obj.TrangThaiBanProperty().get();
            System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbb");
            session.refresh(obj);
            System.out.println(currentTT);
            boolean ttnew = obj.getTrangthai();
            if(ttnew == !currentTT){
                if(currentTT)
                    throw new MonmenuDAOException("Không thể ngừng bán món","Không thể ngừng bán món đã ngừng bán. Vui lòng refresh");
                else
                    throw new MonmenuDAOException("Không thể bán lại món", "Không thể bán lại món đang bán. Vui lòng refresh");
            }
            obj.setTrangthai(!currentTT);
            session.update(obj);
        } catch(MonmenuDAOException ex){
            session.getTransaction().commit();
            session.close();
            throw ex;
        } catch(Exception ex){
            ex.printStackTrace(System.err);
            session.getTransaction().rollback();
            session.close();
            return;
        }
        session.getTransaction().commit();
        session.close();
    }

    public List<Monmenu> findMonmenuByID(int id){
        List<Monmenu> result = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try{
            session.getTransaction().begin();
            String hql = "select m from Monmenu m where m.mamon = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            result = query.list();
            session.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace(System.err);
            session.getTransaction().rollback();
        }
        session.close();
        return result;
    }

    public List<Monmenu> findMonmenuByName(String name){
        List<Monmenu> result = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try{
            session.getTransaction().begin();
            String pattern = "%" + name + "%";
            String hql = "select m from Monmenu m where m.tenmon like :name";
            Query query = session.createQuery(hql);
            query.setParameter("name", pattern);
            result = query.list();
            session.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace(System.err);
            session.getTransaction().rollback();
        }
        session.close();
        return result;
    }
}
