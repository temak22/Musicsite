package ru.mirea.musicsite.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
//@PreAuthorize("hasAuthority('ADMIN')")
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
    public String home(Map<String, Object> model) {
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
    public String addAlbum(
            @RequestParam String name,
            @RequestParam Date release_date,
            @RequestParam String style,
            @RequestParam int artist_id,
            @RequestParam("file") MultipartFile file,
            Map<String, Object> model) throws IOException {

        boolean check = adminService.checkIfArtistExist(artist_id);
        if (!check) {
            Artist artist = new Artist(artist_id, "unknown", null, null, null, null);
            adminService.saveArtist(artist);
        }

        Album album = new Album(0, name, release_date, style, artist_id, null);

        String filename = createFileNameAndSaveFile(file, "/covers");
        album.setCover_file(filename);

        int album_id = adminService.saveAlbum(album);
        album.setAlbum_id(album_id);

        this.artist_id = artist_id;
        this.album = album;
        this.edit_songs = true;

        if (!check)
            return "redirect:/admin/createArtist";
        else
            return "redirect:/admin/createSonglist";
    }

    @GetMapping("/updateAlbum")
    public String formUpdateAlbum(Map<String, Object> model) {
        return "admin/adminAlbumUpdate";
    }

    @PostMapping("/updateAlbum")
    public String updateAlbum(
            @RequestParam int album_id,
            @RequestParam String name,
            @RequestParam Date release_date,
            @RequestParam String style,
            @RequestParam int artist_id,
            @RequestParam("file") MultipartFile file,
            @RequestParam(required=false) boolean edit_songs,
            Map<String, Object> model) throws IOException {

        boolean check = adminService.checkIfArtistExist(artist_id);
        if (!check) {
            Artist artist = new Artist(artist_id, "unknown", null, null, null, null);
            adminService.saveArtist(artist);
        }

        Album album = new Album(album_id, name, release_date, style, artist_id, null);

        String filename = createFileNameAndSaveFile(file, "/covers");
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

        List<SongInAlbum> songsInAlbum = adminService.showSongsByAlbumId(album_id);
        for (SongInAlbum songInAlbum : songsInAlbum) {
            adminService.deleteSong(songInAlbum.getSong_id());
        }

        if (!check)
            return "redirect:/admin/createArtist";

        return "redirect:/admin/createSonglist";
    }

    @GetMapping("/deleteAlbum")
    public String formDeleteAlbum(Map<String, Object> model) {
        return "admin/adminAlbumDelete";
    }

    @PostMapping("/deleteAlbum")
    public String deleteAlbum(
            @RequestParam int album_id,
            Map<String, Object> model) throws IOException {

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
    public String addChart(
            @RequestParam String name,
            @RequestParam("file") MultipartFile file,
            Map<String, Object> model) throws IOException {

        Chart chart = new Chart(0, name,null);

        String filename = createFileNameAndSaveFile(file, "/charts");
        chart.setCover_file(filename);

        int chart_id = adminService.saveChart(chart);
        chart.setChart_id(chart_id);

        this.chart = chart;

        return "redirect:/admin/createChartlist";
    }

    @GetMapping("/updateChart")
    public String formUpdateChart(Map<String, Object> model) {
        return "admin/adminChartUpdate";
    }

    @PostMapping("/updateChart")
    public String updateChart(
            @RequestParam int chart_id,
            @RequestParam String name,
            @RequestParam("file") MultipartFile file,
            @RequestParam(required=false) boolean edit_songs,
            Map<String, Object> model) throws IOException {

        Chart chart = new Chart(chart_id, name,null);

        String filename = createFileNameAndSaveFile(file, "/charts");
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
    public String formDeleteChart(Map<String, Object> model) {
        return "admin/adminChartDelete";
    }

    @PostMapping("/deleteChart")
    public String deleteChart(
            @RequestParam int chart_id,
            Map<String, Object> model) throws IOException {

        adminService.deleteChart(chart_id);
        return "redirect:/admin";
    }

    @GetMapping("/createArtist")
    public String formArtist(Map<String, Object> model) {
        model.put("new_artist_id", artist_id);
        return "admin/adminArtistCreate";
    }

    @PostMapping("/createArtist")
    public String addArtist(
            @RequestParam int artist_id,
            @RequestParam String nickname,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam("avatarFile") MultipartFile avatarFile,
            @RequestParam("pageFile") MultipartFile pageFile,
            Map<String, Object> model) throws IOException {

        Artist artist = new Artist(artist_id, nickname, email, phone, null, null);

        String avatarFilename = createFileNameAndSaveFile(avatarFile, "/avatars");
        artist.setAvatar_file(avatarFilename);

        String pagePhotoFilename = createFileNameAndSaveFile(pageFile, "/pagePhotos");
        artist.setPage_photo_file(pagePhotoFilename);

        adminService.updateArtist(artist.getArtist_id(), artist);

        if (edit_songs)
            return "redirect:/admin/createSonglist";
        else
            return "redirect:/admin";
    }

    @GetMapping("/createSonglist")
    public String formSonglist(Map<String, Object> model) {
        model.put("main_artist_id", artist_id);
        model.put("album", album);

        return "admin/adminSonglistCreate";
    }

    @PostMapping("/createSonglist")
    public String addSonglist(
            @RequestParam String songlist,
            Map<String, Object> model) {

        String[] songs = songlist.split("\r\n");
        for (String songData : songs) {
            if (songData.trim().isEmpty())
                continue;

            String[] data = songData.split(" ");
            if (data.length < 3)
                continue;
            if (!isParsable(data[1]) || !isParsable(data[2]))
                continue;

            StringBuilder song_name = new StringBuilder();
            String[] song_name_sep = data[0].split("_");
            for (String song_name_part : song_name_sep) {
                song_name.append(song_name_part).append(" ");
            }
            song_name.deleteCharAt(song_name.length() - 1);

            Song song = new Song(0, song_name.toString(), artist_id);
            int song_id = adminService.saveSong(song);
            song.setSong_id(song_id);

            if (data.length > 3) {
                String[] feats = data[3].split("_");
                for (String feat : feats) {
                    if (!isParsable(feat))
                        continue;
                    if (!adminService.checkIfArtistExist(Integer.parseInt(feat)))
                        continue;
                    FeatArtist featArtist = new FeatArtist(song_id, Integer.parseInt(feat));
                    adminService.saveFeatArtist(featArtist);
                }
            }

            SongInAlbum songInAlbum = new SongInAlbum(album.getAlbum_id(), song.getSong_id(), Integer.parseInt(data[1]), Integer.parseInt(data[2]));
            adminService.saveSongInAlbum(songInAlbum);
        }

        return "redirect:/admin/createAlbum";
    }

    @GetMapping("/createChartlist")
    public String formChartlist(Map<String, Object> model) {
        model.put("chart", chart);
        return "admin/adminChartlistCreate";
    }

    @PostMapping("/createChartlist")
    public String addChartlist(
            @RequestParam String chartlist,
            Map<String, Object> model) {

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
    public String formUpdateArtist(Map<String, Object> model) {
        return "admin/adminArtistUpdate";
    }

    @PostMapping("/updateArtist")
    public String updateArtist(
            @RequestParam int artist_id,
            @RequestParam String nickname,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam("avatarFile") MultipartFile avatarFile,
            @RequestParam("pageFile") MultipartFile pageFile,
            Map<String, Object> model) throws IOException {

        Artist artist = new Artist(artist_id, nickname, email, phone, null, null);

        String avatarFilename = createFileNameAndSaveFile(avatarFile, "/avatars");
        artist.setAvatar_file(avatarFilename);

        String pagePhotoFilename = createFileNameAndSaveFile(pageFile, "/pagePhotos");
        artist.setPage_photo_file(pagePhotoFilename);

        adminService.updateArtist(artist.getArtist_id(), artist);

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

    private String createFileNameAndSaveFile(MultipartFile file, String folder) throws IOException {
        if (file != null && !file.isEmpty()) {
            File uploadDir = new File(uploadPath + folder);

            if (!uploadDir.exists())
                uploadDir.mkdir();

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + ".PNG";
            file.transferTo(new File(uploadPath + folder + "/" + resultFilename));
            return resultFilename;
        }
        return "";
    }
}