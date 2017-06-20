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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author amirk
 */
@Entity
@Table(name = "login_log")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LoginLog.findAll", query = "SELECT l FROM LoginLog l"),
    @NamedQuery(name = "LoginLog.findByLid", query = "SELECT l FROM LoginLog l WHERE l.lid = :lid"),
    @NamedQuery(name = "LoginLog.findByDevice", query = "SELECT l FROM LoginLog l WHERE l.device = :device"),
    @NamedQuery(name = "LoginLog.findByDate", query = "SELECT l FROM LoginLog l WHERE l.date = :date")})
public class LoginLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lid")
    private Integer lid;
    @Column(name = "device")
    private Integer device;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public LoginLog() {
    }

    public LoginLog(Integer lid) {
        this.lid = lid;
    }

    public LoginLog(Integer lid, Date date) {
        this.lid = lid;
        this.date = date;
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public Integer getDevice() {
        return device;
    }

    public void setDevice(Integer device) {
        this.device = device;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lid != null ? lid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoginLog)) {
            return false;
        }
        LoginLog other = (LoginLog) object;
        if ((this.lid == null && other.lid != null) || (this.lid != null && !this.lid.equals(other.lid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.LoginLog[ lid=" + lid + " ]";
    }
    
}
