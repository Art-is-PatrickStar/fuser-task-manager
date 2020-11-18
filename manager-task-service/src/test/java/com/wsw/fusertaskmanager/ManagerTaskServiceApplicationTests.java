package com.wsw.fusertaskmanager;

import com.wsw.fusertaskmanager.service.TaskPoolTestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
		if (code == 200){
			System.out.println(data);
		}else {
			System.out.println(message + ", " + data);
		}

	}

}
