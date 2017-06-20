/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "course_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CourseType.findAll", query = "SELECT c FROM CourseType c ORDER BY c.courseTypeId DESC"),
    @NamedQuery(name = "CourseType.findAllSort", query = "SELECT c FROM CourseType c ORDER BY c.value"),
    @NamedQuery(name = "CourseType.findByCourseTypeId", query = "SELECT c FROM CourseType c WHERE c.courseTypeId = :courseTypeId"),
    @NamedQuery(name = "CourseType.findByValue", query = "SELECT c FROM CourseType c WHERE c.value = :value"),
    @NamedQuery(name = "CourseType.findByDescription", query = "SELECT c FROM CourseType c WHERE c.description = :description")})
public class CourseType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "course_type_id")
    private Integer courseTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "value")
    private String value;
    @Size(max = 256)
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "courseTypeId")
    private Collection<Course> courseCollection;

    public CourseType() {
    }

    public CourseType(Integer courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public CourseType(Integer courseTypeId, String value) {
        this.courseTypeId = courseTypeId;
        this.value = value;
    }

    public Integer getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Integer courseTypeId) {
        this.courseTypeId = courseTypeId;
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
    public Collection<Course> getCourseCollection() {
        return courseCollection;
    }

    public void setCourseCollection(Collection<Course> courseCollection) {
        this.courseCollection = courseCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (courseTypeId != null ? courseTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CourseType)) {
            return false;
        }
        CourseType other = (CourseType) object;
        if ((this.courseTypeId == null && other.courseTypeId != null) || (this.courseTypeId != null && !this.courseTypeId.equals(other.courseTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.CourseType[ courseTypeId=" + courseTypeId + " ]";
    }
    
}
