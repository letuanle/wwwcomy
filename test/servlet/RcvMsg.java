package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jivesoftware.smack.packet.Message;

import test.lxn.utils.StringUtil;

public class RcvMsg extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String usrName = req.getParameter("username");
		if (StringUtil.isBlankOrNull(usrName))
			return;
		Object o = WebClient.getMap().get(usrName);
		if (o == null)
			return;
		synchronized (o) {
			try {
				o.wait(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Message msg = (Message) WebClient.getMsgMap().remove(usrName);
		// ((Message) WebClient.getMsgMap().get(usrName));
		if (msg == null)
			return;
		String sFrom = msg.getFrom();
		String sMsg = msg.getBody();
		resp.setCharacterEncoding("UTF-8");
		PrintWriter pw = resp.getWriter();
		pw.print(sFrom + ":" + sMsg);
		pw.flush();
		pw.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
}
