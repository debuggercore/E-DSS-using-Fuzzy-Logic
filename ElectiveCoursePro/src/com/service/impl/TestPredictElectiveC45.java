package com.service.impl;

import com.dao.impl.ElectiveCourseDAOImpl;
import com.dao.inter.ElectiveCourseDAOIF;
import com.model.ElectivePredictObj;
import com.model.StatusInfo;
import com.service.inter.ElectiveCourseServiceIF;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TestPredictElectiveC45 extends TestCase {
	
	@Configuration
    static class ElectiveCourseServiceTestContextConfiguration {
        // this bean will be injected into the OrderServiceTest class
        @Bean
        public ElectiveCourseServiceIF electiveService() {
        	return new ElectiveCourseServiceImpl();
            
        }
        
        @Bean
        public ElectiveCourseDAOIF electiveDao() {
        	return new ElectiveCourseDAOImpl();
            
        }
    }
	
	ElectivePredictObj electivePredictObj = new ElectivePredictObj();
	StatusInfo status = new StatusInfo();	
	
	@Autowired
	private ElectiveCourseServiceIF electiveService;
	
	@Autowired
	private ElectiveCourseDAOIF electiveDao;
	
	@Test
	public void testPredictionSuccess(){
		//Predicting data mining
		electivePredictObj.setSubject1Name("DataStructureswithJava");
		electivePredictObj.setSubject1("S");
		electivePredictObj.setSubject1Name("AnalysisandDesignofAlgorithms");
		electivePredictObj.setSubject1("A");
		
		electivePredictObj.setElectiveName("DATA MINING");
		status = electiveService.predictElective(electivePredictObj);
		
		//Check if successfull
		TestCase.assertEquals(null, status.getErrMsg());
		TestCase.assertEquals(true, status.isStatus());
		System.out.println(status.getModel());
		TestCase.assertNotNull(status.getModel());
	}
}
