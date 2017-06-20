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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author amirk
 */
@Entity
@Table(name = "teacher_group")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TeacherGroup.findAll", query = "SELECT t FROM TeacherGroup t"),
    @NamedQuery(name = "TeacherGroup.findByTId", query = "SELECT t FROM TeacherGroup t WHERE t.tId = :tId"),
     @NamedQuery(name = "TeacherGroup.findByTeacher", query = "SELECT t FROM TeacherGroup t WHERE t.tTeacher.userId = :id"),
    @NamedQuery(name = "TeacherGroup.findByTName", query = "SELECT t FROM TeacherGroup t WHERE t.tName = :tName"),
    @NamedQuery(name = "TeacherGroup.findByTDescription", query = "SELECT t FROM TeacherGroup t WHERE t.tDescription = :tDescription"),
    @NamedQuery(name = "TeacherGroup.findByTGroupUserTeacher", query = "SELECT t FROM TeacherGroup t WHERE t.tGroupUserTeacher = :tGroupUserTeacher")})
public class TeacherGroup implements Serializable {

    @OneToMany(mappedBy = "uTeacherGroup")
    private Collection<UserGroup> userGroupCollection;

    @JoinColumn(name = "t_teacher", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User tTeacher;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "t_id")
    private Integer tId;
    @Size(max = 45)
    @Column(name = "t_name")
    private String tName;
    @Size(max = 250)
    @Column(name = "t_description")
    private String tDescription;
    @Column(name = "t_group_user_teacher")
    private Integer tGroupUserTeacher;
  
   

    public TeacherGroup() {
    }

    public TeacherGroup(Integer tId) {
        this.tId = tId;
    }

    public Integer getTId() {
        return tId;
    }

    public void setTId(Integer tId) {
        this.tId = tId;
    }

    public String getTName() {
        return tName;
    }

    public void setTName(String tName) {
        this.tName = tName;
    }

    public String getTDescription() {
        return tDescription;
    }

    public void setTDescription(String tDescription) {
        this.tDescription = tDescription;
    }

    public Integer getTGroupUserTeacher() {
        return tGroupUserTeacher;
    }

    public void setTGroupUserTeacher(Integer tGroupUserTeacher) {
        this.tGroupUserTeacher = tGroupUserTeacher;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tId != null ? tId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TeacherGroup)) {
            return false;
        }
        TeacherGroup other = (TeacherGroup) object;
        if ((this.tId == null && other.tId != null) || (this.tId != null && !this.tId.equals(other.tId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.TeacherGroup[ tId=" + tId + " ]";
    }

    public User getTTeacher() {
        return tTeacher;
    }

    public void setTTeacher(User tTeacher) {
        this.tTeacher = tTeacher;
    }

    @XmlTransient
    public Collection<UserGroup> getUserGroupCollection() {
        return userGroupCollection;
    }

    public void setUserGroupCollection(Collection<UserGroup> userGroupCollection) {
        this.userGroupCollection = userGroupCollection;
    }
    
}
