package com.ruayou.pipeline;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.SchedulerContext;
import com.ruayou.entity.FindBookInfoUrl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Author：ruayou
 * @Date：2024/8/24 11:51
 * @Filename：FindBookInfoUrlPipeline
 */
@PipelineName(value="findBookInfo")
public class FindBookInfoUrlPipeline implements Pipeline<FindBookInfoUrl> {
    private static Log log = LogFactory.getLog(FindBookInfoUrlPipeline.class);
    @Override
    public void process(FindBookInfoUrl bean) {
        log.info(bean);
        HttpRequest request = bean.getRequest();
        if (bean.getNextPage()!=null) {
            SchedulerContext.into(request.subRequest(bean.getNextPage()));
        }
        for (String url : bean.getBookInfoUrl()) {
            SchedulerContext.into(request.subRequest(url));
        }
    }
}
