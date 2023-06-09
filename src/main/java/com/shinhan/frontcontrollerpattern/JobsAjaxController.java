package com.shinhan.frontcontrollerpattern;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.shinhan.model.CompanyService;
import com.shinhan.vo.JobVO;

public class JobsAjaxController implements CommonControllerInterface {

	@Override
	public String excute(Map<String, Object> data) throws Exception {
		//모든 job조회하기
		CompanyService service = new CompanyService();
		HttpServletRequest request = (HttpServletRequest)data.get("request");
		List<JobVO> joblist = service.jobSelectAll();
		request.setAttribute("jobList", joblist);
		
		//JavaScript로 작성된 ajax부분에 전달하는 방법1--JSP만들어서보낸다. (HTML 디자인)
		//return "jobView.jsp";
		
		
		//JavaScript로 작성된 ajax부분에 전달하는 방법2--JSON만들어서보낸다. (JS로 디자인)
		JSONArray arr = new JSONArray();
		for(JobVO job:joblist) {
			JSONObject obj = new JSONObject();
			obj.put("job_id", job.getJob_id());
			obj.put("job_title", job.getJob_title());
			arr.add(obj);	//[{}.{},{}  ]
		}
		//{"jobList":[]}
		JSONObject jobsObj = new JSONObject();
		jobsObj.put("jobList", arr);
		//return "responseBody:{'job_id':'IT', 'job_title':'IT programmer'}";
		return "responseBody:" + jobsObj.toJSONString();
	}

}
