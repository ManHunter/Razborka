package com.razborka.controller;

import com.razborka.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Admin on 25.04.2015.
 */
@Controller
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private PhotoService photoService;

    @RequestMapping(value = "/get/{image_id}", method = RequestMethod.GET)
    public void get(HttpServletResponse response, HttpServletRequest request, @PathVariable int image_id){
        String filename = photoService.getPhotoById(image_id).getPicture();
        //File file = new File(request.getServletContext().getRealPath("/") + "resources\\image\\part" + filename);
        try {
            FileInputStream inputStream = new FileInputStream(request.getServletContext().getRealPath("/") + "resources\\image\\part\\" + filename);
            response.setContentType("image/jpg");
            response.setHeader("Content-disposition", "attachment; filename=\""+filename+"\"");
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


//    @RequestMapping(method = RequestMethod.GET)
//    public String index(ModelMap model) {
//        model.put("photos", new MultipleFileUpload());
//        return "form2";
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public String index(@ModelAttribute(value = "photos") @Valid MultipleFileUpload photos,
//                        BindingResult bindingResult, ModelMap modelMap,
//                        HttpServletRequest request) {
////        MultipleFileUploadValidator multipleFileUploadValidator = new MultipleFileUploadValidator();
////        multipleFileUploadValidator.validate(photos, bindingResult);
////        if(bindingResult.hasErrors()) {
////            //System.out.println("ERROR");
////            return "form2";
////        } else {
//            modelMap.put("listPhotos", UploadFileHelper.multipleFileUpload(
//                    photos.getFiles(), request, true, "images"
//            ));
//            return "success";
//        //}
//    }


//    @Autowired
//    private PhotoService photoService;
//
//    LinkedList<FileMeta> files = new LinkedList<FileMeta>();
//    FileMeta fileMeta = null;
//
//
//    @RequestMapping(value="/upload", method = RequestMethod.POST)
//    public @ResponseBody LinkedList<FileMeta> upload(MultipartHttpServletRequest request, HttpServletResponse response) {
//
//        //1. build an iterator
//        Iterator<String> itr =  request.getFileNames();
//        MultipartFile mpf = null;
//
//        //2. get each file
//        while(itr.hasNext()){
//
//            //2.1 get next MultipartFile
//            mpf = request.getFile(itr.next());
//            System.out.println(mpf.getOriginalFilename() +" uploaded! "+files.size());
//
//            //2.2 if files > 10 remove the first from the list
//            if(files.size() >= 10)
//                files.pop();
//
//            //2.3 create new fileMeta
//            fileMeta = new FileMeta();
//            fileMeta.setFileName(mpf.getOriginalFilename());
//            fileMeta.setFileSize(mpf.getSize()/1024+" Kb");
//            fileMeta.setFileType(mpf.getContentType());
//
//            try {
//                fileMeta.setBytes(mpf.getBytes());
//
//                // copy file to local disk (make sure the path "e.g. D:/temp/files" exists)
//                FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream("E:/files/" + mpf.getOriginalFilename()));
//
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            //2.4 add to files
//            files.add(fileMeta);
//        }
//        // result will be like this
//        // [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
//        return files;
//    }
//
//    @RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
//    public void get(HttpServletResponse response,@PathVariable String value){
//        FileMeta getFile = files.get(Integer.parseInt(value));
//        try {
//            response.setContentType(getFile.getFileType());
//            response.setHeader("Content-disposition", "attachment; filename=\""+getFile.getFileName()+"\"");
//            FileCopyUtils.copy(getFile.getBytes(), response.getOutputStream());
//        }catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
}