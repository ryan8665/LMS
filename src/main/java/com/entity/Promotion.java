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
@Table(name = "promotion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Promotion.findAll", query = "SELECT p FROM Promotion p ORDER BY p.promotionId DESC"),
    @NamedQuery(name = "Promotion.findByPromotionId", query = "SELECT p FROM Promotion p WHERE p.promotionId = :promotionId"),
    @NamedQuery(name = "Promotion.findByTitle", query = "SELECT p FROM Promotion p WHERE p.title = :title"),
    @NamedQuery(name = "Promotion.findByDescription", query = "SELECT p FROM Promotion p WHERE p.description = :description"),
    @NamedQuery(name = "Promotion.findByRegistrationDate", query = "SELECT p FROM Promotion p WHERE p.registrationDate = :registrationDate"),
    @NamedQuery(name = "Promotion.findByExpirationDate", query = "SELECT p FROM Promotion p WHERE p.expirationDate = :expirationDate"),
    @NamedQuery(name = "Promotion.findByValue", query = "SELECT p FROM Promotion p WHERE p.value = :value")})
public class Promotion implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "code")
    private String code;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "promotion_id")
    private Integer promotionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "title")
    private String title;
    @Size(max = 256)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;
    @Column(name = "expiration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "value")
    private int value;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "promotionId")
    private Collection<PromotionUser> promotionUserCollection;
    @JoinColumn(name = "registrar_id", referencedColumnName = "admin_id")
    @ManyToOne(optional = false)
    private Admin registrarId;

    public Promotion() {
    }

    public Promotion(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public Promotion(Integer promotionId, String title, Date registrationDate, int value) {
        this.promotionId = promotionId;
        this.title = title;
        this.registrationDate = registrationDate;
        this.value = value;
    }

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @XmlTransient
    public Collection<PromotionUser> getPromotionUserCollection() {
        return promotionUserCollection;
    }

    public void setPromotionUserCollection(Collection<PromotionUser> promotionUserCollection) {
        this.promotionUserCollection = promotionUserCollection;
    }

    public Admin getRegistrarId() {
        return registrarId;
    }

    public void setRegistrarId(Admin registrarId) {
        this.registrarId = registrarId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (promotionId != null ? promotionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promotion)) {
            return false;
        }
        Promotion other = (Promotion) object;
        if ((this.promotionId == null && other.promotionId != null) || (this.promotionId != null && !this.promotionId.equals(other.promotionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Promotion[ promotionId=" + promotionId + " ]";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
}
