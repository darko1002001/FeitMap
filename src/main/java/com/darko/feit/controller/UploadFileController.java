package com.darko.feit.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.BindException;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.darko.feit.form.UploadItem;
import com.mortennobel.imagescaling.ResampleFilters;
import com.mortennobel.imagescaling.ResampleOp;

@Controller
@RequestMapping(value = "/uploadfile")
public class UploadFileController {
	private static final String IMAGE_DIRECTORY = "D:\\FeitMapImage\\";
	private String uploadFolderPath;
	ServletConfig config;

	public String getUploadFolderPath() {
		return uploadFolderPath;
	}

	public void setUploadFolderPath(String uploadFolderPath) {
		this.uploadFolderPath = uploadFolderPath;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getUploadForm(Model model) {
		model.addAttribute(new UploadItem());
		return "uploadfile";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(UploadItem uploadItem, HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors,
			HttpSession session) {
		try {

			MultipartFile multipartFile = uploadItem.getFileData();
			InputStream inputStream = null;
			OutputStream outputStream = null;
			if (multipartFile.getSize() > 0) {
				inputStream = multipartFile.getInputStream();
				BufferedImage bufferedImage = ImageIO.read(inputStream);
				while (bufferedImage.getWidth() > 320
						&& bufferedImage.getHeight() > 320) {
					ResampleOp resampleOp = new ResampleOp(bufferedImage.getWidth()/2, bufferedImage.getHeight()/2);
					resampleOp.setFilter(ResampleFilters.getLanczos3Filter());
					bufferedImage = resampleOp.filter(bufferedImage, null);
				}
				// File realUpload = new File("C:/");
				outputStream = new FileOutputStream(new File(IMAGE_DIRECTORY,multipartFile.getOriginalFilename()));
				ImageIO.write(
						bufferedImage,
						multipartFile.getOriginalFilename().substring(
								multipartFile.getOriginalFilename().lastIndexOf('.')+1,
								multipartFile.getOriginalFilename().length()),
						outputStream);
				outputStream.close();
				inputStream.close();
				/*session.setAttribute("uploadFile", IMAGE_DIRECTORY
						+ filea.getOriginalFilename());*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/public/homepage";
	}
	
	@RequestMapping(value="/getImage")
	public void getImage(HttpServletResponse response){
		
		File imageFile = null;
		File dir = new File(IMAGE_DIRECTORY);
		String[] children = dir.list();
		if (children == null) {
		    // Either dir does not exist or is not a directory
		} else {
		    for (int i=0; i<children.length; i++) {
		        // Get filename of file or directory
		    	System.out.println(children[i]);
		    	imageFile = new File(dir,children[i]);
		      //  String filename = children[i];
		    }
		}
		
		try {

			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(imageFile));
			int c;
			while ((c = bis.read()) > -1) {
				response.getOutputStream().write(c);
			}
			bis.close();
			return;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
