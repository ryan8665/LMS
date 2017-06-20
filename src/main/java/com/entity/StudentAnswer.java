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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ryan
 */
@Entity
@Table(name = "student_answer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentAnswer.findAll", query = "SELECT s FROM StudentAnswer s"),
    @NamedQuery(name = "StudentAnswer.findByStudentAnswerId", query = "SELECT s FROM StudentAnswer s WHERE s.studentAnswerId = :studentAnswerId"),
    @NamedQuery(name = "StudentAnswer.findByDescription", query = "SELECT s FROM StudentAnswer s WHERE s.description = :description")})
public class StudentAnswer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "student_answer_id")
    private Integer studentAnswerId;
    @Size(max = 64)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "exam_slot_id", referencedColumnName = "exam_slot_id")
    @ManyToOne(optional = false)
    private ExamSlot examSlotId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User userId;

    public StudentAnswer() {
    }

    public StudentAnswer(Integer studentAnswerId) {
        this.studentAnswerId = studentAnswerId;
    }

    public Integer getStudentAnswerId() {
        return studentAnswerId;
    }

    public void setStudentAnswerId(Integer studentAnswerId) {
        this.studentAnswerId = studentAnswerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExamSlot getExamSlotId() {
        return examSlotId;
    }

    public void setExamSlotId(ExamSlot examSlotId) {
        this.examSlotId = examSlotId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentAnswerId != null ? studentAnswerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentAnswer)) {
            return false;
        }
        StudentAnswer other = (StudentAnswer) object;
        if ((this.studentAnswerId == null && other.studentAnswerId != null) || (this.studentAnswerId != null && !this.studentAnswerId.equals(other.studentAnswerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.StudentAnswer[ studentAnswerId=" + studentAnswerId + " ]";
    }
    
}
