package com.chart;

import com.dbHelper.Model;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.servlet.jsp.jstl.sql.Result;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean
public class ChartView implements Serializable {

    private LineChartModel lineModel;

    private PieChartModel pieModel, pieModelVisitor;
    Model om = new Model();

    @PostConstruct
    public void init() {
        initLinearModel();
        initPieModel();
        initPieModelVisitor();
    }

    public PieChartModel getPieModelVisitor() {
        return pieModelVisitor;
    }

    public void setPieModelVisitor(PieChartModel pieModelVisitor) {
        this.pieModelVisitor = pieModelVisitor;
    }

    public LineChartModel getLineModel() {
        return lineModel;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    private void initLinearModel() {
        lineModel = new LineChartModel();
        lineModel.setTitle("Linear Chart");
        lineModel.setLegendPosition("e");
        lineModel.setExtender("skinChart");
        lineModel.setAnimate(true);
        Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);

        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");

        series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);

        lineModel.addSeries(series1);

    }

    private void initPieModel() {
        pieModel = new PieChartModel();
        pieModel.setTitle("بازدید سایت");
        Model om = new Model();
        try {
            ResultSet res = om.result("SELECT `w_os` as os ,count(`w_os`) as c FROM `web_view` where DATE(`w_date`) = CURDATE() group by `w_os` ");
            while (res.next()) {
                pieModel.set(res.getString("os"), res.getInt("c"));
            }
        } catch (Exception e) {
            pieModel.set("error", 0);
        }

        pieModel.setExtender("skinPie");
     //   pieModel.setShowDataLabels(true);
      //   pieModel.setLegendPosition("e");
    }

    private void initPieModelVisitor() {
        pieModelVisitor = new PieChartModel();
        pieModelVisitor.setTitle("وضعیت کاربران");
        pieModelVisitor.set("پیش ثبت نام", Integer.parseInt(om.select("SELECT count(`user_information_id`) FROM `user_information` WHERE `state_id` = 3")));
        pieModelVisitor.set("غیر فعال", Integer.parseInt(om.select("SELECT count(`user_information_id`) FROM `user_information` WHERE `state_id` = 2")));
        pieModelVisitor.set("فعال", Integer.parseInt(om.select("SELECT count(`user_information_id`) FROM `user_information` WHERE `state_id` = 1")));

        pieModelVisitor.setExtender("skinPie");
      //   pieModelVisitor.setShowDataLabels(true);
      //   pieModelVisitor.setLegendPosition("e");
    }

}
