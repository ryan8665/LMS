/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ryan
 */
@Entity
@Table(name = "sub_chapter")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubChapter.findAll", query = "SELECT s FROM SubChapter s ORDER BY s.subChapterId DESC"),
     @NamedQuery(name = "SubChapter.findAllSort", query = "SELECT s FROM SubChapter s ORDER BY s.title"),
     @NamedQuery(name = "SubChapter.findAllfindbychapter", query = "SELECT c FROM SubChapter c WHERE c.chapterId.chapterId = :s ORDER BY c.title"),
    @NamedQuery(name = "SubChapter.findBySubChapterId", query = "SELECT s FROM SubChapter s WHERE s.subChapterId = :subChapterId"),
    @NamedQuery(name = "SubChapter.findByTitle", query = "SELECT s FROM SubChapter s WHERE s.title = :title"),
    @NamedQuery(name = "SubChapter.findByDescription", query = "SELECT s FROM SubChapter s WHERE s.description = :description"),
    @NamedQuery(name = "SubChapter.findBySequentialNumber", query = "SELECT s FROM SubChapter s WHERE s.sequentialNumber = :sequentialNumber")})
public class SubChapter implements Serializable {

    @OneToMany(mappedBy = "mSubChapter")
    private Collection<MediaCenter> mediaCenterCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sub_chapter_id")
    private Integer subChapterId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "title")
    private String title;
    @Size(max = 256)
    @Column(name = "description")
    private String description;
    @Column(name = "sequential_number")
    private Integer sequentialNumber;
    @JoinColumn(name = "chapter_id", referencedColumnName = "chapter_id")
    @ManyToOne(optional = false)
    private Chapter chapterId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subChapterId")
    private Collection<Question> questionCollection;

    public SubChapter() {
    }

    public SubChapter(Integer subChapterId) {
        this.subChapterId = subChapterId;
    }

    public SubChapter(Integer subChapterId, String title) {
        this.subChapterId = subChapterId;
        this.title = title;
    }

    public Integer getSubChapterId() {
        return subChapterId;
    }

    public void setSubChapterId(Integer subChapterId) {
        this.subChapterId = subChapterId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSequentialNumber() {
        return sequentialNumber;
    }

    public void setSequentialNumber(Integer sequentialNumber) {
        this.sequentialNumber = sequentialNumber;
    }

    public Chapter getChapterId() {
        return chapterId;
    }

    public void setChapterId(Chapter chapterId) {
        this.chapterId = chapterId;
    }

    @XmlTransient
    public Collection<Question> getQuestionCollection() {
        return questionCollection;
    }

    public void setQuestionCollection(Collection<Question> questionCollection) {
        this.questionCollection = questionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subChapterId != null ? subChapterId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubChapter)) {
            return false;
        }
        SubChapter other = (SubChapter) object;
        if ((this.subChapterId == null && other.subChapterId != null) || (this.subChapterId != null && !this.subChapterId.equals(other.subChapterId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.SubChapter[ subChapterId=" + subChapterId + " ]";
    }

    @XmlTransient
    public Collection<MediaCenter> getMediaCenterCollection() {
        return mediaCenterCollection;
    }

    public void setMediaCenterCollection(Collection<MediaCenter> mediaCenterCollection) {
        this.mediaCenterCollection = mediaCenterCollection;
    }
    
}
