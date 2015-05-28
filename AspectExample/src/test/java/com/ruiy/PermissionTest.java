package com.ruiy;

import com.ruiy.model.Document;
import com.ruiy.model.Role;
import com.ruiy.model.User;
import com.ruiy.service.DocumentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class PermissionTest {

    @Autowired
    @InjectMocks
    private DocumentService documentService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegularUser() {
        User user = new User();
        user.setRole(Role.ADMIN);
        user.setName("Admin User");

        Document document = new Document("1001", "Summary", "This is a summary");
        System.out.println(documentService.read(user, document));
    }

    @Test
    public void testAdmin() {
        User user = new User();
        user.setRole(Role.USER);
        user.setName("Regular User");

        Document document = new Document("1001", "Summary", "This is a summary");
        documentService.write(user, document, "This is a changed summary");
        System.out.println(document.getContent());
    }

}
