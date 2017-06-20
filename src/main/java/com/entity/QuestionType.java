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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author amirk
 */
@Entity
@Table(name = "question_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuestionType.findAll", query = "SELECT q FROM QuestionType q ORDER BY q.qId DESC"),
    @NamedQuery(name = "QuestionType.findByQId", query = "SELECT q FROM QuestionType q WHERE q.qId = :qId"),
    @NamedQuery(name = "QuestionType.findByQName", query = "SELECT q FROM QuestionType q WHERE q.qName = :qName"),
    @NamedQuery(name = "QuestionType.findByQDescription", query = "SELECT q FROM QuestionType q WHERE q.qDescription = :qDescription")})
public class QuestionType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "q_id")
    private Integer qId;
    @Size(max = 45)
    @Column(name = "q_name")
    private String qName;
    @Size(max = 200)
    @Column(name = "q_description")
    private String qDescription;

    public QuestionType() {
    }

    public QuestionType(Integer qId) {
        this.qId = qId;
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

    public String getQDescription() {
        return qDescription;
    }

    public void setQDescription(String qDescription) {
        this.qDescription = qDescription;
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
        if (!(object instanceof QuestionType)) {
            return false;
        }
        QuestionType other = (QuestionType) object;
        if ((this.qId == null && other.qId != null) || (this.qId != null && !this.qId.equals(other.qId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.QuestionType[ qId=" + qId + " ]";
    }
    
}
