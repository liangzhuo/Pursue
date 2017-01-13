package com.liang.interceptor;

import org.hibernate.dialect.MySQL5Dialect;


public class MySQLDialectPPT extends MySQL5Dialect {  
  
    public String getLimitString(String queryString, int offset, int pageSize) {  
        StringBuffer buffer = new StringBuffer(queryString);  
        buffer.append(" limit ");  
        buffer.append(offset);  
        buffer.append(",");  
        buffer.append(pageSize);  
        return buffer.toString();  
    }  
}  
