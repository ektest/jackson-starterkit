package com.emre.street;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class test {

	public static void main(String[] args) throws JsonParseException,
			JsonMappingException, IOException {
		String jsonSource = "{"
				+ "    \"streetSegment\": ["
				+ "        {"
				+ "            \"Distance\": \"0.04\","
				+ "            \"highway\": \"non-residential\","
				+ "            \"name\": \"Swift1\","
				+ "            \"line\": \"-1.6720224 52.6251985,-1.6721061 52.6250432,-1.6721799, 52.6248908,-1.6721996 52.6247594\","
				+ "            \"wayId\": \"76473524\""
				+ "        },"
				+ "        {"
				+ "            \"Distance\": \"0.05\","
				+ "            \"highway\": \"residential\","
				+ "            \"name\": \"Swift2\","
				+ "            \"line\": \"-1.6721799 52.6248908,-1.6723374 52.6248669,-1.6732035 52.6249774,-1.6734643 52.6249894\","
				+ "            \"wayId\": \"76473523\"" + "        }" + "    ]"
				+ "}";
		// to simulate stream reader
		InputStreamReader in = new InputStreamReader(new ByteArrayInputStream(
				jsonSource.getBytes()));

		BufferedReader streamReader = new BufferedReader(in);

		StringBuilder buff = new StringBuilder();

		String inputStr;
		while ((inputStr = streamReader.readLine()) != null)
			buff.append(inputStr);

		ObjectMapper mapper = new ObjectMapper();

		Street mj = mapper.readValue(buff.toString(), Street.class);

		if (mj == null) {
			System.err.println("null");
		}

		System.out.println(mj.getStreetSegment().get(0).toString());
		
		// Second way
		Street ss = mapper.readValue(jsonSource, Street.class);
		System.out.println(ss.getStreetSegment().get(0).toString());
		
		// Third way
		//List<StreetSegment> list = mapper.readValue( in, new TypeReference<List<StreetSegment>>(){} );
		
		List<Street> scoreByName =  mapper.readValue("["
				+ "        {"
				+ "            \"Distance\": \"0.04\","
				+ "            \"highway\": \"residential\","
				+ "            \"name\": \"Swift1\","
				+ "            \"line\": \"-1.6720224 52.6251985,-1.6721061 52.6250432,-1.6721799, 52.6248908,-1.6721996 52.6247594\","
				+ "            \"wayId\": \"76473524\""
				+ "        },"
				+ "        {"
				+ "            \"Distance\": \"0.05\","
				+ "            \"highway\": \"residential\","
				+ "            \"name\": \"Swift2\","
				+ "            \"line\": \"-1.6721799 52.6248908,-1.6723374 52.6248669,-1.6732035 52.6249774,-1.6734643 52.6249894\","
				+ "            \"wayId\": \"76473523\"" + "        }" + "    ]",
				   new TypeReference<List<StreetSegment>>() { } );
		System.out.println(scoreByName.get(0));
		System.out.println(scoreByName.get(1));
	}

}
