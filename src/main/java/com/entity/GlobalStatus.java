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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "global_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GlobalStatus.findAll", query = "SELECT g FROM GlobalStatus g"),
    @NamedQuery(name = "GlobalStatus.findByGlobalStatusId", query = "SELECT g FROM GlobalStatus g WHERE g.globalStatusId = :globalStatusId"),
    @NamedQuery(name = "GlobalStatus.findByValue", query = "SELECT g FROM GlobalStatus g WHERE g.value = :value"),
    @NamedQuery(name = "GlobalStatus.findByDescription", query = "SELECT g FROM GlobalStatus g WHERE g.description = :description")})
public class GlobalStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "global_status_id")
    private Integer globalStatusId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "value")
    private String value;
    @Size(max = 64)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "globalStatusId")
    private Collection<SCT> sCTCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "globalStatusId")
    private Collection<SubCourse> subCourseCollection;
    @OneToMany(mappedBy = "globalStatusId")
    private Collection<UserSCT> userSCTCollection;

    public GlobalStatus() {
    }

    public GlobalStatus(Integer globalStatusId) {
        this.globalStatusId = globalStatusId;
    }

    public GlobalStatus(Integer globalStatusId, String value) {
        this.globalStatusId = globalStatusId;
        this.value = value;
    }

    public Integer getGlobalStatusId() {
        return globalStatusId;
    }

    public void setGlobalStatusId(Integer globalStatusId) {
        this.globalStatusId = globalStatusId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<SCT> getSCTCollection() {
        return sCTCollection;
    }

    public void setSCTCollection(Collection<SCT> sCTCollection) {
        this.sCTCollection = sCTCollection;
    }

    @XmlTransient
    public Collection<SubCourse> getSubCourseCollection() {
        return subCourseCollection;
    }

    public void setSubCourseCollection(Collection<SubCourse> subCourseCollection) {
        this.subCourseCollection = subCourseCollection;
    }

    @XmlTransient
    public Collection<UserSCT> getUserSCTCollection() {
        return userSCTCollection;
    }

    public void setUserSCTCollection(Collection<UserSCT> userSCTCollection) {
        this.userSCTCollection = userSCTCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (globalStatusId != null ? globalStatusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GlobalStatus)) {
            return false;
        }
        GlobalStatus other = (GlobalStatus) object;
        if ((this.globalStatusId == null && other.globalStatusId != null) || (this.globalStatusId != null && !this.globalStatusId.equals(other.globalStatusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.GlobalStatus[ globalStatusId=" + globalStatusId + " ]";
    }
    
}
