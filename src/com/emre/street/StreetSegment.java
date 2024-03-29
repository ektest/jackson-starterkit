package com.emre.street;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StreetSegment {
    private String distance;
    private String highway;
    private String name;
    private String line;
    private String wayId;

    public StreetSegment() {

    }
    public String getDistance() {
        return distance;
    }
    @Override
	public String toString() {
		return "StreetSegment [distance=" + distance + ", highway=" + highway
				+ ", name=" + name + ", line=" + line + ", wayId=" + wayId
				+ "]";
	}
    @JsonProperty("Distance")
	public void setDistance(String distance) {
        this.distance = distance;
    }
    public String getHighway() {
        return highway;
    }
    public void setHighway(String highway) {
        this.highway = highway;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLine() {
        return line;
    }
    public void setLine(String line) {
        this.line = line;
    }
    public String getWayId() {
        return wayId;
    }
    public void setWayId(String wayId) {
        this.wayId = wayId;
    }
}