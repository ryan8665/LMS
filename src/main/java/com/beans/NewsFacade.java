/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dbHelper.Model;
import com.entity.News;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.Query;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Ryan
 */
@Stateless

@ManagedBean
@ViewScoped
public class NewsFacade extends AbstractFacade<News> {

    private boolean disable = true;
    private String newss, titel, user, enews, etitle, euser, elit;
    String aid;

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public String getElit() {
        return elit;
    }

    public void setElit(String elit) {
        this.elit = elit;
    }

    public String getEnews() {
        return enews;
    }

    public void setEnews(String enews) {
        this.enews = enews;
    }

    public String getEtitle() {
        return etitle;
    }

    public void setEtitle(String etitle) {
        this.etitle = etitle;
    }

    public String getEuser() {
        return euser;
    }

    public void setEuser(String euser) {
        this.euser = euser;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getNewss() {
        return newss;
    }

    public void setNews(String news) {
        this.newss = news;
    }

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NewsFacade() {
        super(News.class);
    }

    public List<News> getNews() {
        return em.createNamedQuery("News.findAll").getResultList();
    }
    int nid;

    public void onRowSelect(SelectEvent event) {
        disable = false;
        nid = ((News) event.getObject()).getNewsId();
        List<News> lv = null;
        lv = em.createNamedQuery("News.findByNewsId").setParameter("newsId", nid).getResultList();
        for (News n : lv) {
            newss = n.getContent();
            titel = n.getTitle();
            user = n.getAdminId().getUserInformationId().getLname();
            System.out.println(user);
        }

    }

    public void saveNews(String userid) {
        Model om = new Model();
        boolean r = om.insert("INSERT INTO `lms`.`news` (`news_id`, `title`, `content`, `content_lite`, `admin_id`) VALUES (NULL, '" + etitle + "', '" + enews + "', '" + elit + "', '" + aid + "');");
        if (r) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "خطای رخ داده است لطفا بعدا دوباره تلاش کنید", "خطای رخ داده است لطفا بعدا دوباره تلاش کنید"));
        } else {
            com.log.logParent.iLog("ثبت اخبار و اطلاعیه", userid);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت شد", "ثبت شد"));
            etitle = "";
            enews = "";
            elit = "";
        }
    }

    public void handleFileUpload(FileUploadEvent event) throws IOException {
        FacesMessage message = new FacesMessage("موفقیت آمیز", " آپلود شد.");
        FacesContext.getCurrentInstance().addMessage(null, message);

        copyStream(event.getFile().getInputstream(), "C:\\mavenproject1\\target\\mavenproject1-1.0-SNAPSHOT\\resources\\news\\" + nid);

    }

    public String copyStream(InputStream is, String i) throws FileNotFoundException, IOException {
        File file = new File(i + ".png");
        FileOutputStream os = new FileOutputStream(file);
        final int buffer_size = 1024;
        try {
            byte[] bytes = new byte[buffer_size];
            for (;;) {
                int count = is.read(bytes, 0, buffer_size);
                if (count == -1) {
                    break;
                }
                os.write(bytes, 0, count);

            }

        } catch (Exception ex) {
        } finally {
            os.close();
        }
        return file.getAbsolutePath();
    }

    public void deleteNews() {

        Query query = em.createNativeQuery("DELETE FROM `lms`.`news` WHERE `news`.`news_id` = 33");
        query.executeUpdate();
    }
    public void doDisable(){
        disable = true;
    }
}
