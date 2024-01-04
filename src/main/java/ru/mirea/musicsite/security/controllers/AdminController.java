package ru.mirea.musicsite.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.mirea.musicsite.entities.*;
import ru.mirea.musicsite.security.entities.Role;
import ru.mirea.musicsite.security.entities.User;
import ru.mirea.musicsite.security.services.AdminService;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Value("${upload.path}")
    private String uploadPath;

    //variables for Model
    private int artist_id = 0;
    private Album album;
    private Chart chart;
    private boolean edit_songs;


    @GetMapping("")
    public String home() {

        return "admin/admin";
    }

    @GetMapping("/createAlbum")
    public String formAlbum(Map<String, Object> model) {

        Iterable<Album> albums = adminService.indexAlbum();
        model.put("albums", albums);
        return "admin/adminAlbumCreate";
    }

    @GetMapping("/")
    public String homeRedirect() {

        return "redirect:/admin";
    }

    @PostMapping("/createAlbum")
    public String addAlbum(@RequestParam String name,
                           @RequestParam Date release_date,
                           @RequestParam String style,
                           @RequestParam int artist_id,
                           @RequestParam("file") MultipartFile file) throws IOException {

        boolean check = adminService.checkIfArtistExist(artist_id);
        if (!check) {
            Artist artist = new Artist(artist_id, "unknown", null, null, null, null);
            adminService.saveArtist(artist);
        }

        Album album = new Album(0, name, release_date, style, artist_id, null);

        String filename = createFileNameAndSaveFile(file, "/img/covers", ".PNG");
        album.setCover_file(filename);

        int album_id = adminService.saveAlbum(album);
        album.setAlbum_id(album_id);

        this.artist_id = artist_id;
        this.album = album;
        this.edit_songs = true;

        if (!check)
            return "redirect:/admin/createArtist";
        else
            return "redirect:/admin/createSong";
    }

    @GetMapping("/updateAlbum")
    public String formUpdateAlbum() {

        return "admin/adminAlbumUpdate";
    }

    @PostMapping("/updateAlbum")
    public String updateAlbum(@RequestParam int album_id,
                              @RequestParam String name,
                              @RequestParam Date release_date,
                              @RequestParam String style,
                              @RequestParam int artist_id,
                              @RequestParam("file") MultipartFile file,
                              @RequestParam(required=false) boolean edit_songs) throws IOException {

        boolean check = adminService.checkIfArtistExist(artist_id);
        if (!check) {
            Artist artist = new Artist(artist_id, "unknown", null, null, null, null);
            adminService.saveArtist(artist);
        }

        Album album = new Album(album_id, name, release_date, style, artist_id, null);

        String filename = createFileNameAndSaveFile(file, "/img/covers", ".PNG");
        album.setCover_file(filename);

        adminService.updateAlbum(album);

        this.edit_songs = edit_songs;
        this.artist_id = artist_id;
        this.album = album;

        if (!edit_songs) {
            if (!check)
                return "redirect:/admin/createArtist";
            return "redirect:/admin";
        }

        if (!check)
            return "redirect:/admin/createArtist";

        return "redirect:/admin/createSong";
    }

    @GetMapping("/deleteAlbum")
    public String formDeleteAlbum() {

        return "admin/adminAlbumDelete";
    }

    @PostMapping("/deleteAlbum")
    public String deleteAlbum(@RequestParam int album_id) {

        adminService.deleteAlbum(album_id);
        return "redirect:/admin";
    }

    @GetMapping("/createChart")
    public String formChart(Map<String, Object> model) {

        Iterable<Chart> charts = adminService.indexChart();
        model.put("charts", charts);
        return "admin/adminChartCreate";
    }

    @PostMapping("/createChart")
    public String addChart(@RequestParam String name,
                           @RequestParam("file") MultipartFile file) throws IOException {

        Chart chart = new Chart(0, name,null);

        String filename = createFileNameAndSaveFile(file, "/img/charts", ".PNG");
        chart.setCover_file(filename);

        int chart_id = adminService.saveChart(chart);
        chart.setChart_id(chart_id);

        this.chart = chart;

        return "redirect:/admin/createChartlist";
    }

    @GetMapping("/updateChart")
    public String formUpdateChart() {

        return "admin/adminChartUpdate";
    }

    @PostMapping("/updateChart")
    public String updateChart(@RequestParam int chart_id,
                              @RequestParam String name,
                              @RequestParam("file") MultipartFile file,
                              @RequestParam(required=false) boolean edit_songs) throws IOException {

        Chart chart = new Chart(chart_id, name,null);

        String filename = createFileNameAndSaveFile(file, "/img/charts", ".PNG");
        chart.setCover_file(filename);

        adminService.updateChart(chart);

        if (!edit_songs)
            return "redirect:/admin";

        List<SongInChart> songsInChart = adminService.showSongsByChartId(chart_id);
        for (SongInChart songInChart : songsInChart) {
            adminService.deleteSongInChart(songInChart.getChart_id(), songInChart.getSong_id());
        }

        this.chart = chart;
        return "redirect:/admin/createChartlist";
    }

    @GetMapping("/deleteChart")
    public String formDeleteChart() {

        return "admin/adminChartDelete";
    }

    @PostMapping("/deleteChart")
    public String deleteChart(@RequestParam int chart_id) {

        adminService.deleteChart(chart_id);
        return "redirect:/admin";
    }

    @GetMapping("/createArtist")
    public String formArtist(Map<String, Object> model) {

        model.put("new_artist_id", artist_id);
        return "admin/adminArtistCreate";
    }

    @PostMapping("/createArtist")
    public String addArtist(@RequestParam int artist_id,
                            @RequestParam String nickname,
                            @RequestParam String email,
                            @RequestParam String phone,
                            @RequestParam("avatarFile") MultipartFile avatarFile,
                            @RequestParam("pageFile") MultipartFile pageFile) throws IOException {

        Artist artist = new Artist(artist_id, nickname, email, phone, null, null);

        String avatarFilename = createFileNameAndSaveFile(avatarFile, "/img/avatars", ".PNG");
        artist.setAvatar_file(avatarFilename);

        String pagePhotoFilename = createFileNameAndSaveFile(pageFile, "/img/pagePhotos", ".PNG");
        artist.setPage_photo_file(pagePhotoFilename);

        adminService.updateArtist(artist.getArtist_id(), artist);

        if (edit_songs)
            return "redirect:/admin/createSonglist";
        else
            return "redirect:/admin";
    }

    @GetMapping("/createSong")
    public String formSong(Map<String, Object> model) {

        if (album != null) {
            model.put("album_id", album.getAlbum_id());
        }
        else
            model.put("album_id", 0);

        return "admin/adminSongCreate";
    }

    @PostMapping("/createSong")
    public String addSong(@RequestParam int album_id,
                          @RequestParam String name,
                          @RequestParam int is_lead_song,
                          @RequestParam int serial_number,
                          @RequestParam int order_number,
                          @RequestParam String featlist,
                          @RequestParam("file") MultipartFile file,
                          @RequestParam(required=false) boolean is_last_song) throws IOException {

        String songFilename = createFileNameAndSaveFile(file, "/mp3/" + album_id, ".mp3");
        Song song = new Song(0, name, artist_id, songFilename);
        int song_id = adminService.saveSong(song);
        song.setSong_id(song_id);

        String[] feats = featlist.split("_");
        for (String feat : feats) {
            if (!isParsable(feat))
                continue;
            if (!adminService.checkIfArtistExist(Integer.parseInt(feat)))
                continue;
            FeatArtist featArtist = new FeatArtist(song_id, Integer.parseInt(feat));
            adminService.saveFeatArtist(featArtist);
        }

        SongInAlbum songInAlbum = new SongInAlbum(album_id, song.getSong_id(), is_lead_song, serial_number, order_number);
        adminService.saveSongInAlbum(songInAlbum);

        if (!is_last_song)
            return "redirect:/admin/createSong";
        else
            return "redirect:/admin/createAlbum";

    }

    @GetMapping("/createChartlist")
    public String formChartlist(Map<String, Object> model) {

        model.put("chart", chart);
        return "admin/adminChartlistCreate";
    }

    @PostMapping("/createChartlist")
    public String addChartlist(@RequestParam String chartlist) {

        String[] songs = chartlist.split("\r\n");
        for (String songData : songs) {
            if (songData.trim().isEmpty())
                continue;

            String[] data = songData.split(" ");
            if (data.length < 2)
                continue;
            if (!isParsable(data[0]) || !isParsable(data[1]))
                continue;
            if (!adminService.checkIfSongExist(Integer.parseInt(data[0])))
                continue;

            SongInChart songInChart = new SongInChart(chart.getChart_id(), Integer.parseInt(data[0]), Integer.parseInt(data[1]));
            adminService.saveSongInChart(songInChart);
        }

        return "redirect:/admin/createChart";
    }

    @GetMapping("/updateArtist")
    public String formUpdateArtist() {

        return "admin/adminArtistUpdate";
    }

    @PostMapping("/updateArtist")
    public String updateArtist(@RequestParam int artist_id,
                               @RequestParam String nickname,
                               @RequestParam String email,
                               @RequestParam String phone,
                               @RequestParam("avatarFile") MultipartFile avatarFile,
                               @RequestParam("pageFile") MultipartFile pageFile) throws IOException {

        Artist artist = new Artist(artist_id, nickname, email, phone, null, null);

        String avatarFilename = createFileNameAndSaveFile(avatarFile, "/img/avatars", ".PNG");
        artist.setAvatar_file(avatarFilename);

        String pagePhotoFilename = createFileNameAndSaveFile(pageFile, "/img/pagePhotos", ".PNG");
        artist.setPage_photo_file(pagePhotoFilename);

        adminService.updateArtist(artist.getArtist_id(), artist);

        return "redirect:/admin";
    }

    @GetMapping("/updateSong")
    public String formUpdateSong() {

        return "admin/adminSongUpdate";
    }

    @PostMapping("/updateSong")
    public String updateSong(@RequestParam int song_id,
                             @RequestParam String name,
                             @RequestParam int is_lead_song,
                             @RequestParam int serial_number,
                             @RequestParam int order_number,
                             @RequestParam String featlist,
                             @RequestParam("file") MultipartFile file) throws IOException {

        String songFilename = createFileNameAndSaveFile(file, "/mp3", ".mp3");
        int artist_id = adminService.getArtistIdBySongId(song_id);
        Song song = new Song(song_id, name, artist_id, songFilename);
        adminService.updateSong(song_id, song);

        adminService.deleteFeatArtists(song_id);
        String[] feats = featlist.split("_");
        for (String feat : feats) {
            if (!isParsable(feat))
                continue;
            if (!adminService.checkIfArtistExist(Integer.parseInt(feat)))
                continue;
            FeatArtist featArtist = new FeatArtist(song_id, Integer.parseInt(feat));
            adminService.saveFeatArtist(featArtist);
        }

        int album_id = adminService.getAlbumIdBySongId(song_id);
        SongInAlbum songInAlbum = new SongInAlbum(album_id, song.getSong_id(), is_lead_song, serial_number, order_number);
        adminService.updateSongInAlbum(album_id, song_id, songInAlbum);

        return "redirect:/admin";
    }


    @GetMapping("/user")
    public String userList(Model model) {

        model.addAttribute("users", adminService.getAllUsers());
        return "admin/userlist";
    }

    @GetMapping("/user/{user_id}")
    public String userEditForm(@PathVariable int user_id,
                               Model model) {

        model.addAttribute("user_edit", adminService.getUserById(user_id));
        model.addAttribute("roles", Role.values());
        return "admin/user_edit";
    }

    @PostMapping("/user")
    public String userEditSave(@RequestParam int userId,
                               @RequestParam String username,
                               @RequestParam String password,
                               @RequestParam Map<String, String> form,
                               @RequestParam boolean isMainAdmin) {

        User user = adminService.getUserById(userId);
        user.setUsername(username);
        user.setPassword(password);

        if (!isMainAdmin) {
            Set<String> roles = Arrays.stream(Role.values())
                    .map(Role::name)
                    .collect(Collectors.toSet());

            user.getRoles().clear();
            user.getRoles().add(Role.USER);
            for (String key : form.keySet()) {
                if (roles.contains(key)) {
                    user.getRoles().add(Role.valueOf(key));
                }
            }
        }

        adminService.saveUser(user);

        return "redirect:/admin/user";
    }


    public static boolean isParsable(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    private String createFileNameAndSaveFile(MultipartFile file, String folder, String type) throws IOException {
        if (file != null && !file.isEmpty()) {
            File uploadDir = new File(uploadPath + folder);

            if (!uploadDir.exists())
                uploadDir.mkdir();

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + type;
            file.transferTo(new File(uploadPath + folder + "/" + resultFilename));
            return resultFilename;
        }
        return "";
    }
}