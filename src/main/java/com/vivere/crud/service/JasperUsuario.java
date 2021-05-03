package com.vivere.crud.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.vivere.crud.entity.Usuario;
import com.vivere.crud.repository.UsuarioRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
@Service
public class JasperUsuario {
	
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	 public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
	        String path = "C:\\Users\\e.cassiano.da.silva\\OneDrive - Accenture\\Desktop\\Reporte";
	        List<Usuario> employees = usuarioRepository.findAll();
	        //load file and compile it
	        File file = ResourceUtils.getFile("classpath:usuarios.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
	        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("createdBy", "Java Techie");
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	        if (reportFormat.equalsIgnoreCase("html")) {
	            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\usuarios.html");
	        }
	        if (reportFormat.equalsIgnoreCase("pdf")) {
	            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\usuarios.pdf");
	        }

	        return "report generated in path : " + path;
	    }
	

}
