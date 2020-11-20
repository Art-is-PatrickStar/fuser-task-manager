package com.wsw.fusertaskmanager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class ManagerTaskServiceApplicationTests {
	@Autowired
	private TaskPoolTestService taskPoolTestService;

	@Test
	void contextLoads() {
		Map<String, Object> taskPoolMap = taskPoolTestService.getTaskPoolData();
		Integer code = (Integer) taskPoolMap.get("code");
		String message = (String) taskPoolMap.get("message");
		String data = (String) taskPoolMap.get("data");
		System.out.println(code + ", " + message + ", " + data);
	}

	@Test
	void testPost(){
		List<Map<String, Object>> mapList = taskPoolTestService.postTaskPoolData("wsw", "123456");
		for (Map<String, Object> map : mapList) {
			System.out.println(map);
		}
	}
}
