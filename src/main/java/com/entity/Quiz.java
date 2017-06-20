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
 * @author ryan
 */
@Entity
@Table(name = "quiz")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Quiz.findAll", query = "SELECT q FROM Quiz q"),
    @NamedQuery(name = "Quiz.findByQId", query = "SELECT q FROM Quiz q WHERE q.qId = :qId"),
    @NamedQuery(name = "Quiz.findByQTeacher", query = "SELECT q FROM Quiz q WHERE q.qTeacher.userId = :Id"),
     @NamedQuery(name = "Quiz.findByQTeacherUser", query = "SELECT q FROM Quiz q WHERE q.qStudent.userId = :Id AND q.qTeacher.userId = :u ORDER BY q.qId"),
    @NamedQuery(name = "Quiz.findByQName", query = "SELECT q FROM Quiz q WHERE q.qName = :qName"),
    @NamedQuery(name = "Quiz.findByQName", query = "SELECT q FROM Quiz q WHERE q.qName = :qName"),
    @NamedQuery(name = "Quiz.findByQDes", query = "SELECT q FROM Quiz q WHERE q.qDes = :qDes"),
    @NamedQuery(name = "Quiz.findByQDateIn", query = "SELECT q FROM Quiz q WHERE q.qDateIn = :qDateIn"),
    @NamedQuery(name = "Quiz.findByQDateFill", query = "SELECT q FROM Quiz q WHERE q.qDateFill = :qDateFill"),
    @NamedQuery(name = "Quiz.findByQMark", query = "SELECT q FROM Quiz q WHERE q.qMark = :qMark"),
    @NamedQuery(name = "Quiz.findByQFlag", query = "SELECT q FROM Quiz q WHERE q.qFlag = :qFlag"),
    @NamedQuery(name = "Quiz.findByQNumber", query = "SELECT q FROM Quiz q WHERE q.qNumber = :qNumber")})
public class Quiz implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "q_id")
    private Integer qId;
    @Size(max = 45)
    @Column(name = "q_name")
    private String qName;
    @Size(max = 140)
    @Column(name = "q_des")
    private String qDes;
    @Column(name = "q_date_in")
    @Temporal(TemporalType.TIMESTAMP)
    private Date qDateIn;
    @Column(name = "q_date_fill")
    @Temporal(TemporalType.TIMESTAMP)
    private Date qDateFill;
    @Size(max = 5)
    @Column(name = "q_mark")
    private String qMark;
    @Column(name = "q_flag")
    private Integer qFlag;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "q_number")
    private String qNumber;
    @JoinColumn(name = "q_hardness", referencedColumnName = "hardness_id")
    @ManyToOne
    private Hardness qHardness;
    @JoinColumn(name = "q_sct", referencedColumnName = "user_s_c_t_id")
    @ManyToOne
    private UserSCT qSct;
    @JoinColumn(name = "q_student", referencedColumnName = "user_id")
    @ManyToOne
    private User qStudent;
    @JoinColumn(name = "q_subChapter", referencedColumnName = "sub_chapter_id")
    @ManyToOne
    private SubChapter qsubChapter;
    @JoinColumn(name = "q_teacher", referencedColumnName = "user_id")
    @ManyToOne
    private User qTeacher;

    public Quiz() {
    }

    public Quiz(Integer qId) {
        this.qId = qId;
    }

    public Quiz(Integer qId, String qNumber) {
        this.qId = qId;
        this.qNumber = qNumber;
    }

    public Integer getQId() {
        return qId;
    }

    public void setQId(Integer qId) {
        this.qId = qId;
    }

    public String getQName() {
        return qName;
    }

    public void setQName(String qName) {
        this.qName = qName;
    }

    public String getQDes() {
        return qDes;
    }

    public void setQDes(String qDes) {
        this.qDes = qDes;
    }

    public Date getQDateIn() {
        return qDateIn;
    }

    public void setQDateIn(Date qDateIn) {
        this.qDateIn = qDateIn;
    }

    public Date getQDateFill() {
        return qDateFill;
    }

    public void setQDateFill(Date qDateFill) {
        this.qDateFill = qDateFill;
    }

    public String getQMark() {
        return qMark;
    }

    public void setQMark(String qMark) {
        this.qMark = qMark;
    }

    public Integer getQFlag() {
        return qFlag;
    }

    public void setQFlag(Integer qFlag) {
        this.qFlag = qFlag;
    }

    public String getQNumber() {
        return qNumber;
    }

    public void setQNumber(String qNumber) {
        this.qNumber = qNumber;
    }

    public Hardness getQHardness() {
        return qHardness;
    }

    public void setQHardness(Hardness qHardness) {
        this.qHardness = qHardness;
    }

    public UserSCT getQSct() {
        return qSct;
    }

    public void setQSct(UserSCT qSct) {
        this.qSct = qSct;
    }

    public User getQStudent() {
        return qStudent;
    }

    public void setQStudent(User qStudent) {
        this.qStudent = qStudent;
    }

    public SubChapter getQsubChapter() {
        return qsubChapter;
    }

    public void setQsubChapter(SubChapter qsubChapter) {
        this.qsubChapter = qsubChapter;
    }

    public User getQTeacher() {
        return qTeacher;
    }

    public void setQTeacher(User qTeacher) {
        this.qTeacher = qTeacher;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (qId != null ? qId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Quiz)) {
            return false;
        }
        Quiz other = (Quiz) object;
        if ((this.qId == null && other.qId != null) || (this.qId != null && !this.qId.equals(other.qId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Quiz[ qId=" + qId + " ]";
    }
    
}
