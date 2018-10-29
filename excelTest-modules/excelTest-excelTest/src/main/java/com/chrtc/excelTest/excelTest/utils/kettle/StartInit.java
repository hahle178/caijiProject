package com.chrtc.excelTest.excelTest.utils.kettle;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component("KettleEnvironmentInit")
public class StartInit implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		//初始化环境***
		com.chrtc.excelTest.excelTest.utils.kettle.KettleInit.init();
		org.pentaho.di.core.KettleEnvironment.init();
	}

}
