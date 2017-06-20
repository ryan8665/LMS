/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dbHelper.Model;
import com.entity.Chapter;
import com.entity.MediaCenter;
import com.entity.SubChapter;
import com.entity.SubCourse;
import static com.log.logParent.iLog;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
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
public class MediaCenterFacade extends AbstractFacade<MediaCenter> {

    boolean disable = true;
    private String view, name, disc, short_description, date, url , cid , subc;
    private String iname, ishort, idescription, ilink;
    private String subCource, chapter, subchapter;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getSubc() {
        return subc;
    }

    public void setSubc(String subc) {
        this.subc = subc;
    }
    
    

    public String getSubCource() {
        return subCource;
    }

    public void setSubCource(String subCource) {
        this.subCource = subCource;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getSubchapter() {
        return subchapter;
    }

    public void setSubchapter(String subchapter) {
        this.subchapter = subchapter;
    }
    

    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname;
    }

    public String getIshort() {
        return ishort;
    }

    public void setIshort(String ishort) {
        this.ishort = ishort;
    }

    public String getIdescription() {
        return idescription;
    }

    public void setIdescription(String idescription) {
        this.idescription = idescription;
    }

    public String getIlink() {
        return ilink;
    }

    public void setIlink(String ilink) {
        this.ilink = ilink;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public MediaCenterFacade() {
        super(MediaCenter.class);
    }

    public List<MediaCenter> getMediaCenter() {
        return em.createNamedQuery("MediaCenter.findByFlag").setParameter("flag", 1).getResultList();
    }

    public List<MediaCenter> getMediaCenterPDF() {
        return em.createNamedQuery("MediaCenter.findByFlag").setParameter("flag", 2).getResultList();
    }

    public void onRowSelect(SelectEvent event) {
        disable = false;
        int uid = ((MediaCenter) event.getObject()).getMid();
        List<MediaCenter> lv = null;
        lv = em.createNamedQuery("MediaCenter.findByMid").setParameter("mid", uid).getResultList();
        for (MediaCenter c : lv) {
            view = c.getVisit() + "";
            name = c.getTitle();
            disc = c.getDescription();
            short_description = c.getShortDescription();
            url = c.getUrl();
            cid = c.getMSubChapter().getChapterId().getSubCourseId().getName();
            subc = c.getMSubChapter().getTitle();
           
            

        }
    }

    public void sendMedia(String flag, String i) {
        disable = true;
        Model om = new Model();
        boolean res = om.insert("INSERT INTO `lms`.`media_center` (`mid`, `flag`, `title`, `short_description`, `description`, `date`, `url`, `visit` ,`m_sub_chapter` ) VALUES "
                + "(NULL, '" + flag + "', '" + iname + "', '" + ishort + "', '" + idescription + "', CURRENT_TIMESTAMP, '" + ilink + "', '0' , '"+subchapter.trim()+"');");
        iname = "";
        ishort = "";
        idescription = "";
        ilink = "";
        if (res) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ثبت نشد", ""));
        } else {
            if (flag.equals("1")) {
                iLog("‌ثبت فیلم آموزشی جدید", i);
            } else {
                iLog("‌ثبت فایل آموزشی جدید", i);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت شد", ""));
        }

    }

    public List<Chapter> haha() {
        if (subCource != null) {
            return em.createNamedQuery("Chapter.findAllfindbycource").setParameter("s", Integer.parseInt(subCource.trim())).getResultList();
        } else {
            return null;
        }

    }

    public List<SubCourse> getSubCourseSort() {
        return em.createNamedQuery("SubCourse.findAllSort").getResultList();
    }

    public List<SubChapter> getChapterSortFindBySubchapter() {

        if (chapter != null) {
            return em.createNamedQuery("SubChapter.findAllfindbychapter").setParameter("s", Integer.parseInt(chapter.trim())).getResultList();
        } else {
            return null;
        }
    }

}
