package fbdatabase;

public class Friend {
    int fid,status;
    String semail,remail,date;
    public Friend() {}
    public Friend(int fid, int status, String semail, String remail, String date) {
	
	  this.fid = fid;
	  this.status = status;
	  this.semail = semail;
	  this.remail = remail;
	  this.date = date;
    }
     public int getFid() {
	   return fid;
     }
     public void setFid(int fid) {
	    this.fid = fid;
     }
     public int getStatus() {
	    return status;
     }
     public void setStatus(int status) {
	    this.status = status;
     }
     public String getSemail() {
	   return semail;
     }
     public void setSemail(String semail) {
	   this.semail = semail;
     }
     public String getRemail() {
	   return remail;
     }
     public void setRemail(String remail) {
	   this.remail = remail;
     }
     public String getDate() {
	   return date;
     } 
     public void setDate(String date) {
	    this.date = date;
     }
  
}
