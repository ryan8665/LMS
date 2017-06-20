/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dbHelper.Model;
import com.entity.Comprehensive;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author amirk
 */
@Stateless
@ManagedBean
@ViewScoped
public class ComprehensiveFacade extends AbstractFacade<Comprehensive> {

    private boolean disable = true;
    private String shortdesc, desc, title, creator, course, hardness;
    private String ishortdesc, idesc, ititle, icreator, icourse, ihardness;
    private Date doDate;
    private int sid;
    private Date exDate;
    private int number = 10;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    

    public Date getExDate() {
        return exDate;
    }

    public void setExDate(Date exDate) {
        this.exDate = exDate;
    }
    

    public String getIshortdesc() {
        return ishortdesc;
    }

    public void setIshortdesc(String ishortdesc) {
        this.ishortdesc = ishortdesc;
    }

    public String getIdesc() {
        return idesc;
    }

    public void setIdesc(String idesc) {
        this.idesc = idesc;
    }

    public String getItitle() {
        return ititle;
    }

    public void setItitle(String ititle) {
        this.ititle = ititle;
    }

    public String getIcreator() {
        return icreator;
    }

    public void setIcreator(String icreator) {
        this.icreator = icreator;
    }

    public String getIcourse() {
        return icourse;
    }

    public void setIcourse(String icourse) {
        this.icourse = icourse;
    }

    public String getIhardness() {
        return ihardness;
    }

    public void setIhardness(String ihardness) {
        this.ihardness = ihardness;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getHardness() {
        return hardness;
    }

    public void setHardness(String hardness) {
        this.hardness = hardness;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public void setShortdesc(String shortdesc) {
        this.shortdesc = shortdesc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getDoDate() {
        return doDate;
    }

    public void setDoDate(Date doDate) {
        this.doDate = doDate;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComprehensiveFacade() {
        super(Comprehensive.class);
    }

    public List<Comprehensive> getComprehensive() {
        return em.createNamedQuery("Comprehensive.findAll").getResultList();
    }

    public void onRowSelect(SelectEvent event) {
        disable = false;
        int uid = ((Comprehensive) event.getObject()).getCId();
        sid = uid;
        List<Comprehensive> lv = null;
        lv = em.createNamedQuery("Comprehensive.findByCId").setParameter("cId", uid).getResultList();
        for (Comprehensive c : lv) {
            shortdesc = c.getCShortDescription();
            desc = c.getCDescription();
            title = c.getCTitle();
            creator = c.getCCreator().getUserInformationId().getFname() + " " + c.getCCreator().getUserInformationId().getLname();
            doDate = c.getCExecuteDate();
            course = c.getCCourse().getName();
            hardness = c.getCHardness().getDescription();

        }
    }

    public void submitComprehensive(int admin) {
        disable = true;
        Model om = new Model();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        boolean res = om.insert("INSERT INTO `lms`.`comprehensive` (`c_id`, `c_title`, `c_description`, `c_execute_date`, `c_short_description`, `c_creator`, `c_course`, `c_hardness`) VALUES "
                + "(NULL, '" + ititle + "', '" + idesc + "', CURRENT_TIMESTAMP, '" + ishortdesc + "', '" + admin + "', '" + icourse.trim() + "', '" + ihardness.trim() + "');");
        if (res) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ثبت نشد", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت شد", ""));
        }
        ititle = idesc = ishortdesc = icourse = ihardness = "";
    }

}
