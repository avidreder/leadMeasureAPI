package net.avidreder.lead_measure.measure;
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
    private MeasureService measureService;

    @Test
    public void getMeasures() throws Exception {
        List<Measure> measures = new ArrayList<Measure>();
        when(measureService.getAllMeasures()).thenReturn(measures);
        this.mvc.perform(MockMvcRequestBuilders.get("/measures").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    @Test
    public void createNewMeasureSuccess() throws Exception {
        Measure measure = new Measure("testMeasure");
        measure.setId(1);
        Gson gson = new Gson();
        String json = gson.toJson(measure);
        when(measureService.createNewMeasure("testMeasure")).thenReturn(measure);
        this.mvc.perform(MockMvcRequestBuilders.post("/measures")
            .param("measureName", "testMeasure"))
                .andExpect(status().isCreated())
                .andExpect(content().string(equalTo(json.toString())));
    }

    @Test
    public void getMeasureByID() throws Exception {
        Measure measure = new Measure("testMeasure");
        measure.setId(1);
        Gson gson = new Gson();
        String json = gson.toJson(measure);
        when(measureService.getMeasureById(1)).thenReturn(measure);
        this.mvc.perform(MockMvcRequestBuilders.get("/measure/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(json.toString())));
    }

    @Test
    public void updateMeasure() throws Exception {
        Measure measure = new Measure("newTestMeasure");
        measure.setId(1);
        Gson gson = new Gson();
        String json = gson.toJson(measure);
        when(measureService.updateMeasureById(anyInt(), Mockito.any(Measure.class))).thenReturn(measure);
        this.mvc.perform(MockMvcRequestBuilders.put("/measure/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(json.toString())));
    }

    @Test
    public void deleteMeasureByID() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.delete("/measure/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Measure deleted")));
    }
}