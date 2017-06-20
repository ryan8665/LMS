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
 * @author amirk
 */
@Entity
@Table(name = "alarm")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alarm.findAll", query = "SELECT a FROM Alarm a ORDER BY a.aId DESC"),
    @NamedQuery(name = "Alarm.findByUser", query = "SELECT a FROM Alarm a WHERE a.aReciver.userId = :id ORDER BY a.aId DESC"),
    @NamedQuery(name = "Alarm.findByAId", query = "SELECT a FROM Alarm a WHERE a.aId = :aId"),
    @NamedQuery(name = "Alarm.findByATitle", query = "SELECT a FROM Alarm a WHERE a.aTitle = :aTitle"),
    @NamedQuery(name = "Alarm.findByADescription", query = "SELECT a FROM Alarm a WHERE a.aDescription = :aDescription"),
    @NamedQuery(name = "Alarm.findByAFlag", query = "SELECT a FROM Alarm a WHERE a.aFlag = :aFlag")})
public class Alarm implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "a_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date aDate;

    @JoinColumn(name = "a_sender", referencedColumnName = "user_id")
    @ManyToOne
    private User aSender;
    @JoinColumn(name = "a_reciver", referencedColumnName = "user_id")
    @ManyToOne
    private User aReciver;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "a_id")
    private Integer aId;
    @Size(max = 45)
    @Column(name = "a_title")
    private String aTitle;
    @Size(max = 200)
    @Column(name = "a_description")
    private String aDescription;
    @Column(name = "a_flag")
    private Integer aFlag;

    public Alarm() {
    }

    public Alarm(Integer aId) {
        this.aId = aId;
    }

    public Integer getAId() {
        return aId;
    }

    public void setAId(Integer aId) {
        this.aId = aId;
    }

    public String getATitle() {
        return aTitle;
    }

    public void setATitle(String aTitle) {
        this.aTitle = aTitle;
    }

    public String getADescription() {
        return aDescription;
    }

    public void setADescription(String aDescription) {
        this.aDescription = aDescription;
    }

    public Integer getAFlag() {
        return aFlag;
    }

    public void setAFlag(Integer aFlag) {
        this.aFlag = aFlag;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aId != null ? aId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alarm)) {
            return false;
        }
        Alarm other = (Alarm) object;
        if ((this.aId == null && other.aId != null) || (this.aId != null && !this.aId.equals(other.aId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Alarm[ aId=" + aId + " ]";
    }

    public User getASender() {
        return aSender;
    }

    public void setASender(User aSender) {
        this.aSender = aSender;
    }

    public User getAReciver() {
        return aReciver;
    }

    public void setAReciver(User aReciver) {
        this.aReciver = aReciver;
    }

    public Date getADate() {
        return aDate;
    }

    public void setADate(Date aDate) {
        this.aDate = aDate;
    }
    
}
