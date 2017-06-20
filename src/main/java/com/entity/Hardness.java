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
@Table(name = "hardness")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hardness.findAll", query = "SELECT h FROM Hardness h"),
    @NamedQuery(name = "Hardness.findByHardnessId", query = "SELECT h FROM Hardness h WHERE h.hardnessId = :hardnessId"),
    @NamedQuery(name = "Hardness.findByValue", query = "SELECT h FROM Hardness h WHERE h.value = :value"),
    @NamedQuery(name = "Hardness.findByDescription", query = "SELECT h FROM Hardness h WHERE h.description = :description")})
public class Hardness implements Serializable {

    @OneToMany(mappedBy = "cHardness")
    private Collection<Comprehensive> comprehensiveCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "hardness_id")
    private Integer hardnessId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "value")
    private int value;
    @Size(max = 64)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hardnessId")
    private Collection<Question> questionCollection;

    public Hardness() {
    }

    public Hardness(Integer hardnessId) {
        this.hardnessId = hardnessId;
    }

    public Hardness(Integer hardnessId, int value) {
        this.hardnessId = hardnessId;
        this.value = value;
    }

    public Integer getHardnessId() {
        return hardnessId;
    }

    public void setHardnessId(Integer hardnessId) {
        this.hardnessId = hardnessId;
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
        hash += (hardnessId != null ? hardnessId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hardness)) {
            return false;
        }
        Hardness other = (Hardness) object;
        if ((this.hardnessId == null && other.hardnessId != null) || (this.hardnessId != null && !this.hardnessId.equals(other.hardnessId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Hardness[ hardnessId=" + hardnessId + " ]";
    }

    @XmlTransient
    public Collection<Comprehensive> getComprehensiveCollection() {
        return comprehensiveCollection;
    }

    public void setComprehensiveCollection(Collection<Comprehensive> comprehensiveCollection) {
        this.comprehensiveCollection = comprehensiveCollection;
    }
    
}
