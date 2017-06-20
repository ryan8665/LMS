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
@Table(name = "user_gender")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserGender.findAll", query = "SELECT u FROM UserGender u"),
    @NamedQuery(name = "UserGender.findByUserGenderId", query = "SELECT u FROM UserGender u WHERE u.userGenderId = :userGenderId"),
    @NamedQuery(name = "UserGender.findByValue", query = "SELECT u FROM UserGender u WHERE u.value = :value")})
public class UserGender implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_gender_id")
    private Integer userGenderId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "value")
    private String value;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userGenderId")
    private Collection<UserInformation> userInformationCollection;

    public UserGender() {
    }

    public UserGender(Integer userGenderId) {
        this.userGenderId = userGenderId;
    }

    public UserGender(Integer userGenderId, String value) {
        this.userGenderId = userGenderId;
        this.value = value;
    }

    public Integer getUserGenderId() {
        return userGenderId;
    }

    public void setUserGenderId(Integer userGenderId) {
        this.userGenderId = userGenderId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @XmlTransient
    public Collection<UserInformation> getUserInformationCollection() {
        return userInformationCollection;
    }

    public void setUserInformationCollection(Collection<UserInformation> userInformationCollection) {
        this.userInformationCollection = userInformationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userGenderId != null ? userGenderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserGender)) {
            return false;
        }
        UserGender other = (UserGender) object;
        if ((this.userGenderId == null && other.userGenderId != null) || (this.userGenderId != null && !this.userGenderId.equals(other.userGenderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.UserGender[ userGenderId=" + userGenderId + " ]";
    }
    
}
