package checkman.entity;

public class UserAccess {
  private String hash;
  
  private String uid;
  
  public String getHash() {
    return this.hash;
  }
  
  public String getUid() {
    return this.uid;
  }
  
  public void setHash(String paramString) {
    this.hash = paramString;
  }
  
  public void setUid(String paramString) {
    this.uid = paramString;
  }
}