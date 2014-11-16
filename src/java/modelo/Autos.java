/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author INTERNET2
 */
@Entity
@Table(name = "autos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Autos.findAll", query = "SELECT a FROM Autos a"),
    @NamedQuery(name = "Autos.findByIdAuto", query = "SELECT a FROM Autos a WHERE a.idAuto = :idAuto"),
    @NamedQuery(name = "Autos.findByPlaca", query = "SELECT a FROM Autos a WHERE a.placa = :placa"),
    @NamedQuery(name = "Autos.findByColor", query = "SELECT a FROM Autos a WHERE a.color = :color")})
public class Autos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_auto")
    private Integer idAuto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "placa")
    private String placa;
    @Size(max = 45)
    @Column(name = "color")
    private String color;

    public Autos() {
    }

    public Autos(Integer idAuto) {
        this.idAuto = idAuto;
    }

    public Autos(Integer idAuto, String placa) {
        this.idAuto = idAuto;
        this.placa = placa;
    }

    public Integer getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(Integer idAuto) {
        this.idAuto = idAuto;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAuto != null ? idAuto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autos)) {
            return false;
        }
        Autos other = (Autos) object;
        if ((this.idAuto == null && other.idAuto != null) || (this.idAuto != null && !this.idAuto.equals(other.idAuto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Autos[ idAuto=" + idAuto + " ]";
    }
    
}
