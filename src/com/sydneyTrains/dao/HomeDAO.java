package com.sydneyTrains.dao;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.stereotype.Repository;

import net.sf.json.JSONObject;

@Repository
public interface HomeDAO {

	public JSONObject getYearList() throws FileNotFoundException, IOException;
	
	public JSONObject getDefaulterData(String year) throws FileNotFoundException, IOException;
}
