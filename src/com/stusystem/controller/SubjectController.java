package com.stusystem.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stusystem.dao.SubjectDao;
import com.stusystem.entity.SubjectBean;

@Controller
@RequestMapping(value = "subject")
public class SubjectController {
	@Autowired
	private SubjectDao subjectDao;
//	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
//	SubjectDao subjectDao = (SubjectDao) applicationContext.getBean("subjectDao");
	//返回多条课程信息
	@RequestMapping(value = {"/subjectlist"})
	public String getStudent(SubjectBean sbj,Model model) throws Exception{
		if(sbj.getSubjectName()!=null&&sbj.getSubjectName()!=""){
			sbj.setSubjectName(URLDecoder.decode(sbj.getSubjectName(), "UTF-8"));
		}
		List<SubjectBean> subjectlist = subjectDao.getSubject(sbj);
		int sbjpage = subjectDao.getsbjpage(sbj);
		model.addAttribute("subjectlist", subjectlist);
		model.addAttribute("sbjpage", sbjpage);
		model.addAttribute("subjectname", sbj.getSubjectName());
		return "subjectlist";
	}
	//返回一条课程信息到课程信息的编辑页面
	@RequestMapping(value = {"/subjecteditor"})
	public String studenteditor(SubjectBean sbj,Model model) throws Exception {
		if(sbj.getSubjectId()==0){
			return "subjecteditor";
		}else{
			SubjectBean subjectone = subjectDao.getSubjectone(sbj);
			model.addAttribute("subjectone", subjectone);
			return "subjecteditor";
		}
	}
	//删除一条课程信息
	@RequestMapping(value = {"/subjectdel"})
	public void subjectdel(SubjectBean sbj,HttpServletResponse response) throws IOException {
		int a = 0;
		try {
			subjectDao.subjectdel(sbj);
		} catch (Exception e) {
			a=a+1;
			response.getWriter().println("{'status':'0'}");
			e.printStackTrace();
		}
		if(a==0){
			response.getWriter().println("{'status':'1'}");
		}else{
		}
	}
	//添加一条课程信息
	@RequestMapping(value = {"/subjectadd"})
	public void subjectadd(SubjectBean sbj,HttpServletResponse response) throws IOException{
		int a = 0;
		try {
			if(sbj.getSubjectId()==0){
				sbj.setSubjectName(URLDecoder.decode(sbj.getSubjectName(), "UTF-8"));
				sbj.setTeacherName(URLDecoder.decode(sbj.getTeacherName(), "UTF-8"));
				subjectDao.subjectadd(sbj);
			}else{
				sbj.setSubjectName(URLDecoder.decode(sbj.getSubjectName(), "UTF-8"));
				sbj.setTeacherName(URLDecoder.decode(sbj.getTeacherName(), "UTF-8"));
				subjectDao.subjectxiugai(sbj);
			}
		} catch (Exception e) {
			a=a+1;
			response.getWriter().println("{'status':'0'}");
			e.printStackTrace();
		}
		if(a==0){
			response.getWriter().println("{'status':'1'}");
		}else{
		}
	}
}
