package com.vinzlac.catmash.cucumber.stepdefinitions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinzlac.catmash.web.model.CatsProposalRequest;
import com.vinzlac.catmash.web.model.CatsProposalResponse;
import com.vinzlac.catmash.web.rest.CatController;
import com.vinzlac.catmash.domain.service.VoteService;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CatStepDefs extends StepDefs {

    @Autowired
    private CatController catController;

    @Autowired
    VoteService voteService;

    @Autowired
    ObjectMapper objectMapper;

    private MockMvc restCatMockMvc;

    CatsProposalResponse catsProposalResponse;

    @Before
    public void setup() {
        restCatMockMvc = MockMvcBuilders.standaloneSetup(catController).build();
    }

    @When("I request a cats proposal")
    public void iRequestACatsProposal() throws Exception {
        actions = restCatMockMvc.perform(
                post("/cats/proposal")
                        .accept(MediaType.APPLICATION_JSON));
    }

    @Then("a cats proposal is returned")
    public void aCatsProposalIsReturned() throws Exception {
        actions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.uuid").exists())
                .andExpect(jsonPath("$.cats").exists())
                .andExpect(jsonPath("$.cats", Matchers.hasSize(2)))
                .andExpect(jsonPath("$.cats[0].id").exists())
                .andExpect(jsonPath("$.cats[1].id").exists());
    }

    @Given("I have done a cats proposal")
    public void iHaveDoneACatsProposal() throws Exception {
        MvcResult mvcResult = restCatMockMvc.perform(
                post("/cats/proposal").accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();

        catsProposalResponse = objectMapper.readValue(contentAsString, CatsProposalResponse.class);

    }

    @When("I vote for the first cat of the proposal")
    public void iVoteForTheFirstCatOfTheProposal() throws Exception {
        CatsProposalRequest catsProposalRequest = new CatsProposalRequest(catsProposalResponse.getUuid());
        String requestBody = objectMapper.writeValueAsString(catsProposalRequest);
        actions = restCatMockMvc.perform(
                post("/cats/"+ catsProposalResponse.getCatProposalsResponse().iterator().next().getId()+"/vote")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody));
    }

    @Then("a vote is returned")
    public void aVoteIsReturned() throws Exception {
        actions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id").exists());
    }
}
