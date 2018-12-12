package me.vertretungsplan.parser;

import static org.junit.Assert.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import me.vertretungsplan.objects.SubstitutionSchedule;
import me.vertretungsplan.objects.SubstitutionScheduleData;

public class UntisSubstitutionTest {

	UntisSubstitutionParser parser;
	SubstitutionScheduleData scheduleData;
	
	@Before
	public void setUp() throws Exception {
		final JSONObject json = new JSONObject();
		final JSONArray urls = new JSONArray();
		final JSONArray classes = new JSONArray();
		
		scheduleData = new SubstitutionScheduleData();
		
		urls.put("https://erato.webuntis.com/WebUntis/api/public/printpreview/timetable?type=1&id=260&date=20181211&formatId=2");
		
		classes.put("5c");
		classes.put("6a");
		classes.put("6b");
		classes.put("6c");
		
		json.put("urls", urls);
		json.put("classes", classes);
		json.put("encoding", "UTF-8");
		
		scheduleData.setData(json);
		
		parser = new UntisSubstitutionParser(scheduleData, null);
		
	}

	@Test
	public void test() throws Exception{
		SubstitutionSchedule schedule = parser.getSubstitutionSchedule();
		System.out.println(schedule.getDays());
	}

}
