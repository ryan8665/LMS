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
 * @author ryan
 */
@Entity
@Table(name = "video")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v ORDER BY v.vId DESC"),
    @NamedQuery(name = "Video.findByVId", query = "SELECT v FROM Video v WHERE v.vId = :vId"),
    @NamedQuery(name = "Video.findByVTitle", query = "SELECT v FROM Video v WHERE v.vTitle = :vTitle"),
    @NamedQuery(name = "Video.findByVDesc", query = "SELECT v FROM Video v WHERE v.vDesc = :vDesc"),
    @NamedQuery(name = "Video.findByVLink", query = "SELECT v FROM Video v WHERE v.vLink = :vLink"),
    @NamedQuery(name = "Video.findByVView", query = "SELECT v FROM Video v WHERE v.vView = :vView"),
    @NamedQuery(name = "Video.findByVDate", query = "SELECT v FROM Video v WHERE v.vDate = :vDate")})
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "v_id")
    private Integer vId;
    @Size(max = 40)
    @Column(name = "v_title")
    private String vTitle;
    @Size(max = 80)
    @Column(name = "v_desc")
    private String vDesc;
    @Size(max = 200)
    @Column(name = "v_link")
    private String vLink;
    @Column(name = "v_view")
    private Integer vView;
    @Basic(optional = false)
    @NotNull
    @Column(name = "v_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vDate;

    public Video() {
    }

    public Video(Integer vId) {
        this.vId = vId;
    }

    public Video(Integer vId, Date vDate) {
        this.vId = vId;
        this.vDate = vDate;
    }

    public Integer getVId() {
        return vId;
    }

    public void setVId(Integer vId) {
        this.vId = vId;
    }

    public String getVTitle() {
        return vTitle;
    }

    public void setVTitle(String vTitle) {
        this.vTitle = vTitle;
    }

    public String getVDesc() {
        return vDesc;
    }

    public void setVDesc(String vDesc) {
        this.vDesc = vDesc;
    }

    public String getVLink() {
        return vLink;
    }

    public void setVLink(String vLink) {
        this.vLink = vLink;
    }

    public Integer getVView() {
        return vView;
    }

    public void setVView(Integer vView) {
        this.vView = vView;
    }

    public Date getVDate() {
        return vDate;
    }

    public void setVDate(Date vDate) {
        this.vDate = vDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vId != null ? vId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Video)) {
            return false;
        }
        Video other = (Video) object;
        if ((this.vId == null && other.vId != null) || (this.vId != null && !this.vId.equals(other.vId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Video[ vId=" + vId + " ]";
    }
    
}
