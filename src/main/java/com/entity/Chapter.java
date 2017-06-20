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
@Table(name = "chapter")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chapter.findAll", query = "SELECT c FROM Chapter c ORDER BY c.chapterId DESC"),
    @NamedQuery(name = "Chapter.findAllSort", query = "SELECT c FROM Chapter c ORDER BY c.title"),
    @NamedQuery(name = "Chapter.findAllfindbycource", query = "SELECT c FROM Chapter c WHERE c.subCourseId.subCourseId = :s ORDER BY c.title"),
    @NamedQuery(name = "Chapter.findByChapterId", query = "SELECT c FROM Chapter c WHERE c.chapterId = :chapterId"),
    @NamedQuery(name = "Chapter.findByTitle", query = "SELECT c FROM Chapter c WHERE c.title = :title"),
    @NamedQuery(name = "Chapter.findByDescription", query = "SELECT c FROM Chapter c WHERE c.description = :description")})
public class Chapter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "chapter_id")
    private Integer chapterId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "title")
    private String title;
    @Size(max = 256)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "sub_course_id", referencedColumnName = "sub_course_id")
    @ManyToOne(optional = false)
    private SubCourse subCourseId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chapterId")
    private Collection<SubChapter> subChapterCollection;

    public Chapter() {
    }

    public Chapter(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public Chapter(Integer chapterId, String title) {
        this.chapterId = chapterId;
        this.title = title;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
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

    public SubCourse getSubCourseId() {
        return subCourseId;
    }

    public void setSubCourseId(SubCourse subCourseId) {
        this.subCourseId = subCourseId;
    }

    @XmlTransient
    public Collection<SubChapter> getSubChapterCollection() {
        return subChapterCollection;
    }

    public void setSubChapterCollection(Collection<SubChapter> subChapterCollection) {
        this.subChapterCollection = subChapterCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chapterId != null ? chapterId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chapter)) {
            return false;
        }
        Chapter other = (Chapter) object;
        if ((this.chapterId == null && other.chapterId != null) || (this.chapterId != null && !this.chapterId.equals(other.chapterId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Chapter[ chapterId=" + chapterId + " ]";
    }
    
}
