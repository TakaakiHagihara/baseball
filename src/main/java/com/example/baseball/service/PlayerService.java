package com.example.baseball.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.baseball.domain.Player;
import com.example.baseball.repository.PlayerRepository;

/**
 * repository(DBアクセス)の処理を呼び出すserviceクラス
 * DBアクセス処理を行いたい時に当serviceクラスのメソッドが呼ばれる
 * @param id
 */
@Service
public class PlayerService {

    //@Autowiredを付けて宣言するとBeanをインジェクトしてくれるのでnewしなくても使うことが可能
    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public Player findById(Long id) {
        Optional<Player> optionalPlayer = playerRepository.findById(id);
        Player player = optionalPlayer.get();
        return player;
    }

    public Player save(Player player) {
        return playerRepository.save(player);
    }

    public void delete(Long id) {
        playerRepository.deleteById(id);
    }
    
}