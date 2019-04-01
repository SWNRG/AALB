package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MonitoringTest {
private String host;
private double cpup;
private double memp;

	public MonitoringTest(String name) {
		ArrayList<JSONObject> json=new ArrayList<JSONObject>();
	    JSONObject obj;
	    String fileName = "dockerStats.txt";
	    String line = null;
		FileReader fileReader;
		try {
			fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			try {
				while((line = bufferedReader.readLine()) != null) {
					String cpu = null;
					String memory = null;
				    obj = (JSONObject) new JSONParser().parse(line);
				    json.add(obj);
				//Get containers Name
				    String containerName = (String)obj.get("name");
				//Get cpu usage percent
				    if(containerName.equals(name)) {
				    cpu = (String)obj.get("cpu");
				//Get memory usage
				    JSONObject resultObject = (JSONObject) obj.get("memory");
				    memory = (String)resultObject.get("percent");
					//Convert results from string to double
				    memp = Double.parseDouble(memory=memory.replaceAll("\\D+",""))/100;
				    cpup = Double.parseDouble(cpu=cpu.replaceAll("\\D+",""))/100;
				    System.out.println(containerName+": cpu:"+
				                       cpup+", memory:"+memp);
				    }

   }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        // Always close files.
	    try {
			bufferedReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	public double getCpup() {
		return cpup;
	}

	public double getMemp() {
		return memp;
	}

	}

