package com.stusystem.dao;

import java.util.List;

import com.stusystem.entity.TeacherBean;

public interface TeacherDao {
	//得到多条教师记录
	public List<TeacherBean> getTeacher(TeacherBean teacherbean) throws Exception;
	//得到教师信息有多少页
	public int getteapage(TeacherBean teacherbean) throws Exception;
	//根据教师id返回一条教师记录
	public TeacherBean getTeacherone(TeacherBean teacherbean) throws Exception;
	//根据id删除一条教师记录
	public void teacherdel(TeacherBean teacherbean)throws Exception;
	//添加一条教师记录
	public void teacheradd(TeacherBean teacherbean)throws Exception;
	//根据id修改相应教师信息
	public void teacherxiugai(TeacherBean teacherbean)throws Exception;
}
