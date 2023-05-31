package com.zerobase.noticeboard.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.Iterator;
import java.util.Map;


@Service
public class FileService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void uploadFile(MultipartHttpServletRequest multiRequest) throws Exception {
        Map<String, MultipartFile> files = multiRequest.getFileMap();

        Iterator<Map.Entry<String, MultipartFile>> iterator = files.entrySet().iterator();

        MultipartFile mFile;

        String filePath = "C:\\Users\\apple\\Downloads\\";

        String saveFileName = "";

        while (iterator.hasNext()) {
            Map.Entry<String, MultipartFile> entry = iterator.next();

            mFile = entry.getValue();

            String fileName = mFile.getOriginalFilename();

            String fileCutName = fileName.substring(0, fileName.lastIndexOf("."));

            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);

            String saveFilePath = filePath + File.separator + fileName;

            File fileFolder = new File(filePath);
            if (!fileFolder.exists()) {
                if (fileFolder.mkdirs()) {
                    logger.info("[file.mkdirs] : Success");
                } else {
                    logger.error("[file.mkdirs] : Fail");
                }
            }

            File saveFile = new File(saveFilePath);
            if (saveFile.isFile()) {
                boolean exist = true;
                int index = 0;
                while (exist) {
                    index++;
                    saveFileName = fileCutName + "(" + index + ")." + fileExt;
                    String dictFile = filePath + File.separator + saveFileName;
                    exist = new File(dictFile).isFile();
                    if (!exist) {
                        saveFilePath = dictFile;
                    }
                }
                mFile.transferTo(new File(saveFilePath));
            } else {
                mFile.transferTo(saveFile);
            }
        }
    }
}
