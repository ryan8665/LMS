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
@Table(name = "sub_course")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubCourse.findAll", query = "SELECT s FROM SubCourse s ORDER BY s.subCourseId DESC"),
    @NamedQuery(name = "SubCourse.findAllSort", query = "SELECT s FROM SubCourse s ORDER BY s.name"),
    @NamedQuery(name = "SubCourse.findByAllSortSubCourseId", query = "SELECT s FROM SubCourse s WHERE s.subCourseId = :subCourseId ORDER BY s.name"),
    @NamedQuery(name = "SubCourse.findBySubCourseId", query = "SELECT s FROM SubCourse s WHERE s.subCourseId = :subCourseId"),
    @NamedQuery(name = "SubCourse.findByDescription", query = "SELECT s FROM SubCourse s WHERE s.description = :description"),
    @NamedQuery(name = "SubCourse.findByInclusive", query = "SELECT s FROM SubCourse s WHERE s.inclusive = :inclusive")})
public class SubCourse implements Serializable {

    @OneToMany(mappedBy = "cCourse")
    private Collection<Comprehensive> comprehensiveCollection;

    @Size(max = 30)
    @Column(name = "name")
    private String name;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sub_course_id")
    private Integer subCourseId;
    @Size(max = 64)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inclusive")
    private boolean inclusive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subCourseId")
    private Collection<Chapter> chapterCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subCourseId")
    private Collection<SCT> sCTCollection;
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    @ManyToOne(optional = false)
    private Course courseId;
    @JoinColumn(name = "global_status_id", referencedColumnName = "global_status_id")
    @ManyToOne(optional = false)
    private GlobalStatus globalStatusId;

    public SubCourse() {
    }

    public SubCourse(Integer subCourseId) {
        this.subCourseId = subCourseId;
    }

    public SubCourse(Integer subCourseId, boolean inclusive) {
        this.subCourseId = subCourseId;
        this.inclusive = inclusive;
    }

    public Integer getSubCourseId() {
        return subCourseId;
    }

    public void setSubCourseId(Integer subCourseId) {
        this.subCourseId = subCourseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getInclusive() {
        return inclusive;
    }

    public void setInclusive(boolean inclusive) {
        this.inclusive = inclusive;
    }

    @XmlTransient
    public Collection<Chapter> getChapterCollection() {
        return chapterCollection;
    }

    public void setChapterCollection(Collection<Chapter> chapterCollection) {
        this.chapterCollection = chapterCollection;
    }

    @XmlTransient
    public Collection<SCT> getSCTCollection() {
        return sCTCollection;
    }

    public void setSCTCollection(Collection<SCT> sCTCollection) {
        this.sCTCollection = sCTCollection;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    public GlobalStatus getGlobalStatusId() {
        return globalStatusId;
    }

    public void setGlobalStatusId(GlobalStatus globalStatusId) {
        this.globalStatusId = globalStatusId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subCourseId != null ? subCourseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubCourse)) {
            return false;
        }
        SubCourse other = (SubCourse) object;
        if ((this.subCourseId == null && other.subCourseId != null) || (this.subCourseId != null && !this.subCourseId.equals(other.subCourseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.SubCourse[ subCourseId=" + subCourseId + " ]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Comprehensive> getComprehensiveCollection() {
        return comprehensiveCollection;
    }

    public void setComprehensiveCollection(Collection<Comprehensive> comprehensiveCollection) {
        this.comprehensiveCollection = comprehensiveCollection;
    }
    
}
