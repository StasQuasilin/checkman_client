package checkman.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
  MessageDigest md;
  
  public MD5() {
    try {
      this.md = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
      noSuchAlgorithmException.printStackTrace();
    }
  }
  
  public String checkSum(String paramString) {
    this.md.update(paramString.getBytes(StandardCharsets.UTF_8));
    byte[] arrayOfByte = this.md.digest();
    StringBuilder stringBuilder = new StringBuilder();
    for (byte b : arrayOfByte) {
      stringBuilder.append(String.format("%02x", b));
    } 
    return stringBuilder.toString();
  }
}