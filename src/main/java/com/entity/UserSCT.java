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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ryan
 */
@Entity
@Table(name = "user_s_c_t")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserSCT.findAll", query = "SELECT u FROM UserSCT u"),
    @NamedQuery(name = "UserSCT.findByUserSCTId", query = "SELECT u FROM UserSCT u WHERE u.userSCTId = :userSCTId"),
    @NamedQuery(name = "UserSCT.findByid", query = "SELECT u FROM UserSCT u WHERE u.userSCTSCTId.sCTId = :id ORDER BY u.userSCTId"),
     @NamedQuery(name = "UserSCT.findByTeacher", query = "SELECT u FROM UserSCT u WHERE u.userSCTSCTId.teacherId.userId = :i ORDER BY u.userSCTSCTId"),
    @NamedQuery(name = "UserSCT.findByDescription", query = "SELECT u FROM UserSCT u WHERE u.description = :description"),
    @NamedQuery(name = "UserSCT.findByUser", query = "SELECT u FROM UserSCT u WHERE u.userSubCourseUserId.userInformationId.userInformationId = :id ORDER BY u.userSCTSCTId DESC"),
    @NamedQuery(name = "UserSCT.findUpdateStatuse", query = "UPDATE UserSCT P SET P.globalStatusId = :b WHERE P.userSCTId = :a"),
    @NamedQuery(name = "UserSCT.findByRegistrationDate", query = "SELECT u FROM UserSCT u WHERE u.registrationDate = :registrationDate")})
public class UserSCT implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_s_c_t_id")
    private Integer userSCTId;
    @Size(max = 256)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;
    @JoinColumn(name = "global_status_id", referencedColumnName = "global_status_id")
    @ManyToOne
    private GlobalStatus globalStatusId;
    @JoinColumn(name = "user_sub_course_user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User userSubCourseUserId;
    @JoinColumn(name = "user_s_c_t_s_c_t_id", referencedColumnName = "s_c_t_id")
    @ManyToOne(optional = false)
    private SCT userSCTSCTId;

    public UserSCT() {
    }

    public UserSCT(Integer userSCTId) {
        this.userSCTId = userSCTId;
    }

    public UserSCT(Integer userSCTId, Date registrationDate) {
        this.userSCTId = userSCTId;
        this.registrationDate = registrationDate;
    }

    public Integer getUserSCTId() {
        return userSCTId;
    }

    public void setUserSCTId(Integer userSCTId) {
        this.userSCTId = userSCTId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public GlobalStatus getGlobalStatusId() {
        return globalStatusId;
    }

    public void setGlobalStatusId(GlobalStatus globalStatusId) {
        this.globalStatusId = globalStatusId;
    }

    public User getUserSubCourseUserId() {
        return userSubCourseUserId;
    }

    public void setUserSubCourseUserId(User userSubCourseUserId) {
        this.userSubCourseUserId = userSubCourseUserId;
    }

    public SCT getUserSCTSCTId() {
        return userSCTSCTId;
    }

    public void setUserSCTSCTId(SCT userSCTSCTId) {
        this.userSCTSCTId = userSCTSCTId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userSCTId != null ? userSCTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserSCT)) {
            return false;
        }
        UserSCT other = (UserSCT) object;
        if ((this.userSCTId == null && other.userSCTId != null) || (this.userSCTId != null && !this.userSCTId.equals(other.userSCTId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.UserSCT[ userSCTId=" + userSCTId + " ]";
    }
    
}
