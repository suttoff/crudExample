package br.com.archive.controller;

import br.com.archive.entity.Player;
import br.com.archive.service.PlayerService;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlayerControllerTest {

    private PlayerController playerController;

    @Mock
    private PlayerService service;

    @Before
    public void setUp() {
        this.playerController = spy(new PlayerController(this.service));
    }

    @Test
    public void testSuccessGet() {
        when(this.service.get(1)).thenReturn(EnhancedRandom.randomListOf(2, Player.class));
        List<Player> response = this.playerController.get(1);

        assertNotNull(response);
        verify(this.service, atLeastOnce()).get(1L);
        assertEquals(2, response.size());
    }

    @Test
    public void testSuccessCreate() {
        when(this.service.create(any(Player.class))).thenReturn("Player successfully included.");

        String response = this.playerController.create(createRequest());

        assertNotNull(response);
        verify(this.service, atLeastOnce()).create(any(Player.class));
        assertEquals("Player successfully included.", response);
    }

    @Test
    public void testSuccessUpdateAge() {
        when(this.service.updateAge(eq(2L), any(Player.class))).thenReturn("Update successfully.");

        String response = this.playerController.updateAge(createRequest(), 2);

        assertNotNull(response);
        verify(this.service, atLeastOnce()).updateAge(eq(2L), any(Player.class));
        assertEquals("Update successfully.", response);
    }

    @Test
    public void testSuccessDelete() {
        this.playerController.delete(2L);

        verify(this.service, atLeastOnce()).delete(eq(2L));
    }

    @Test
    public void testSuccessCreateByArchivo() throws IOException {
        when(this.service.receivedArchiveToCreate(any(MockMultipartFile.class))).thenReturn("Player successfully included by archive.");

        String response = this.playerController.createByArchivo(new MockMultipartFile("teste", new byte[0]));

        assertNotNull(response);
        verify(this.service, atLeastOnce()).receivedArchiveToCreate(any(MockMultipartFile.class));
        assertEquals("Player successfully included by archive.", response);
    }

    private Player createRequest() {
        return Player.builder()
                .id(2L)
                .name("Matheus Suttoff")
                .age(23)
                .build();
    }
}