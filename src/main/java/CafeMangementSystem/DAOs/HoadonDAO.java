package CafeMangementSystem.DAOs;

import CafeMangementSystem.Entities.Hoadon;
import CafeMangementSystem.Entities.Nhanvien;
import CafeMangementSystem.Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HoadonDAO implements DAO<Hoadon> {
    private static HoadonDAO instance;
    private HoadonDAO() {}
    public static HoadonDAO getInstance() {
        if (instance == null) {
            return new HoadonDAO();
        }
        return instance;
    }

    @Override
    public int getMaxId() {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Number value = 0;

        try {
            session.getTransaction().begin();

            String sql = "select max(hd.mahoadon) from " + Hoadon.class.getName() + " hd";
            Query<Number> query = session.createQuery(sql);
            value = query.getSingleResult();

            if (value==null) {
                value = 0;
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return value.intValue();
    }

    @Override
    public List<Hoadon> getAll() {
        return null;
    }

    @Override
    public boolean insert(Hoadon obj) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();

        try {
            session.getTransaction().begin();

            session.save(obj);

            session.flush();
            System.out.println("Hóa đơn mới dược thêm vào DB:" + obj);

            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return false;
    }

    @Override
    public Hoadon get(int id) {
        return null;
    }

    @Override
    public boolean update(int id, Hoadon newObj) {
        return false;
    }

    @Override
    public boolean delete(Hoadon obj) {
        return false;
    }
}
