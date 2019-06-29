package com.example.baseball.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.baseball.domain.Player;
import com.example.baseball.service.PlayerService;

@Controller
//クラスに対して@RequestMappingを付けると、クラス内のメソッド全てに適用される
//よって、つまりこのクラスのメソッドは全て、http://localhost:8080/playersから始まるURLにマッピングされる
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @GetMapping
    //メソッドの引数にModel型の値を設定するとModelのインスタンスが自動的に渡される
    public String index(Model model) {
        List<Player> players = playerService.findAll();
        //引数で受け取ったmodelに値を詰めることで、テンプレートに値を渡すことができる
        //ここではplayersというキー名でplayerのListを設定している
        model.addAttribute("players", players);
        //returnしている文字列を元に、src/main/resources/templates/配下からファイルを見つけてユーザに返す
        //returnしている文字列を元に、src/main/resources/templates/配下からファイルを見つけてユーザに返される
        return "players/index";
    }

    @GetMapping("new")
    public String newPlayer(Model model) {
        //新規作成画面に対してPlayerインスタンスを渡す
        //これがないと入力エラー時に入力していた内容を保持することができない
        Player player = new Player();
        model.addAttribute("player", player);
        return "players/new";
    }

    @GetMapping("{id}/edit")
    //メソッドの引数に@PathVariableを設定するとURL上の値を取得することができる
    //ここでは、http://localhost/players/1にアクセスされるとidには1が入る
    public String edit(@PathVariable Long id, Model model) {
        Player player = playerService.findById(id);
        model.addAttribute("player", player);
        return "players/edit";
    }

    @GetMapping("{id}")
    public String show(@PathVariable Long id, Model model) {
        Player player = playerService.findById(id);
        model.addAttribute("player", player);
        return "players/show";
    }

    @PostMapping
    //メソッドの引数に@ModelAttributeをつけると送信されたリクエストのbodyの情報を取得できる
    //playerに@Validをつけることでvalidationチェック対象とする
    //エラーがあるとBindingResult bindingResultの中にエラーの情報がセットされる
    public String create(@Valid @ModelAttribute Player player, BindingResult bindingResult) {
        //エラーがある場合、元の画面に返す
        if(bindingResult.hasErrors()) {
            return "players/new";
        }
        playerService.save(player);
        return "redirect:/players";
    }

    @PutMapping("{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute Player player, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            //エラーがあった場合、http://localhost:8080/players/editを表示(画面遷移しない)
            return "players/edit";
        }
        player.setId(id);
        playerService.save(player);
        //createメソッドの処理が終わった後、http://localhost:8080/playersを表示(リダイレクトなのでそのまま再表示ではなくアクセスさせるイメージ)
        return "redirect:/players";
    }

    @DeleteMapping("{id}")
    public String destroy(@PathVariable Long id) {
        playerService.delete(id);
        return "redirect:/players";
    }
}