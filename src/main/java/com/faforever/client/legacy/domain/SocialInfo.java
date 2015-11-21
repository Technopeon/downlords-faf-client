package com.faforever.client.legacy.domain;

import java.util.List;

/**
 * Other than other server objects, the "social" command is a category of different objects like friend list, foe list
 * and a list of channels to join. Only one field is filled at a time.
 */
public class SocialInfo extends ServerMessage {

  private List<String> friends;
  private List<String> foes;
  private List<String> autojoin;

  public List<String> getAutojoin() {
    return autojoin;
  }

  public void setAutojoin(List<String> autojoin) {
    this.autojoin = autojoin;
  }

  /**
   * List of user names that are friends. May be {@code null}.
   */
  public List<String> getFriends() {
    return friends;
  }

  public void setFriends(List<String> friends) {
    this.friends = friends;
  }

  /**
   * List of user names that are foes. May be {@code null}.
   */
  public List<String> getFoes() {
    return foes;
  }

  public void setFoes(List<String> foes) {
    this.foes = foes;
  }

  /**
   * List of channel names to join.
   */
  public List<String> getAutoJoin() {
    return autojoin;
  }

  public void setAutoJoin(List<String> autojoin) {
    this.autojoin = autojoin;
  }
}
