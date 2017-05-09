package com.lw.sssp.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lw.sssp.entity.Employee;
import com.lw.sssp.service.DeptService;
import com.lw.sssp.service.EmployeeService;

@Controller
public class EmployeeHandler {

	@Autowired
	private EmployeeService employeeService ;
	
	@Autowired
	private DeptService deptService;
	
	// 这个方法的核心就是处理service层的createTime问题
	@ModelAttribute
	public void getEmployee(@RequestParam(value="id",required=false)Integer id,
				Map<String, Object> map ){
		System.out.println("getEmployee");
		if(id != null ){
			// 在这里从数据库中查询dept，也就是说dept的id已经变化了
			Employee employee = employeeService.get(id);
			// 解决级联属性不可以修改的bug
			employee.setDept(null);
			// 存入map.注意：需要将Employee存入map，让Employee的生命周期变长。
			map.put("employee", employee);
		}
	}
	@RequestMapping("/emp/delete/{id}")
	public String delete(@PathVariable("id")Integer id ){
		employeeService.delete(id);
		return "redirect:/emp/list" ;
	}
	
	@RequestMapping(value="/emp/update")
	public String update(Employee employee){
		System.out.println("update");
		employeeService.save(employee);
		return "redirect:/emp/list" ;
	}
	
	
	@RequestMapping(value="/emp/{id}")
	public String input(@PathVariable("id")Integer id,Map<String,Object> map){
		Employee employee = employeeService.get(id);
		//数据回显
		map.put("employee", employee); 
		map.put("depts", deptService.getAll());
		return "emp/input"; 
	}
	
	@ResponseBody
	@RequestMapping(value="/validateLastName",method=RequestMethod.POST)
	public String validateLastName(String lastName){
		Employee employee = employeeService.getByLastName(lastName);
		if(employee == null ){
			return "1"; // 1：表示没有查询到数据，可以正常添加
		}else{
			return "0" ;// 0：表示查询到数据，不能添加
		}
	}
	
	@RequestMapping(value="/emp/input",method=RequestMethod.GET)
	public String input(Map<String, Object> map){
		map.put("depts", deptService.getAll());
		map.put("employee", new Employee()) ; // 注意：这里的Employee对应前台的modelAttribute属性
		return "emp/input" ;
	}
	
	@RequestMapping("/emp/list")
	public String list(@RequestParam(value="pageNo",required=false,defaultValue="1") String pageNoStr,Map<String , Object> map){
		
		int pageNo = Integer.parseInt(pageNoStr);
		if(pageNo < 1 ){
			pageNo = 1; 
		}
		Page<Employee> page = employeeService.getPage(pageNo, 5);
		map.put("page", page);
		return "emp/list"; 
	}
	
	@RequestMapping("/emp/save")
	public String save(Employee employee){
		employeeService.save(employee);
		return "redirect:/emp/list"; // 重定向到列表页面
	}
}
