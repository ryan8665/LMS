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
@Table(name = "comprehensive_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComprehensiveUser.findAll", query = "SELECT c FROM ComprehensiveUser c"),
    @NamedQuery(name = "ComprehensiveUser.findByCuId", query = "SELECT c FROM ComprehensiveUser c WHERE c.cuId = :cuId"),
    @NamedQuery(name = "ComprehensiveUser.findByUser", query = "SELECT c FROM ComprehensiveUser c WHERE c.cuUser.userId = :id"),
     @NamedQuery(name = "ComprehensiveUser.findByUserteacher", query = "SELECT c FROM ComprehensiveUser c WHERE c.cuComprehensive.cCourse.subCourseId = :t AND c.cuUser.userId = :id ORDER BY c.cuId"),
     @NamedQuery(name = "ComprehensiveUser.findByExam", query = "SELECT c FROM ComprehensiveUser c WHERE c.cuComprehensive.cId = :id ORDER BY c.cuId DESC"),
    @NamedQuery(name = "ComprehensiveUser.findByCuMark", query = "SELECT c FROM ComprehensiveUser c WHERE c.cuMark = :cuMark"),
    @NamedQuery(name = "ComprehensiveUser.findByCuDate", query = "SELECT c FROM ComprehensiveUser c WHERE c.cuDate = :cuDate")})
public class ComprehensiveUser implements Serializable {

    @JoinColumn(name = "cu_user", referencedColumnName = "user_id")
    @ManyToOne
    private User cuUser;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cu_id")
    private Integer cuId;
    @Column(name = "cu_mark")
    private String cuMark;
    @Column(name = "cu_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cuDate;
    @JoinColumn(name = "cu_comprehensive", referencedColumnName = "c_id")
    @ManyToOne
    private Comprehensive cuComprehensive;

    public ComprehensiveUser() {
    }

    public ComprehensiveUser(Integer cuId) {
        this.cuId = cuId;
    }

    public Integer getCuId() {
        return cuId;
    }

    public void setCuId(Integer cuId) {
        this.cuId = cuId;
    }

    public String getCuMark() {
        return cuMark;
    }

    public void setCuMark(String cuMark) {
        this.cuMark = cuMark;
    }

    public Date getCuDate() {
        return cuDate;
    }

    public void setCuDate(Date cuDate) {
        this.cuDate = cuDate;
    }

    public Comprehensive getCuComprehensive() {
        return cuComprehensive;
    }

    public void setCuComprehensive(Comprehensive cuComprehensive) {
        this.cuComprehensive = cuComprehensive;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cuId != null ? cuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComprehensiveUser)) {
            return false;
        }
        ComprehensiveUser other = (ComprehensiveUser) object;
        if ((this.cuId == null && other.cuId != null) || (this.cuId != null && !this.cuId.equals(other.cuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.ComprehensiveUser[ cuId=" + cuId + " ]";
    }

    public User getCuUser() {
        return cuUser;
    }

    public void setCuUser(User cuUser) {
        this.cuUser = cuUser;
    }
    
}
