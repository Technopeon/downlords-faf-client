package com.faforever.client.util;

import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;

import java.nio.file.Path;

public class UID {

  public interface UidLibrary extends StdCallLibrary {

    UidLibrary INSTANCE = (UidLibrary) Native.loadLibrary("uid", UidLibrary.class);

    String uid(String salt, String logFile);
  }

  static {
      System.loadLibrary("uid");
  }

  private UID() {
    // Utility class
  }

  public static String generate(String salt, Path logFile) {
    return UidLibrary.INSTANCE.uid(salt, logFile.toAbsolutePath().toString());
  }

}
