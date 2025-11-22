/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FilmBioskop;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author RIYANA
 */
@Entity
@Table(name = "studio")
@NamedQueries({
    @NamedQuery(name = "Studio.findAll", query = "SELECT s FROM Studio s"),
    @NamedQuery(name = "Studio.findByIdStudio", query = "SELECT s FROM Studio s WHERE s.idStudio = :idStudio"),
    @NamedQuery(name = "Studio.findByNamaStudio", query = "SELECT s FROM Studio s WHERE s.namaStudio = :namaStudio")})
public class Studio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_studio")
    private String idStudio;
    @Column(name = "nama_studio")
    private String namaStudio;

    public Studio() {
    }

    public Studio(String idStudio) {
        this.idStudio = idStudio;
    }

    public String getIdStudio() {
        return idStudio;
    }

    public void setIdStudio(String idStudio) {
        this.idStudio = idStudio;
    }

    public String getNamaStudio() {
        return namaStudio;
    }

    public void setNamaStudio(String namaStudio) {
        this.namaStudio = namaStudio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStudio != null ? idStudio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Studio)) {
            return false;
        }
        Studio other = (Studio) object;
        if ((this.idStudio == null && other.idStudio != null) || (this.idStudio != null && !this.idStudio.equals(other.idStudio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FilmBioskop.Studio[ idStudio=" + idStudio + " ]";
    }
    
}
