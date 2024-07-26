package com.nsd.rsa_encryption.apis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.html2pdf.HtmlConverter;

@RestController
@RequestMapping("/pdfFile")
public class Pdf {

	@GetMapping("/pdf")
	public ResponseEntity<ByteArrayResource> pdfGen() throws IOException {

		ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();

		HtmlConverter.convertToPdf(new ByteArrayInputStream(getPDF().getBytes()), pdfOutputStream);
		byte[] pdfBytes = pdfOutputStream.toByteArray();
		String fileName = "testing" + ".pdf";

		ByteArrayResource resource = new ByteArrayResource(pdfBytes);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("attachment", fileName);

		return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}

	@GetMapping("/test")
	public String test() {
		return getPDF();
	}

	public static String getPDF() {

		StringBuilder htmlBuilder = new StringBuilder();
		htmlBuilder.append("<!DOCTYPE html>\n");
		htmlBuilder.append("<html lang=\"en\">\n");
		htmlBuilder.append(
				"<meta http-equiv=Content-Type content=\"text/html;charset=utf-8\"> <meta content=\"IE=edge,chrome=1\" http-equiv=X-UA-Compatible> <meta name=viewport content=\"width=device-width, initial-scale=1\">\n");
		htmlBuilder.append("\n<head>\n");
		htmlBuilder.append("    <meta charset=\"UTF-8\">\n");
		htmlBuilder.append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
		htmlBuilder.append(
				"<link href=\"https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap\" rel=\"stylesheet\">\n");
		htmlBuilder.append(
				"<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css\">\n");
		htmlBuilder.append("    <title>Document</title>\n");
		htmlBuilder.append("</head>\n");

		htmlBuilder.append("\n<body style=\"font-family: 'Poppins', sans-serif;\">\n");

		htmlBuilder.append("<p>Click the link below to view the image:</p>\n");
		htmlBuilder.append("<a href=\"data:image/png;base64,").append(Data.base64File)
				.append("\" target=\"_blank\">View Image</a>\n");
		htmlBuilder.append("</body>\n");
		htmlBuilder.append("</html>\n");
		return htmlBuilder.toString();

	}
}
