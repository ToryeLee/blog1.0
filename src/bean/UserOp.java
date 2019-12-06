package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import user.User;

public class UserOp {
	Connection conn=null;
    PreparedStatement ps=null;
    DBConn dbc=new DBConn();
	public User getuser(){
		dbc.LoadDatabase();
    	conn=dbc.ConnecteDatabase();
        ResultSet re;
        String sql="select * from user_info where userid='1375011167'";
        try {
			ps=conn.prepareStatement(sql);
			re=ps.executeQuery();
			if(re.next()){
				User user=new User();
				user.setUserid(re.getString(1));
				user.setLogname(re.getString(3));
				user.setGender(re.getString(4));
				user.setPhone(re.getString(5));
				user.setEmail(re.getString(6));
				user.setEducation(re.getString(7));
				user.setProblem(re.getString(8));
				user.setAnswer(re.getString(9));
				user.setSelfintro(re.getString(10));
				return user;
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
	}

}
