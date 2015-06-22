package org.opencb.opencga.server.ws;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.opencb.datastore.core.QueryResponse;
import org.opencb.datastore.core.QueryResult;
import org.opencb.opencga.catalog.CatalogManagerTest;
import org.opencb.opencga.catalog.models.Individual;

import javax.ws.rs.client.WebTarget;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by jacobo on 22/06/15.
 */
public class IndividualWSServerTest {

    private static WSServerTestUtils serverTestUtils;
    private WebTarget webTarget;
    private String sessionId;
    private int studyId;
    private int in1;
    private int in2;
    private int in3;
    private int in4;

    @BeforeClass
    static public void initServer() throws Exception {
        serverTestUtils = new WSServerTestUtils();
        serverTestUtils.initServer();
    }

    @AfterClass
    static public void shutdownServer() throws Exception {
        serverTestUtils.shutdownServer();
    }

    @Before
    public void init() throws Exception {
        serverTestUtils.setUp();
        webTarget = serverTestUtils.getWebTarget();
        sessionId = OpenCGAWSServer.catalogManager.login("user", CatalogManagerTest.PASSWORD, "localhost").first().getString("sessionId");
        studyId = OpenCGAWSServer.catalogManager.getStudyId("user@1000G:phase1");
        in1 = OpenCGAWSServer.catalogManager.createIndividual(studyId, "in1", "f1", -1, -1, null, null, sessionId).first().getId();
        in2 = OpenCGAWSServer.catalogManager.createIndividual(studyId, "in2", "f1", -1, -1, null, null, sessionId).first().getId();
        in3 = OpenCGAWSServer.catalogManager.createIndividual(studyId, "in3", "f2", -1, -1, null, null, sessionId).first().getId();
        in4 = OpenCGAWSServer.catalogManager.createIndividual(studyId, "in4", "f2", -1, -1, null, null, sessionId).first().getId();
    }


    @Test
    public void createIndividualTest() throws IOException {
        String json = webTarget.path("individuals").path("create")
                .queryParam("studyId", studyId)
                .queryParam("name", "new_individual1")
                .queryParam("gender", "FEMALE")
                .queryParam("family", "The Family Name")
                .queryParam("sid", sessionId).request().get(String.class);

        QueryResponse<QueryResult<Individual>> response = WSServerTestUtils.parseResult(json, Individual.class);

        Individual individual = response.getResponse().get(0).first();
        assertEquals(Individual.Gender.FEMALE, individual.getGender());
        assertEquals("The Family Name", individual.getFamily());
        assertEquals("new_individual1", individual.getName());
        assertTrue(individual.getId() > 0);
    }

    @Test
    public void getIndividualTest() throws IOException {
        String json = webTarget.path("individuals").path(Integer.toString(in1)).path("info")
                .queryParam("studyId", studyId)
                .queryParam("exclude", "projects.studies.individuals.gender")
                .queryParam("sid", sessionId).request().get(String.class);

        QueryResponse<QueryResult<Individual>> response = WSServerTestUtils.parseResult(json, Individual.class);

        Individual individual = response.getResponse().get(0).first();
        assertEquals("f1", individual.getFamily());
        assertEquals(null, individual.getGender());
        assertTrue(individual.getId() > 0);
    }

    @Test
    public void searchIndividualTest() throws IOException {
        String json = webTarget.path("individuals").path("search")
                .queryParam("studyId", studyId)
                .queryParam("family", "f1")
                .queryParam("exclude", "projects.studies.individuals.gender")
                .queryParam("sid", sessionId).request().get(String.class);

        QueryResponse<QueryResult<Individual>> response = WSServerTestUtils.parseResult(json, Individual.class);

        List<Individual> result = response.getResponse().get(0).getResult();

        assertEquals(2, result.size());
        for (Individual individual : result) {
            assertEquals("f1", individual.getFamily());
            assertEquals(null, individual.getGender());
            assertTrue(individual.getId() > 0);
        }
    }

    @Test
    public void updateIndividualTest() throws IOException {
        String json = webTarget.path("individuals").path(Integer.toString(in1)).path("update")
                .queryParam("family", "f3")
                .queryParam("sid", sessionId).request().get(String.class);

        QueryResponse<QueryResult<Individual>> response = WSServerTestUtils.parseResult(json, Individual.class);
        System.out.println("json = " + json);

        Individual individual = response.getResponse().get(0).first();
        assertEquals("f3", individual.getFamily());
    }

}
