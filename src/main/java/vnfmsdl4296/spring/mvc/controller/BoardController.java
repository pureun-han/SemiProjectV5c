package vnfmsdl4296.spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vnfmsdl4296.spring.mvc.service.BoardService;
import vnfmsdl4296.spring.mvc.service.GoogleCaptchaUtil;
import vnfmsdl4296.spring.mvc.vo.BoardVO;
import vnfmsdl4296.spring.mvc.vo.MemberVO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class BoardController {

    private BoardService bsrv;
    private GoogleCaptchaUtil gcutil;

    @Autowired
    public BoardController(BoardService bsrv, GoogleCaptchaUtil gcutil)
    {   this.bsrv = bsrv;
        this.gcutil = gcutil;}

    // 목록보기
    @RequestMapping(value = "/board/list")
    public ModelAndView list(String cp) {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("layout/layout"); // 뷰이름 지정
        mv.addObject("action", "../board/list.jsp");

        // 목록 불러오기
        ArrayList<BoardVO> bdlist = bsrv.showBoard(cp);
        mv.addObject("bdlist", bdlist);

        // 총 게시물 수 불러오기
        int bdcnt = bsrv.countBoard();
        mv.addObject("bdcnt", bdcnt);

        return mv;
    }

    // 새글쓰기
    @RequestMapping(value = "/board/write")
    public ModelAndView write() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("layout/layout"); // 뷰이름 지정

        mv.addObject("action", "../board/write.jsp");

        return mv;


    }




    // 새글쓰기
    @RequestMapping(value = "/board/write", method = RequestMethod.POST)
    public String writeok(BoardVO bd,
                          HttpServletRequest req,
                          RedirectAttributes rda) {
        String returnPage = "redirect:/board/write";
        String gCaptcha = req.getParameter("g-recaptcha");

        if (gcutil.checkCaptcha(gCaptcha)) {
            bsrv.newBoard(bd);
            returnPage = "redirect:/board/list?cp=1";
        }   else {
            rda.addFlashAttribute("checkFail", "자동가입방지 확인 실패!");
        }
        return returnPage;
    }

    // 본문보기
    @RequestMapping(value = "/board/view")
    public ModelAndView view(String bno) {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("layout/layout"); // 뷰이름 지정
        mv.addObject("action", "../board/view.jsp");

        BoardVO b = bsrv.showOneBoard(bno);
        mv.addObject("b", b);

        return mv;
    }

    // 수정하기
    @RequestMapping(value = "/board/update")
    public ModelAndView update() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("layout/layout"); // 뷰이름 지정

        mv.addObject("action", "../board/modify.jsp");

        return mv;
    }

    // 삭제하기
    @RequestMapping(value = "/board/delete")
    public String delete(String bno) {

        bsrv.removeBoard(bno);

        return "redirect:/board/list?cp=1";
    }

}
