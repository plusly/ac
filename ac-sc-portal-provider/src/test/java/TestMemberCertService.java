import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.atguigu.ac.sc.service.MemberCertService;

@ContextConfiguration
@RunWith(SpringRunner.class)
public class TestMemberCertService {

	@Autowired
	MemberCertService memberCertService;
	
	@Test
	public void test1() throws Exception {
		memberCertService.getListCertIdByAccType(new Byte("0"));
	}
}
