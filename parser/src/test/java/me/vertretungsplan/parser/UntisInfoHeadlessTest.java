package me.vertretungsplan.parser;

import static org.junit.Assert.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import me.vertretungsplan.objects.SubstitutionSchedule;
import me.vertretungsplan.objects.SubstitutionScheduleData;

public class UntisInfoHeadlessTest {

	private SubstitutionScheduleData scheduleData;
	private UntisInfoHeadlessParser parser;
	
	@Before
	public void setUp() throws Exception {
		final JSONObject json = new JSONObject();
		final String uri = "https://www.egwerther.de/vertretungsplan/w00000.htm";
		final JSONArray classes = new JSONArray();
		
        scheduleData = new SubstitutionScheduleData();
        
		classes.put("5a");
		classes.put("6a");
		classes.put("6c");
		classes.put("7a");
		classes.put("7b");
		classes.put("7c");
		classes.put("8a");
		classes.put("8b");
		
		json.put("url", uri);
		json.put("encoding", "UTF-8");
		json.put("classes", classes);
		
		scheduleData.setData(json);
		
		parser = new UntisInfoHeadlessParser(scheduleData, null);
	}

	@Test
	public void test() throws Exception{
		SubstitutionSchedule schedule = parser.getSubstitutionSchedule();
		
		// Since the HTML File defines the Schedule for two days,
		// We expect the size of the getDays() array to be 2.
		assertEquals(2, schedule.getDays().size());
		
		// Check the number of lectures substitutions for each day.
		assertEquals(29, schedule.getDays().get(0).getSubstitutions().size());
		assertEquals(13, schedule.getDays().get(1).getSubstitutions().size());
		}

}
