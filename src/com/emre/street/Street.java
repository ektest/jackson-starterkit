package com.emre.street;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Street {
    private List<StreetSegment> streetSegment;

    public Street() {
        // TODO Auto-generated constructor stub
    }

    public List<StreetSegment> getStreetSegment() {
        return streetSegment;
    }

    public void setStreetSegment(List<StreetSegment> streetSegment) {
        this.streetSegment = streetSegment;
    }
    /*
      	public static class StreetSegment {
			public String distance;
			public String highway;
			public String name;
			public String line;
			public String wayId;
		}
     */
}