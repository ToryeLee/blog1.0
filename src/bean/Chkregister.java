package bean;

//import java.io.UnsupportedEncodingException;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Chkregister {//0 logname,1 realname, 2 passwd,3 email,4 gender,5 uaddress,6 phone
	                       //7 problem,8 answer,9 province,10 education,11 hobbies,12 selfintro
	Connection conn=null;
    PreparedStatement ps=null;
    DBConn dbc=new DBConn();
    public int Register(String info[]){     //注册
    	
    	dbc.LoadDatabase();
    	conn=dbc.ConnecteDatabase();
        String sql="select COUNT(*) from user_info where userid=?";
        ResultSet re;
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1, info[0]);
            re=ps.executeQuery();
            re.next();
            if(re.getInt(1)>0) return -1; //弹出窗口,用户已存在
            else {
                sql="insert into user_info values (?,?,?,?,?,?,?,?,?,?)";
                ps=conn.prepareStatement(sql);
//              String userpwd_e=Encryption(info[2]);//调用加密
//              ps.setString(3, userpwd_e);
                
                for(int i=0;i<10;i++){
//                	if(i==2);
//                else
                	ps.setString(i+1, info[i]);
                }
                
                ps.executeUpdate();
                 return 1;//弹出窗口,注册成功
            }
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
            System.out.println("register error");
        }
        dbc.Close();
        return 2;
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
