package com.uyghur.tp;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.RequestAware;

import java.util.Map;

/**
 * Created by AUser on 2015-12-21 021.
 */
public class Adiljan extends ActionSupport implements RequestAware {

    private Map<String, Object> requestMap;

    public String index() throws Exception {
        System.out.println("index...");
//        ActionContext.getContext().put("name","hap");
        requestMap.put("name", "阿迪力江。亚森");
        return SUCCESS;
    }

    @Override
    public void setRequest(Map<String, Object> map) {
        this.requestMap = map;
    }

    public String getName() {
        return "Adiljan";
    }
}
