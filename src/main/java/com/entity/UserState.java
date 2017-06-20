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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ryan
 */
@Entity
@Table(name = "user_state")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserState.findAll", query = "SELECT u FROM UserState u"),
    @NamedQuery(name = "UserState.findByUserStateId", query = "SELECT u FROM UserState u WHERE u.userStateId = :userStateId"),
    @NamedQuery(name = "UserState.findByValue", query = "SELECT u FROM UserState u WHERE u.value = :value")})
public class UserState implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_state_id")
    private Integer userStateId;
    @Size(max = 64)
    @Column(name = "value")
    private String value;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stateId")
    private Collection<UserInformation> userInformationCollection;

    public UserState() {
    }

    public UserState(Integer userStateId) {
        this.userStateId = userStateId;
    }

    public Integer getUserStateId() {
        return userStateId;
    }

    public void setUserStateId(Integer userStateId) {
        this.userStateId = userStateId;
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
        hash += (userStateId != null ? userStateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserState)) {
            return false;
        }
        UserState other = (UserState) object;
        if ((this.userStateId == null && other.userStateId != null) || (this.userStateId != null && !this.userStateId.equals(other.userStateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.UserState[ userStateId=" + userStateId + " ]";
    }
    
}
