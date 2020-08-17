package CafeMangementSystem;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "lichsu_giamon", schema = "quancafe", catalog = "")
@IdClass(LichsuGiamonPK.class)
public class LichsuGiamon {
    private Object mamon;
    private Timestamp thoidiemcapnhat;
    private Double giacapnhat;
    private Monmenu monmenuByMamon;

    @Id
    @Column(name = "mamon", nullable = false)
    public Object getMamon() {
        return mamon;
    }

    public void setMamon(Object mamon) {
        this.mamon = mamon;
    }

    @Id
    @Column(name = "thoidiemcapnhat", nullable = false)
    public Timestamp getThoidiemcapnhat() {
        return thoidiemcapnhat;
    }

    public void setThoidiemcapnhat(Timestamp thoidiemcapnhat) {
        this.thoidiemcapnhat = thoidiemcapnhat;
    }

    @Basic
    @Column(name = "giacapnhat", nullable = false, precision = 0)
    public Double getGiacapnhat() {
        return giacapnhat;
    }

    public void setGiacapnhat(Double giacapnhat) {
        this.giacapnhat = giacapnhat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LichsuGiamon that = (LichsuGiamon) o;

        if (mamon != null ? !mamon.equals(that.mamon) : that.mamon != null) return false;
        if (thoidiemcapnhat != null ? !thoidiemcapnhat.equals(that.thoidiemcapnhat) : that.thoidiemcapnhat != null)
            return false;
        if (giacapnhat != null ? !giacapnhat.equals(that.giacapnhat) : that.giacapnhat != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mamon != null ? mamon.hashCode() : 0;
        result = 31 * result + (thoidiemcapnhat != null ? thoidiemcapnhat.hashCode() : 0);
        result = 31 * result + (giacapnhat != null ? giacapnhat.hashCode() : 0);
        return result;
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
