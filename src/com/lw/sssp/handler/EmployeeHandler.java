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
	
	// ��������ĺ��ľ��Ǵ���service���createTime����
	@ModelAttribute
	public void getEmployee(@RequestParam(value="id",required=false)Integer id,
				Map<String, Object> map ){
		System.out.println("getEmployee");
		if(id != null ){
			// ����������ݿ��в�ѯdept��Ҳ����˵dept��id�Ѿ��仯��
			Employee employee = employeeService.get(id);
			// ����������Բ������޸ĵ�bug
			employee.setDept(null);
			// ����map.ע�⣺��Ҫ��Employee����map����Employee���������ڱ䳤��
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
		//���ݻ���
		map.put("employee", employee); 
		map.put("depts", deptService.getAll());
		return "emp/input"; 
	}
	
	@ResponseBody
	@RequestMapping(value="/validateLastName",method=RequestMethod.POST)
	public String validateLastName(String lastName){
		Employee employee = employeeService.getByLastName(lastName);
		if(employee == null ){
			return "1"; // 1����ʾû�в�ѯ�����ݣ������������
		}else{
			return "0" ;// 0����ʾ��ѯ�����ݣ��������
		}
	}
	
	@RequestMapping(value="/emp/input",method=RequestMethod.GET)
	public String input(Map<String, Object> map){
		map.put("depts", deptService.getAll());
		map.put("employee", new Employee()) ; // ע�⣺�����Employee��Ӧǰ̨��modelAttribute����
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
		return "redirect:/emp/list"; // �ض����б�ҳ��
	}
}
