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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author amirk
 */
@Entity
@Table(name = "web_policies")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WebPolicies.findAll", query = "SELECT w FROM WebPolicies w"),
    @NamedQuery(name = "WebPolicies.findByPId", query = "SELECT w FROM WebPolicies w WHERE w.pId = :pId"),
    @NamedQuery(name = "WebPolicies.findByPTitle", query = "SELECT w FROM WebPolicies w WHERE w.pTitle = :pTitle"),
    @NamedQuery(name = "WebPolicies.findByPContent", query = "SELECT w FROM WebPolicies w WHERE w.pContent = :pContent")})
public class WebPolicies implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "p_id")
    private Integer pId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "p_title")
    private String pTitle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2500)
    @Column(name = "p_content")
    private String pContent;

    public WebPolicies() {
    }

    public WebPolicies(Integer pId) {
        this.pId = pId;
    }

    public WebPolicies(Integer pId, String pTitle, String pContent) {
        this.pId = pId;
        this.pTitle = pTitle;
        this.pContent = pContent;
    }

    public Integer getPId() {
        return pId;
    }

    public void setPId(Integer pId) {
        this.pId = pId;
    }

    public String getPTitle() {
        return pTitle;
    }

    public void setPTitle(String pTitle) {
        this.pTitle = pTitle;
    }

    public String getPContent() {
        return pContent;
    }

    public void setPContent(String pContent) {
        this.pContent = pContent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pId != null ? pId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WebPolicies)) {
            return false;
        }
        WebPolicies other = (WebPolicies) object;
        if ((this.pId == null && other.pId != null) || (this.pId != null && !this.pId.equals(other.pId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.WebPolicies[ pId=" + pId + " ]";
    }
    
}
