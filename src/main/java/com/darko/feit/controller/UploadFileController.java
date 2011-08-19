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
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.darko.feit.form.Image;
import com.darko.feit.form.UploadItem;
import com.darko.feit.service.ImagesService;
import com.darko.feit.util.Constants;
import com.mortennobel.imagescaling.ResampleFilters;
import com.mortennobel.imagescaling.ResampleOp;

@Controller
@RequestMapping(value = "/uploadfile")
public class UploadFileController {
	
	private String uploadFolderPath;
	ServletConfig config;

	@Autowired
	private ImagesService imageService;
	
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
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss_");
				String path = sdf.format(calendar.getTime())+multipartFile.getOriginalFilename();
				
				System.out.println(path);
				System.out.println(multipartFile.getContentType());
				File file = new File(Constants.IMAGE_DIRECTORY,path);
				outputStream = new FileOutputStream(file);
				ImageIO.write(
						bufferedImage,
						multipartFile.getOriginalFilename().substring(
								multipartFile.getOriginalFilename().lastIndexOf('.')+1,
								multipartFile.getOriginalFilename().length()),
						outputStream);
				outputStream.close();
				inputStream.close();
				
				Image image = new Image();
				image.setPath(path);
				image.setContentType(multipartFile.getContentType());
				image.setSize(multipartFile.getSize());
				image.setFilename(multipartFile.getOriginalFilename());
				
				Integer addImageId = imageService.addImage(image);
				
				System.out.println("Uploaded Image ID" + String.valueOf(addImageId));
				/*session.setAttribute("uploadFile", IMAGE_DIRECTORY
						+ filea.getOriginalFilename());*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/public/uploadfile";
	}
	
	
}
