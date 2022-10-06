package com.ducanh.casestudy.controller;

import com.ducanh.casestudy.model.*;
import com.ducanh.casestudy.repository.jwt.IAppUserRepo;
import com.ducanh.casestudy.service.appuser.IAppUserService;

import com.ducanh.casestudy.service.player.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
//@RequestMapping("")
public class PlayerController {



    @Autowired
    private IAppUserService iAppUserService;

    @Autowired
    private IAppUserRepo iAppUserRepo;


//    @Value("${upload_file_avatar}")
//    private String upload_file_avatar;
//
//    @Value("${upload_file_background}")
//    private String upload_file_background;

    @Autowired
    private IPlayerService playerService;


    @GetMapping("/list-player")
    public ResponseEntity<Iterable<Player>> getPlayers() {
        Iterable<Player> players = playerService.findAll();
        if (!players.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/pagePlayer")
    public ResponseEntity<Page<Player>> showPagePlayer(@PageableDefault(value = 5) Pageable pageable) {
        Page<Player> player_page = playerService.findPage(pageable);
        if (!player_page.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(player_page, HttpStatus.OK);
    }

    @GetMapping("/position")
    public ResponseEntity<Iterable<Position>> getPosition() {
        Iterable<Position> playerPositions = playerService.findAllPosition();
        if (!playerPositions.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(playerPositions, HttpStatus.OK);
    }

    @GetMapping("/performance")
    public ResponseEntity<Iterable<Performance>> getPerformance() {
        Iterable<Performance> playerPerformance = playerService.findAllPerformance();
        if (!playerPerformance.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(playerPerformance, HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<Iterable<Status>> getStatus() {
        Iterable<Status> playerStatus = playerService.findAllStatus();
        if (!playerStatus.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(playerStatus, HttpStatus.OK);
    }

    @GetMapping("/find-player-by-id/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        Optional<Player> playerOptional = playerService.findById(id);
        return playerOptional.map(player -> new ResponseEntity<>(player, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search-player")
    public ResponseEntity<Iterable<Player>> getPlayerByName(@RequestParam("search") String search) {
        Iterable<Player> players = playerService.findAllByName(search);
        if (!players.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(players, HttpStatus.OK);
    }



    @PostMapping("/create-player")
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        playerService.save(player);
        return new ResponseEntity<>(player, HttpStatus.CREATED);
    }

    @PutMapping("/edit-player/{id}")
    public ResponseEntity<Player> editPlayer(@PathVariable Long id, @RequestBody Player player) {
        Optional<Player> playerOptional = playerService.findById(id);
        if (!playerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        player.setId(id);
        playerService.save(player);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }
    @DeleteMapping ("/delete-player/{id}")
    public ResponseEntity<Player> deletePlayer(@PathVariable Long id){
        Optional<Player> playerOptional = playerService.findById(id);
        if (!playerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        playerService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/page-player")
    public ResponseEntity<Page<Player>> showAllPage(@PageableDefault(value = 5) Pageable pageable) {
        Page<Player> playerPage = playerService.findPage(pageable);
        if (!playerPage.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(playerPage, HttpStatus.OK);
    }


//    @GetMapping ("total-player")
//    public ResponseEntity<Iterable<Player>> totalPlayer(){
//        Iterable<Player> players = playerService.findAll();
//        if (!players.iterator().hasNext()){
//            new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(players,HttpStatus.OK);
//    }


}
