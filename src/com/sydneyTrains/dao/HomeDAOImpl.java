package com.sydneyTrains.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Repository
public class HomeDAOImpl implements HomeDAO {

	public JSONObject getYearList() throws FileNotFoundException, IOException {

		JSONObject jsonObj = null;
		String content = null;
		File file = null;
		try {
		file = ResourceUtils.getFile("classpath:Year.json");

		// Read File Content
		content = new String(Files.readAllBytes(file.toPath()));
		jsonObj = (JSONObject) JSONSerializer.toJSON(content);
		}
		catch(FileNotFoundException fnfe)
		{
			throw fnfe;
		}
		catch(IOException ioe)
		{
			throw ioe;
		}
		catch(Exception e)
		{
			throw e;
		}
		return jsonObj;

	}

	public JSONObject getDefaulterData(String year)  throws FileNotFoundException, IOException {

		JSONObject jsonObj = null;
		String content = null;
		File file = null;
		try {
		file = ResourceUtils.getFile("classpath:"+year+".json");

		// Read File Content
		content = new String(Files.readAllBytes(file.toPath()));
		jsonObj = (JSONObject) JSONSerializer.toJSON(content);
		}
		catch(FileNotFoundException fnfe)
		{
			throw fnfe;
		}
		catch(IOException ioe)
		{
			throw ioe;
		}
		catch(Exception e)
		{
			throw e;
		}
		return jsonObj;

	}

}
