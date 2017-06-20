/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ryan
 */
@Entity
@Table(name = "geo_state")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeoState.findAll", query = "SELECT g FROM GeoState g"),
    @NamedQuery(name = "GeoState.findByGeoStateId", query = "SELECT g FROM GeoState g WHERE g.geoStateId = :geoStateId"),
    @NamedQuery(name = "GeoState.findByValue", query = "SELECT g FROM GeoState g WHERE g.value = :value")})
public class GeoState implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "geo_state_id")
    private Integer geoStateId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "value")
    private String value;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "geoStateId")
    private Collection<GeoCity> geoCityCollection;

    public GeoState() {
    }

    public GeoState(Integer geoStateId) {
        this.geoStateId = geoStateId;
    }

    public GeoState(Integer geoStateId, String value) {
        this.geoStateId = geoStateId;
        this.value = value;
    }

    public Integer getGeoStateId() {
        return geoStateId;
    }

    public void setGeoStateId(Integer geoStateId) {
        this.geoStateId = geoStateId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @XmlTransient
    public Collection<GeoCity> getGeoCityCollection() {
        return geoCityCollection;
    }

    public void setGeoCityCollection(Collection<GeoCity> geoCityCollection) {
        this.geoCityCollection = geoCityCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (geoStateId != null ? geoStateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GeoState)) {
            return false;
        }
        GeoState other = (GeoState) object;
        if ((this.geoStateId == null && other.geoStateId != null) || (this.geoStateId != null && !this.geoStateId.equals(other.geoStateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.GeoState[ geoStateId=" + geoStateId + " ]";
    }
    
}
