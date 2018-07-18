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
		
		//���ÿͻ��˵Ľ��뷽ʽΪutf-8  
        response.setContentType("text/html;charset=utf-8");   
        response.setCharacterEncoding("UTF-8");  
        UserDao MyPOJO=new UserDao();//�½�UserDao��Ķ���myPOJO  
        //���ݱ�ʾ����ȡJSP�ļ��б��������Ĳ���  
        String bookname=new String(request.getParameter("Key").getBytes("iso-8859-1"), "utf-8");
        /*String bookname =URLEncoder.encode("������" ,"UTF-8");
        System.out.println(bookname);
        String keyWord =URLDecoder.decode(bookname,"UTF-8");
        System.out.println(keyWord);*/
        
        PrintWriter out = response.getWriter();//��Ӧ����
        try{
        	List book_list = (List) MyPOJO.SearchBook(bookname);
            String result = null;
            Map map=new HashMap();
            map.put("BOOK",book_list);
            if(book_list.isEmpty()){
            	//result = JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
            	result = JSON.toJSONString(map,SerializerFeature.WriteMapNullValue);
            	System.out.println("����IF�����Print");
            	System.out.println(result);
            }else{
            	//result = JSON.toJSONString(map,SerializerFeature.DisableCircularReferenceDetect);
            	result = JSON.toJSONString(map,SerializerFeature.WriteMapNullValue);
            	System.out.println("����ELSE�����Print");
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
