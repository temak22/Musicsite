package ru.mirea.musicsite;

import org.springframework.stereotype.Component;
import ru.mirea.musicsite.dtos.AlbumDto;
import ru.mirea.musicsite.dtos.AlbumSongDto;
import ru.mirea.musicsite.dtos.ArtistSongDto;
import ru.mirea.musicsite.dtos.SongDto;
import ru.mirea.musicsite.entities.*;
import ru.mirea.musicsite.security.entities.User;
import ru.mirea.musicsite.services.BrowseService;
import ru.mirea.musicsite.services.LibraryService;
import ru.mirea.musicsite.services.SearchService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class DtoConverter {

    private final LibraryService libraryService;
    private final SearchService searchService;
    private final BrowseService browseService;


    public DtoConverter(LibraryService libraryService, BrowseService browseService, SearchService searchService) {
        this.libraryService = libraryService;
        this.browseService = browseService;
        this.searchService = searchService;
    }

    public ArrayList<SongDto> convertSongsInLibraryToSongDtoList(List<SongInLibrary> songsInLibrary) {
        ArrayList<SongDto> songList = new ArrayList<>();

        for (SongInLibrary songInLibrary : songsInLibrary) {
            int song_id = songInLibrary.getSong_id();

            Song song = libraryService.showSong(song_id);
            Artist artist = libraryService.showArtist(song.getMain_artist_id());
            Album album = libraryService.showAlbumBySongId(song_id);

            songList.add(new SongDto(song.getSong_id(), song.getName(), artist, album, 1));
        }

        return songList;
    }

    public ArrayList<AlbumDto> convertAlbumsInLibraryToAlbumDtoList(List<AlbumInLibrary> albumsInLibrary) {
        ArrayList<AlbumDto> albumList = new ArrayList<>();

        for (AlbumInLibrary albumInLibrary : albumsInLibrary) {
            int album_id = albumInLibrary.getAlbum_id();

            Album album = libraryService.showAlbum(album_id);
            Artist artist = libraryService.showArtist(album.getArtist_id());

            albumList.add(new AlbumDto(album, artist, 1));
        }

        return albumList;
    }

    public ArrayList<SongDto> convertSongsToSongDtoList(List<Song> songs, User currentUser) {
        ArrayList<SongDto> songList = new ArrayList<>();

        for (Song song : songs) {
            int artist_id = song.getMain_artist_id();
            Artist artist = searchService.showArtist(artist_id);

            int song_id = song.getSong_id();
            Album album = searchService.showAlbumBySongId(song_id);

            boolean isInLibrary;
            int is_in_library;
            if (currentUser != null) {
                isInLibrary = searchService.isInLibrary(currentUser.getUser_id(), song_id);
                if (isInLibrary)
                    is_in_library = 1;
                else
                    is_in_library = 0;
            }
            else
                is_in_library = 0;

            songList.add(
                    new SongDto(
                            song.getSong_id(),
                            song.getName(),
                            artist,
                            album,
                            is_in_library
                    )
            );
        }

        return songList;
    }

    public ArrayList<AlbumDto> convertAlbumsToAlbumDtoList(List<Album> albums) {
        ArrayList<AlbumDto> albumList = new ArrayList<>();

        for (Album album : albums) {
            int artist_id = album.getArtist_id();
            Artist artist = searchService.showArtist(artist_id);
            albumList.add(new AlbumDto(album, artist, 1));
        }

        return albumList;
    }

    public ArrayList<ArtistSongDto> convertSongsAndFeatsToArtistSongDtoList(List<Song> songs, List<FeatArtist> feats, User currentUser) {
        ArrayList<ArtistSongDto> songList = new ArrayList<>();

        for (Song song : songs) {
            int song_id = song.getSong_id();
            Album album = browseService.showAlbumBySongId(song_id);

            boolean isInLibrary;
            int is_in_library;
            if (currentUser != null) {
                isInLibrary = browseService.isSongInLibrary(currentUser.getUser_id(), song_id);
                if (isInLibrary)
                    is_in_library = 1;
                else
                    is_in_library = 0;
            }
            else
                is_in_library = 0;

            songList.add(new ArtistSongDto(
                    song_id,
                    song.getName(),
                    album,
                    is_in_library,
                    song.getListening()));
        }

        for (FeatArtist feat : feats) {
            int song_id = feat.getSong_id();
            Song song = browseService.showSong(song_id);
            Album album = browseService.showAlbumBySongId(song_id);
            boolean isInLibrary;
            int is_in_library;
            if (currentUser != null) {
                isInLibrary = browseService.isSongInLibrary(currentUser.getUser_id(), song_id);
                if (isInLibrary)
                    is_in_library = 1;
                else
                    is_in_library = 0;
            }
            else
                is_in_library = 0;

            songList.add(new ArtistSongDto(
                    song_id,
                    song.getName(),
                    album,
                    is_in_library,
                    song.getListening()));
        }

        songList.sort(Comparator.comparingInt(ArtistSongDto::getListening).reversed());

        return songList;
    }

    public ArrayList<SongDto> convertSongsInChartToSongDtoList(List<SongInChart> songsInChart, User currentUser) {
        ArrayList<SongDto> songList = new ArrayList<>();

        for (SongInChart songInChart : songsInChart) {
            int song_id = songInChart.getSong_id();
            Song song = browseService.showSong(song_id);

            Artist artist = browseService.showArtist(song.getMain_artist_id());
            Album album = browseService.showAlbumBySongId(song_id);

            boolean isInLibrary;
            int is_in_library;
            if (currentUser != null) {
                isInLibrary = browseService.isSongInLibrary(currentUser.getUser_id(), song_id);
                if (isInLibrary)
                    is_in_library = 1;
                else
                    is_in_library = 0;
            }
            else
                is_in_library = 0;

            songList.add(new SongDto(
                    song_id,
                    song.getName(),
                    artist,
                    album,
                    is_in_library));
        }

        return songList;
    }

    public ArrayList<AlbumSongDto> convertSongsInAlbumToAlbumSongDtoList(List<SongInAlbum> songsInAlbum, User currentUser) {
        ArrayList<AlbumSongDto> songList = new ArrayList<>();

        for (SongInAlbum songInAlbum : songsInAlbum) {
            int song_id = songInAlbum.getSong_id();
            Song song = browseService.showSong(song_id);
            boolean isInLibrary;
            int is_in_library;
            if (currentUser != null) {
                isInLibrary = browseService.isSongInLibrary(currentUser.getUser_id(), song_id);
                if (isInLibrary)
                    is_in_library = 1;
                else
                    is_in_library = 0;
            }
            else
                is_in_library = 0;

            songList.add(new AlbumSongDto(
                    song_id,
                    song.getName(),
                    songInAlbum.getIs_lead_song(),
                    songInAlbum.getSerial_number(),
                    is_in_library));
        }

        return songList;
    }
}
