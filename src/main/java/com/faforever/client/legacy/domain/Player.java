package com.faforever.client.legacy.domain;

import java.util.Map;

/**
 * A player info as received from the FAF server. The FAF server sends it as JSON string which is deserialized into an
 * instance of this class.
 */
public class Player {

  private int id;
  private String clan;
  private String login;
  private Avatar avatar;
  private String country;
  private float ratingMean;
  private Integer numberOfGames;
  private float ratingDeviation;
  private float ladderRatingMean;
  private Map<String, String> league;
  private float ladderRatingDeviation;

  public String getClan() {
    return clan;
  }

  public void setClan(String clan) {
    this.clan = clan;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public Avatar getAvatar() {
    return avatar;
  }

  public void setAvatar(Avatar avatar) {
    this.avatar = avatar;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public float getRatingMean() {
    return ratingMean;
  }

  public void setRatingMean(float ratingMean) {
    this.ratingMean = ratingMean;
  }

  public Integer getNumberOfGames() {
    return numberOfGames;
  }

  public void setNumberOfGames(Integer numberOfGames) {
    this.numberOfGames = numberOfGames;
  }

  public float getRatingDeviation() {
    return ratingDeviation;
  }

  public void setRatingDeviation(float ratingDeviation) {
    this.ratingDeviation = ratingDeviation;
  }

  public float getLadderRatingMean() {
    return ladderRatingMean;
  }

  public void setLadderRatingMean(float ladderRatingMean) {
    this.ladderRatingMean = ladderRatingMean;
  }

  public Map<String, String> getLeague() {
    return league;
  }

  public void setLeague(Map<String, String> league) {
    this.league = league;
  }

  public float getLadderRatingDeviation() {
    return ladderRatingDeviation;
  }

  public void setLadderRatingDeviation(float ladderRatingDeviation) {
    this.ladderRatingDeviation = ladderRatingDeviation;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
