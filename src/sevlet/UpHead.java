package sevlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSON;

import Class.UserDao;

/**
 * Servlet implementation class UpHead
 */
@WebServlet("/UpHead")
public class UpHead extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String filename = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpHead() {
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
		
		String account = request.getParameter("account");
        Part part = request.getPart("file");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw=response.getWriter();
        if(account.equals("") || part == null){
        	System.out.println("account=null||part=null");
            pw.write("ERRO");
        }else {
            try {
                UserDao user = new UserDao();
                //String namePic = ((Object) part).getSubmittedFileName();
                String cd = part.getHeader("Content-Disposition");
                //截取不同类型的文件需要自行判断
                String namePic = cd.substring(cd.lastIndexOf("=")+2, cd.length()-1);
                String filenameEx = namePic.substring(namePic.length()-4);
                Map map  = new HashMap();
                if(filenameEx.equals(".jpg") || filenameEx.equals(".png")){
                    System.out.println(filenameEx);
                    String URL = "/head/" + account + filenameEx;
                    System.out.println(URL);
                    int responseCode = user.UpUserhead(URL,account);
                    String result ;
                    if(responseCode >=1 ) {
                        part.write(account + filenameEx);
                        map.put("result", String.valueOf(responseCode));
                        result = JSON.toJSONString(map);
                        pw.write(result);
                    }
                }else{
                	System.out.println("文件类型错误");
                    pw.write("ERRO");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        pw.flush();
        pw.close();
	}
}
