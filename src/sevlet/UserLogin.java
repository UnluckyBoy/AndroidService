package sevlet;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Class.UserDao;

import com.alibaba.fastjson.JSON;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//doGet(request, response);  
		  
        //设置客户端的解码方式为utf-8  
        response.setContentType("text/html;charset=utf-8");  
        //  
        response.setCharacterEncoding("UTF-8");  
        
        UserDao MyPOJO=new UserDao();//新建MyPOJO类的对象myPOJO  
  
        //根据标示名获取JSP文件中表单所包含的参数  
        String name=request.getParameter("Name");  
        String password=request.getParameter("Pwd");
        PrintWriter out = response.getWriter();//回应请求
        
        boolean responseCode = MyPOJO.isuserlogin(name,password);
        Map<String, String> map = new HashMap<String, String>();
        map.put("result",String.valueOf(responseCode));
        String result= JSON.toJSONString(map);
        
        out.write(result);      
        out.flush();  
        out.close();
        
        if(responseCode){
        	//打印登陆日志
            try{
            	File Login_Log=new File("E:/User_log/Login_log.txt");
            	PrintStream output=System.out;
            	ByteArrayOutputStream bos = new ByteArrayOutputStream();
            	System.setOut(new PrintStream(bos));       
            	System.out.println("用户："+name+"登陆于"+new Date());       
            	System.setOut(output);
            	FileWriter fw =new FileWriter(Login_Log,true) ;
            	fw.append(bos.toString());
            	fw.close();
            }catch(FileNotFoundException e){
            	e.printStackTrace();
            }
        }
	}
}
