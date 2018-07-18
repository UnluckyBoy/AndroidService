package sevlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Class.UserDao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Servlet implementation class getTypeSearch
 */
@WebServlet("/getTypeSearch")
public class getTypeSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getTypeSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");   
        response.setCharacterEncoding("UTF-8");  
        UserDao MyPOJO=new UserDao();
        String booktype=new String(request.getParameter("type").getBytes("iso-8859-1"), "utf-8");
        PrintWriter out = response.getWriter();//回应请求
        try{
        	List book_list = (List) MyPOJO.TypeSearch(booktype);
            String result = null;
            Map map=new HashMap();
            map.put("BOOK",book_list);
            if(book_list.isEmpty()){
            	result = JSON.toJSONString(map,SerializerFeature.WriteMapNullValue);
            	System.out.println("这是IF里面的Print");
            	System.out.println(result);
            }else{
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
