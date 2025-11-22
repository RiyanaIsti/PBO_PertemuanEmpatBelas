/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FilmBioskop;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author RIYANA
 */
@Entity
@Table(name = "jadwal_tayang")
@NamedQueries({
    @NamedQuery(name = "JadwalTayang.findAll", query = "SELECT j FROM JadwalTayang j"),
    @NamedQuery(name = "JadwalTayang.findByIdJadwal", query = "SELECT j FROM JadwalTayang j WHERE j.idJadwal = :idJadwal"),
    @NamedQuery(name = "JadwalTayang.findByIdStudio", query = "SELECT j FROM JadwalTayang j WHERE j.idStudio = :idStudio"),
    @NamedQuery(name = "JadwalTayang.findByTanggalTayang", query = "SELECT j FROM JadwalTayang j WHERE j.tanggalTayang = :tanggalTayang"),
    @NamedQuery(name = "JadwalTayang.findByWaktuTayang", query = "SELECT j FROM JadwalTayang j WHERE j.waktuTayang = :waktuTayang")})
public class JadwalTayang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_jadwal")
    private String idJadwal;
    @Column(name = "id_studio")
    private String idStudio;
    @Basic(optional = false)
    @Column(name = "tanggal_tayang")
    @Temporal(TemporalType.DATE)
    private Date tanggalTayang;
    @Basic(optional = false)
    @Column(name = "waktu_tayang")
    @Temporal(TemporalType.TIME)
    private Date waktuTayang;
    @JoinColumn(name = "id_film", referencedColumnName = "id_film")
    @ManyToOne
    private Film idFilm;

    public JadwalTayang() {
    }

    public JadwalTayang(String idJadwal) {
        this.idJadwal = idJadwal;
    }

    public JadwalTayang(String idJadwal, Date tanggalTayang, Date waktuTayang) {
        this.idJadwal = idJadwal;
        this.tanggalTayang = tanggalTayang;
        this.waktuTayang = waktuTayang;
    }

    public String getIdJadwal() {
        return idJadwal;
    }

    public void setIdJadwal(String idJadwal) {
        this.idJadwal = idJadwal;
    }

    public String getIdStudio() {
        return idStudio;
    }

    public void setIdStudio(String idStudio) {
        this.idStudio = idStudio;
    }

    public Date getTanggalTayang() {
        return tanggalTayang;
    }

    public void setTanggalTayang(Date tanggalTayang) {
        this.tanggalTayang = tanggalTayang;
    }

    public Date getWaktuTayang() {
        return waktuTayang;
    }

    public void setWaktuTayang(Date waktuTayang) {
        this.waktuTayang = waktuTayang;
    }

    public Film getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(Film idFilm) {
        this.idFilm = idFilm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJadwal != null ? idJadwal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JadwalTayang)) {
            return false;
        }
        JadwalTayang other = (JadwalTayang) object;
        if ((this.idJadwal == null && other.idJadwal != null) || (this.idJadwal != null && !this.idJadwal.equals(other.idJadwal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FilmBioskop.JadwalTayang[ idJadwal=" + idJadwal + " ]";
    }
    
}
