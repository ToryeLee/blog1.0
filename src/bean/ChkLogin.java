package bean;

//import java.io.UnsupportedEncodingException;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import user.*;
public class ChkLogin {
	Connection conn=null;
    PreparedStatement ps=null;
    DBConn dbc=new DBConn();
   
    public int Login(String userid,String userpsw){        //登录
    	dbc.LoadDatabase();
    	conn=dbc.ConnecteDatabase();

        String sql="select COUNT(*) from user_info where userid=?";
        ResultSet re;
        
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,userid);
            re=ps.executeQuery();
            re.next();
            if(re.getInt(1)>0){
                sql="select COUNT(*) from user_info where userid=? and userpsw=?";//
                ps=conn.prepareStatement(sql);         //
                ps.setString(1, userid);             //
//                String userpwd_e=Encryption(passwd);  //调用加密函数
//                ps.setString(2, userpwd_e);
                ps.setString(2, userpsw);

                re=ps.executeQuery();
                re.next();
//                System.out.println(re.getInt(1));
                if(re.getInt(1)>0)
                return 1; //弹出窗口,登陆成功
                else return 2;//弹出窗口，密码错误
                
            }
            else return 3;//    "用户不存在"

        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
            System.out.println("Login error");
        }
        dbc.Close();
        return 0;
    }
    
    public int check(String userid){        //登录
    	dbc.LoadDatabase();
    	conn=dbc.ConnecteDatabase();

        String sql="select COUNT(*) from user_info where userid=?";
        ResultSet re;
        
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,userid);
            re=ps.executeQuery();
            re.next();
            if(re.getInt(1)>0){
                return 1;
            }
            else return 2;//    "用户不存在"

        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
            System.out.println("Login error");
        }
        dbc.Close();
        return 0;
    }
    
//    public String getProblem(String logname,String utype){
//    	dbc.LoadDatabase();
//    	conn=dbc.ConnecteDatabase();
////    	System.out.println("Loagining........");
////    	System.out.println(utype);
//        String sql="select * from userid where logname=? and utype=? ";
//        ResultSet re;
//        
//        try {
//            ps=conn.prepareStatement(sql);
//            ps.setString(1,logname);
//            ps.setString(2, utype);
//
//            re=ps.executeQuery();
//            ;
//            if(re.next()){
//                
//                return re.getString(8);
//                
//            }
//
//        } catch (SQLException e) {
//            // TODO 自动生成的 catch 块
//            e.printStackTrace();
//            System.out.println("error2");
//        }
//        dbc.Close();
//        return "null";
//    }
//    public int chkAnswer(String logname,String utype,String answer){
//    	dbc.LoadDatabase();
//    	conn=dbc.ConnecteDatabase();
////    	System.out.println("Loagining........");
////    	System.out.println(utype);
//        String sql="select COUNT(*) from userid where logname=? and utype=?";
//        ResultSet re;
//        
//        try {
//            ps=conn.prepareStatement(sql);
//            ps.setString(1,logname);
//            ps.setString(2, utype);
//            re=ps.executeQuery();
//            re.next();
//            if(re.getInt(1)>0){
//                sql="select problem from userid where logname=? and answer=?";//
//                ps=conn.prepareStatement(sql);         //
//                ps.setString(1, logname);             //
//                ps.setString(2, answer);
//                re=ps.executeQuery();
//                re.next();
//                if(re.getInt(1)>0)
//                return 1;//回答正确
//                else return 2;//回答错误
//                
//            }
//
//        } catch (SQLException e) {
//            // TODO 自动生成的 catch 块
//            e.printStackTrace();
//            System.out.println("error2");
//        }
//        dbc.Close();
//        return 0;
//    }
    
    public User getUser(String userid){
    	dbc.LoadDatabase();
    	conn=dbc.ConnecteDatabase();
        String sql="select * from user_info where userid=?";
        ResultSet re;
        User user =new User();
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,userid);
            re=ps.executeQuery();
            re.next();         
            user.setUserid(re.getString(1));
            user.setUserpsw(re.getString(2));
            user.setLogname(re.getString(3));
            user.setGender(re.getString(4));
            user.setPhone(re.getString(5));
            user.setEmail(re.getString(6));
            user.setEducation(re.getString(7));
            user.setProblem(re.getString(8));
            user.setAnswer(re.getString(9));
            user.setSelfintro(re.getString(10));
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
            System.out.println("Login error");
        }
        dbc.Close();
        return user;
    	
    }
//    public String Encryption(String passwd){//加密
//        String userpwd_e="";
//        try {
//            MessageDigest md5=MessageDigest.getInstance("MD5");
//            byte data[];
//            data=passwd.trim().getBytes("UTF-8");
//            userpwd_e=new String(md5.digest(),"UTF-8");
////             BASE64Encoder base64en = new BASE64Encoder();//加密后的字符串
////             userpwd_e=base64en.encode(md5.digest(userpwd.trim().getBytes("utf-8")));
//           System.out.println("encryption success,userpwd="+passwd+"userpwd_e="+userpwd_e);
//        } catch (NoSuchAlgorithmException e) {
//            // TODO 自动生成的 catch 块
//            e.printStackTrace();
//            System.out.println("MD5 error");
//            return passwd;
//        } catch (UnsupportedEncodingException e) {
//            // TODO 自动生成的 catch 块
//            e.printStackTrace();
//            System.out.println("encryption error");
//            return passwd;
//        }
//        
//        return userpwd_e;
//        
//    }

}
