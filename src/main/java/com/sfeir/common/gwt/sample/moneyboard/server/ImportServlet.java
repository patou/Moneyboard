package com.sfeir.common.gwt.sample.moneyboard.server;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.google.common.base.Strings;
import com.sfeir.common.gwt.sample.moneyboard.server.file.AppEngineFileItemFactory;
import com.sfeir.common.gwt.sample.moneyboard.shared.beans.User;
import com.sfeir.common.gwt.shared.exceptions.NotAccessAllowedException;

public class ImportServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1666037506773944641L;

	private TransactionsServiceImpl service = new TransactionsServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (ServletFileUpload.isMultipartContent(req)) {
			ServletFileUpload servletFileUpload = new ServletFileUpload(new AppEngineFileItemFactory());
			try {
				List<FileItem> fileItems = servletFileUpload.parseRequest(req);
				String account = null;
				byte[] contenu = null;
				for (FileItem fileItem : fileItems) {
					// Le champs contenant le fichier
					if (!fileItem.isFormField()) {
						contenu = IOUtils.toByteArray(fileItem.getInputStream());
					}
					// Le champ titre
					else {
						account = fileItem.getString();
					}
				}
				if (Strings.isNullOrEmpty(account)) {
					resp.getWriter().println("KO");
					return;
				}
				HttpSession session = req.getSession();
				if (session == null)
					throw new NotAccessAllowedException();
				User user = (User) session.getAttribute("context");
				if (user == null)
					throw new NotAccessAllowedException();
				service.importOperations(account, user);
			} catch (NotAccessAllowedException e) {
				e.printStackTrace();
				resp.sendError(HttpServletResponse.SC_FORBIDDEN, e.getLocalizedMessage());
				
			} catch (Exception e) {
				e.printStackTrace();
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
			}
			return;
		}
		super.doPost(req, resp);
	}
}
