package com.ysusoft.complain.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysusoft.basic.faq.bean.FAQBean;
import com.ysusoft.basic.faq.service.FAQService;
import com.ysusoft.complain.bean.Complain;
import com.ysusoft.complain.service.ComplainService;
import com.ysusoft.frame.sys.base.bean.Grid;
import com.ysusoft.frame.sys.base.bean.ResultInfo;
import com.ysusoft.frame.sys.exception.SysException;
import com.ysusoft.frame.sys.util.HttpClientUtil;

/**
 * @author sonyy
 * @date 2017年6月6日 下午3:24:21 投诉表单
 */
@Controller
@RequestMapping("complain/")
public class ComplainController {
	
	@Autowired
	private ComplainService cs;
	
	@Autowired
	private FAQService fs;
	
	private String url = "https://api.weixin.qq.com/";
	private String charset = "utf-8";
	private HttpClientUtil httpClientUtil = new HttpClientUtil();
	
	@RequestMapping("index")
	public String index(){
		return "complain/complain";
	}

	@RequestMapping("taskList")
	public String taskList(){
		return "complain/taskList";
	}
	
	@RequestMapping("newtaskList")
	public String newtaskList(){
		return "complain/newtaskList";
	}
	
	@RequestMapping("verifycode")
	@ResponseBody
	public ResultInfo<Complain> verifycode(Complain complain,HttpServletRequest request) throws SysException{
		ResultInfo<Complain> rs = new ResultInfo<Complain>();
		String sessionString = (String) request.getSession().getAttribute(SESSION_KEY_OF_RAND_CODE);
		String codeString = complain.getCode();
		if(codeString==null||sessionString==null||!codeString.equalsIgnoreCase(sessionString)){
			rs.setMessage("验证码错误");
			rs.setSuccess(false);
			return rs;
		}else{
			return cs.addComplain(complain);
		}
	}
	
	@RequestMapping("selectFAQ")
	public String selectFAQ(){
		return "complain/selectFAQ";
	}

	@RequestMapping("getFAQ")
	@ResponseBody
	public Grid<FAQBean> getFAQ(String type,@RequestParam("page")int page,@RequestParam("pageSize")int pageSize) throws SysException{
		return fs.getFAQ(type,page-1, pageSize);
	}
	
	@RequestMapping("getFAQContent")
	@ResponseBody
	public ResultInfo<String> getFAQContent(@RequestParam("faqId")int faqId) throws SysException{
		return fs.getFAQContent(faqId);
	}
	
	@RequestMapping("acception")
	@ResponseBody
	public ResultInfo<Complain> acception(Complain complain,HttpServletRequest request) throws SysException{
		ResultInfo<Complain> rs = new ResultInfo<Complain>();
		String type = complain.getType();
		//微信端直接保存
		if("wx".equals(type)){
			return cs.addComplainForWX(complain,"1");
		}
		else{
			rs.setMessage("系统繁忙，请稍候再试！");
			rs.setSuccess(false);
			return rs;
		}
	}
	
	/*===========微信小程序start=============*/

	@RequestMapping("search")
	@ResponseBody
	public ResultInfo<Complain> search(String userId,String type,HttpServletRequest request) throws SysException{
		if("wx".equals(type)){
			return cs.getComplain(userId, type, request);
		}else{
			return cs.getComplain(userId, type, request);
		}
	}

	@RequestMapping("searchWXCode")
	@ResponseBody
	public String getMSG(String code) throws SysException{
		
		String httpOrgCreateTest = url + "sns/jscode2session";
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("appid","wx6e4c2a76e5bcdd43");
		createMap.put("secret","096d4cce55fe6340097328b1471dc584");
		createMap.put("js_code",code);
		createMap.put("grant_type","authorization_code");
		String httpOrgCreateTestRtn = httpClientUtil.doPost(httpOrgCreateTest,createMap,charset);
		
		return httpOrgCreateTestRtn;
	}

	@RequestMapping("weChatUpgrade")
	public String weChatUpgrande(){
		return "complain/weChatUpgrade";
	}
	
	/*===========微信小程序end=============*/
	
	/**
     * 这里用作存入session的名称
     */
    private static final String SESSION_KEY_OF_RAND_CODE = "randCode"; 

    /**
     * 干扰线
     */
    private static final int count = 200;

    /**
     * 定义图形大小(宽)
     */
    private static final int width = 105;
    /**
     * 定义图形大小(高)
     */
    private static final int height = 30;
    /**
     * 干扰线的长度=1.414*lineWidth
     */
    private static final int lineWidth = 1;
    
    private static final char[] CHARS = { '2', '3', '4', '5', '6', '7', '8',
        '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
        'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' ,
        'a','b','c','d','e','f','g','h','i','j','k','m','n','p','q','r','s','t','u','v','w','x','y','z' };
    
    private static String[] fontNames = {"Times New Roman","宋体","黑体","方正姚体","Meiryo"};  
    
    @RequestMapping(value = "generateCode")
    public void VerificationCode( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 设置页面不缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // 在内存中创建图象
        final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        final Graphics2D graphics = (Graphics2D) image.getGraphics();
        // 设定背景颜色
        graphics.setColor(Color.WHITE); // ---1.Color.WHITE为白色
        graphics.fillRect(0, 0, width, height);//填充衍射
        // 设定边框颜色
        //graphics.setColor(getRandColor(100, 200)); // ---2.这是以数字型来设置颜色，颜色模式是指使用三种基色：红、绿、蓝，通过三种颜色的调整得出其它各种颜色，这三种基色的值范围为0～255
        graphics.drawRect(0, 0, width - 1, height - 1);
        
        final Random random = new Random();
        // 随机产生干扰线，使图象中的认证码不易被其它程序探测到
        for (int i = 0; i < count; i++) {
            graphics.setColor(randomColor()); // ---3.
            final int x = random.nextInt(width - lineWidth - 1) + 1; // 保证画在边框之内
            final int y = random.nextInt(height - lineWidth - 1) + 1;
            final int xl = random.nextInt(lineWidth);
            final int yl = random.nextInt(lineWidth);
            graphics.drawLine(x, y, x + xl, y + yl);
        }
        
        // 取随机产生的认证码(4位数字)
        final String resultCode = getRandomString();
        for (int i = 0; i < resultCode.length(); i++) {
            // 设置字体颜色
            graphics.setColor(randomColor());
            // 设置字体样式
            int index = random.nextInt(fontNames.length);  
            String fontName = fontNames[index];  
            graphics.setFont(new Font(fontName, Font.BOLD, 20));
            
            // 设置字符，字符间距，上边距
            graphics.drawString(String.valueOf(resultCode.charAt(i)), (23 * i) + 8, 26);
        }
        System.out.println("直接输出："+resultCode);
        // 将认证码存入SESSION
        request.getSession().setAttribute(SESSION_KEY_OF_RAND_CODE, resultCode);
        // 图象生效
        graphics.dispose();

        // 输出图象到页面
        ImageIO.write(image, "JPEG", response.getOutputStream());
        
    }
    
    /**
     * 随机字体颜色
     * @return
     */
    private Color randomColor(){  
    	final Random random = new Random();
        int red = random.nextInt(200);  
        int green = random.nextInt(200);  
        int blue = random.nextInt(200);  
        return new Color(red,green,blue);  
    }
    
    /**
     * 获取6位随机数
     */
    private static String getRandomString() {
    	final Random random = new Random();
    	StringBuffer buffer = new StringBuffer();
        for(int i = 0; i < 4; i++){
            buffer.append(CHARS[random.nextInt(CHARS.length)]);
        }
        return buffer.toString();
    }
	
}
