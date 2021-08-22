package me.studybook.api.service;

import org.imgscalr.Scalr;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.UUID;

public class ImageFileService {

//    static String filePath = "C:\\tmp\\"; // 개발
    static String filePath = "/home/ubuntu/apps/studybook/upload/"; // 배포

    public static String[] upload(MultipartFile file) throws Exception {
        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        System.out.println(ext);
        UUID uuid = UUID.randomUUID();
        String fileName = uuid.toString().replaceAll("-",  "") + ext;
        String previewFileName = uuid.toString().replaceAll("-",  "")+"_preview" + ext;
        System.out.println(fileName);

        File _file = new File(filePath + fileName);
        //첫 번째 방법, MultipatrFile클래스의 transferTo()를 사용하여 업로드하려는 파일을 특정파일로 저장
        //MultipartFile.transferTo(File file) - Byte형태의 데이터를 File객체에 설정한 파일 경로에 전송한다.
        file.transferTo(_file);

        BufferedImage originImg = ImageIO.read(_file);
        System.out.println(originImg.getWidth());
        System.out.println(originImg.getHeight());

        int dw = 100, dh = 100;
        // 원본 이미지의 너비와 높이 입니다.
        int ow = originImg.getWidth();
        int oh = originImg.getHeight();
        // 원본 너비를 기준으로 하여 썸네일의 비율로 높이를 계산합니다.
        int nw = ow; int nh = (ow * dh) / dw;
        // 계산된 높이가 원본보다 높다면 crop이 안되므로
        // 원본 높이를 기준으로 썸네일의 비율로 너비를 계산합니다.
        if(nh > oh) {
            nw = (oh * dw) / dh; nh = oh;
        }
        // 계산된 크기로 원본이미지를 가운데에서 crop 합니다.
        BufferedImage cropImg = Scalr.crop(originImg, (ow-nw)/2, (oh-nh)/2, nw, nh);
        // crop된 이미지로 썸네일을 생성합니다.

        BufferedImage resizeImg = Scalr.resize(cropImg, dw, dh);
        System.out.println(resizeImg.getWidth());

        ImageIO.write(resizeImg, "jpg", new File(filePath+previewFileName));

        String[] images = {fileName, previewFileName};
        return images;
    }

    public static void deletes(File profile, File profilePreview) {
        if( profile.exists() ){
            if(profile.delete()){
                System.out.println("파일삭제 성공");
            }else{
                System.out.println("파일삭제 실패");
            }
        }else{
            System.out.println("파일이 존재하지 않습니다.");
        }

        if( profilePreview.exists() ){
            if(profilePreview.delete()){
                System.out.println("파일삭제 성공");
            }else{
                System.out.println("파일삭제 실패");
            }
        }else{
            System.out.println("파일이 존재하지 않습니다.");
        }
    }
}
