package com.mfg.page;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;


public class Page extends SimpleTagSupport implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer current;
    private Long count;
    private Integer pageCount;
    private String path;
    private String param;
    private boolean notQueryCount = false;

    public Page() {
        this.current = 1; 
        this.count = 0L; 
        this.pageCount = 10;
    }

    @Override
    public void doTag() throws JspException, IOException {
        int pageSize = (int) (this.count / this.pageCount + (this.count % this.pageCount > 0 ? 1 : 0));
        JspWriter out = this.getJspContext().getOut();
        StringBuffer sb = new StringBuffer();
        sb.append("<div class=\"page\">");
        sb.append("<ul>");
        if (this.current == 1) {
            sb.append("<li class=\"disabled\">首页</li><li class=\"disabled\">上一页</li>");
        } else {
            sb.append("<li><a href=\"");
            sb.append(this.path);
            sb.append("?current=");
            sb.append(1);
            if (this.param != null && !"".equals(this.param)) {
                sb.append("&");
                sb.append(this.param);
            }
            sb.append("\">首页</a></li>");
            sb.append("<li><a href=\"");
            sb.append(this.path);
            sb.append("?current=");
            sb.append(this.current - 1);
            if (this.param != null && !"".equals(this.param)) {
                sb.append("&");
                sb.append(this.param);
            }
            sb.append("\">上一页</a></li>");
        }
        if (pageSize <= 10) {
            for (int i = 1; i <= pageSize; i++) {
                if (i == this.current) {
                    sb.append("<li class=\"current\">");
                    sb.append(i);
                    sb.append("</li>");
                } else {
                    sb.append("<li><a href=\"");
                    sb.append(this.path);
                    sb.append("?current=");
                    sb.append(i);
                    if (this.param != null && !"".equals(this.param)) {
                        sb.append("&");
                        sb.append(this.param);
                    }
                    sb.append("\">");
                    sb.append(i);
                    sb.append("</a></li>");
                }
            }
        } else {
            int index = 1;
            if (this.current > 4) {
                if (this.current + 4 >= pageSize) {
                    for (int j = pageSize - 9; j <= pageSize; j++) {
                        if (j == this.current) {
                            sb.append("<li class=\"current\">");
                            sb.append(j);
                            sb.append("</li>");
                        } else {
                            sb.append("<li><a href=\"");
                            sb.append(this.path);
                            sb.append("?current=");
                            sb.append(j);
                            if (this.param != null && !"".equals(this.param)) {
                                sb.append("&");
                                sb.append(this.param);
                            }
                            sb.append("\">");
                            sb.append(j);
                            sb.append("</a></li>");
                        }
                    }
                } else {
                    for (int j = this.current - 4; j <= pageSize; j++) {
                        if (j == this.current) {
                            sb.append("<li class=\"current\">");
                            sb.append(j);
                            sb.append("</li>");
                        } else {
                            sb.append("<li><a href=\"");
                            sb.append(this.path);
                            sb.append("?current=");
                            sb.append(j);
                            if (this.param != null && !"".equals(this.param)) {
                                sb.append("&");
                                sb.append(this.param);
                            }
                            sb.append("\">");
                            sb.append(j);
                            sb.append("</a></li>");
                        }
                        index++;
                        if (index > 10) {
                            break;
                        }
                    }
                }
            } else {
                for (int i = 1; i <= pageSize; i++) {
                    if (i == this.current) {
                        sb.append("<li class=\"current\">");
                        sb.append(i);
                        sb.append("</li>");
                    } else {
                        sb.append("<li><a href=\"");
                        sb.append(this.path);
                        sb.append("?current=");
                        sb.append(i);
                        if (this.param != null && !"".equals(this.param)) {
                            sb.append("&");
                            sb.append(this.param);
                        }
                        sb.append("\">");
                        sb.append(i);
                        sb.append("</a></li>");
                    }
                    index++;
                    if (index > 10) {
                        break;
                    }
                }
            }
        }
        
        if (this.current.equals(pageSize) || this.count == 0) {
            sb.append("<li class=\"disabled\">下一页</li><li class=\"disabled\">末页</li>");
        } else {
            sb.append("<li><a href=\"");
            sb.append(this.path);
            sb.append("?current=");
            sb.append(this.current + 1);
            if (this.param != null && !"".equals(this.param)) {
                sb.append("&");
                sb.append(this.param);
            }
            sb.append("\">下一页</a></li>");
            sb.append("<li><a  href=\"");
            sb.append(this.path);
            sb.append("?current=");
            sb.append(pageSize);
            if (this.param != null && !"".equals(this.param)) {
                sb.append("&");
                sb.append(this.param);
            }
            sb.append("\">末页</a></li>");
        }
        sb.append("</ul>");
        sb.append("</div>");
        out.print(sb);
    }

    public Long getCount() {
        return this.count;
    }

    /**
     * 璁剧疆鎬昏褰曟潯鏁�
     *
     * @param count
     */
    public void setCount(Long count) {
        this.count = count;
    }

    /**
     * 鑾峰彇褰撳墠绗嚑椤�
     *
     * @return
     */
    public Integer getCurrent() {
        return this.current;
    }

    /**
     * 璁剧疆褰撳墠绗嚑椤�
     *
     * @param current
     */
    public void setCurrent(Integer current) {
        try {
            if (current <= 0) {
                this.current = 1;
            } else {
                this.current = current;
            }
        } catch (Exception e) {
            this.current = 1;
        }
    }

   
    public Integer getPageCount() {
        return this.pageCount;
    }

    
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    
    public String getPath() {
        return path;
    }

    
    public void setPath(String path) {
        this.path = path;
    }

   
    public String getParam() {
        return param;
    }

    
    public void setParam(String param) {
        String[] x = param.split("&");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < x.length; i++) {
            String[] y = x[i].split("=");
            if (y.length > 1 && !"".equals(y[1].trim())) {
                sb.append(x[i]);
                sb.append("&");
            }
        }
        this.param = sb.toString().substring(0, sb.toString().lastIndexOf("&"));
    }

   
    public int getPages() {
        if (this.count % this.pageCount == 0) {
            return (int) (this.count / this.pageCount);
        } else {
            return (int) (this.count / this.pageCount + 1);
        }
    }

    
    public boolean firstEnable() {
        return previoEnable();
    }

    
    public boolean lastEnable() {
        return nextEnable();
    }

    /**
     * 鏄惁鏈変笅涓�椤�
     *
     * @return
     */
    public boolean nextEnable() {
        return this.current * this.pageCount < this.count;
    }

   
    public boolean previoEnable() {
        return this.current > 1;
    }

    public boolean isNotQueryCount() {
        return this.notQueryCount;
    }

    public void setNotQueryCount(boolean notQueryCount) {
        this.notQueryCount = notQueryCount;
    }

    
    public int getStartOfPage(long pageNo, long pageSize) {
        return (int) ((pageNo - 1) * pageSize);
    }
}