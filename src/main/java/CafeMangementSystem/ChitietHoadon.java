package CafeMangementSystem;

import javax.persistence.*;

@Entity
@Table(name = "chitiet_hoadon", schema = "quancafe", catalog = "")
@IdClass(ChitietHoadonPK.class)
public class ChitietHoadon {
    private Object mahoadon;
    private Object mamon;
    private Integer soluong;
    private Double giaban;
    private Double tongtien;
    private Hoadon hoadonByMahoadon;
    private Monmenu monmenuByMamon;

    @Id
    @Column(name = "mahoadon", nullable = false)
    public Object getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(Object mahoadon) {
        this.mahoadon = mahoadon;
    }

    @Id
    @Column(name = "mamon", nullable = false)
    public Object getMamon() {
        return mamon;
    }

    public void setMamon(Object mamon) {
        this.mamon = mamon;
    }

    @Basic
    @Column(name = "soluong", nullable = true)
    public Integer getSoluong() {
        return soluong;
    }

    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }

    @Basic
    @Column(name = "giaban", nullable = true, precision = 0)
    public Double getGiaban() {
        return giaban;
    }

    public void setGiaban(Double giaban) {
        this.giaban = giaban;
    }

    @Basic
    @Column(name = "tongtien", nullable = false, precision = 0)
    public Double getTongtien() {
        return tongtien;
    }

    public void setTongtien(Double tongtien) {
        this.tongtien = tongtien;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChitietHoadon that = (ChitietHoadon) o;

        if (mahoadon != null ? !mahoadon.equals(that.mahoadon) : that.mahoadon != null) return false;
        if (mamon != null ? !mamon.equals(that.mamon) : that.mamon != null) return false;
        if (soluong != null ? !soluong.equals(that.soluong) : that.soluong != null) return false;
        if (giaban != null ? !giaban.equals(that.giaban) : that.giaban != null) return false;
        if (tongtien != null ? !tongtien.equals(that.tongtien) : that.tongtien != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mahoadon != null ? mahoadon.hashCode() : 0;
        result = 31 * result + (mamon != null ? mamon.hashCode() : 0);
        result = 31 * result + (soluong != null ? soluong.hashCode() : 0);
        result = 31 * result + (giaban != null ? giaban.hashCode() : 0);
        result = 31 * result + (tongtien != null ? tongtien.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "mahoadon", referencedColumnName = "mahoadon", nullable = false)
    public Hoadon getHoadonByMahoadon() {
        return hoadonByMahoadon;
    }

    public void setHoadonByMahoadon(Hoadon hoadonByMahoadon) {
        this.hoadonByMahoadon = hoadonByMahoadon;
    }

    @ManyToOne
    @JoinColumn(name = "mamon", referencedColumnName = "mamon", nullable = false)
    public Monmenu getMonmenuByMamon() {
        return monmenuByMamon;
    }

    public void setMonmenuByMamon(Monmenu monmenuByMamon) {
        this.monmenuByMamon = monmenuByMamon;
    }
}
