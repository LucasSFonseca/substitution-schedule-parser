package me.vertretungsplan.parser;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import me.vertretungsplan.objects.SubstitutionSchedule;
import me.vertretungsplan.objects.SubstitutionScheduleData;

public class TurboVertretungTesDemo extends BaseDemoTest {
	private String html1;
	private String html2;
	
	private TurboVertretungParser parser;
	private SubstitutionScheduleData scheduleData;
	
	@Before
	public void setUp() throws Exception {
        final JSONObject json = new JSONObject();
		final JSONArray urls = new JSONArray();
		final JSONArray classes = new JSONArray();
        scheduleData = new SubstitutionScheduleData();
        
        html1 = readResource("/turbovertretung/turboVertretung1.html");
		urls.put("https://www.goethe-schule.de/ANBgkqhkiG9w0BAQEFAAOCAQ8goethe-schuleAMIIBCgKCAQEAnkFG3NUV4779/internet1.html");
		urls.put("https://www.goethe-schule.de/ANBgkqhkiG9w0BAQEFAAOCAQ8goethe-schuleAMIIBCgKCAQEAnkFG3NUV4779/internet2.html");
		
		classes.put("5N");
		classes.put("6A");
		classes.put("6B");
		classes.put("6C");
		classes.put("8C");
		classes.put("8N");
		classes.put("9A");
		classes.put("Q1");
		
		json.put("urls", urls);
		json.put("encoding", "UTF-8");
		json.put("classes", classes);
		
		scheduleData.setData(json);
		
        parser = new TurboVertretungParser(scheduleData, null);
	}

	@Test
	public void demoTest1() throws Exception {
        List<Document> docs = new ArrayList<>();
        docs.add(Jsoup.parse(html1));
        
        SubstitutionSchedule schedule = parser.getSubstitutionSchedule();
        System.out.println(schedule.getDays());
	}
	
	

}
