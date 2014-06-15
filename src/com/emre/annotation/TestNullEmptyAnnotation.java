package com.emre.annotation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class TestNullEmptyAnnotation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		//we can also set the global option to ignore null values!
		//mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		Foo foo = new Foo();
		foo.fieldOne = "1";
		foo.fieldTwo = "2";
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(foo);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jsonString.toString());

	}

	/*
	 * e.g. here fieldOne and fieldTwo will be ommitted from json if they are null, 
	 * respectively, because this is the default set by the class annotation. 
	 * fieldThree however will override the default and will always be included, because of the annotation on the field.
	 */
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	// or @JsonInclude(Include.NON_NULL)
	// or @JsonInclude(Include.NON_EMPTY)
	public static class Foo {
	    public String fieldOne;
	    public String fieldTwo;
	    @JsonSerialize(include=JsonSerialize.Inclusion.ALWAYS)
	    public String fieldThree;
	    public String fiedlNull;
	}
}