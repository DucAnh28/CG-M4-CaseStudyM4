package com.ducanh.casestudy.controller;

import com.ducanh.casestudy.model.*;

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
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/player")
public class PlayerController {
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

}
