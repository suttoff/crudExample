package br.com.archive.controller;

import br.com.archive.entity.Team;
import br.com.archive.service.TeamService;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TeamControllerTest {

    private TeamController teamController;

    @Mock
    private TeamService service;

    @Before
    public void setUp() {
        this.teamController = spy(new TeamController(this.service));
    }

    @Test
    public void testSuccessCreate() {
        when(this.service.create(any(Team.class))).thenReturn("Team successfully included.");

        String response = this.teamController.create(EnhancedRandom.random(Team.class));

        assertNotNull(response);
        verify(this.service, atLeastOnce()).create(any(Team.class));
        assertEquals("Team successfully included.", response);
    }

    @Test
    public void testSuccessAddMember() {
        when(this.service.addMember(eq(2L), eq(15L))).thenReturn("Member added successfully.");

        String response = this.teamController.addMember(2, 15);

        assertNotNull(response);
        verify(this.service, atLeastOnce()).addMember(2L, 15L);
        assertEquals("Member added successfully.", response);
    }

    @Test
    public void testSuccessGet() {
        when(this.service.get(15L)).thenReturn(EnhancedRandom.randomListOf(2, Team.class));
        List<Team> response = this.teamController.get(15);

        assertNotNull(response);
        verify(this.service, atLeastOnce()).get(15L);
        assertEquals(2, response.size());
    }

    @Test
    public void testSuccessDelete() {
        this.teamController.delete(15L);

        verify(this.service, atLeastOnce()).delete(eq(15L));
    }
}