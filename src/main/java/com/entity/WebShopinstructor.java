/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author amirk
 */
@Entity
@Table(name = "web_shopinstructor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WebShopinstructor.findAll", query = "SELECT w FROM WebShopinstructor w"),
    @NamedQuery(name = "WebShopinstructor.findBySId", query = "SELECT w FROM WebShopinstructor w WHERE w.sId = :sId"),
    @NamedQuery(name = "WebShopinstructor.findBySTitle", query = "SELECT w FROM WebShopinstructor w WHERE w.sTitle = :sTitle"),
    @NamedQuery(name = "WebShopinstructor.findBySDes", query = "SELECT w FROM WebShopinstructor w WHERE w.sDes = :sDes")})
public class WebShopinstructor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "s_id")
    private Integer sId;
    @Size(max = 100)
    @Column(name = "s_title")
    private String sTitle;
    @Size(max = 200)
    @Column(name = "s_des")
    private String sDes;

    public WebShopinstructor() {
    }

    public WebShopinstructor(Integer sId) {
        this.sId = sId;
    }

    public Integer getSId() {
        return sId;
    }

    public void setSId(Integer sId) {
        this.sId = sId;
    }

    public String getSTitle() {
        return sTitle;
    }

    public void setSTitle(String sTitle) {
        this.sTitle = sTitle;
    }

    public String getSDes() {
        return sDes;
    }

    public void setSDes(String sDes) {
        this.sDes = sDes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sId != null ? sId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WebShopinstructor)) {
            return false;
        }
        WebShopinstructor other = (WebShopinstructor) object;
        if ((this.sId == null && other.sId != null) || (this.sId != null && !this.sId.equals(other.sId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.WebShopinstructor[ sId=" + sId + " ]";
    }
    
}
