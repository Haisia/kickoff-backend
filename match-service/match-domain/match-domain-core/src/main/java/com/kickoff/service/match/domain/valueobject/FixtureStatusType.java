package com.kickoff.service.match.domain.valueobject;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum FixtureStatusType {
  UNKNOWN("unknown", "Unknown", "Unknown", "Unknown"),
  TBD("tbd", "Time To Be Defined", "Scheduled", "Scheduled but date and time are not known"),
  NS("ns", "Not Started", "Scheduled", "Scheduled but not started yet"),
  H1("1h", "First Half, Kick Off", "In Play", "First half in play"),
  HT("ht", "Halftime", "In Play", "Halftime break, waiting for the second half"),
  H2("2h", "Second Half, 2nd Half Started", "In Play", "Second half in play"),
  ET("et", "Extra Time", "In Play", "Extra time period in play"),
  BT("bt", "Break Time", "In Play", "Break during extra time"),
  P("p", "Penalty In Progress", "In Play", "Penalty shootout is in progress"),
  SUSP("susp", "Match Suspended", "In Play", "Match suspended by referee's decision, may be rescheduled another day"),
  INT("int", "Match Interrupted", "In Play", "Match temporarily interrupted by referee's decision, should resume in a few minutes"),
  FT("ft", "Match Finished", "Finished", "Match finished in the regular time"),
  AET("aet", "Match Finished After Extra Time", "Finished", "Match finished after extra time without a penalty shootout"),
  PEN("pen", "Match Finished After Penalty Shootout", "Finished", "Match finished after a penalty shootout"),
  PST("pst", "Match Postponed", "Postponed", "Match postponed to another day, once date and time are known the status will change"),
  CANC("canc", "Match Cancelled", "Cancelled", "Match cancelled and will not be played"),
  ABD("abd", "Match Abandoned", "Abandoned", "Match abandoned for reasons such as weather or safety, may or may not be rescheduled"),
  AWD("awd", "Technical Loss", "Not Played", "Match was forfeited and not played"),
  WO("wo", "Walk Over", "Not Played", "Victory by forfeit or absence of competitor"),
  LIVE("live", "Live Match, In Progress", "In Play", "A live match in progress, but details like times or phases are unavailable");

  public final String code;
  public final String name;
  public final String type;
  public final String description;

  FixtureStatusType(String code, String name, String type, String description) {
    this.code = code;
    this.name = name;
    this.type = type;
    this.description = description;
  }

  public static FixtureStatusType parseCodeIgnoreCase(String code) {
    if (code == null) {
      return FixtureStatusType.UNKNOWN;
    }
    return Arrays.stream(FixtureStatusType.values())
      .filter(name -> name.getCode().equalsIgnoreCase(code))
      .findFirst()
      .orElse(FixtureStatusType.UNKNOWN);
  }
}