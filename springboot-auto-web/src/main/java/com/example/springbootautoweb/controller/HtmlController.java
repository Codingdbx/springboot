package com.example.springbootautoweb.controller;


import com.example.springbootautoweb.entity.PageSet;
import com.example.springbootautoweb.service.PageSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/3/9 17:17
 * @since JDK1.8
 */
@RestController
public class HtmlController {

    private final PageSetService pageSetService;

    private final HttpServletRequest request;

    private final HttpServletResponse response;

    @Autowired
    public HtmlController(PageSetService pageSetService, HttpServletRequest request, HttpServletResponse response) {
        this.pageSetService = pageSetService;
        this.request = request;
        this.response = response;
    }

    @RequestMapping("/index.html")
    public String index() {
        StringBuilder sb = new StringBuilder();

        sb.append("<!DOCTYPE html>");
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>auto-web</title>");
        sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
        //样式内容
        sb.append("<style type=\"text/css\">");

        sb.append("</style>");
        sb.append("</head>");

        //主体部分
        sb.append("<body>");

        sb.append("<form action=\"/file/fileUpLoad\" method=\"post\" enctype=\"multipart/form-data\">");
        sb.append("选择文件: ");
        sb.append("<input type=\"file\" name=\"upload\">");
        sb.append("<input type=\"submit\" value=\"上传\">");
        sb.append("</form>");


        sb.append("<div style = \"margin-top:100px\">");
        sb.append("<table width=\"50%\" style=\"word-break:break-all\" border=\"0\" cellpadding=\"5\" cellspacing=\"5\">");
        sb.append("<tbody>");

        sb.append("<tr>");

        sb.append("<td valign=\"top\" width=\"15%\">");
        sb.append("<p style=\"background-color: #f4f4f4;\">");
        sb.append("页面名称");
        sb.append("</p>");
        sb.append("</td>");
        sb.append("<td valign=\"top\" width=\"30%\">");
        sb.append("<p style=\"background-color: #f4f4f4;\">");
        sb.append("页面地址");
        sb.append("</p>");
        sb.append("</td>");
        sb.append("<td valign=\"top\" width=\"15%\">");
        sb.append("<p style=\"background-color: #f4f4f4;\">");
        sb.append("操作");
        sb.append("</p>");
        sb.append("</td>");

        sb.append("</tr>");

        List<PageSet> pageSetList = pageSetService.selectAll();
        for (PageSet pageSet : pageSetList) {

            sb.append("<tr>");
            sb.append("<td valign=\"top\" width=\"15%\">");
            sb.append(pageSet.getPageName());
            sb.append("</td>");
            sb.append("<td valign=\"top\" width=\"30%\">");
            sb.append(pageSet.getPageUrl());
            sb.append("</td>");
            sb.append("<td valign=\"top\" width=\"15%\">");
            sb.append("<button type=\"button\" onclick=\"javascript:window.open('/execute?pageId=" + pageSet.getPageId() + "');\">");
            sb.append("执行");
            sb.append("</button>");
            sb.append("</a>");
            sb.append("</td>");
            sb.append("</tr>");
        }


        sb.append("</tbody>");
        sb.append("</table>");

        sb.append("</body>");
        sb.append("</html>");

        return sb.toString();
    }

    private String getHost() {
        String scheme = request.getScheme();//协议
        String serverName = request.getServerName();//服务名
        int serverPort = request.getServerPort();//端口
        String contextPath = request.getContextPath();//上下文路径

        StringBuilder sb = new StringBuilder();
        sb.append(scheme).append("://").append(serverName).append(":").append(serverPort).append(contextPath).append("/");
        return sb.toString();
    }
}
