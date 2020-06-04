package vnfmsdl4296.spring.mvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import vnfmsdl4296.spring.mvc.vo.BoardVO;
import vnfmsdl4296.spring.mvc.vo.ReplyVO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("brdao")
public class BDReplyDAO {

    private JdbcTemplate jdbcTemplate;

    @Value("#{jdbc['insertBDReplySQL']}") private  String insertBDReplySQL;
    @Value("#{jdbc['selectBDReplySQL']}") private  String selectBDReplySQL;


    @Autowired
    public BDReplyDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 댓글 쓰기
    public void insertReply(ReplyVO rvo) {
        Object[] params = new Object[] {
                rvo.getReply(), rvo.getUserid(),
                rvo.getBno() };
        jdbcTemplate.update(insertBDReplySQL, params);
    }

}
