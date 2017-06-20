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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ryan
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findAllStudent", query = "SELECT u FROM User u WHERE u.userTypeId.userTypeId = :i ORDER BY u.userInformationId DESC"),
    @NamedQuery(name = "User.findAllTeacherSort", query = "SELECT u FROM User u WHERE u.userTypeId.userTypeId = :i ORDER BY u.userInformationId.lname DESC"),
    @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId"),
    @NamedQuery(name = "User.findByTitle", query = "SELECT u FROM User u WHERE u.title = :title"),
    @NamedQuery(name = "User.findByImei", query = "SELECT u FROM User u WHERE u.imei = :imei"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByRegistrationDate", query = "SELECT u FROM User u WHERE u.registrationDate = :registrationDate"),
    
    @NamedQuery(name = "User.findByLastLoginTime", query = "SELECT u FROM User u WHERE u.lastLoginTime = :lastLoginTime")})
public class User implements Serializable {

    @OneToMany(mappedBy = "uUser")
    private Collection<UserGroup> userGroupCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tTeacher")
    private Collection<TeacherGroup> teacherGroupCollection;

    @OneToMany(mappedBy = "aSender")
    private Collection<Alarm> alarmCollection;
    @OneToMany(mappedBy = "aReciver")
    private Collection<Alarm> alarmCollection1;
    @OneToMany(mappedBy = "cuUser")
    private Collection<ComprehensiveUser> comprehensiveUserCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Size(max = 64)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 17)
    @Column(name = "imei")
    private String imei;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "password")
    private String password;
    @Column(name = "registration_date")
    @Temporal(TemporalType.DATE)
    private Date registrationDate;
    @Column(name = "last_login_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginTime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Log> logCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacherId")
    private Collection<SCT> sCTCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userSubCourseUserId")
    private Collection<UserSCT> userSCTCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<PromotionUser> promotionUserCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<StudentAnswer> studentAnswerCollection;
    @OneToMany(mappedBy = "reciverId")
    private Collection<Message> messageCollection;
    @OneToMany(mappedBy = "senderId")
    private Collection<Message> messageCollection1;
    @OneToMany(mappedBy = "creatorId")
    private Collection<Exam> examCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Invoice> invoiceCollection;
    @JoinColumn(name = "geo_city_id", referencedColumnName = "geo_city_id")
    @ManyToOne(optional = false)
    private GeoCity geoCityId;
    @JoinColumn(name = "grade_id", referencedColumnName = "grade_id")
    @ManyToOne
    private Grade gradeId;
    @JoinColumn(name = "major_id", referencedColumnName = "major_id")
    @ManyToOne(optional = false)
    private Major majorId;
    @JoinColumn(name = "school_id", referencedColumnName = "school_id")
    @ManyToOne(optional = false)
    private School schoolId;
    @JoinColumn(name = "user_information_id", referencedColumnName = "user_information_id")
    @ManyToOne(optional = false)
    private UserInformation userInformationId;
    @JoinColumn(name = "user_type_id", referencedColumnName = "user_type_id")
    @ManyToOne(optional = false)
    private UserType userTypeId;

    public User() {
    }

    public User(Integer userId) {
        this.userId = userId;
    }

    public User(Integer userId, String imei, String password) {
        this.userId = userId;
        this.imei = imei;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @XmlTransient
    public Collection<Log> getLogCollection() {
        return logCollection;
    }

    public void setLogCollection(Collection<Log> logCollection) {
        this.logCollection = logCollection;
    }

    @XmlTransient
    public Collection<SCT> getSCTCollection() {
        return sCTCollection;
    }

    public void setSCTCollection(Collection<SCT> sCTCollection) {
        this.sCTCollection = sCTCollection;
    }

    @XmlTransient
    public Collection<UserSCT> getUserSCTCollection() {
        return userSCTCollection;
    }

    public void setUserSCTCollection(Collection<UserSCT> userSCTCollection) {
        this.userSCTCollection = userSCTCollection;
    }

    @XmlTransient
    public Collection<PromotionUser> getPromotionUserCollection() {
        return promotionUserCollection;
    }

    public void setPromotionUserCollection(Collection<PromotionUser> promotionUserCollection) {
        this.promotionUserCollection = promotionUserCollection;
    }

    @XmlTransient
    public Collection<StudentAnswer> getStudentAnswerCollection() {
        return studentAnswerCollection;
    }

    public void setStudentAnswerCollection(Collection<StudentAnswer> studentAnswerCollection) {
        this.studentAnswerCollection = studentAnswerCollection;
    }

    @XmlTransient
    public Collection<Message> getMessageCollection() {
        return messageCollection;
    }

    public void setMessageCollection(Collection<Message> messageCollection) {
        this.messageCollection = messageCollection;
    }

    @XmlTransient
    public Collection<Message> getMessageCollection1() {
        return messageCollection1;
    }

    public void setMessageCollection1(Collection<Message> messageCollection1) {
        this.messageCollection1 = messageCollection1;
    }

    @XmlTransient
    public Collection<Exam> getExamCollection() {
        return examCollection;
    }

    public void setExamCollection(Collection<Exam> examCollection) {
        this.examCollection = examCollection;
    }

    @XmlTransient
    public Collection<Invoice> getInvoiceCollection() {
        return invoiceCollection;
    }

    public void setInvoiceCollection(Collection<Invoice> invoiceCollection) {
        this.invoiceCollection = invoiceCollection;
    }

    public GeoCity getGeoCityId() {
        return geoCityId;
    }

    public void setGeoCityId(GeoCity geoCityId) {
        this.geoCityId = geoCityId;
    }

    public Grade getGradeId() {
        return gradeId;
    }

    public void setGradeId(Grade gradeId) {
        this.gradeId = gradeId;
    }

    public Major getMajorId() {
        return majorId;
    }

    public void setMajorId(Major majorId) {
        this.majorId = majorId;
    }

    public School getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(School schoolId) {
        this.schoolId = schoolId;
    }

    public UserInformation getUserInformationId() {
        return userInformationId;
    }

    public void setUserInformationId(UserInformation userInformationId) {
        this.userInformationId = userInformationId;
    }

    public UserType getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(UserType userTypeId) {
        this.userTypeId = userTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.User[ userId=" + userId + " ]";
    }

    @XmlTransient
    public Collection<Alarm> getAlarmCollection() {
        return alarmCollection;
    }

    public void setAlarmCollection(Collection<Alarm> alarmCollection) {
        this.alarmCollection = alarmCollection;
    }

    @XmlTransient
    public Collection<Alarm> getAlarmCollection1() {
        return alarmCollection1;
    }

    public void setAlarmCollection1(Collection<Alarm> alarmCollection1) {
        this.alarmCollection1 = alarmCollection1;
    }

    @XmlTransient
    public Collection<ComprehensiveUser> getComprehensiveUserCollection() {
        return comprehensiveUserCollection;
    }

    public void setComprehensiveUserCollection(Collection<ComprehensiveUser> comprehensiveUserCollection) {
        this.comprehensiveUserCollection = comprehensiveUserCollection;
    }

    @XmlTransient
    public Collection<TeacherGroup> getTeacherGroupCollection() {
        return teacherGroupCollection;
    }

    public void setTeacherGroupCollection(Collection<TeacherGroup> teacherGroupCollection) {
        this.teacherGroupCollection = teacherGroupCollection;
    }

    @XmlTransient
    public Collection<UserGroup> getUserGroupCollection() {
        return userGroupCollection;
    }

    public void setUserGroupCollection(Collection<UserGroup> userGroupCollection) {
        this.userGroupCollection = userGroupCollection;
    }
    
}
