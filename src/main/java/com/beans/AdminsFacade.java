/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dbHelper.Model;
import com.entity.Admin;
import com.entity.Answer;
import com.entity.Log;
import com.entity.UserGender;
import com.utility.sendSms;
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
 * @author Ryan
 */
@Stateless

@ManagedBean
@ViewScoped
public class AdminsFacade extends AbstractFacade<Answer> {

    private String flag = "2";
    private int uid;
    private boolean disable = true;
    private String name, family, email, phone, lastlogin, nid;
    private String number, msg;
    boolean sdisable3 = true;
    private String sname, sfamily, semail, stel, spass, sgender;
    private String meli;

    public String getMeli() {
        return meli;
    }

    public void setMeli(String meli) {
        this.meli = meli;
    }

    public String getSgender() {
        return sgender;
    }

    public void setSgender(String sgender) {
        this.sgender = sgender;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSfamily() {
        return sfamily;
    }

    public void setSfamily(String sfamily) {
        this.sfamily = sfamily;
    }

    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail;
    }

    public String getStel() {
        return stel;
    }

    public void setStel(String stel) {
        this.stel = stel;
    }

    public String getSpass() {
        return spass;
    }

    public void setSpass(String spass) {
        this.spass = spass;
    }

    public boolean isSdisable3() {
        return sdisable3;
    }

    public void setSdisable3(boolean sdisable3) {
        this.sdisable3 = sdisable3;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(String lastlogin) {
        this.lastlogin = lastlogin;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdminsFacade() {
        super(Answer.class);
    }

    public List<Admin> getAdmins() {
        return em.createNamedQuery("Admin.findByFlag").setParameter("flag", Integer.parseInt(flag)).getResultList();
    }

    public List<Log> getLog() {
        return em.createNamedQuery("Log.finduserinfiid").setParameter("id", uid).getResultList();

    }

    public void onRowSelect(SelectEvent event) {
        sdisable3 = false;
        int aid = ((Admin) event.getObject()).getAdminId();
        List<Admin> lv = null;
        lv = em.createNamedQuery("Admin.findByAdminId").setParameter("adminId", aid).getResultList();
        for (Admin a : lv) {
            name = a.getUserInformationId().getFname();
            family = a.getUserInformationId().getLname();
            number = phone = a.getUserInformationId().getMobile();
            email = a.getUserInformationId().getEmail();
            nid = a.getUserInformationId().getNationalId();
            lastlogin = a.getLastSeen() + "";
            uid = a.getUserInformationId().getUserInformationId();

        }
    }

    public void sendSMS(String i) {
        if (number.trim().length() < 11) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "شماره تلفن همراه معتبر نمی‌باشد", "ارسال نشد"));
            number = "";

        } else {
            try {
                Model om = new Model();
                String temp = number.substring(1, 4);
                Integer.parseInt(temp);
                sendSms._send(number, msg);

                om.insert("INSERT INTO `lms`.`sms_log` (`sid`, `title`, `message`, `date`) VALUES "
                        + "(NULL, '" + number + "', '" + msg + "', CURRENT_TIMESTAMP);");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ارسال شد", "ارسال شد"));
                com.log.logParent.iLog("ارسال پیامک", i);

                msg = "";
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ارسال نشد", "ارسال نشد"));

                msg = "";
            }
        }

    }

    public void doRegisterTeacher(String i) {

        Model om = new Model();
        long pass = (long) (Math.random() * 99999999L) + 10000000L;
        String temptel = om.select("SELECT `mobile` FROM `user_information` WHERE `mobile` = " + stel.trim());
        if (!temptel.equals(stel.trim())) {
            String iid = om.select("SELECT max(`user_information_id`) FROM `user_information` ");
            int temp = Integer.parseInt(iid) + 1;
            iid = temp + "";
            boolean res = om.insert("INSERT INTO `lms`.`user_information` (`user_information_id`, `fname`, `lname`, `user_gender_id`, `national_id`, `password`, `mobile`, `email`, `state_id`) VALUES"
                    + " (" + iid + ", '" + sname + "', '" + sfamily + "', '" + sgender.charAt(0) + "', '" + meli + "', " + pass + ", '" + stel.trim() + "', '" + semail + "', '1');");
            if (res) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ثبت نشد", "ثبت نشد"));
            } else {
                boolean res2 = om.insert("INSERT INTO `lms`.`user` (`user_id`, `title`, `imei`, `geo_city_id`, `school_id`, `major_id`, `password`, `registration_date`, `last_login_time`, `user_type_id`, `user_information_id`, `grade_id`) VALUES "
                        + "(NULL, NULL, '', '0', '1', '1', '" + pass + "', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '2', '" + iid + "', '1');");
                if (res2) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ثبت نشد", "ثبت نشد"));
                } else {

                    com.utility.sendSms.sendPass(stel, sname + " " + sfamily, stel, pass + "");

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت شد", "ثبت شد"));
                    com.log.logParent.iLog(" ایجاد اکانت معلم" + sfamily, i);
                    sname = "";
                    sfamily = "";
                    semail = "";
                    stel = "";
                    spass = "";
                    sgender = "";
                    meli = "";
                }
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "این شماره در سامانه موجود می‌باشد", "این شماره در سامانه موجود می‌باشد"));
            sname = "";
            sfamily = "";
            semail = "";
            stel = "";
            spass = "";
            sgender = "";
            meli = "";
        }
    }

    public void doRegister(String i) {

        Model om = new Model();
        long pass = (long) (Math.random() * 99999999L) + 10000000L;
        String temptel = om.select("SELECT `mobile` FROM `user_information` WHERE `mobile` = " + stel.trim());
        if (!temptel.equals(stel.trim())) {
            String iid = om.select("SELECT max(`user_information_id`) FROM `user_information` ");
            int temp = Integer.parseInt(iid) + 1;
            iid = temp + "";
            boolean res = om.insert("INSERT INTO `lms`.`user_information` (`user_information_id`, `fname`, `lname`, `user_gender_id`, `national_id`, `password`, `mobile`, `email`, `state_id`) VALUES"
                    + " (" + iid + ", '" + sname + "', '" + sfamily + "', '" + sgender.charAt(0) + "', '', NULL, '" + stel.trim() + "', '" + semail + "', '1');");
            if (res) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ثبت نشد", "ثبت نشد"));
            } else {
                boolean res2 = om.insert("INSERT INTO `lms`.`user` (`user_id`, `title`, `imei`, `geo_city_id`, `school_id`, `major_id`, `password`, `registration_date`, `last_login_time`, `user_type_id`, `user_information_id`, `grade_id`) VALUES "
                        + "(NULL, NULL, '', '0', '1', '1', '12345678', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '3', '" + iid + "', '1');");
                if (res2) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ثبت نشد", "ثبت نشد"));
                } else {

                    boolean res3 = om.insert("INSERT INTO `lms`.`admin` (`admin_id`, `user_information_id`, `password`, `last_seen`, `flag`) VALUES "
                            + "('" + iid + "', '" + iid + "', '" + pass + "', CURRENT_TIMESTAMP, '2');");
                    if (res3) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ثبت نشد", "ثبت نشد"));
                    } else {
                        com.utility.sendSms.sendPass(stel, sname + " " + sfamily, stel, pass + "");
                        sname = "";
                        sfamily = "";
                        semail = "";
                        stel = "";
                        spass = "";
                        sgender = "";
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت شد", "ثبت شد"));
                        com.log.logParent.iLog("ایجاد اکانت همکار", i);
                    }
                }
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "این شماره در سامانه موجود می‌باشد", "این شماره در سامانه موجود می‌باشد"));
            sname = "";
            sfamily = "";
            semail = "";
            stel = "";
            spass = "";
            sgender = "";
        }
    }
    Model omd = new Model();

    public String allUsers() {
        return omd.select("SELECT count(`user_id`) FROM `user`");
    }

    public String allteachers() {
        return omd.select("SELECT count(`user_id`) FROM `user` where `user_type_id` = 2");
    }

    public String allCoworkers() {
        return omd.select("SELECT count(`admin_id`) FROM `admin` where flag = 2");
    }

    public String allstudent() {
        return omd.select("SELECT count(`user_id`) FROM `user` where `user_type_id` = 1");
    }

}
