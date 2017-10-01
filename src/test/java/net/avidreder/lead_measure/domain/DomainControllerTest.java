package net.avidreder.lead_measure.domain;
import net.avidreder.lead_measure.measure.MeasureService;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(DomainController.class)
public class DomainControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DomainService domainService;

    @MockBean
    private MeasureService measureService;

    @Test
    public void getDomains() throws Exception {
        List<Domain> domains = new ArrayList<Domain>();
        when(domainService.getAllDomains()).thenReturn(domains);
        this.mvc.perform(MockMvcRequestBuilders.get("/domains").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    @Test
    public void createNewDomainSuccess() throws Exception {
        Domain domain = new Domain("testDomain");
        domain.setId(1);
        Gson gson = new Gson();
        String json = gson.toJson(domain);
        when(domainService.createNewDomain("testDomain")).thenReturn(domain);
        this.mvc.perform(MockMvcRequestBuilders.post("/domains")
            .param("domainName", "testDomain"))
                .andExpect(status().isCreated())
                .andExpect(content().string(equalTo(json.toString())));
    }

    @Test
    public void getDomainByID() throws Exception {
        Domain domain = new Domain("testDomain");
        domain.setId(1);
        Gson gson = new Gson();
        String json = gson.toJson(domain);
        when(domainService.getDomainById(1)).thenReturn(domain);
        this.mvc.perform(MockMvcRequestBuilders.get("/domain/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(json.toString())));
    }

    @Test
    public void updateDomain() throws Exception {
        Domain domain = new Domain("newTestDomain");
        domain.setId(1);
        Gson gson = new Gson();
        String json = gson.toJson(domain);
        when(domainService.updateDomainById(anyInt(), Mockito.any(Domain.class))).thenReturn(domain);
        this.mvc.perform(MockMvcRequestBuilders.put("/domain/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(json.toString())));
    }

    @Test
    public void deleteDomainByID() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.delete("/domain/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Domain deleted")));
    }
}