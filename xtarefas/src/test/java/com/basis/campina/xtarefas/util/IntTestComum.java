package com.basis.campina.xtarefas.util;

import com.basis.campina.xtarefas.XtarefasApplication;
import org.junit.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(classes = XtarefasApplication.class)
@ExtendWith(SpringExtension.class)
public abstract class IntTestComum {

    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Autowired
    public void setWebApplicationContext(WebApplicationContext pWebApplicationContext) {
        webApplicationContext = pWebApplicationContext;
    }

    protected MockMvc getMockMvc() {
        return mockMvc;
    }
}
