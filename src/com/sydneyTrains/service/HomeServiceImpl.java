package com.sydneyTrains.service;


import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sydneyTrains.dao.HomeDAOImpl;

import net.sf.json.JSONObject;

@Service
public class HomeServiceImpl implements HomeService {

	@Autowired
	HomeDAOImpl homeDao;
	
	
	@Override
	public String getYearList() throws FileNotFoundException, IOException{
	
		JSONObject yearObj = null;
		try {
			yearObj = homeDao.getYearList();
		} 
		catch(FileNotFoundException fnfe)
		{
			throw fnfe;
		}
		catch (IOException ioe) {
			throw ioe;
		}
			
		return yearObj.toString();

	}

	@Override
	public JSONObject getefaulterData(String year) throws FileNotFoundException, IOException {

		JSONObject obj = null;
		try {
			obj = homeDao.getDefaulterData(year);
		
		} 
		
		catch(FileNotFoundException fnfe)
		{
			throw fnfe;
		}
		catch (IOException ioe) {
			throw ioe;
		}
		
		
		return obj;
		
		
	}
	

}




