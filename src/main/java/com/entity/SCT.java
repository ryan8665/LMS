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
@Table(name = "s_c_t")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SCT.findAll", query = "SELECT s FROM SCT s"),
    @NamedQuery(name = "SCT.findAllSort", query = "SELECT s FROM SCT s ORDER BY s.sCTId DESC"),
    @NamedQuery(name = "SCT.findBySCTId", query = "SELECT s FROM SCT s WHERE s.sCTId = :sCTId"),
    @NamedQuery(name = "SCT.findByTitle", query = "SELECT s FROM SCT s WHERE s.title = :title"),
    @NamedQuery(name = "SCT.findByTeacher", query = "SELECT s FROM SCT s WHERE s.teacherId.userId = :id"),
    @NamedQuery(name = "SCT.findByTeacherID", query = "SELECT s FROM SCT s WHERE s.teacherId.userId = :id GROUP BY s.subCourseId.name ORDER BY s.subCourseId.name"),
    @NamedQuery(name = "SCT.findByDescription", query = "SELECT s FROM SCT s WHERE s.description = :description"),
    @NamedQuery(name = "SCT.findUpdateStatuse", query = "UPDATE SCT P SET P.globalStatusId = :b WHERE P.sCTId = :a"),
    @NamedQuery(name = "SCT.findByPrice", query = "SELECT s FROM SCT s WHERE s.price = :price")})
public class SCT implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "s_c_t_id")
    private Integer sCTId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "title")
    private String title;
    @Size(max = 4256)
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private Integer price;
    @JoinColumn(name = "global_status_id", referencedColumnName = "global_status_id")
    @ManyToOne(optional = false)
    private GlobalStatus globalStatusId;
    @JoinColumn(name = "sub_course_id", referencedColumnName = "sub_course_id")
    @ManyToOne(optional = false)
    private SubCourse subCourseId;
    @JoinColumn(name = "teacher_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User teacherId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userSCTSCTId")
    private Collection<UserSCT> userSCTCollection;

    public SCT() {
    }

    public SCT(Integer sCTId) {
        this.sCTId = sCTId;
    }

    public SCT(Integer sCTId, String title) {
        this.sCTId = sCTId;
        this.title = title;
    }

    public Integer getSCTId() {
        return sCTId;
    }

    public void setSCTId(Integer sCTId) {
        this.sCTId = sCTId;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public GlobalStatus getGlobalStatusId() {
        return globalStatusId;
    }

    public void setGlobalStatusId(GlobalStatus globalStatusId) {
        this.globalStatusId = globalStatusId;
    }

    public SubCourse getSubCourseId() {
        return subCourseId;
    }

    public void setSubCourseId(SubCourse subCourseId) {
        this.subCourseId = subCourseId;
    }

    public User getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(User teacherId) {
        this.teacherId = teacherId;
    }

    @XmlTransient
    public Collection<UserSCT> getUserSCTCollection() {
        return userSCTCollection;
    }

    public void setUserSCTCollection(Collection<UserSCT> userSCTCollection) {
        this.userSCTCollection = userSCTCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sCTId != null ? sCTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SCT)) {
            return false;
        }
        SCT other = (SCT) object;
        if ((this.sCTId == null && other.sCTId != null) || (this.sCTId != null && !this.sCTId.equals(other.sCTId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.SCT[ sCTId=" + sCTId + " ]";
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
}
