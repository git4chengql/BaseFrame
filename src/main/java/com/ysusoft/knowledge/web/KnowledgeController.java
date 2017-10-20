package com.ysusoft.knowledge.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ysusoft.frame.sys.exception.SysException;
import com.ysusoft.knowledge.service.BehaviorService;
import com.ysusoft.knowledge.service.CaseService;
import com.ysusoft.knowledge.service.InsureService;
import com.ysusoft.knowledge.service.LawService;
import com.ysusoft.knowledge.service.QuestionService;
import com.ysusoft.knowledge.service.ReportService;
import com.ysusoft.knowledge.service.SolutionService;
import com.ysusoft.knowledge.service.StandardService;

/**
 * @author songyy
 * @date 2017年8月15日 知识库 
 */
@Controller
@RequestMapping("knowledge/")
public class KnowledgeController {
	
	@Autowired
	private QuestionService qs;
	@Autowired
	private LawService laws;
	@Autowired
	private StandardService stands;
	@Autowired
	private BehaviorService behvs;
	@Autowired
	private CaseService cases;
	@Autowired
	private InsureService insures;
	@Autowired
	private SolutionService soluts;
	@Autowired
	private ReportService reports;
	
	@RequestMapping("index")
	public String index(){
		return "knowledge/knowledge";
	}
	
	@RequestMapping("question")
	public String question(Map<String,Object> map,HttpServletRequest request) throws SysException{
		map.put("questions", qs.getQuestion());
		return "knowledge/question";
	}
	
	@RequestMapping("law")
	public String law(Map<String,Object> map,HttpServletRequest request) throws SysException{
		map.put("laws", laws.getLaw());
		return "knowledge/law";
	}
	
	@RequestMapping("standard")
	public String standard(Map<String,Object> map,HttpServletRequest request) throws SysException{
		map.put("standards", stands.getStandard());
		return "knowledge/standard";
	}
	
	@RequestMapping("behavior")
	public String behavior(Map<String,Object> map,HttpServletRequest request) throws SysException{
		map.put("behaviors", behvs.getBehv());
		return "knowledge/behavior";
	}
	
	@RequestMapping("case")
	public String cases(Map<String,Object> map,HttpServletRequest request) throws SysException{
		map.put("cases", cases.getCase());
		return "knowledge/case";
	}
	
	@RequestMapping("insure")
	public String insure(Map<String,Object> map,HttpServletRequest request) throws SysException{
		map.put("insures", insures.getInsure());
		return "knowledge/insure";
	}
	
	@RequestMapping("solution")
	public String soluts(Map<String,Object> map,HttpServletRequest request) throws SysException{
		map.put("solutions", soluts.getSolution());
		return "knowledge/solution";
	}
	
	@RequestMapping("report")
	public String report(Map<String,Object> map,HttpServletRequest request) throws SysException{
		map.put("reports", reports.getReport());
		return "knowledge/report";
	}

}
