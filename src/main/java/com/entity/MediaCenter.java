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
@Table(name = "media_center")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MediaCenter.findAll", query = "SELECT m FROM MediaCenter m"),
    @NamedQuery(name = "MediaCenter.findByMid", query = "SELECT m FROM MediaCenter m WHERE m.mid = :mid"),
    @NamedQuery(name = "MediaCenter.findByFlag", query = "SELECT m FROM MediaCenter m WHERE m.flag = :flag ORDER BY m.mid"),
    @NamedQuery(name = "MediaCenter.findByTitle", query = "SELECT m FROM MediaCenter m WHERE m.title = :title"),
    @NamedQuery(name = "MediaCenter.findByShortDescription", query = "SELECT m FROM MediaCenter m WHERE m.shortDescription = :shortDescription"),
    @NamedQuery(name = "MediaCenter.findByDescription", query = "SELECT m FROM MediaCenter m WHERE m.description = :description")})
public class MediaCenter implements Serializable {

    @JoinColumn(name = "m_sub_chapter", referencedColumnName = "sub_chapter_id")
    @ManyToOne
    private SubChapter mSubChapter;

    @Basic(optional = false)
    @NotNull
    @Column(name = "visit")
    private int visit;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "url")
    private String url;

    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mid")
    private Integer mid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "flag")
    private int flag;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "short_description")
    private String shortDescription;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "description")
    private String description;

    public MediaCenter() {
    }

    public MediaCenter(Integer mid) {
        this.mid = mid;
    }

    public MediaCenter(Integer mid, int flag, String title, String shortDescription, String description) {
        this.mid = mid;
        this.flag = flag;
        this.title = title;
        this.shortDescription = shortDescription;
        this.description = description;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mid != null ? mid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MediaCenter)) {
            return false;
        }
        MediaCenter other = (MediaCenter) object;
        if ((this.mid == null && other.mid != null) || (this.mid != null && !this.mid.equals(other.mid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.MediaCenter[ mid=" + mid + " ]";
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }

    public SubChapter getMSubChapter() {
        return mSubChapter;
    }

    public void setMSubChapter(SubChapter mSubChapter) {
        this.mSubChapter = mSubChapter;
    }
    
}
