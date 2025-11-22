/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FilmBioskop;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author RIYANA
 */
@Entity
@Table(name = "film")
@NamedQueries({
    @NamedQuery(name = "Film.findAll", query = "SELECT f FROM Film f"),
    @NamedQuery(name = "Film.findByIdFilm", query = "SELECT f FROM Film f WHERE f.idFilm = :idFilm"),
    @NamedQuery(name = "Film.findByJudul", query = "SELECT f FROM Film f WHERE f.judul = :judul"),
    @NamedQuery(name = "Film.findByGenre", query = "SELECT f FROM Film f WHERE f.genre = :genre"),
    @NamedQuery(name = "Film.findByDurasi", query = "SELECT f FROM Film f WHERE f.durasi = :durasi")})
public class Film implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_film")
    private String idFilm;
    @Basic(optional = false)
    @Column(name = "judul")
    private String judul;
    @Basic(optional = false)
    @Column(name = "genre")
    private String genre;
    @Basic(optional = false)
    @Column(name = "durasi")
    private int durasi;
    @OneToMany(mappedBy = "idFilm")
    private Collection<JadwalTayang> jadwalTayangCollection;

    public Film() {
    }

    public Film(String idFilm) {
        this.idFilm = idFilm;
    }

    public Film(String idFilm, String judul, String genre, int durasi) {
        this.idFilm = idFilm;
        this.judul = judul;
        this.genre = genre;
        this.durasi = durasi;
    }

    public String getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(String idFilm) {
        this.idFilm = idFilm;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDurasi() {
        return durasi;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public Collection<JadwalTayang> getJadwalTayangCollection() {
        return jadwalTayangCollection;
    }

    public void setJadwalTayangCollection(Collection<JadwalTayang> jadwalTayangCollection) {
        this.jadwalTayangCollection = jadwalTayangCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFilm != null ? idFilm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Film)) {
            return false;
        }
        Film other = (Film) object;
        if ((this.idFilm == null && other.idFilm != null) || (this.idFilm != null && !this.idFilm.equals(other.idFilm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FilmBioskop.Film[ idFilm=" + idFilm + " ]";
    }
    
}
