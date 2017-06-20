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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "admin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Admin.findAll", query = "SELECT a FROM Admin a ORDER BY a.adminId DESC"),
    @NamedQuery(name = "Admin.findByAdminId", query = "SELECT a FROM Admin a WHERE a.adminId = :adminId"),
      @NamedQuery(name = "Admin.findByFlag", query = "SELECT a FROM Admin a WHERE a.flag = :flag ORDER BY a.adminId DESC"),
    @NamedQuery(name = "Admin.findByPassword", query = "SELECT a FROM Admin a WHERE a.password = :password"),
    @NamedQuery(name = "Admin.findByLastSeen", query = "SELECT a FROM Admin a WHERE a.lastSeen = :lastSeen")})
public class Admin implements Serializable {

    @OneToMany(mappedBy = "cCreator")
    private Collection<Comprehensive> comprehensiveCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creatorId")
    private Collection<Question> questionCollection;

    @Basic(optional = false)
    @NotNull
    @Column(name = "flag")
    private int flag;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "admin_id")
    private Integer adminId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_seen")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSeen;
    @JoinColumn(name = "user_information_id", referencedColumnName = "user_information_id")
    @ManyToOne(optional = false)
    private UserInformation userInformationId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adminId")
    private Collection<ServerStatus> serverStatusCollection;
    @OneToMany(mappedBy = "adminId")
    private Collection<News> newsCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "admin")
    private Question question;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "registrarId")
    private Collection<Promotion> promotionCollection;

    public Admin() {
    }

    public Admin(Integer adminId) {
        this.adminId = adminId;
    }

    public Admin(Integer adminId, String password, Date lastSeen) {
        this.adminId = adminId;
        this.password = password;
        this.lastSeen = lastSeen;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Date lastSeen) {
        this.lastSeen = lastSeen;
    }

    public UserInformation getUserInformationId() {
        return userInformationId;
    }

    public void setUserInformationId(UserInformation userInformationId) {
        this.userInformationId = userInformationId;
    }

    @XmlTransient
    public Collection<ServerStatus> getServerStatusCollection() {
        return serverStatusCollection;
    }

    public void setServerStatusCollection(Collection<ServerStatus> serverStatusCollection) {
        this.serverStatusCollection = serverStatusCollection;
    }

    @XmlTransient
    public Collection<News> getNewsCollection() {
        return newsCollection;
    }

    public void setNewsCollection(Collection<News> newsCollection) {
        this.newsCollection = newsCollection;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @XmlTransient
    public Collection<Promotion> getPromotionCollection() {
        return promotionCollection;
    }

    public void setPromotionCollection(Collection<Promotion> promotionCollection) {
        this.promotionCollection = promotionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adminId != null ? adminId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Admin)) {
            return false;
        }
        Admin other = (Admin) object;
        if ((this.adminId == null && other.adminId != null) || (this.adminId != null && !this.adminId.equals(other.adminId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Admin[ adminId=" + adminId + " ]";
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @XmlTransient
    public Collection<Question> getQuestionCollection() {
        return questionCollection;
    }

    public void setQuestionCollection(Collection<Question> questionCollection) {
        this.questionCollection = questionCollection;
    }

    @XmlTransient
    public Collection<Comprehensive> getComprehensiveCollection() {
        return comprehensiveCollection;
    }

    public void setComprehensiveCollection(Collection<Comprehensive> comprehensiveCollection) {
        this.comprehensiveCollection = comprehensiveCollection;
    }
    
}
