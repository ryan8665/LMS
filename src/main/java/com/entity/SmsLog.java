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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author amirk
 */
@Entity
@Table(name = "sms_log")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SmsLog.findAll", query = "SELECT s FROM SmsLog s ORDER BY s.date DESC"),
    @NamedQuery(name = "SmsLog.findBySid", query = "SELECT s FROM SmsLog s WHERE s.sid = :sid"),
    @NamedQuery(name = "SmsLog.findByTitle", query = "SELECT s FROM SmsLog s WHERE s.title = :title"),
    @NamedQuery(name = "SmsLog.findByMessage", query = "SELECT s FROM SmsLog s WHERE s.message = :message"),
    @NamedQuery(name = "SmsLog.findByDate", query = "SELECT s FROM SmsLog s WHERE s.date = :date")})
public class SmsLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sid")
    private Integer sid;
    @Size(max = 170)
    @Column(name = "title")
    private String title;
    @Size(max = 400)
    @Column(name = "message")
    private String message;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public SmsLog() {
    }

    public SmsLog(Integer sid) {
        this.sid = sid;
    }

    public SmsLog(Integer sid, Date date) {
        this.sid = sid;
        this.date = date;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
        hash += (sid != null ? sid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SmsLog)) {
            return false;
        }
        SmsLog other = (SmsLog) object;
        if ((this.sid == null && other.sid != null) || (this.sid != null && !this.sid.equals(other.sid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.SmsLog[ sid=" + sid + " ]";
    }
    
}
