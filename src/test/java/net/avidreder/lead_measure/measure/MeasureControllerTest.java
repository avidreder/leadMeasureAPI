package net.avidreder.lead_measure.measure;
import net.avidreder.lead_measure.domain.Domain;
import net.avidreder.lead_measure.domain.DomainService;
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
@WebMvcTest(MeasureController.class)
public class MeasureControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DomainService domainService;

    @MockBean
    private MeasureService measureService;

    @Test
    public void getMeasures() throws Exception {
        List<Measure> measures = new ArrayList<Measure>();
        when(measureService.getAllMeasures(1)).thenReturn(measures);
        this.mvc.perform(MockMvcRequestBuilders.get("/1/measures").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    @Test
    public void createNewMeasureSuccess() throws Exception {
        Measure measure = new Measure("testMeasure");
        Domain domain = new Domain("testDomain");
        domain.setId(1);
        measure.setId(1);
        measure.setDomain(domain);
        Gson gson = new Gson();
        String json = gson.toJson(measure);
        when(measureService.createNewMeasure(1, "testMeasure")).thenReturn(measure);
        this.mvc.perform(MockMvcRequestBuilders.post("/1/measures")
            .param("measureName", "testMeasure"))
                .andExpect(status().isCreated())
                .andExpect(content().string(equalTo(json.toString())));
    }

    @Test
    public void getMeasureByID() throws Exception {
        Measure measure = new Measure("testMeasure");
        Domain domain = new Domain("testDomain");
        domain.setId(1);
        measure.setId(1);
        measure.setDomain(domain);
        Gson gson = new Gson();
        String json = gson.toJson(measure);
        when(measureService.getMeasureById(1, 1)).thenReturn(measure);
        this.mvc.perform(MockMvcRequestBuilders.get("/1/measure/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(json.toString())));
    }

    @Test
    public void updateMeasure() throws Exception {
        Measure measure = new Measure("newTestMeasure");
        Domain domain = new Domain("testDomain");
        domain.setId(1);
        measure.setId(1);
        measure.setDomain(domain);
        Gson gson = new Gson();
        String json = gson.toJson(measure);
        when(measureService.updateMeasureById(anyInt(), anyInt(), Mockito.any(Measure.class))).thenReturn(measure);
        this.mvc.perform(MockMvcRequestBuilders.put("/1/measure/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(json.toString())));
    }

    @Test
    public void deleteMeasureByID() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.delete("/1/measure/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Measure deleted")));
    }
}