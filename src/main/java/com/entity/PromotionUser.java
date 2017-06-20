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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ryan
 */
@Entity
@Table(name = "promotion_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PromotionUser.findAll", query = "SELECT p FROM PromotionUser p ORDER BY p.promotionUserId DESC"),
    @NamedQuery(name = "PromotionUser.findByPromotionUserId", query = "SELECT p FROM PromotionUser p WHERE p.promotionUserId = :promotionUserId"),
    @NamedQuery(name = "PromotionUser.findByTitle", query = "SELECT p FROM PromotionUser p WHERE p.title = :title"),
    @NamedQuery(name = "PromotionUser.findByDescription", query = "SELECT p FROM PromotionUser p WHERE p.description = :description")})
public class PromotionUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "promotion_user_id")
    private Integer promotionUserId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "title")
    private String title;
    @Size(max = 256)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "promotion_id", referencedColumnName = "promotion_id")
    @ManyToOne(optional = false)
    private Promotion promotionId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
    private User userId;

    public PromotionUser() {
    }

    public PromotionUser(Integer promotionUserId) {
        this.promotionUserId = promotionUserId;
    }

    public PromotionUser(Integer promotionUserId, String title) {
        this.promotionUserId = promotionUserId;
        this.title = title;
    }

    public Integer getPromotionUserId() {
        return promotionUserId;
    }

    public void setPromotionUserId(Integer promotionUserId) {
        this.promotionUserId = promotionUserId;
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

    public Promotion getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Promotion promotionId) {
        this.promotionId = promotionId;
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
        hash += (promotionUserId != null ? promotionUserId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PromotionUser)) {
            return false;
        }
        PromotionUser other = (PromotionUser) object;
        if ((this.promotionUserId == null && other.promotionUserId != null) || (this.promotionUserId != null && !this.promotionUserId.equals(other.promotionUserId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.PromotionUser[ promotionUserId=" + promotionUserId + " ]";
    }
    
}
