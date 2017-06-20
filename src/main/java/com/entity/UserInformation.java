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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "user_information")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserInformation.findAll", query = "SELECT u FROM UserInformation u"),
    
    @NamedQuery(name = "UserInformation.findByUserInformationId", query = "SELECT u FROM UserInformation u WHERE u.userInformationId = :userInformationId"),
    @NamedQuery(name = "UserInformation.findByFname", query = "SELECT u FROM UserInformation u WHERE u.fname = :fname"),
    @NamedQuery(name = "UserInformation.findByLname", query = "SELECT u FROM UserInformation u WHERE u.lname = :lname"),
    @NamedQuery(name = "UserInformation.findByNationalId", query = "SELECT u FROM UserInformation u WHERE u.nationalId = :nationalId"),
    @NamedQuery(name = "UserInformation.findByPassword", query = "SELECT u FROM UserInformation u WHERE u.password = :password"),
    @NamedQuery(name = "UserInformation.findByMobile", query = "SELECT u FROM UserInformation u WHERE u.mobile = :mobile"),
    @NamedQuery(name = "UserInformation.findupdateimei", query = "UPDATE UserInformation P SET P.stateId = :b WHERE P.userInformationId = :a"),
    @NamedQuery(name = "UserInformation.findByEmail", query = "SELECT u FROM UserInformation u WHERE u.email = :email")})
public class UserInformation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_information_id")
    private Integer userInformationId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "fname")
    private String fname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "lname")
    private String lname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "national_id")
    private String nationalId;
    @Size(max = 11)
    @Column(name = "password")
    private String password;
    @Size(max = 11)
    @Column(name = "mobile")
    private String mobile;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 64)
    @Column(name = "email")
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userInformationId")
    private Collection<Admin> adminCollection;
    @JoinColumn(name = "user_gender_id", referencedColumnName = "user_gender_id")
    @ManyToOne(optional = false)
    private UserGender userGenderId;
    @JoinColumn(name = "state_id", referencedColumnName = "user_state_id")
    @ManyToOne(optional = false)
    private UserState stateId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userInformationId")
    private Collection<User> userCollection;

    public UserInformation() {
    }

    public UserInformation(Integer userInformationId) {
        this.userInformationId = userInformationId;
    }

    public UserInformation(Integer userInformationId, String fname, String lname, String nationalId) {
        this.userInformationId = userInformationId;
        this.fname = fname;
        this.lname = lname;
        this.nationalId = nationalId;
    }

    public Integer getUserInformationId() {
        return userInformationId;
    }

    public void setUserInformationId(Integer userInformationId) {
        this.userInformationId = userInformationId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public Collection<Admin> getAdminCollection() {
        return adminCollection;
    }

    public void setAdminCollection(Collection<Admin> adminCollection) {
        this.adminCollection = adminCollection;
    }

    public UserGender getUserGenderId() {
        return userGenderId;
    }

    public void setUserGenderId(UserGender userGenderId) {
        this.userGenderId = userGenderId;
    }

    public UserState getStateId() {
        return stateId;
    }

    public void setStateId(UserState stateId) {
        this.stateId = stateId;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userInformationId != null ? userInformationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserInformation)) {
            return false;
        }
        UserInformation other = (UserInformation) object;
        if ((this.userInformationId == null && other.userInformationId != null) || (this.userInformationId != null && !this.userInformationId.equals(other.userInformationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.UserInformation[ userInformationId=" + userInformationId + " ]";
    }
    
}
