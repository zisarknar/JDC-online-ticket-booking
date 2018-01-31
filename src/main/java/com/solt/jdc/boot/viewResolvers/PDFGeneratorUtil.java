package com.solt.jdc.boot.viewResolvers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Map;

@Component
public class PDFGeneratorUtil {
	@Autowired
	private TemplateEngine templateEngine;

	
	public byte[] createPdf(String templateName, Map map,HttpServletResponse response) throws Exception {
		Assert.notNull(templateName, "The templateName can not be null");
		Context ctx = new Context();
		if (map != null) {
			Iterator itMap = map.entrySet().iterator();
			while (itMap.hasNext()) {
				Map.Entry pair = (Map.Entry) itMap.next();
				ctx.setVariable(pair.getKey().toString(), pair.getValue());
			}
		}

		String processedHtml = templateEngine.process(templateName, ctx);
		FileOutputStream os = null;
		String fileName = "Result";
		
			/*final File outputFile = new File("D:\\" + fileName + ".pdf");
			os = new FileOutputStream(outputFile);*/
			
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(processedHtml);
			renderer.layout();
			renderer.createPDF(bos, false);
			renderer.finishPDF();
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename="+"ticket.pdf");
			System.out.println("PDF created successfully");
			return bos.toByteArray();
		
	}
}
