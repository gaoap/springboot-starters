package com.gaoap.learning.multipleds;

import com.gaoap.learning.multipleds.annotation.CurdataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DataSourceAspect implements Ordered {
    @Pointcut("@annotation(run.halo.app.annotation.CurdataSource)")
    public void dataSourcePointCut() {

    }

    @Before("dataSourcePointCut()")
    public void doBefore(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        CurdataSource ds = method.getAnnotation(CurdataSource.class);
        if (ds == null) {
            DBContexHolder.setDataSource(DataSourceNames.FIRST);
        } else {
            DBContexHolder.setDataSource(ds.name());
        }


    }

    @After("dataSourcePointCut()")
    public void after(JoinPoint point) {
        DBContexHolder.clearDataSource();
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
