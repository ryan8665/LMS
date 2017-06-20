/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToOne;
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
@Table(name = "question")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q ORDER BY q.questionId DESC"),
    @NamedQuery(name = "Question.findByQuestionId", query = "SELECT q FROM Question q WHERE q.questionId = :questionId"),
    @NamedQuery(name = "Question.findupdate", query = "UPDATE Question P SET p.rightAnswerId = :b WHERE P.questionId = :a"),
    @NamedQuery(name = "Question.findByTitle", query = "SELECT q FROM Question q WHERE q.title = :title"),
    @NamedQuery(name = "Question.findByDescriptiveSolution", query = "SELECT q FROM Question q WHERE q.descriptiveSolution = :descriptiveSolution"),
    @NamedQuery(name = "Question.findByCreatorId", query = "SELECT q FROM Question q WHERE q.creatorId = :creatorId"),
    @NamedQuery(name = "Question.findByDescriptiveAnswer", query = "SELECT q FROM Question q WHERE q.descriptiveAnswer = :descriptiveAnswer")})
public class Question implements Serializable {

    @JoinColumn(name = "creator_id", referencedColumnName = "admin_id")
    @ManyToOne(optional = false)
    private Admin creatorId;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "question_id")
    private Integer questionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "title")
    private String title;
    @Size(max = 256)
    @Column(name = "descriptive_solution")
    private String descriptiveSolution;
    @Basic(optional = false)
    @NotNull

    @Column(name = "descriptive_answer")
    private String descriptiveAnswer;
    @OneToMany(mappedBy = "questionId")
    private Collection<ExamSlot> examSlotCollection;
    @JoinColumn(name = "hardness_id", referencedColumnName = "hardness_id")
    @ManyToOne(optional = false)
    private Hardness hardnessId;
    @JoinColumn(name = "importance_id", referencedColumnName = "importance_id")
    @ManyToOne(optional = false)
    private Importance importanceId;
    @JoinColumn(name = "right_answer_id", referencedColumnName = "answer_id")
    @ManyToOne(optional = false)
    private Answer rightAnswerId;
    @JoinColumn(name = "sub_chapter_id", referencedColumnName = "sub_chapter_id")
    @ManyToOne(optional = false)
    private SubChapter subChapterId;
    @JoinColumn(name = "question_id", referencedColumnName = "admin_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Admin admin;
    @OneToMany(mappedBy = "questionId")
    private Collection<Answer> answerCollection;

    public Question() {
    }

    public Question(Integer questionId) {
        this.questionId = questionId;
    }

    public Question(Integer questionId, String title, int creatorId) {
        this.questionId = questionId;
        this.title = title;

    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescriptiveSolution() {
        return descriptiveSolution;
    }

    public void setDescriptiveSolution(String descriptiveSolution) {
        this.descriptiveSolution = descriptiveSolution;
    }

    public String getDescriptiveAnswer() {
        return descriptiveAnswer;
    }

    public void setDescriptiveAnswer(String descriptiveAnswer) {
        this.descriptiveAnswer = descriptiveAnswer;
    }

    @XmlTransient
    public Collection<ExamSlot> getExamSlotCollection() {
        return examSlotCollection;
    }

    public void setExamSlotCollection(Collection<ExamSlot> examSlotCollection) {
        this.examSlotCollection = examSlotCollection;
    }

    public Hardness getHardnessId() {
        return hardnessId;
    }

    public void setHardnessId(Hardness hardnessId) {
        this.hardnessId = hardnessId;
    }

    public Importance getImportanceId() {
        return importanceId;
    }

    public void setImportanceId(Importance importanceId) {
        this.importanceId = importanceId;
    }

    public Answer getRightAnswerId() {
        return rightAnswerId;
    }

    public void setRightAnswerId(Answer rightAnswerId) {
        this.rightAnswerId = rightAnswerId;
    }

    public SubChapter getSubChapterId() {
        return subChapterId;
    }

    public void setSubChapterId(SubChapter subChapterId) {
        this.subChapterId = subChapterId;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @XmlTransient
    public Collection<Answer> getAnswerCollection() {
        return answerCollection;
    }

    public void setAnswerCollection(Collection<Answer> answerCollection) {
        this.answerCollection = answerCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (questionId != null ? questionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.questionId == null && other.questionId != null) || (this.questionId != null && !this.questionId.equals(other.questionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Question[ questionId=" + questionId + " ]";
    }

    public Admin getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Admin creatorId) {
        this.creatorId = creatorId;
    }

}
