package com.stusystem.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stusystem.dao.TeacherDao;
import com.stusystem.entity.TeacherBean;

@Controller
@RequestMapping(value = "teacher")
public class TeacherController {
	@Autowired
	private TeacherDao teacherDao;
//	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
//	TeacherDao teacherDao = (TeacherDao) applicationContext.getBean("teacherDao");
	
	//返回多条教师信息记录到教师信息管理页面
	@RequestMapping(value = {"/teacherlist"})
	public String getStudent(TeacherBean tea,Model model,HttpServletRequest request) throws Exception{
		if(tea.getTeacherName()!=null&&tea.getTeacherName()!=""){
			tea.setTeacherName(URLDecoder.decode(tea.getTeacherName(), "UTF-8"));
		}
		List<TeacherBean> teacherlist = teacherDao.getTeacher(tea);
		int teapage = teacherDao.getteapage(tea);
		model.addAttribute("teacherlist", teacherlist);
		model.addAttribute("teapage", teapage);
		model.addAttribute("teachername", tea.getTeacherName());
		return "teacherlist";
	}
	//返回一条教师信息到教师个人信息页面
	@RequestMapping(value = {"/teacherone"})
	public String getTeacherone(TeacherBean tea,Model model) throws Exception {
		TeacherBean teacherone = teacherDao.getTeacherone(tea);
		model.addAttribute("teaone", teacherone);
		return "teacherone";
	}
	//点击编辑按钮返查询一条教师信息到编辑页面
	@RequestMapping(value = {"/teachereditor"})
	public String teachereditor(TeacherBean tea,Model model) throws Exception {
		if(tea.getTeacherId()==0){
			return "teachereditor";
		}else{
			TeacherBean teacherone = teacherDao.getTeacherone(tea);
			model.addAttribute("teacherone", teacherone);
			return "teachereditor";
		}
	}
	//删除一条教师信息记录
	@RequestMapping(value = {"/teacherdel"})
	public void teacherdel(TeacherBean tea,HttpServletResponse response) throws IOException {
		int a = 0;
		try {
			teacherDao.teacherdel(tea);
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
	//添加一条教师用户信息
	@RequestMapping(value = {"/teacheradd"})
	public void teacheradd(TeacherBean tea,HttpServletResponse response) throws IOException{
		int a = 0;
		try {
			if(tea.getTeacherId()==0){
				tea.setTeacherName(URLDecoder.decode(tea.getTeacherName(), "UTF-8"));
				tea.setTeacherSystem(URLDecoder.decode(tea.getTeacherSystem(), "UTF-8"));
				tea.setTeacherSex(URLDecoder.decode(tea.getTeacherSex(), "UTF-8"));
				tea.setTeacherEmail(URLDecoder.decode(tea.getTeacherEmail(), "UTF-8"));
				teacherDao.teacheradd(tea);
			}else{
				tea.setTeacherName(URLDecoder.decode(tea.getTeacherName(), "UTF-8"));
				tea.setTeacherSystem(URLDecoder.decode(tea.getTeacherSystem(), "UTF-8"));
				tea.setTeacherSex(URLDecoder.decode(tea.getTeacherSex(), "UTF-8"));
				tea.setTeacherEmail(URLDecoder.decode(tea.getTeacherEmail(), "UTF-8"));
				teacherDao.teacherxiugai(tea);
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
