package com.atguigu.ac.sc.handler;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.atguigu.ac.sc.entity.Member;
import com.atguigu.ac.sc.entity.ResultEntity;
import com.atguigu.ac.sc.service.ManagerProviderSerivce;
import com.atguigu.ac.sc.service.ProcessRemoteService;

@Controller
public class ProcessHandler {

	@Autowired
	private ProcessRemoteService processRemoteService;
	
	@Autowired
	private ManagerProviderSerivce managerProviderSerivce;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/consumer/process/detail/{memberId}")
	public String consumerProcessDetail(@PathVariable("memberId") Integer memberId, Model model) {
		
		Map<String, Object> certDetail = managerProviderSerivce.getMemberCertDetail(memberId);
		
		model.addAttribute("detailMap", certDetail);
		
		return "process/detail";
	}
	
	@RequestMapping("/get/approval/task/list")
	public String getApprovalTaskList(Model model) {
		
		ResultEntity<List<Map<String,Object>>> resultEntity = processRemoteService.getApprovalTaskListRemote();
		
		List<Map<String,Object>> taskList = resultEntity.getData();
		
		model.addAttribute("taskList", taskList);
		
		return "process/task_list";
	}
	
	@RequestMapping("/see/process/definition/picture")
	public void seePicture(String pdKey, String pdVersion, HttpServletResponse response) throws IOException {
		
		//1.创建封装头信息的对象
		HttpHeaders headers = new HttpHeaders();
		
		//2.设置响应数据的内容类型为PNG图片
		headers.setContentType(MediaType.IMAGE_PNG);
		
		String url = "http://ac-sc-activiti-provider/get/process/definition/picture/"+pdKey+"/"+pdVersion;
		
		ResponseEntity<byte[]> responseEntity = restTemplate.exchange(url, 
				HttpMethod.POST, new HttpEntity<byte[]>(headers), byte[].class);
		
		byte[] body = responseEntity.getBody();
		
		ByteArrayInputStream inputStream = new ByteArrayInputStream(body);
		
		ServletOutputStream outputStream = response.getOutputStream();
	    
		int byteData = 0;
		
		while((byteData = inputStream.read()) != -1) {
			
			outputStream.write(byteData);
		}
	}
	
	@RequestMapping("/upload/process/definition/file")
	public String uploadProcessDefinitionFile(@RequestParam("bpmnFile") MultipartFile multipartFile) throws IOException {
		
		String originalFilename = multipartFile.getOriginalFilename();
		
		System.out.println(originalFilename);
		
		//1.生成临时文件的文件名
		String tempFileName = UUID.randomUUID().toString();
		
		String tempTxName = originalFilename.substring(originalFilename.lastIndexOf("."));
		
		//2.创建临时文件对应的File对象
		File tempFile = File.createTempFile(tempFileName, tempTxName);
		
		//3.将表单上传的文件另存到临时文件中
		multipartFile.transferTo(tempFile);
		
		//4.根据临时文件创建FileSystemResource对象
		FileSystemResource resource = new FileSystemResource(tempFile);
		
		//5.为RestTemplate请求封装参数
		LinkedMultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
		
		//※提示：这里的pdfile关系到Provider中接收文件数据时的请求参数名
		paramMap.add("pdfile", resource);
		
		//6.声明Provider端的URL地址值
		String url = "http://ac-sc-activiti-provider/process/remote/deploy";
		
		//7.执行上传操作
		//※提示：String.class表示服务器返回String类型的响应数据
		String response = restTemplate.postForObject(url, paramMap, String.class);
		System.err.println("response:"+response);
		
		return "redirect:/show/process/definition/list";
	}
	
	@RequestMapping("/show/process/definition/list")
	public String showProcessDefinitionList(Model model) {
		
		ResultEntity<List<Map<String,Object>>> resultEntity = processRemoteService.getAllProcess();
		
		if(ResultEntity.SUCCESS.equals(resultEntity.getResult())) {
			
			List<Map<String,Object>> data = resultEntity.getData();
			
			model.addAttribute("processDefinitionList", data);
			
			return "process/definition_list";
			
		}else {
			
			String message = resultEntity.getMessage();
			
			model.addAttribute("MESSAGE", message);

			return "error11";
		}
		
	}
}
