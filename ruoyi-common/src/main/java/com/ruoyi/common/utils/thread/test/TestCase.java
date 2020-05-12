package com.ruoyi.common.utils.thread.test;



import com.ruoyi.common.utils.thread.MultiThreadHandler;
import com.ruoyi.common.utils.thread.exception.ChildThreadException;
import com.ruoyi.common.utils.thread.parallel.MultiParallelThreadHandler;

import java.util.HashMap;
import java.util.Map;


public class TestCase implements Runnable {

	private String name;
	private Map<String, Object> result;
	
	public TestCase(String name, Map<String, Object> result) {
		this.name = name;
		this.result = result;
	}
	
	@Override
	public void run() {
		// 模拟线程执行1000ms
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 模拟线程1和线程3抛出异常
		if(name.equals("1") || name.equals("3")) {
			throw new RuntimeException(name + ": throw exception");
		}
		Map<String, Object> t=(Map<String, Object>)result.get(name);
		if(t==null){
			t=new HashMap<String, Object>();
			t.put("msg", "成功!");
			t.put("result", "success");
			result.put(name,t);
		}

	}
	
	public static void main(String[] args) {
		
		System.out.println("main begin \t=================");
		Map<String, Object> resultMap = new HashMap<String, Object>(8, 1);
		MultiThreadHandler handler = new MultiParallelThreadHandler();
//		ExecutorService service = Executors.newFixedThreadPool(3);
//		MultiThreadHandler handler = new ParallelTaskWithThreadPool(service);
		TestCase task = null;
		// 启动5个子线程作为要处理的并行任务，共同完成结果集resultMap
		for(int i=1; i<=5 ; i++){
			task = new TestCase("" + i, resultMap);
			handler.addTask(task);
		}
		try {
			handler.run();
		} catch (ChildThreadException e) {
			System.out.println(e.getAllStackTraceMessage());
		}
		
		System.out.println(resultMap);
//		service.shutdown();
		System.out.println("main end \t=================");
	}
}