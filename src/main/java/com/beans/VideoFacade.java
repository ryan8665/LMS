/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dbHelper.Model;
import com.entity.Video;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author ryan
 */
@Stateless
@ManagedBean
@ViewScoped
public class VideoFacade extends AbstractFacade<Video> {

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    String id,name,des,link,view,course;
    Date date;
    boolean disable = true;

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VideoFacade() {
        super(Video.class);
    }
    
    public List<Video> getVideo() {
        return em.createNamedQuery("Video.findAll").getResultList();
   }
    
    public void onRowSelect(SelectEvent event) {
        disable = false;
        int uid = ((Video) event.getObject()).getVId();
        List<Video> lv = null;
        lv = em.createNamedQuery("Video.findByVId").setParameter("vId", uid).getResultList();
        for (Video c : lv) {
            view = c.getVView()+"";
            link = c.getVLink();
            des = c.getVDesc();
            name = c.getVTitle();
            date = c.getVDate();
        }
    }
    public void dosub(){
          disable = true;
        Model om = new Model();
        boolean res = om.insert("INSERT INTO `lms`.`video` (`v_id`, `v_title`, `v_desc`, `v_link`, `v_course`, `v_view`, `v_date`) VALUES "
                + "(NULL, '"+name+"', '"+des+"', '"+link+"', '"+course+"', '0', CURRENT_TIMESTAMP);");
        if(res){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ثبت نشد", "ثبت نشد"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت شد", "ثبت شد"));
        }
        
    }
    
}
