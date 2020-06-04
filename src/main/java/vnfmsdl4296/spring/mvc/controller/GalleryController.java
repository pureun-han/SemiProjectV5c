package vnfmsdl4296.spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import vnfmsdl4296.spring.mvc.service.BoardService;
import vnfmsdl4296.spring.mvc.service.GalleryService;
import vnfmsdl4296.spring.mvc.vo.BoardVO;
import vnfmsdl4296.spring.mvc.vo.GalleryVO;

import java.util.ArrayList;

@Controller
public class GalleryController {

    private GalleryService gsrv;

    @Autowired
    public GalleryController(GalleryService gsrv) {
        this.gsrv = gsrv;
    }

    // 목록보기
    @RequestMapping(value = "/gallery/list")
    public ModelAndView list() {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("layout/layout"); // 뷰이름 지정
        mv.addObject("action", "../gallery/list.jsp");


        return mv;
    }

    // 새글쓰기
    @RequestMapping(value = "/gallery/write")
    public ModelAndView write() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("layout/layout"); // 뷰이름 지정

        mv.addObject("action", "../gallery/write.jsp");

        return mv;
    }

    // 새글쓰기
    // 여러 개의 이미지를 업로드하는 경우
    // 이미지 폼 이름은 모두 동일하게 설정함
    // 즉, MultipartFile img1, MultipartFile img2
    // MultipartFile img3 식으로 사용하는 것보다는
    // MultipartFile[] img 식으로 사용하는 것이 더 편하기 때문임
    @PostMapping(value = "/gallery/write")
    public String writeok(GalleryVO gvo, MultipartFile[] img) {

        gsrv.newGallery(gvo, img);

        return "redirect:/gallery/list";
    }

    // 본문보기
    @RequestMapping(value = "/gallery/view")
    public ModelAndView view() {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("layout/layout"); // 뷰이름 지정
        mv.addObject("action", "../gallery/view.jsp");


        return mv;
    }

//    // 수정하기
//    @RequestMapping(value = "/gallery/update")
//    public ModelAndView update() {
//
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("layout/layout"); // 뷰이름 지정
//
//        mv.addObject("action", "../gallery/modify.jsp");
//
//        return mv;
//    }
//
//    // 삭제하기
//    @RequestMapping(value = "/gallery/delete")
//    public String delete(String bno) {
//
//
//        return "redirect:/board/list?cp=1";
//    }

}
