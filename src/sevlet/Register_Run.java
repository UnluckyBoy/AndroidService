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
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import Class.UserDao;
//import Class.userregister;

/**
 * Servlet implementation class Register_Run
 */
@WebServlet("/Register_Run")
public class Register_Run extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register_Run() {
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
        
        boolean responseCode=false;
        UserDao myPOJO=new UserDao();
  
        //String name=request.getParameter("Name");
        String account=request.getParameter("account");
        String password=request.getParameter("Pwd");
        
        String result =null;
        PrintWriter out = response.getWriter();//回应请求
        if(account!=null&&password!=null){
        	responseCode = myPOJO.userregister(account,password);
            Map<String, String> map = new HashMap<String, String>();
            map.put("result",String.valueOf(responseCode));
            result= JSON.toJSONString(map);
            //out.write(result);
        	
        }
        else {
        	Map<String, String> map = new HashMap<String, String>();
            map.put("result",String.valueOf(responseCode));
            result= JSON.toJSONString(map);
        }
        out.write(result);
        out.flush();
        out.close();
        
        if(responseCode){
        	//打印注册日志
            try{
            	File Register_Log=new File("/User_log/Register_log.txt");
            	PrintStream output=System.out;
            	ByteArrayOutputStream bos = new ByteArrayOutputStream();
            	System.setOut(new PrintStream(bos));       
            	System.out.println("用户："+account+"注册于"+new Date());       
            	System.setOut(output);
            	FileWriter fw =new FileWriter(Register_Log,true) ;
            	fw.append(bos.toString());
            	fw.close();
            }catch(FileNotFoundException e){
            	e.printStackTrace();
            }
        }
	}
}