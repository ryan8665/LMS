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
@Table(name = "exam_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExamDetail.findAll", query = "SELECT e FROM ExamDetail e"),
    @NamedQuery(name = "ExamDetail.findByExamDetailId", query = "SELECT e FROM ExamDetail e WHERE e.examDetailId = :examDetailId"),
    @NamedQuery(name = "ExamDetail.findByTitle", query = "SELECT e FROM ExamDetail e WHERE e.title = :title"),
    @NamedQuery(name = "ExamDetail.findByTakingDate", query = "SELECT e FROM ExamDetail e WHERE e.takingDate = :takingDate")})
public class ExamDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "exam_detail_id")
    private Integer examDetailId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "title")
    private String title;
    @Column(name = "taking_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takingDate;
    @OneToMany(mappedBy = "examId")
    private Collection<ExamSlot> examSlotCollection;
    @JoinColumn(name = "exam_id", referencedColumnName = "exam_id")
    @ManyToOne(optional = false)
    private Exam examId;

    public ExamDetail() {
    }

    public ExamDetail(Integer examDetailId) {
        this.examDetailId = examDetailId;
    }

    public ExamDetail(Integer examDetailId, String title) {
        this.examDetailId = examDetailId;
        this.title = title;
    }

    public Integer getExamDetailId() {
        return examDetailId;
    }

    public void setExamDetailId(Integer examDetailId) {
        this.examDetailId = examDetailId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTakingDate() {
        return takingDate;
    }

    public void setTakingDate(Date takingDate) {
        this.takingDate = takingDate;
    }

    @XmlTransient
    public Collection<ExamSlot> getExamSlotCollection() {
        return examSlotCollection;
    }

    public void setExamSlotCollection(Collection<ExamSlot> examSlotCollection) {
        this.examSlotCollection = examSlotCollection;
    }

    public Exam getExamId() {
        return examId;
    }

    public void setExamId(Exam examId) {
        this.examId = examId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (examDetailId != null ? examDetailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamDetail)) {
            return false;
        }
        ExamDetail other = (ExamDetail) object;
        if ((this.examDetailId == null && other.examDetailId != null) || (this.examDetailId != null && !this.examDetailId.equals(other.examDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.ExamDetail[ examDetailId=" + examDetailId + " ]";
    }
    
}
