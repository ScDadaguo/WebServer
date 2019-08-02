package com.guohao.webserver.core.context.holder;

import com.guohao.webserver.core.filter.Filter;
import lombok.Data;

/**
 * @author guohao
 * @date 2019/7/31
 */
@Data
public class FilterHolder {
    private Filter filter;
    private String filterClass;

    public FilterHolder(String filterClass) {
        this.filterClass = filterClass;
    }
}
