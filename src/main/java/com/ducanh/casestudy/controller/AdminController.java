package com.ducanh.casestudy.controller;

import com.ducanh.casestudy.model.*;
import com.ducanh.casestudy.model.dto.ICountRole;
import com.ducanh.casestudy.service.approle.IAppRoleService;
import com.ducanh.casestudy.service.appuser.AppUserService;
import com.ducanh.casestudy.service.appuser.IAppUserService;
import com.ducanh.casestudy.service.coach.ICoachService;
import com.ducanh.casestudy.service.player.IPlayerService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ServletContext servletContext;
    @Value("${upload_file_avatar}")
    private String upload_file_avatar;
    @Autowired
    private IAppUserService appUserService;
    @Autowired
    private IAppRoleService appRoleService;
    @Autowired
    private ICoachService coachService;
    @Autowired
    private IPlayerService playerService;
    // User:
    @GetMapping("/user")
    public ResponseEntity<Iterable<AppUser>> getAllUser(){
        List<AppUser> appUsers = (List<AppUser>) appUserService.findAll();
        return new ResponseEntity<>(appUsers, HttpStatus.OK);
    }
    @GetMapping("/countRole")
    public ResponseEntity<Iterable<ICountRole>> getCountRole(){
        Iterable<ICountRole> countRoles = appUserService.getRoleNumber();
        return new ResponseEntity<>(countRoles,HttpStatus.OK);
    }
    // HLV
    @GetMapping("/coach")
    public ResponseEntity<Iterable<Coach>> displayAllCoach() {
        List<Coach> coaches = (List<Coach>) coachService.findAll();
        return new ResponseEntity<>(coaches, HttpStatus.OK);
    }

    @GetMapping("/coach-page")
    public ResponseEntity<Page<Coach>> displayCoachPage(@PageableDefault(value = 2) Pageable pageable) {
        Page<Coach> coaches = coachService.findAllPage(pageable);
        return new ResponseEntity<>(coaches, HttpStatus.OK);
    }

    @PostMapping("/coach")
    public ResponseEntity<Coach> addCoach(@ModelAttribute("coach") Coach coach, @ModelAttribute("username") String username, @ModelAttribute("password") String password, @ModelAttribute("avaFile") MultipartFile avaFile) {
        String path = servletContext.getRealPath("/");
        System.out.println("path: "+ path);
        if (avaFile != null) {
            String avaFileName = avaFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(avaFile.getBytes(), new File(upload_file_avatar + avaFileName));
                coach.setAvatarURL("/image/" + avaFileName);
            } catch (IOException ex) {
                coach.setAvatarURL("image/Error");
                System.out.println("Loi khi upload File");
                ex.printStackTrace();
            }
        }
        AppUser newUser = new AppUser(username, password);
        Set<AppRole> roles = new HashSet<>();
        roles.add(appRoleService.findById(2L).get());
        newUser.setAppRole(roles);
        appUserService.save(newUser);
        coach.setAppUser(newUser);
        coachService.save(coach);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/coach/{id}")
    public ResponseEntity<Coach> editCoach(@PathVariable Long id, Coach coach,@ModelAttribute("avaFile") MultipartFile avaFile) {
        Optional<Coach> coachOptional = coachService.findById(id);
        if (!coachOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String path = servletContext.getRealPath("/");
        System.out.println("path: "+ path);
        if (avaFile != null) {
            String avaFileName = avaFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(avaFile.getBytes(), new File(upload_file_avatar + avaFileName));
                coach.setAvatarURL("/image/" + avaFileName);
            } catch (IOException ex) {
                coach.setAvatarURL("image/Error");
                System.out.println("Loi khi upload File");
                ex.printStackTrace();
            }
        }
        coach.setId(coachOptional.get().getId());
        return new ResponseEntity<>(coachService.save(coach), HttpStatus.OK);
    }

    @DeleteMapping("/coach/{id}")
    public ResponseEntity<Coach> deleteCoach(@PathVariable Long id) {
        Optional<Coach> coachDelete = coachService.findById(id);
        if (!coachDelete.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        coachService.remove(id);
        return new ResponseEntity<>(coachDelete.get(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/coach/sortAsc")
    public ResponseEntity<Iterable<Coach>> sortCoachBySalaryAsc() {
        Iterable<Coach> coaches = coachService.sortCoachSalaryAsc();
        return new ResponseEntity<>(coaches, HttpStatus.OK);
    }

    @GetMapping("/coach/sortDesc")
    public ResponseEntity<Iterable<Coach>> sortCoachBySalaryDesc() {
        Iterable<Coach> coaches = coachService.sortCoachSalaryDesc();
        return new ResponseEntity<>(coaches, HttpStatus.OK);
    }
    @GetMapping("/coach/role")
    public ResponseEntity<Iterable<Coach>> findCoachByRole(@RequestParam String role) {
        Iterable<Coach> coach1 = coachService.findCoachByRole(role);
        return new ResponseEntity<>(coach1, HttpStatus.OK);
    }
    // Player:
    @GetMapping("/player/list")
    public ResponseEntity<Iterable<Player>> getPlayers() {
        Iterable<Player> players = playerService.findAll();
        if (!players.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("player/page")
    public ResponseEntity<Page<Player>> showPagePlayer(@PageableDefault(value = 5) Pageable pageable) {
        Page<Player> player_page = playerService.findPage(pageable);
        if (!player_page.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(player_page, HttpStatus.OK);
    }

    @GetMapping("/player/position")
    public ResponseEntity<Iterable<Position>> getPosition() {
        Iterable<Position> playerPositions = playerService.findAllPosition();
        if (!playerPositions.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(playerPositions, HttpStatus.OK);
    }

    @GetMapping("/player/performance")
    public ResponseEntity<Iterable<Performance>> getPerformance() {
        Iterable<Performance> playerPerformance = playerService.findAllPerformance();
        if (!playerPerformance.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(playerPerformance, HttpStatus.OK);
    }

    @GetMapping("/player/status")
    public ResponseEntity<Iterable<Status>> getStatus() {
        Iterable<Status> playerStatus = playerService.findAllStatus();
        if (!playerStatus.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(playerStatus, HttpStatus.OK);
    }

    @GetMapping("/player/find-player-by-id/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        Optional<Player> playerOptional = playerService.findById(id);
        return playerOptional.map(player -> new ResponseEntity<>(player, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/player/search")
    public ResponseEntity<Iterable<Player>> getPlayerByName(@RequestParam("search") String search) {
        Iterable<Player> players = playerService.findAllByName(search);
        if (!players.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @PutMapping("/player/edit/{id}")
    public ResponseEntity<Player> editPlayer(@PathVariable Long id, @RequestBody Player player) {
        Optional<Player> playerOptional = playerService.findById(id);
        if (!playerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        player.setId(id);
        playerService.save(player);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @DeleteMapping("/player/delete/{id}")
    public ResponseEntity<Player> deletePlayer(@PathVariable Long id) {
        Optional<Player> playerOptional = playerService.findById(id);
        if (!playerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        playerService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/player/create")
    public ResponseEntity<Player> addPlayer(@ModelAttribute("player") Player player, @ModelAttribute("avaFile") MultipartFile avaFile) {
        String path = servletContext.getRealPath("/");
        System.out.println("path: "+ path);
        if (avaFile != null) {
            String avaFileName = avaFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(avaFile.getBytes(), new File(upload_file_avatar + avaFileName));
                player.setAvatarURL("/image/" + avaFileName);
            } catch (IOException ex) {
                player.setAvatarURL("image/Error");
                System.out.println("Loi khi upload File");
                ex.printStackTrace();
            }
        }
        playerService.save(player);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/image/{path}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImageAsResponseEntity(@PathVariable String path) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        InputStream in = servletContext.getResourceAsStream(upload_file_avatar + path);
        byte[] media = IOUtils.toByteArray(in);
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
        return responseEntity;
    }
}
