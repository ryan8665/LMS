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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "geo_city")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeoCity.findAll", query = "SELECT g FROM GeoCity g"),
    @NamedQuery(name = "GeoCity.findByGeoCityId", query = "SELECT g FROM GeoCity g WHERE g.geoCityId = :geoCityId"),
    @NamedQuery(name = "GeoCity.findByValue", query = "SELECT g FROM GeoCity g WHERE g.value = :value")})
public class GeoCity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "geo_city_id")
    private Integer geoCityId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "value")
    private String value;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "geoCityId")
    private Collection<School> schoolCollection;
    @JoinColumn(name = "geo_state_id", referencedColumnName = "geo_state_id")
    @ManyToOne(optional = false)
    private GeoState geoStateId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "geoCityId")
    private Collection<User> userCollection;

    public GeoCity() {
    }

    public GeoCity(Integer geoCityId) {
        this.geoCityId = geoCityId;
    }

    public GeoCity(Integer geoCityId, String value) {
        this.geoCityId = geoCityId;
        this.value = value;
    }

    public Integer getGeoCityId() {
        return geoCityId;
    }

    public void setGeoCityId(Integer geoCityId) {
        this.geoCityId = geoCityId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @XmlTransient
    public Collection<School> getSchoolCollection() {
        return schoolCollection;
    }

    public void setSchoolCollection(Collection<School> schoolCollection) {
        this.schoolCollection = schoolCollection;
    }

    public GeoState getGeoStateId() {
        return geoStateId;
    }

    public void setGeoStateId(GeoState geoStateId) {
        this.geoStateId = geoStateId;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (geoCityId != null ? geoCityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GeoCity)) {
            return false;
        }
        GeoCity other = (GeoCity) object;
        if ((this.geoCityId == null && other.geoCityId != null) || (this.geoCityId != null && !this.geoCityId.equals(other.geoCityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.GeoCity[ geoCityId=" + geoCityId + " ]";
    }
    
}
