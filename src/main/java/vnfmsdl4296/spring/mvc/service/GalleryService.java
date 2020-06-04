package vnfmsdl4296.spring.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vnfmsdl4296.spring.mvc.dao.BoardDAO;
import vnfmsdl4296.spring.mvc.dao.GalleryDAO;
import vnfmsdl4296.spring.mvc.vo.BoardVO;
import vnfmsdl4296.spring.mvc.vo.GalleryVO;

import java.util.ArrayList;
import java.util.List;

@Service("gsrv")
public class GalleryService {

    private GalleryDAO gdao;
    private ImgUploadUtil imgutil;

    // 새 갤러리 쓰기
    @Autowired
    public GalleryService(GalleryDAO gdao, ImgUploadUtil imgutil) {
        this.gdao = gdao;
        this.imgutil = imgutil;}

    public void newGallery(GalleryVO gvo, MultipartFile[] img) {

        // 업로드 이미지 처리
        // 첨부파일이 존재하는 경우
        if (imgutil.checkGalleryFiles(img)) {
            List<String> fnames = new ArrayList<>();
            for (MultipartFile f : img) {
                // 첨부 파일이 존재한다면 서버에 저장하고
                // 결과로 파일이름을 받아서 배열에 저장함
                if (!f.getOriginalFilename().isEmpty())
                    fnames.add(imgutil.ImgUpload(f));
                else
                    fnames.add(null);
            }


            gvo.setFname1(fnames.get(0));
            gvo.setFname2(fnames.get(1));
            gvo.setFname3(fnames.get(2));
        }

        // 테이블에 갤러리관련 정보 저장
        String id = gdao.insertGallery(gvo);

        // 썸네일 이미지 생성
        imgutil.imageCropResize(gvo.getFname1(), id);



    }
//
//    public ArrayList<BoardVO> showBoard(String cp) {
//        int snum = (Integer.parseInt(cp) -1) * 10;
//
//        return (ArrayList<BoardVO>)gdao.selectGallery(snum);
//    }
//
//    public BoardVO showOneBoard(String bno) {
//        return bdao.selectOneBoard(bno);
//    }
//
//    public int countBoard() {
//        return bdao.selectCountBoard();
//    }
//
//    // 특정 게시글 삭제하기
//    public void removeBoard(String bno) {
//        bdao.deleteBoard(bno);
//    }
}
