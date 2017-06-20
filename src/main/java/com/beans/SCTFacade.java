package com.beans;

import com.dbHelper.Model;
import com.entity.SCT;
import com.entity.User;
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
 * @author Ryan
 */
@Stateless
@ManagedBean
@ViewScoped
public class SCTFacade extends AbstractFacade<SCT> {
    private String a ="asdasd";

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
    private boolean disable = true;
    private String desc, title, teacher, price, cource;
     private String ndesc, ntitle, nteacher, nprice, ncource;
    private Date create;
    private int pid;

    public String getNdesc() {
        return ndesc;
    }

    public void setNdesc(String ndesc) {
        this.ndesc = ndesc;
    }

    public String getNtitle() {
        return ntitle;
    }

    public void setNtitle(String ntitle) {
        this.ntitle = ntitle;
    }

    public String getNteacher() {
        return nteacher;
    }

    public void setNteacher(String nteacher) {
        this.nteacher = nteacher;
    }

    public String getNprice() {
        return nprice;
    }

    public void setNprice(String nprice) {
        this.nprice = nprice;
    }

    public String getNcource() {
        return ncource;
    }

    public void setNcource(String ncource) {
        this.ncource = ncource;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
    

    public String getCource() {
        return cource;
    }

    public void setCource(String cource) {
        this.cource = cource;
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

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
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

    public SCTFacade() {
        super(SCT.class);
    }

    public List<SCT> getSCT() {
        return em.createNamedQuery("SCT.findAllSort").getResultList();
    }

    public void onRowSelect(SelectEvent event) {
        disable = false;
        int nid = ((SCT) event.getObject()).getSCTId();
        pid = nid;
        List<SCT> lv = null;
        lv = em.createNamedQuery("SCT.findBySCTId").setParameter("sCTId", nid).getResultList();
        for (SCT s : lv) {
            title = s.getTitle();
            desc = s.getDescription();
            cource = s.getSubCourseId().getName();
            price = s.getPrice() + "";
            teacher = s.getTeacherId().getUserInformationId().getFname() + " " + s.getTeacherId().getUserInformationId().getLname();
            create = s.getCreateDate();
        }

    }
     public List<User> getTeachersSort() {
        return em.createNamedQuery("User.findAllTeacherSort").setParameter("i", Integer.parseInt("2")).getResultList();
    }
    public void saveNewPackage(String i){
        Model om = new Model();
        
        boolean res = om.insert("INSERT INTO `lms`.`s_c_t` (`s_c_t_id`, `title`, `description`, `sub_course_id`, `teacher_id`, `global_status_id`, `price`, `create_date`) VALUES "
                + "(NULL, '"+ntitle+"', '"+ndesc+"', '"+ncource.trim()+"', '"+nteacher.trim()+"', '1', '"+nprice+"', CURRENT_TIMESTAMP);");
        System.out.println("INSERT INTO `lms`.`s_c_t` (`s_c_t_id`, `title`, `description`, `sub_course_id`, `teacher_id`, `global_status_id`, `price`, `create_date`) VALUES "
                + "(NULL, '"+ntitle+"', '"+ndesc+"', '"+ncource+"', '"+nteacher+"', '1', '"+nprice+"', CURRENT_TIMESTAMP);");
        if(res){
            ntitle = "";
            ndesc="";
            ncource = "";
            nteacher = "";
            nprice="";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ثبت نشد", "ثبت نشد"));
        }else{
             ntitle = "";
            ndesc="";
            ncource = "";
            nteacher = "";
            nprice="";
             com.log.logParent.iLog("‌ایجاد پکیج جدید", i);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت شد", "ثبت شد"));
        }
        
    }
}