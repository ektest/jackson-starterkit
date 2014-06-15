package com.emre.test;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Test {

	private MyValue myValue;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Test test = new Test();
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonTest ="{\"node\":{\"type\":\"community\",\"field_incentives\":{\"und\":[{\"value\":\"fun\"},{\"value\":\"nice\"}]},\"field_community_email\":{\"und\":[{\"value\":\"some@one.com\"}]}}}";
			/*
			   {
				  "node": {
				    "type": "community",
				    "field_incentives": {
				      "und": [
				        {
				          "value": "fun"
				        },
				        {
				          "value": "nice"
				        }
				      ]
				    },
				    "field_community_email": {
				      "und": [
				        {
				          "value": "some@one.com"
				        }
				      ]
				    }
				  }
				}
			 */

			//MyValue value = mapper.readValue(new File("data.json"), MyValue.class);
			// or:
			// MyValue value = mapper.readValue(new URL("http://some.com/api/entry.json"), MyValue.class);
			MyValue value = mapper.readValue("{\"name\":\"Bob\", \"age\":13}", MyValue.class);
			System.out.println(value);
			// MyValue value = mapper.readValue(new URL("http://some.com/api/entry.json"), MyValue.class);
			Person person = mapper.readValue("{\"name\":\"Bob\", \"age\":13}", Person.class);
			System.out.println(person);

			mapper.writeValue(new File("result.json"), value);
			// or:
			byte[] jsonBytes = mapper.writeValueAsBytes(value);
			System.out.println(jsonBytes.toString());
			// or:
			String jsonString = mapper.writeValueAsString(value);
			System.out.println(jsonString.toString());
			
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static class MyValue {
		  public String name;
		  public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public int age;
		  // NOTE: if using getters/setters, can keep fields `protected` or `private`
		@Override
		public String toString() {
			return "MyValue [name=" + name + ", age=" + age + "]";
		}
		  
		}
}
