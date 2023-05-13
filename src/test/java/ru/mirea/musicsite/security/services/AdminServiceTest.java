package ru.mirea.musicsite.security.services;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.mirea.musicsite.DAO.ArtistDAO;
import ru.mirea.musicsite.entities.Artist;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @MockBean
    private ArtistDAO artistDAO;

    @Test
    void saveArtist() {
        Artist artist = new Artist();
        Mockito.doReturn(2)
                .when(artistDAO)
                .save(ArgumentMatchers.any(Artist.class));

        int isArtistCreated = adminService.saveArtist(artist);
        assertEquals(2, isArtistCreated);
    }
}