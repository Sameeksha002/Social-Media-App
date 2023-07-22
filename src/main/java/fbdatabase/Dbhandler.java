package fbdatabase;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;


public class Dbhandler {

	String error;

	public Dbhandler() {
	error="";
	}
	
	Connection getconnection() {
		Connection connection  = null;
		try {
		  Class.forName("com.mysql.cj.jdbc.Driver"); 
		  connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/fbdatabase","root","sampom#12");  
		}
		catch(Exception e) {
			error=e.getMessage();
		}
		return connection;
	}
	
	//to insert data 
	public void insert(User user) {
		 Connection connection =null;
		try {
		  connection =getconnection();
		  String query="insert into userinfo values(?,?,?)";
		  PreparedStatement stmt=connection.prepareStatement(query);
		  stmt.setString(1,user.getName());  
		  stmt.setString(2,user.getEmail());
		  stmt.setString(3,user.getPassword()); 
		  stmt.executeUpdate(); 
		}
		catch(Exception e) {
			error="Not valid email";
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//to edit data 
	public void update(User user) {
		 Connection connection =null;
		try {
		  connection =getconnection();
		  String query="update userinfo set name=?,password=? where email=?";
		  PreparedStatement stmt=connection.prepareStatement(query);
		  stmt.setString(1,user.getName());  
		  stmt.setString(2,user.getPassword());
		  stmt.setString(3,user.getEmail());
		  stmt.executeUpdate(); 
		}
		catch(Exception e) {
			error=e.getMessage();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//validate function
	public User isValid(User user) {
		User u=null;
	    Connection connection = null;
		try {
		  connection =getconnection();
		  String query="select * from userinfo where email=? and password=?";
		  PreparedStatement stmt=connection.prepareStatement(query);  
		  stmt.setString(1,user.getEmail());
		  stmt.setString(2,user.getPassword()); 
		  ResultSet rs=stmt.executeQuery();   
		  if(rs.next()) {
			  u=new User(rs.getString(1),rs.getString(2),rs.getString(3));
		  }
		}
		catch(Exception e) {
			error=e.getMessage();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return u;
	}
	
	//insert friend details
	public void insert(Friend friend) {
		 Connection connection =null;
		try {
		  connection =getconnection();
		  String query="insert into friend(senderemail,reciveremail,status,dt) values(?,?,?,?)";
		  PreparedStatement stmt=connection.prepareStatement(query);
		  stmt.setString(1,friend.getSemail());  
		  stmt.setString(2,friend.getRemail());
		  stmt.setInt(3,friend.getStatus());
		  stmt.setString(4,friend.getDate()); 
		  stmt.executeUpdate(); 
		}
		catch(Exception e) {
			error="user not exist";
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//pending request function 
	public ArrayList<Friend> pending(String lemail) {
		ArrayList <Friend> arrf= new ArrayList<>();
	    Connection connection = null;
		try {
		  connection =getconnection();
		  String query="select * from friend where reciveremail=? and status=0 order by dt desc";
		  PreparedStatement stmt=connection.prepareStatement(query);  
		  stmt.setString(1,lemail);
		  ResultSet rs=stmt.executeQuery();   
		  while(rs.next()) {
			  int fid=rs.getInt(1);
			  String sedemail=rs.getString(2);
			  String recemail=rs.getString(3);
			  int status=rs.getInt(4);
			  String dt=rs.getString(5);
			  Friend f=new Friend(fid,status,sedemail,recemail,dt);
			  arrf.add(f);
		  }
		}
		catch(Exception e) {
			error="Friend Not Exist";
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arrf;
	}
	
	//friend request accept function	
		public void Accept(int fid){
		
		    Connection connection = null;
			try {
			  connection =getconnection();
			  String query="update friend set status=1 where fid=?";
			  PreparedStatement stmt=connection.prepareStatement(query); 
			  stmt.setInt(1,fid);
			  stmt.executeUpdate();   
			}
			catch(Exception e) {
				error=e.getMessage();
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		
		}
		
	//friend request reject function	
		public void Reject(int fid){
		
		    Connection connection = null;
			try {
			  connection =getconnection();
			  String query="update friend set status=2 where fid=?";
			  PreparedStatement stmt=connection.prepareStatement(query);  
			  stmt.executeUpdate();   
			}
			catch(Exception e) {
				error=e.getMessage();
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		
		}	
	
	//friend getting function
		public ArrayList<Friend> requestAccept(String lemail) {
			ArrayList <Friend> arrf= new ArrayList<>();
		    Connection connection = null;
			try {
			  connection =getconnection();
			  String query="select * from friend where (reciveremail=? or senderemail=?) and status=1 order by dt desc";
			  PreparedStatement stmt=connection.prepareStatement(query);  
			  stmt.setString(1,lemail);
			  stmt.setString(2,lemail);
			  ResultSet rs=stmt.executeQuery();
			  while(rs.next()) {
				  int fid=rs.getInt(1);
				  String sedemail=rs.getString(2);
				  String recemail=rs.getString(3);
				  int status=rs.getInt(4);
				  String dt=rs.getString(5);
				  Friend f=new Friend(fid,status,sedemail,recemail,dt);
				  arrf.add(f);
			  }
			}
			catch(Exception e) {
				error=e.getMessage();
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return arrf;
		}
	
    //for getting name of request sender
	public String getNames( String email) {
		 User u=null;
		 Connection connection =null;
			try {
			  connection =getconnection();
			  String query="select * from userinfo where email=?";
			  PreparedStatement stmt=connection.prepareStatement(query);
			  stmt.setString(1,email);  
			  ResultSet rs=stmt.executeQuery();
			  if(rs.next()) {
				  u=new User(rs.getString(1),rs.getString(2),rs.getString(3));
			  }
			}
			catch(Exception e) {
				error=e.getMessage();
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return u.getName();
		
	}
	
	//get profile of friend
	public User getProfile(String femail){
		 User u= null; 
	    Connection connection = null;
		try {
		  connection =getconnection();
		  String query="select * from userinfo where email=?";
		  PreparedStatement stmt=connection.prepareStatement(query);  
		  stmt.setString(1,femail);  
		  ResultSet rs=stmt.executeQuery();
		  if(rs.next()) {
			  u=new User(rs.getString(1),rs.getString(2),rs.getString(3));
		  }
		}
		catch(Exception e) {
			error=e.getMessage();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return u;
	
	}	
	
	//wallpostcontaintent function
	public void insert(Wallpost w) {
		 Connection connection =null;
		 String photo = w.getPhoto();
		 InputStream stream = new ByteArrayInputStream(photo.getBytes(StandardCharsets.UTF_8));
			try {
			  connection =getconnection();
			  String query="insert into wallpost(semail,wpostcontainent,dt,photo) values(?,?,?,?)";
			  PreparedStatement stmt=connection.prepareStatement(query);
			  stmt.setString(1,w.getSemail());  
			  stmt.setString(2,w.getWpostcontainent());
			  stmt.setString(3,w.getDt());
			  if(stream!=null) {
				  stmt.setBlob(4,stream);
			  }
			  stmt.executeUpdate();
			
			}
			catch(Exception e) {
				error="cannot post";
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		
	}
	
	
	//getting email of friends
	public ArrayList<String> getfremail(String lemail) {
		
		ArrayList <String> fe= new ArrayList<>();
	    Connection connection = null;
		try {
		  connection =getconnection();
		  String qy="select * from friend where (reciveremail=? or senderemail=?) and status=1 order by dt desc";
		  PreparedStatement st=connection.prepareStatement(qy);  
		  st.setString(1,lemail);
		  st.setString(2,lemail);
		  ResultSet r=st.executeQuery();
		  while(r.next()) {
			  String sedemail=r.getString(2);
			  String recemail=r.getString(3);
			  if(lemail.equals(sedemail)) {
			     fe.add(recemail);
			  }
			  else {
				  fe.add(sedemail);
			  }
		  }
	
		}
		catch(Exception e) {
			error=e.getMessage();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return fe;
	}
	
	//all post display
	public ArrayList<Wallpost> postDisplay(String lemail) {
		ArrayList <Wallpost> arrf= new ArrayList<>();
	    Connection connection = null;
		try {
		  connection =getconnection();
		  
		  ArrayList <String> fe=getfremail(lemail);
	
		  String query="select * from wallpost order by wid desc";
		  PreparedStatement stmt=connection.prepareStatement(query);  
		  ResultSet rs=stmt.executeQuery();
		  while(rs.next()) {
			int wid= rs.getInt(1);
			String semail= rs.getString(2);
			String wpost= rs.getString(3);
			String dt= rs.getString(4);
			String photo= rs.getString(5);
			if(fe.contains(semail)){
				Wallpost w= new Wallpost(wid,semail,wpost,dt,photo);
				arrf.add(w);
			}
		  }
		}
		catch(Exception e) {
			error=e.getMessage();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arrf;
	}
	
	//error function
	public String getError() {
		return error;
	}
	
}
