package com.example.baseball.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.baseball.domain.Player;

//DBアクセスを行うクラス
//テーブルへアクセスするための基本的な処理はJPAが用意してくれているのでSQLを書かなくてよい
//JPAのJpaRepositoryを継承したinterfaceを作成することで利用できる
//findAllやsave等用意されている典型的な操作以外のことをしたくなれば、repositoryに追記していくことになる
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

}