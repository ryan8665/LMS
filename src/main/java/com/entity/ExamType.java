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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ryan
 */
@Entity
@Table(name = "exam_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExamType.findAll", query = "SELECT e FROM ExamType e"),
    @NamedQuery(name = "ExamType.findByExamTypeId", query = "SELECT e FROM ExamType e WHERE e.examTypeId = :examTypeId"),
    @NamedQuery(name = "ExamType.findByTitle", query = "SELECT e FROM ExamType e WHERE e.title = :title"),
    @NamedQuery(name = "ExamType.findByDescription", query = "SELECT e FROM ExamType e WHERE e.description = :description")})
public class ExamType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "exam_type_id")
    private Integer examTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "title")
    private String title;
    @Size(max = 256)
    @Column(name = "description")
    private String description;

    public ExamType() {
    }

    public ExamType(Integer examTypeId) {
        this.examTypeId = examTypeId;
    }

    public ExamType(Integer examTypeId, String title) {
        this.examTypeId = examTypeId;
        this.title = title;
    }

    public Integer getExamTypeId() {
        return examTypeId;
    }

    public void setExamTypeId(Integer examTypeId) {
        this.examTypeId = examTypeId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (examTypeId != null ? examTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamType)) {
            return false;
        }
        ExamType other = (ExamType) object;
        if ((this.examTypeId == null && other.examTypeId != null) || (this.examTypeId != null && !this.examTypeId.equals(other.examTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.ExamType[ examTypeId=" + examTypeId + " ]";
    }
    
}
