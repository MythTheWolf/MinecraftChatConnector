package com.myththewolf.MCC.lib;

public interface MessageChannelListener {
  public void onEvent(String message,TCPServer server);
}
