package Class;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.portable.InputStream;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.sun.xml.internal.fastinfoset.sax.Properties;

public class UserDao {
	private static String drv = "com.mysql.jdbc.Driver";  
	private static String url = "jdbc:mysql://localhost:3306/users?characterEncoding=utf8";  
	private static String user = "QFMY";  
	private static String pwd = "Shuaixiaohai2";
	private ResultSet resultSet;
    
	
    public boolean isuserlogin(String name,String password){  
        boolean isValid = false;  
  
        String sql="select * from users.login where user_account='"+name+"' and user_pwd='"+password+"'";  
        try{  
            Class.forName(drv).newInstance();
            Connection conn = (Connection) DriverManager.getConnection(url,user,pwd);
            //Connection conn = this.getConnection();
            Statement stm = (Statement) conn.createStatement();  
            ResultSet rs = stm.executeQuery(sql);  
  
            if(rs.next()){  
                isValid = true;  
            }  
            rs.close();  
            stm.close();  
            conn.close();  
        }catch (Exception e) {  
            e.printStackTrace();  
            System.out.println(e);  
        }  
        if(isValid){//判断用户名以及密码是否与设定相符  
            return true;  
        }  
        else return false;  
    }
    
    public boolean userregister(String account,String password){  
    	  
        boolean b = false;  
  
        String sql = "select * from users.login where user_account='"+account+"'";  
  
        try{  
            Class.forName(drv).newInstance();  
            Connection conn = (Connection) DriverManager.getConnection(url,user,pwd);
            //Connection conn = this.getConnection();
            Statement stm = (Statement) conn.createStatement();  
            ResultSet rs = stm.executeQuery(sql);
  
            if(!rs.next()){  
  
                sql = "insert into users.login(user_account,user_pwd) " +
                		"values('"+account+"','"+password+"')";
                stm.execute(sql);  
                b = true;  
            }  
  
            rs.close();  
            stm.close();  
            conn.close();  
        }catch (Exception e) {  
            e.printStackTrace();  
            System.out.println(e);  
        }  
        if(b)  
        {  
            return true;  
        }  
        else return false;  
    }
    
    public List SearchBook(String bookname){
    	String sql="select * from users.textmessage where name='"+bookname+"' or writer='"+bookname+"'";
    	//String sql="select * from users.textmessage where name like '%'"+bookname+"'%'";
    	BOOKDao Run=new BOOKDao();
    	return Run.BOOKDao(sql);
    }
    
    public List TypeSearch(String type){
    	String sql="select * from users.textmessage where type='"+type+"'";
    	BOOKDao type_Run=new BOOKDao();
    	return type_Run.BOOKDao(sql);
    }
    
    public List LoadAllBook(){
    	String sql="select * from users.textmessage";
    	BOOKDao Run=new BOOKDao();
    	return Run.BOOKDao(sql);
    }
    
    public List BookFile(String bookname){
    	String sql="select * from users.textfile where book_Id='"+bookname+"'";
    	BOOKDao Run=new BOOKDao();
    	return Run.FileDao(sql);
    }
    
    public List getAdvertise(){
        String sql="select * from users.advertise";
        BOOKDao getView=new BOOKDao();
        return getView.ViewPage(sql);
    }
    public List Recommende(){
    	String sql="select * from users.recommendbook";
    	BOOKDao recommende=new BOOKDao();
    	return recommende.BOOKDao(sql);
    }
    public List GetUser(String userKey){
    	String sql="select * from users.login where user_account='"+userKey+"'";
    	BOOKDao getIfo=new BOOKDao();
    	return getIfo.UserIfo(sql);
    }
    public int UpName(String name,String account)throws SQLException{
    	String sql="update users.login set user_name='"+name+"' where user_account='"+account+"'";
    	BOOKDao up=new BOOKDao();
    	return up.upDate(sql);
    }
    
    public int UpUserhead(String url,String account)throws SQLException{
    	String sql="update users.login set user_head='"+url+"' where user_account='"+account+"'";
    	BOOKDao upHead=new BOOKDao();
    	return upHead.upDate(sql);
    }
}