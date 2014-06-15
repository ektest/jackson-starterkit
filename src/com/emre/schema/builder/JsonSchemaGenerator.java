package com.emre.schema.builder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.emre.street.Street;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;

/**
 * Json schema generator from POJOs used in web services.
 * 
 * The build file has an ANT target that runs this class.
 * 
 * @author Emre Koca
 */
@SuppressWarnings("deprecation")
public class JsonSchemaGenerator {

	/** add classes for the schema generator here */
	// static Class<?>[] clazz =
	static Map<String, Class<?>[]> classes = getFoldedrsAndClasses();

	// { StatusResponse.class, Cart.class,
	// LeadAllocationResult.class, PromoResponse.class,
	// UserValidationResponse.class, FS2014JanConditionJson.class };

	public static void main(String[] args) throws JsonGenerationException,
			IOException {

		ObjectMapper mapper = new ObjectMapper();
		// For Jackson lib version < 1.9 // import org.codehaus.jackson.map.SerializationConfig.Feature;
		// mapper.configure(Feature.WRITE_DATES_AS_TIMESTAMPS, false);
		// For Jackson lib version > 1.9
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		//or mapper.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		for (Map.Entry<String, Class<?>[]> schema : classes.entrySet()) {
			File folder = new File(schema.getKey());
			folder.mkdirs();
			for (Class<?> c : schema.getValue()) {
				writeSchema(mapper, folder, c);
			}
		}
	}

	protected static void writeSchema(ObjectMapper mapper, File folder,
			Class<?> c) throws IOException {
		File schemaFile = new File(folder, c.getSimpleName() + ".json");
		System.out.println("-------------------------- " + schemaFile.getName()
				+ " --------------------------");

		String schema = createJsonSchema(c, mapper);
		System.out.println(schema);
		StringUtil.write(schemaFile, schema);
	}

	protected static String createJsonSchema(Class<?> clazz, ObjectMapper mapper) {

		try {
			JsonSchema jsonSchema = mapper.generateJsonSchema(clazz);
			// For Jackson lib version < 1.9
			//return mapper.defaultPrettyPrintingWriter().writeValueAsString(jsonSchema);
			// For Jackson lib version > 1.9
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			//or mapper.configure(SerializationFeature.INDENT_OUTPUT,true);
			return mapper.writeValueAsString(
					jsonSchema);
		} catch (Exception e) {
			e.printStackTrace(System.out);
			return "";
		}
	}

	static Map<String, Class<?>[]> getFoldedrsAndClasses() {

		Map<String, Class<?>[]> folders = new HashMap<String, Class<?>[]>();

		addFolder(folders, "responses", StatusResponse.class, Street.class);

		return folders;
	}

	static void addFolder(Map<String, Class<?>[]> folders, String folder,
			Class<?>... classes) {
		folders.put(folder, classes);
	}
}