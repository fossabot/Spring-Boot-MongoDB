package io.github.anantharajuc.sbmdb.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.anantharajuc.sbmdb.api.ResourcePaths;
import io.github.anantharajuc.sbmdb.model.Student;
import io.github.anantharajuc.sbmdb.service.StudentServiceImpl;

/*
 * Student Query Controller
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@RestController
@RequestMapping(value=ResourcePaths.Student.V1.ROOT)
public class StudentQueryController 
{
	@Autowired
	StudentServiceImpl studentServiceImpl;
	
	@Autowired
	ModelMapper modelMapper;
	
	/*
	 * Method that returns all students from the datastore 
	 * 
	 * HTTP Status:
	 * 
	 * 200 - OK: Everything worked as expected.
	 */
	@GetMapping()
	public List<Student> findAllStudents()
	{
		return studentServiceImpl.findAllStudents();
	}
	
	@GetMapping(value=ResourcePaths.SORTING)
	public List<Student> findAllStudentsSorting(@RequestParam String direction, @RequestParam String properties)
	{
		return studentServiceImpl.findAllStudentsSorting(direction, properties);
	}
	
	@GetMapping(value=ResourcePaths.PAGINATION)
	public List<Student> findAllStudentsPagination(@RequestParam int pageNo, @RequestParam int pageSize)
	{
		return studentServiceImpl.findAllStudentsPagination(pageNo, pageSize);
	}
	
	/*
	 * Method to search a student by id.
	 * 
	 * @return ResponseEntity with a <code>Student</code> object and the HTTP status
	 * 
	 * HTTP Status:
	 * 
	 * 200 - OK: Everything worked as expected.
	 */	
	@GetMapping(value=ResourcePaths.ID)
	public Student findStudentById(@PathVariable String id)
	{
		return studentServiceImpl.findStudentById(id);
	}
	
	@GetMapping(value=ResourcePaths.NAME)
	public List<Student> findStudentByName(@RequestParam String name)
	{
		//return studentServiceImpl.findStudentByName(name);
		
		return studentServiceImpl.nativeQueryFindByName(name);
	}
	
	@GetMapping(value=ResourcePaths.DEPARTMENT)
	public List<Student> findStudentByDepartmentName(@RequestParam String departmentName)
	{
		return studentServiceImpl.findStudentByDepartmentName(departmentName);
	}
}
