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
@Table(name = "server_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServerStatus.findAll", query = "SELECT s FROM ServerStatus s ORDER BY s.serverStatusId DESC"),
    @NamedQuery(name = "ServerStatus.findByServerStatusId", query = "SELECT s FROM ServerStatus s WHERE s.serverStatusId = :serverStatusId"),
    @NamedQuery(name = "ServerStatus.findByDescription", query = "SELECT s FROM ServerStatus s WHERE s.description = :description")})
public class ServerStatus implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "server_status_id")
    private Integer serverStatusId;
    @Size(max = 100)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "admin_id", referencedColumnName = "admin_id")
    @ManyToOne(optional = false)
    private Admin adminId;
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    @ManyToOne
    private Status statusId;

    public ServerStatus() {
    }

    public ServerStatus(Integer serverStatusId) {
        this.serverStatusId = serverStatusId;
    }

    public Integer getServerStatusId() {
        return serverStatusId;
    }

    public void setServerStatusId(Integer serverStatusId) {
        this.serverStatusId = serverStatusId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Admin getAdminId() {
        return adminId;
    }

    public void setAdminId(Admin adminId) {
        this.adminId = adminId;
    }

    public Status getStatusId() {
        return statusId;
    }

    public void setStatusId(Status statusId) {
        this.statusId = statusId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serverStatusId != null ? serverStatusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServerStatus)) {
            return false;
        }
        ServerStatus other = (ServerStatus) object;
        if ((this.serverStatusId == null && other.serverStatusId != null) || (this.serverStatusId != null && !this.serverStatusId.equals(other.serverStatusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.ServerStatus[ serverStatusId=" + serverStatusId + " ]";
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
