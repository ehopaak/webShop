package com.shinhan.frontcontrollerpattern;

import java.util.Map;

//모든 Controller의 규격서
public interface CommonControllerInterface {
	
	public String excute(Map<String, Object> data) throws Exception;
	
}
