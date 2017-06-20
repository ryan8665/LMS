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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ryan
 */
@Entity
@Table(name = "exam_slot")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExamSlot.findAll", query = "SELECT e FROM ExamSlot e"),
    @NamedQuery(name = "ExamSlot.findByExamSlotId", query = "SELECT e FROM ExamSlot e WHERE e.examSlotId = :examSlotId"),
    @NamedQuery(name = "ExamSlot.findByTitle", query = "SELECT e FROM ExamSlot e WHERE e.title = :title")})
public class ExamSlot implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "exam_slot_id")
    private Integer examSlotId;
    @Size(max = 64)
    @Column(name = "title")
    private String title;
    @JoinColumn(name = "exam_id", referencedColumnName = "exam_detail_id")
    @ManyToOne
    private ExamDetail examId;
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    @ManyToOne
    private Question questionId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examSlotId")
    private Collection<StudentAnswer> studentAnswerCollection;

    public ExamSlot() {
    }

    public ExamSlot(Integer examSlotId) {
        this.examSlotId = examSlotId;
    }

    public Integer getExamSlotId() {
        return examSlotId;
    }

    public void setExamSlotId(Integer examSlotId) {
        this.examSlotId = examSlotId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ExamDetail getExamId() {
        return examId;
    }

    public void setExamId(ExamDetail examId) {
        this.examId = examId;
    }

    public Question getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Question questionId) {
        this.questionId = questionId;
    }

    @XmlTransient
    public Collection<StudentAnswer> getStudentAnswerCollection() {
        return studentAnswerCollection;
    }

    public void setStudentAnswerCollection(Collection<StudentAnswer> studentAnswerCollection) {
        this.studentAnswerCollection = studentAnswerCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (examSlotId != null ? examSlotId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamSlot)) {
            return false;
        }
        ExamSlot other = (ExamSlot) object;
        if ((this.examSlotId == null && other.examSlotId != null) || (this.examSlotId != null && !this.examSlotId.equals(other.examSlotId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.ExamSlot[ examSlotId=" + examSlotId + " ]";
    }
    
}
