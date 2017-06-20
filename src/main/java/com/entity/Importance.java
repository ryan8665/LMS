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
@Table(name = "importance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Importance.findAll", query = "SELECT i FROM Importance i"),
    @NamedQuery(name = "Importance.findByImportanceId", query = "SELECT i FROM Importance i WHERE i.importanceId = :importanceId"),
    @NamedQuery(name = "Importance.findByValue", query = "SELECT i FROM Importance i WHERE i.value = :value"),
    @NamedQuery(name = "Importance.findByDescription", query = "SELECT i FROM Importance i WHERE i.description = :description")})
public class Importance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "importance_id")
    private Integer importanceId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "value")
    private int value;
    @Size(max = 64)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "importanceId")
    private Collection<Question> questionCollection;

    public Importance() {
    }

    public Importance(Integer importanceId) {
        this.importanceId = importanceId;
    }

    public Importance(Integer importanceId, int value) {
        this.importanceId = importanceId;
        this.value = value;
    }

    public Integer getImportanceId() {
        return importanceId;
    }

    public void setImportanceId(Integer importanceId) {
        this.importanceId = importanceId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        hash += (importanceId != null ? importanceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Importance)) {
            return false;
        }
        Importance other = (Importance) object;
        if ((this.importanceId == null && other.importanceId != null) || (this.importanceId != null && !this.importanceId.equals(other.importanceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Importance[ importanceId=" + importanceId + " ]";
    }
    
}
