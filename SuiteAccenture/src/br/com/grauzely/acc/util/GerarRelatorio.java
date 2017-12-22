package br.com.grauzely.acc.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
 
import javax.swing.JFrame;
 
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

public class GerarRelatorio extends JFrame{

	private static final long serialVersionUID = 1L;
 
    public void mostrarRelatorio() throws JRException, ClassNotFoundException, SQLException {
 
        String reportSrcFile = "C:\\Users\\g.da.silva.alves\\JaspersoftWorkspace\\MyReports\\SRsReports.jrxml";
 
        // First, compile jrxml file.
        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, null);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(700, 500);
        this.setVisible(true);
        System.out.print("Done!");
 
    }
	
}
