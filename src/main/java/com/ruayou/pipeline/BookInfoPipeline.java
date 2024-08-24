package com.ruayou.pipeline;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.ruayou.config.MyBatisUtils;
import com.ruayou.entity.BookInfo;
import com.ruayou.mapper.BookMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

/**
 * @Author：ruayou
 * @Date：2024/8/24 0:04
 * @Filename：BookInfoPipeline
 */
@PipelineName(value = "boobInfo")
public class BookInfoPipeline implements Pipeline<BookInfo> {
    private static Log log = LogFactory.getLog(BookInfoPipeline.class);

    @Override
    public void process(BookInfo bean) {
        bean.parseInfo();
        log.info(bean);
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        try {
            mapper.InsertBook(bean);
            sqlSession.commit();
        }catch (Exception e){
            log.error("异常回滚：{}",e.getCause());
            sqlSession.rollback();
        }finally {
            sqlSession.close();
        }
    }

}
