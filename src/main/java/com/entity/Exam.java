/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ryan
 */
@Entity
@Table(name = "exam")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exam.findAll", query = "SELECT e FROM Exam e"),
    @NamedQuery(name = "Exam.findByExamId", query = "SELECT e FROM Exam e WHERE e.examId = :examId"),
     @NamedQuery(name = "Exam.findByUser", query = "SELECT e FROM Exam e WHERE e.creatorId.userId = :id ORDER BY e.examId"),
    @NamedQuery(name = "Exam.findByTitle", query = "SELECT e FROM Exam e WHERE e.title = :title"),
    @NamedQuery(name = "Exam.findByCreationDate", query = "SELECT e FROM Exam e WHERE e.creationDate = :creationDate"),
    @NamedQuery(name = "Exam.findByExhibitionDate", query = "SELECT e FROM Exam e WHERE e.exhibitionDate = :exhibitionDate"),
    @NamedQuery(name = "Exam.findByDescribtion", query = "SELECT e FROM Exam e WHERE e.describtion = :describtion")})
public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "exam_id")
    private Integer examId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "exhibition_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exhibitionDate;
    @Size(max = 256)
    @Column(name = "describtion")
    private String describtion;
    @JoinColumn(name = "creator_id", referencedColumnName = "user_id")
    @ManyToOne
    private User creatorId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examId")
    private Collection<ExamDetail> examDetailCollection;

    public Exam() {
    }

    public Exam(Integer examId) {
        this.examId = examId;
    }

    public Exam(Integer examId, String title, Date creationDate) {
        this.examId = examId;
        this.title = title;
        this.creationDate = creationDate;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getExhibitionDate() {
        return exhibitionDate;
    }

    public void setExhibitionDate(Date exhibitionDate) {
        this.exhibitionDate = exhibitionDate;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public User getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(User creatorId) {
        this.creatorId = creatorId;
    }

    @XmlTransient
    public Collection<ExamDetail> getExamDetailCollection() {
        return examDetailCollection;
    }

    public void setExamDetailCollection(Collection<ExamDetail> examDetailCollection) {
        this.examDetailCollection = examDetailCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (examId != null ? examId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exam)) {
            return false;
        }
        Exam other = (Exam) object;
        if ((this.examId == null && other.examId != null) || (this.examId != null && !this.examId.equals(other.examId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Exam[ examId=" + examId + " ]";
    }
    
}
