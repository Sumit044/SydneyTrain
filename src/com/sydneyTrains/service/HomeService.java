package com.sydneyTrains.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;

@Service
public interface HomeService {
	
	public String getYearList() throws FileNotFoundException, IOException;
	
	public JSONObject getefaulterData(String year) throws FileNotFoundException, IOException;
	
	

}
