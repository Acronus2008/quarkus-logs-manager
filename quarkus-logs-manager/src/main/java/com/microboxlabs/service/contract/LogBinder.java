package com.microboxlabs.service.contract;

import com.microboxlabs.service.datasource.domain.Log;
import com.microboxlabs.util.DateUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.regex.Matcher;

@Mapper(imports = {
        DateUtil.class,
        LocalDateTime.class
})
public interface LogBinder {
    LogBinder LOG_BINDER = Mappers.getMapper(LogBinder.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "timestamp", expression = "java(DateUtil.parseLogDateTime.apply(source.group(1)))")
    @Mapping(target = "logLevel", expression = "java(source.group(2))")
    @Mapping(target = "serviceName", expression = "java(source.group(3))")
    @Mapping(target = "message", expression = "java(source.group(4))")
    Log bindLog(Matcher source);

}
