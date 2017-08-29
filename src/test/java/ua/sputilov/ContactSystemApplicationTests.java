package ua.sputilov;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("test")
public abstract class ContactSystemApplicationTests {
	
	protected Logger LOG = LoggerFactory.getLogger(this.getClass());
}
