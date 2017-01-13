package com.liang.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.liang.mongodb.model.Resource;
import com.liang.mongodb.service.MongoServiceImpl;
import com.mongodb.gridfs.GridFSDBFile;

@Controller
@RequestMapping(value="/resource")
public class ResourceController {
	@Autowired
	private MongoServiceImpl mongoService;
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public String upload(@RequestParam("file") CommonsMultipartFile[] files){
		try {
			for(CommonsMultipartFile cFile : files){
				System.out.println(cFile.getOriginalFilename());
				long startTime = System.currentTimeMillis();
				mongoService.save(cFile.getInputStream(), cFile.getOriginalFilename());
				long endTime = System.currentTimeMillis();
				System.out.println("用时："+(endTime-startTime));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/resource/resourceList";
	}
	
	@RequestMapping("/addResource")
	public String addResource(){
		return "resource/addResource";
	}
	
	@RequestMapping(value="/resourceList")
	public String resourceList(Model model){
		List<GridFSDBFile> gridFSDBFiles = mongoService.queryList();
		List<Resource> resources = new ArrayList<Resource>();
		for(GridFSDBFile file : gridFSDBFiles){
			Resource resource = new Resource();
			resource.setId(file.getId().toString());
			resource.setFileName(file.getFilename());
			resource.setContentType(file.getContentType());
			resources.add(resource);
		}
		model.addAttribute("resources", resources);
		return "resource/resourceList";
	}
	
	@RequestMapping(value="/delete")
	public String deleteResource(String id){
		mongoService.delete(id);
		return "redirect:/resource/resourceList";
	}
	
	@RequestMapping(value="/download")
	public void download(String id,HttpServletResponse response){
		GridFSDBFile gridFSDBFile = mongoService.queryByObjectId(id);
		try {
			OutputStream out = response.getOutputStream();
			response.setContentType("application/octet-stream");
			// 获取原文件名
			String name = (String) gridFSDBFile.get("filename");
			String fileName = new String(name.getBytes("UTF-8"), "ISO8859-1");

			// 设置下载文件名
			response.addHeader("Content-Disposition", "attachment; filename=\""
					+ fileName + "\"");
			gridFSDBFile.writeTo(out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
