/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author amirk
 */
@Entity
@Table(name = "user_group")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserGroup.findAll", query = "SELECT u FROM UserGroup u"),
      @NamedQuery(name = "UserGroup.findByTeacherGroup", query = "SELECT u FROM UserGroup u WHERE u.uTeacherGroup.tId = :id"),
    @NamedQuery(name = "UserGroup.findByUId", query = "SELECT u FROM UserGroup u WHERE u.uId = :uId"),
    @NamedQuery(name = "UserGroup.findByUDate", query = "SELECT u FROM UserGroup u WHERE u.uDate = :uDate")})
public class UserGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "u_id")
    private Integer uId;
    @Column(name = "u_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uDate;
    @JoinColumn(name = "u_teacher_group", referencedColumnName = "t_id")
    @ManyToOne
    private TeacherGroup uTeacherGroup;
    @JoinColumn(name = "u_user", referencedColumnName = "user_id")
    @ManyToOne
    private User uUser;

    public UserGroup() {
    }

    public UserGroup(Integer uId) {
        this.uId = uId;
    }

    public Integer getUId() {
        return uId;
    }

    public void setUId(Integer uId) {
        this.uId = uId;
    }

    public Date getUDate() {
        return uDate;
    }

    public void setUDate(Date uDate) {
        this.uDate = uDate;
    }

    public TeacherGroup getUTeacherGroup() {
        return uTeacherGroup;
    }

    public void setUTeacherGroup(TeacherGroup uTeacherGroup) {
        this.uTeacherGroup = uTeacherGroup;
    }

    public User getUUser() {
        return uUser;
    }

    public void setUUser(User uUser) {
        this.uUser = uUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uId != null ? uId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserGroup)) {
            return false;
        }
        UserGroup other = (UserGroup) object;
        if ((this.uId == null && other.uId != null) || (this.uId != null && !this.uId.equals(other.uId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.UserGroup[ uId=" + uId + " ]";
    }
    
}
