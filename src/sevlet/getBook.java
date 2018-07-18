package sevlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import Class.UserDao;

/**
 * Servlet implementation class getBook
 */
@WebServlet("/getBook")
public class getBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//设置客户端的解码方式为utf-8  
        response.setContentType("text/html;charset=utf-8");   
        response.setCharacterEncoding("UTF-8");  
        UserDao MyPOJO=new UserDao();//新建UserDao类的对象myPOJO  
        //根据标示名获取JSP文件中表单所包含的参数  
        String bookname=new String(request.getParameter("Key").getBytes("iso-8859-1"), "utf-8");
        /*String bookname =URLEncoder.encode("侠客行" ,"UTF-8");
        System.out.println(bookname);
        String keyWord =URLDecoder.decode(bookname,"UTF-8");
        System.out.println(keyWord);*/
        
        PrintWriter out = response.getWriter();//回应请求
        try{
        	List book_list = (List) MyPOJO.SearchBook(bookname);
            String result = null;
            Map map=new HashMap();
            map.put("BOOK",book_list);
            if(book_list.isEmpty()){
            	//result = JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
            	result = JSON.toJSONString(map,SerializerFeature.WriteMapNullValue);
            	System.out.println("这是IF里面的Print");
            	System.out.println(result);
            }else{
            	//result = JSON.toJSONString(map,SerializerFeature.DisableCircularReferenceDetect);
            	result = JSON.toJSONString(map,SerializerFeature.WriteMapNullValue);
            	System.out.println("这是ELSE里面的Print");
            	System.out.println(result);
            }
            out.write(result);
        }catch(Exception e){
        	e.printStackTrace();
        }
        out.flush();  
        out.close();
	}

}
