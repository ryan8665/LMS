package com.utility;

import com.beans.AbstractFacade;
import com.dbHelper.Model;
import com.entity.Question;
import java.sql.ResultSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author amirk
 */
public class getQuestion extends AbstractFacade<Question> {

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public getQuestion() {
        super(Question.class);
    }

    public List<eQuestion> randomQuestion(int count, int hardnes, int subchapter, int importance) {
        Model om = new Model();
        ResultSet rs = om.result("");
        return null;
    }

}
