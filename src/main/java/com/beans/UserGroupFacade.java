/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.entity.UserGroup;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author amirk
 */
@Stateless
@ManagedBean
@ViewScoped

public class UserGroupFacade extends AbstractFacade<UserGroup> {

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserGroupFacade() {
        super(UserGroup.class);
    }
    
    public List<UserGroup> TeacherGroupByTeacher(int id) {
        return em.createNamedQuery("UserGroup.findByTeacherGroup").setParameter("id", id).getResultList();
    }
    public List<UserGroup> getGroups() {
        return em.createNamedQuery("UserGroup.findAll").getResultList();
    }
    
}
