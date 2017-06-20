/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dbHelper.Model;
import com.entity.Chapter;
import com.entity.Question;
import com.entity.SubChapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import java.nio.file.Paths;
import javax.servlet.ServletContext;

/**
 *
 * @author Ryan
 */
@Stateless

@ManagedBean
@ViewScoped
public class QuestionFacade extends AbstractFacade<Question> {

    private boolean disable = true;
    private String adminID;
    private String qquestion, answer, description, creator, hardnes, importance, rightanswer, chapter, tip, qid;
    private String groupID, courceID, subcourceID, chapterID, subchapterID;
    private String iquestion, idescription, itip, icreator, ihardnes, iimportance, ichapter;
    private String qtype;
     private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
     
     

    public String getQtype() {
        return qtype;
    }

    public void setQtype(String qtype) {
        this.qtype = qtype;
    }
    
    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getIquestion() {
        return iquestion;
    }

    public void setIquestion(String iquestion) {
        this.iquestion = iquestion;
    }

    public String getIdescription() {
        return idescription;
    }

    public void setIdescription(String idescription) {
        this.idescription = idescription;
    }

    public String getItip() {
        return itip;
    }

    public void setItip(String itip) {
        this.itip = itip;
    }

    public String getIcreator() {
        return icreator;
    }

    public void setIcreator(String icreator) {
        this.icreator = icreator;
    }

    public String getIhardnes() {
        return ihardnes;
    }

    public void setIhardnes(String ihardnes) {
        this.ihardnes = ihardnes;
    }

    public String getIimportance() {
        return iimportance;
    }

    public void setIimportance(String iimportance) {
        this.iimportance = iimportance;
    }

    public String getIchapter() {
        return ichapter;
    }

    public void setIchapter(String ichapter) {
        this.ichapter = ichapter;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getCourceID() {
        return courceID;
    }

    public void setCourceID(String courceID) {
        this.courceID = courceID;
    }

    public String getSubcourceID() {
        return subcourceID;
    }

    public void setSubcourceID(String subcourceID) {
        this.subcourceID = subcourceID;
    }

    public String getChapterID() {
        return chapterID;
    }

    public void setChapterID(String chapterID) {
        this.chapterID = chapterID;
    }

    public String getSubchapterID() {
        return subchapterID;
    }

    public void setSubchapterID(String subchapterID) {
        this.subchapterID = subchapterID;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getQquestion() {
        return qquestion;
    }

    public void setQquestion(String qquestion) {
        this.qquestion = qquestion;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getHardnes() {
        return hardnes;
    }

    public void setHardnes(String hardnes) {
        this.hardnes = hardnes;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public String getRightanswer() {
        return rightanswer;
    }

    public void setRightanswer(String rightanswer) {
        this.rightanswer = rightanswer;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
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

    public QuestionFacade() {
        super(Question.class);
    }

    public List<Question> getQuestion() {
        return em.createNamedQuery("Question.findAll").getResultList();
    }
    
    public void onRowSelect(SelectEvent event) {
        disable = false;
        int uid = ((Question) event.getObject()).getQuestionId();
        List<Question> lv = null;
        lv = em.createNamedQuery("Question.findByQuestionId").setParameter("questionId", uid).getResultList();
        for (Question q : lv) {
            qquestion = q.getTitle();
            answer = q.getRightAnswerId().getValue();
            description = q.getDescriptiveAnswer();
            creator = q.getCreatorId().getUserInformationId().getFname() + " " + q.getCreatorId().getUserInformationId().getLname();
            hardnes = q.getHardnessId().getDescription();
            importance = q.getImportanceId().getDescription();
            tip = q.getDescriptiveSolution();
            chapter = q.getSubChapterId().getTitle();
            qid = q.getQuestionId() + "";

        }
    }
     public void handleFileUpload(FileUploadEvent event) throws IOException {
        FacesMessage message = new FacesMessage("موفقیت آمیز", " آپلود شد.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        
                copyStream(event.getFile().getInputstream(),"C:\\mavenproject1\\target\\mavenproject1-1.0-SNAPSHOT\\resources\\question\\"+qid);
        
    
    }
    public String  copyStream(InputStream is, String i) throws FileNotFoundException, IOException {
        File file = new File( i + ".png");
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
        }finally{
            os.close();
        }
        return file.getAbsolutePath();
    }

    public void doSubmit(String i) {
        Model om = new Model();
        boolean res = om.insert("INSERT INTO `lms`.`question` (`question_id`, `title`, `descriptive_solution`, `creator_id`, `hardness_id`, `importance_id`, `right_answer_id`, `sub_chapter_id`, `descriptive_answer`,`qtype` ,time) VALUES"
                + " (NULL, '" + iquestion + "', '" + idescription + "', '" + adminID + "', '" + ihardnes.trim() + "', '" + iimportance.trim() + "', '1', '" + subchapterID.trim() + "', '" + itip + "', '" + qtype.trim() + "', '" + time + "');");
        if (res) {
           
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ثبت نشد", "ثبت نشد"));
        } else {
             com.log.logParent.iLog("‌ثبت سوال جدید", i);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت شد", "ثبت شد"));
        }

    }

    public void changRightAnswer() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "نام کاربری  یا پسورد اشتباه می باشد", "نام کاربری  یا پسورد اشتباه می باشد"));
    }
    
    public List<Chapter> haha(){
        if(subcourceID != null){
            return em.createNamedQuery("Chapter.findAllfindbycource").setParameter("s", Integer.parseInt(subcourceID.trim())).getResultList();
        }else{
            return null;
        }
           
     }
    public List<Chapter> haha2(int c){
        
            return em.createNamedQuery("Chapter.findAllfindbycource").setParameter("s", c).getResultList();
        
           
     }
    
     public List<SubChapter> getChapterSortFindBySubchapter() {
       
            if (chapterID != null) {
            return em.createNamedQuery("SubChapter.findAllfindbychapter").setParameter("s", Integer.parseInt(chapterID.trim())).getResultList();
        } else {
            return null;
        }
     }
     public void subc(int a){
         subcourceID = a+"";
     }
    
}
