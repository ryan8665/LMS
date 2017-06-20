/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author amirk
 */
@Entity
@Table(name = "comprehensive")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comprehensive.findAll", query = "SELECT c FROM Comprehensive c ORDER BY c.cId DESC"),
    @NamedQuery(name = "Comprehensive.findByCId", query = "SELECT c FROM Comprehensive c WHERE c.cId = :cId"),
    @NamedQuery(name = "Comprehensive.findByCTitle", query = "SELECT c FROM Comprehensive c WHERE c.cTitle = :cTitle"),
    @NamedQuery(name = "Comprehensive.findByCDescription", query = "SELECT c FROM Comprehensive c WHERE c.cDescription = :cDescription"),
    @NamedQuery(name = "Comprehensive.findByCExecuteDate", query = "SELECT c FROM Comprehensive c WHERE c.cExecuteDate = :cExecuteDate"),
    @NamedQuery(name = "Comprehensive.findByCShortDescription", query = "SELECT c FROM Comprehensive c WHERE c.cShortDescription = :cShortDescription")})
public class Comprehensive implements Serializable {

    @JoinColumn(name = "c_course", referencedColumnName = "sub_course_id")
    @ManyToOne
    private SubCourse cCourse;
    @JoinColumn(name = "c_hardness", referencedColumnName = "hardness_id")
    @ManyToOne
    private Hardness cHardness;

    @JoinColumn(name = "c_creator", referencedColumnName = "admin_id")
    @ManyToOne
    private Admin cCreator;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "c_id")
    private Integer cId;
    @Size(max = 45)
    @Column(name = "c_title")
    private String cTitle;
    @Size(max = 200)
    @Column(name = "c_description")
    private String cDescription;
    @Column(name = "c_execute_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cExecuteDate;
    @Size(max = 45)
    @Column(name = "c_short_description")
    private String cShortDescription;
    @OneToMany(mappedBy = "cuComprehensive")
    private Collection<ComprehensiveUser> comprehensiveUserCollection;

    public Comprehensive() {
    }

    public Comprehensive(Integer cId) {
        this.cId = cId;
    }

    public Integer getCId() {
        return cId;
    }

    public void setCId(Integer cId) {
        this.cId = cId;
    }

    public String getCTitle() {
        return cTitle;
    }

    public void setCTitle(String cTitle) {
        this.cTitle = cTitle;
    }

    public String getCDescription() {
        return cDescription;
    }

    public void setCDescription(String cDescription) {
        this.cDescription = cDescription;
    }

    public Date getCExecuteDate() {
        return cExecuteDate;
    }

    public void setCExecuteDate(Date cExecuteDate) {
        this.cExecuteDate = cExecuteDate;
    }

    public String getCShortDescription() {
        return cShortDescription;
    }

    public void setCShortDescription(String cShortDescription) {
        this.cShortDescription = cShortDescription;
    }

    @XmlTransient
    public Collection<ComprehensiveUser> getComprehensiveUserCollection() {
        return comprehensiveUserCollection;
    }

    public void setComprehensiveUserCollection(Collection<ComprehensiveUser> comprehensiveUserCollection) {
        this.comprehensiveUserCollection = comprehensiveUserCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cId != null ? cId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comprehensive)) {
            return false;
        }
        Comprehensive other = (Comprehensive) object;
        if ((this.cId == null && other.cId != null) || (this.cId != null && !this.cId.equals(other.cId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Comprehensive[ cId=" + cId + " ]";
    }

    public Admin getCCreator() {
        return cCreator;
    }

    public void setCCreator(Admin cCreator) {
        this.cCreator = cCreator;
    }

    public SubCourse getCCourse() {
        return cCourse;
    }

    public void setCCourse(SubCourse cCourse) {
        this.cCourse = cCourse;
    }

    public Hardness getCHardness() {
        return cHardness;
    }

    public void setCHardness(Hardness cHardness) {
        this.cHardness = cHardness;
    }
    
}
