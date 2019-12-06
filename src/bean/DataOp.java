package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import user.User;

public class DataOp {
	Connection conn=null;
    PreparedStatement ps=null;
    DBConn dbc=new DBConn();
    public String getProblem(String userid){
    	dbc.LoadDatabase();
    	conn=dbc.ConnecteDatabase();
//    	System.out.println("Loagining........");
//    	System.out.println(utype);
        String sql="select problem from user_info where userid=? ";
        ResultSet re;
        
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,userid);

            re=ps.executeQuery();
            if(re.next()){
                
                return re.getString(1);
                
            }

        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
            System.out.println("error2");
        }
        dbc.Close();
        return "null";
    }
    public int chkAnswer(String userid,String answer){
    	dbc.LoadDatabase();
    	conn=dbc.ConnecteDatabase();
//    	System.out.println("Loagining........");
//    	System.out.println(utype);
        ResultSet re;
        String sql="select answer from user_info where userid=?";//
        
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,userid);
            re=ps.executeQuery();
            re.next();
//            System.out.println("the real answer is:"+re.getString(1)+re.getString(1).length()+answer);
            if(re.getString(1).equalsIgnoreCase(answer))
                return 1;//回答正确
                else return 2;//回答错误
                

        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
            System.out.println("error2");
        }
        dbc.Close();
        return 0;
    }
	public int resetPsw(String userid, String passwd) {
		// TODO Auto-generated method stub
		dbc.LoadDatabase();
    	conn=dbc.ConnecteDatabase();
//    	ResultSet re;
    	int flag=0;
		String sql= "update user_info set userpsw=? where userid = ?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,passwd);
			ps.setString(2, userid);
			 flag = ps.executeUpdate();
			dbc.Close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	public int updateData(User user1, User user2) {
		// TODO Auto-generated method stub
		dbc.LoadDatabase();
    	conn=dbc.ConnecteDatabase();
//    	ResultSet re;
	    String sql="";
	    int flag=0;
	    try {
			if(!user1.getLogname().equals(user2.getLogname())){
				sql="update user_info set logname=? where userid=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, user2.getLogname());
				ps.setString(2, user1.getUserid());
				ps.executeUpdate();
			}
			if(!user1.getEmail().equals(user2.getEmail())){
				sql="update user_info set email=? where userid=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, user2.getEmail());
				ps.setString(2, user2.getUserid());
				ps.executeUpdate();
			}
			if(!user1.getGender().equals(user2.getGender())){
				sql="update user_info set gendere=? where userid=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, user2.getGender());
				ps.setString(2, user2.getLogname());
				ps.executeUpdate();
			}

			if(!user1.getPhone().equals(user2.getPhone())){
				sql="update user_info set phone=? where userid=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, user2.getPhone());
				ps.setString(2, user2.getUserid());
				ps.executeUpdate();
				}
			
			if(!user1.getProblem().equals(user2.getProblem())){
				sql="update user_info set problem=? where userid=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, user2.getProblem());
				ps.setString(2, user2.getUserid());
				ps.executeUpdate();
				}
			
			if(!user1.getAnswer().equals(user2.getAnswer())){
				sql="update user_info set answer=? where userid=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, user2.getAnswer());
				ps.setString(2, user2.getUserid());
				ps.executeUpdate();
				}

			if(!user1.getEducation().equals(user2.getEducation())){
				sql="update user_info set education=? where userid=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, user2.getEducation());
				ps.setString(2, user2.getUserid());
				ps.executeUpdate();
		}

			if(!user1.getSelfintro().equals(user2.getSelfintro())){
				sql="update user_info set selfintro=? where userid=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, user2.getSelfintro());
				ps.setString(2, user2.getUserid());
				ps.executeUpdate();
		}
			flag=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag=0;
		}
		
		
		return flag;
	}

}
