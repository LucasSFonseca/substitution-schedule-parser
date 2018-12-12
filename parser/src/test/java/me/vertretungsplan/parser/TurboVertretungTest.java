package me.vertretungsplan.parser;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import me.vertretungsplan.objects.SubstitutionSchedule;
import me.vertretungsplan.objects.SubstitutionScheduleData;

public class TurboVertretungTest {
	private TurboVertretungParser parser;
	private TurboVertretungParser parserForNull;

	
	@Before
	public void setUp() throws Exception {
		// Setting Up for non-null response;
		SubstitutionScheduleData data = new SubstitutionScheduleData();
        final JSONObject json = new JSONObject();
        final JSONArray classes = new JSONArray();
        final JSONArray teachers = new JSONArray();
        
        classes.put("7a");
        classes.put("8a");
        classes.put("8b");
        classes.put("9a");
        classes.put("10a");
        
        teachers.put("Eiji Adachi");
        teachers.put("Selan Rodrigues");
        teachers.put("Edgar Faria");
        teachers.put("Marcia Jacyntha");
        
        json.put("classes", classes);
        json.put("teachers", teachers);
        data.setData(json);
        data.setType(SubstitutionSchedule.Type.STUDENT);
        parser = new TurboVertretungParser(data, null);
		
        // Setting Up for null response;
		SubstitutionScheduleData dataForNull = new SubstitutionScheduleData();
		final JSONObject jsonForNull = new JSONObject();
        
        jsonForNull.put("classes", new JSONArray());
        jsonForNull.put("teachers", new JSONArray());
        
        dataForNull.setData(jsonForNull);
        dataForNull.setType(SubstitutionSchedule.Type.STUDENT);
        
        parserForNull = new TurboVertretungParser(dataForNull, null);

	}

	@Test
	public void testGetAllClasses() throws IOException, JSONException{
		List<String> classes = parser.getAllClasses();
		assertEquals(5, classes.size());
	}
	
	@Test
	public void testGetAllClassesForZero() throws IOException, JSONException{
		List<String> classes = parserForNull.getAllClasses();
		assertEquals(0, classes.size());
	}
	
	// Since the method getAllTeachers is not implemented,
	// This test also expects the retunr value to be null.
	@Test
	public void testGetAllTeachers() throws IOException, JSONException{
		List<String> teachers = parser.getAllTeachers();
		assertEquals(null, teachers);
	}
	
}
