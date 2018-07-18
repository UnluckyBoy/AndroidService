package Class;

import java.net.URLEncoder;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class BOOKDao {
	private String drv = "com.mysql.jdbc.Driver";  
	private String url = "jdbc:mysql://localhost:3306/users?characterEncoding=utf8";  
	private String user = "QFMY";  
	private String pwd = "Shuaixiaohai2";

	public List BOOKDao(String sql){
		List<Object> listDatas =new ArrayList<Object>() ;
    	try{
    		Class.forName(drv).newInstance();  
            Connection conn = (Connection) DriverManager.getConnection(url,user,pwd);  
            Statement stm = (Statement) conn.createStatement();  
            ResultSet rs = stm.executeQuery(sql);//Ö´ÐÐSQL
            while(rs.next()){
            	Map<String ,Object> map=new HashMap<String, Object>();
            	String rBid = rs.getString("Id");
                String rBname = rs.getString("name");
                String rBpic = rs.getString("pic");
                //String bookname =URLEncoder.encode(rBpic ,"UTF-8");
                //String rBpic=new String(rs.getString("pic").getBytes("iso-8859-1"), "utf-8");
                String rBwriter = rs.getString("writer");
                String rBtype = rs.getString("type");
                //String rBtyperun = rs.getString("type_Run");
                
                map.put("B_ID", rBid);
                map.put("B_Name",rBname);
                map.put("B_Pic",rBpic);
                map.put("B_Writer",rBwriter);
                map.put("B_Type",rBtype);
                //map.put("B_typerun",rBtyperun);
                listDatas.add(map);
            }
    	}catch(Exception e){
    		e.printStackTrace();
            System.out.println(e);
    	}
		return listDatas;
	}
	
	public List FileDao(String sql){
		List<Object> listDatas =new ArrayList<Object>() ;
    	try{
    		Class.forName(drv).newInstance();  
            Connection conn = (Connection) DriverManager.getConnection(url,user,pwd);  
            Statement stm = (Statement) conn.createStatement();  
            ResultSet rs = stm.executeQuery(sql);//Ö´ÐÐSQL
            while(rs.next()){
            	Map<String ,Object> map=new HashMap<String, Object>();
            	String rBid = rs.getString("book_Id");
                String rBname = rs.getString("book_name");
                String rBpic = rs.getString("book_file");
            	
                map.put("id", rBid);
            	map.put("name",rBname);
                map.put("file",rBpic);
                listDatas.add(map);
            }
    	}catch(Exception e){
    		e.printStackTrace();  
            System.out.println(e);
    	}
		return listDatas;
	}
	
	public List UserIfo(String sql){
		List<Object> listDatas =new ArrayList<Object>() ;
    	try{
    		Class.forName(drv).newInstance();  
            Connection conn = (Connection) DriverManager.getConnection(url,user,pwd);  
            Statement stm = (Statement) conn.createStatement();  
            ResultSet rs = stm.executeQuery(sql);//Ö´ÐÐSQL
            while(rs.next()){
            	Map<String ,Object> map=new HashMap<String, Object>();
            	String name = rs.getString("user_name");
                String account = rs.getString("user_account");
                String pwd = rs.getString("user_pwd");
                String head=rs.getString("user_head");
            	
                map.put("name",name);
                map.put("account",account);
                map.put("pwd",pwd);
                map.put("head",head);
                listDatas.add(map);
            }
    	}catch(Exception e){
    		e.printStackTrace();
            System.out.println(e);
    	}
		return listDatas;
	}
	
	public List ViewPage(String sql){
		List list = new ArrayList();
		try{
			try {
				Class.forName(drv).newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
            Connection conn = (Connection) DriverManager.getConnection(url,user,pwd);
            //Connection conn = this.getConnection();
            Statement stm = (Statement) conn.createStatement();  
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()){
                Map<String, String> map1 = new HashMap<String, String>();
                String rpid = rs.getString("name");
                String rpurl = rs.getString("pic");
                String rphead=rs.getString("url");
                map1.put("name", rpid);
                map1.put("pic", rpurl);
                map1.put("head", rphead);
                list.add(map1);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return list;
	}
	public int upDate(String sql)throws SQLException{
		int rs=0;
		try{
			Class.forName(drv).newInstance();  
            Connection conn = (Connection) DriverManager.getConnection(url,user,pwd);  
            Statement stm = (Statement) conn.createStatement();
            rs = stm.executeUpdate(sql);
            return rs;
		}catch(Exception e){
    		e.printStackTrace();
            System.out.println(e);
            //return rs;
    	}
		//if()
		return rs;
	}
}
