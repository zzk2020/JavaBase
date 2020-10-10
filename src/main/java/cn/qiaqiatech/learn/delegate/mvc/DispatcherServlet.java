package cn.qiaqiatech.learn.delegate.mvc;

import cn.qiaqiatech.learn.delegate.mvc.controllers.MemberController;
import cn.qiaqiatech.learn.delegate.mvc.controllers.OrderController;
import cn.qiaqiatech.learn.delegate.mvc.controllers.SystemController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zzk
 */
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDispatch(req, resp);
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uri = req.getRequestURI();
        String id = req.getParameter("id");

        if("getMemberById".equals(uri)) {
            new MemberController().getMemberById(id);
        } else if("getOrderById".equals(uri)) {
            new OrderController().getOrderById(id);
        } else if("logout".equals(uri)) {
            new SystemController().logout();
        } else {
            resp.getWriter().write("404 Not Found!!!");
        }
    }
}
