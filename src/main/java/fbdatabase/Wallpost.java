package fbdatabase;

public class Wallpost {
  int wid;
  String semail,wpostcontainent,dt,photo;
  public Wallpost() {}

  public Wallpost(int wid, String semail, String wpostcontainent, String dt, String photo) {
	this.wid = wid;
	this.semail = semail;
	this.wpostcontainent = wpostcontainent;
	this.dt = dt;
	this.photo = photo;
  }
 
   public int getWid() {
	 return wid;
   }
   public void setWid(int wid) {
	 this.wid = wid;
   }
   public String getSemail() {
	  return semail;
   }
   public void setSemail(String semail) {
	  this.semail = semail;
   }
   public String getWpostcontainent() {
	 return wpostcontainent;
   }
   public void setWpostcontainent(String wpostcontainent) {
	  this.wpostcontainent = wpostcontainent;
   }
   public String getDt() {
	  return dt;
   }
   public void setDt(String dt) {
	 this.dt = dt;
   }

   public String getPhoto() {
	 return photo;
   }

   public void setPhoto(String photo) {
	 this.photo = photo;
   }
 
}
