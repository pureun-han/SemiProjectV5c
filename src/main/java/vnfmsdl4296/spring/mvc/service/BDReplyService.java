package vnfmsdl4296.spring.mvc.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vnfmsdl4296.spring.mvc.dao.BDReplyDAO;
import vnfmsdl4296.spring.mvc.vo.ReplyVO;

@Service("brsrv")
public class BDReplyService {

    private BDReplyDAO brdao;

    @Autowired
    public BDReplyService(BDReplyDAO brdao){
        this.brdao = brdao;
    }

    // 댓글쓰기
    public void makeReply(ReplyVO rvo) {
        brdao.insertReply(rvo);
    }
}
