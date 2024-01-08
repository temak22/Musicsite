package ru.mirea.musicsite;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mirea.musicsite.repositories.*;

public class RepositoriesCollection {

    @Autowired
    public AlbumRepository albumRepository;

    @Autowired
    public ArtistRepository artistRepository;

    @Autowired
    public SongRepository songRepository;

    @Autowired
    public SongInAlbumRepository songInAlbumRepository;

    @Autowired
    public ChartRepository chartRepository;

    @Autowired
    public SongInChartRepository songInChartRepository;

    @Autowired
    public FeatArtistRepository featArtistRepository;

    @Autowired
    public SongInLibraryRepository songInLibraryRepository;

    @Autowired
    public AlbumInLibraryRepository albumInLibraryRepository;
}
