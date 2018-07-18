package sevlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import Class.UserDao;

/**
 * Servlet implementation class UpName
 */
@WebServlet("/UpName")
public class UpName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpName() {
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
        int responseCode=0;
        String result = null;
        UserDao MyPOJO=new UserDao();
        //String name=request.getParameter("name");
        String name=new String(request.getParameter("name").getBytes("iso-8859-1"), "utf-8");
        String account=request.getParameter("account");
        PrintWriter out = response.getWriter();
        if(name!=null){
        	try {
				responseCode = MyPOJO.UpName(name,account);
				 Map<String, String> map = new HashMap<String, String>();
		         map.put("result",String.valueOf(responseCode));
		         result= JSON.toJSONString(map);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            /*Map<String, String> map = new HashMap<String, String>();
            map.put("result",String.valueOf(responseCode));
            result= JSON.toJSONString(map);*/
        }else{
        	Map<String, String> map = new HashMap<String, String>();
            map.put("result",String.valueOf(responseCode));
            result= JSON.toJSONString(map);
        }
        out.write(result);
        out.flush();
        out.close();
	}

}
