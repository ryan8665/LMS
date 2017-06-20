/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ryan
 */
@Entity
@Table(name = "invoice_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InvoiceStatus.findAll", query = "SELECT i FROM InvoiceStatus i"),
    @NamedQuery(name = "InvoiceStatus.findByInvoiceStatusId", query = "SELECT i FROM InvoiceStatus i WHERE i.invoiceStatusId = :invoiceStatusId"),
    @NamedQuery(name = "InvoiceStatus.findByDescription", query = "SELECT i FROM InvoiceStatus i WHERE i.description = :description"),
    @NamedQuery(name = "InvoiceStatus.findByValue", query = "SELECT i FROM InvoiceStatus i WHERE i.value = :value"),
    @NamedQuery(name = "InvoiceStatus.findByDate", query = "SELECT i FROM InvoiceStatus i WHERE i.date = :date")})
public class InvoiceStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "invoice_status_id")
    private Integer invoiceStatusId;
    @Size(max = 64)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "value")
    private String value;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JoinColumn(name = "invoice_id", referencedColumnName = "invoice_id")
    @ManyToOne(optional = false)
    private Invoice invoiceId;

    public InvoiceStatus() {
    }

    public InvoiceStatus(Integer invoiceStatusId) {
        this.invoiceStatusId = invoiceStatusId;
    }

    public InvoiceStatus(Integer invoiceStatusId, String value, Date date) {
        this.invoiceStatusId = invoiceStatusId;
        this.value = value;
        this.date = date;
    }

    public Integer getInvoiceStatusId() {
        return invoiceStatusId;
    }

    public void setInvoiceStatusId(Integer invoiceStatusId) {
        this.invoiceStatusId = invoiceStatusId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Invoice getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Invoice invoiceId) {
        this.invoiceId = invoiceId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invoiceStatusId != null ? invoiceStatusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvoiceStatus)) {
            return false;
        }
        InvoiceStatus other = (InvoiceStatus) object;
        if ((this.invoiceStatusId == null && other.invoiceStatusId != null) || (this.invoiceStatusId != null && !this.invoiceStatusId.equals(other.invoiceStatusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.InvoiceStatus[ invoiceStatusId=" + invoiceStatusId + " ]";
    }
    
}
