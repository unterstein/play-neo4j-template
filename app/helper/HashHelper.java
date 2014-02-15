package helper;

import play.Logger;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashHelper implements Serializable {

  private static final HashHelper INSTANCE = new HashHelper();

  private static SecureRandom RANDOM = new SecureRandom();

  private MessageDigest digest;

  private HashHelper() {
    try {
      digest = MessageDigest.getInstance("SHA");
    } catch (NoSuchAlgorithmException e) {
      Logger.error("unable to create sha digest", e);
    }
  }

  public static final String createId(int length) {
    return new BigInteger(length * 3, RANDOM).toString(32).substring(3, 3 + length);
  }

  public static final String createId() {
    return createId(64);
  }

  public static HashHelper getInstance() {
    return INSTANCE;
  }

  public String sha(String in) {
    String hash = new String(digest.digest(in.getBytes()));
    return new BigInteger(1, hash.getBytes()).toString(16);
  }
}
