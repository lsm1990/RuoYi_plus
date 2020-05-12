package com.ruoyi.cms.service;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Queues;
import com.ruoyi.cms.domain.Pv;
import com.ruoyi.common.utils.IpUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.useragent.UserAgentUtils;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.DeviceType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Component("pvQueueService")
public class PVQueueService implements InitializingBean {

    private static final Logger logger = LogManager.getLogger(PVQueueService.class);

    @Autowired
    IPvService pvService;

    //创建一个可重用固定线程数的线程池
    private ExecutorService pool = Executors.newFixedThreadPool(1);

    private static final BlockingQueue<Pv> blockingQueue = new ArrayBlockingQueue<Pv>(1000000);

    //线程活动
    private volatile boolean threadActivity = true;

    @Override
    public void afterPropertiesSet() throws Exception {
        pool.execute(new Runnable(){
            @Override
            public void run() {
                while (threadActivity) { //如果系统关闭，则不再运行
                    try {
                        List<Pv> data = new ArrayList<Pv>();
                        //每次到1000条数据才进行入库，或者等待1分钟，没达到100条也继续入库
                        Queues.drain(blockingQueue, data, 50, 1, TimeUnit.MINUTES);//第三个参数：数量; 第四个参数：时间; 第五个参数：时间单位
                        if(CollectionUtil.isNotEmpty(data)){
                            pvService.insertPvBatch(data);
                        }

                    } catch (InterruptedException e) {
                        //    e.printStackTrace();
                        if (logger.isErrorEnabled()) {
                            logger.error("访问量消费队列错误",e);
                        }
                    }
                }
            }});
    }

    public void pushPvQueue(HttpServletRequest request,Pv pv){

        pv.setIp(IpUtils.getIpAddr(request));
        if(StringUtils.isEmpty(pv.getReferer())){
            pv.setReferer(request.getHeader("referer"));
        }
        Browser browserObj = UserAgentUtils.getBrowser(request);
        String browser=browserObj.getName();//浏览器类型
        pv.setBrowser(browser);
        String deviceType="Unknown";//设备类型
        DeviceType deviceType1=UserAgentUtils.getDeviceType(request);//是否pc
        if(deviceType1!=null){
            deviceType=deviceType1.getName();
        }
        pv.setDeviceType(deviceType);

        //add(anObject):添加元素到队列里，添加成功返回true，容量满了添加失败会抛出IllegalStateException异常
        //offer(anObject):表示如果可能的话,将anObject加到BlockingQueue里,即如果BlockingQueue可以容纳,则返回true,否则返回false.（本方法不阻塞当前执行方法的线程）
        //offer(E o, long timeout, TimeUnit unit),可以设定等待的时间，如果在指定的时间内，还不能往队列中加入BlockingQueue，则返回失败。
        //put(anObject):把anObject加到BlockingQueue里,如果BlockQueue没有空间,则调用此方法的线程被阻断直到BlockingQueue里面有空间再继续.
        blockingQueue.offer(pv);//添加一个元素并返回true 如果队列已满，则返回false
    }
    @PreDestroy
    public void destroy() {
        threadActivity = false;
        pool.shutdownNow();
    }
}
