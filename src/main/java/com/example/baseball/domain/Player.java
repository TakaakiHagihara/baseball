package com.example.baseball.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//@EntityをつけることでDBのテーブルと紐づく
@Entity
//当PJではインメモリDBを用いるため使わないが、DBのテーブルを指定する場合は、
//@Table(name="テーブル名", schema="スキーマ名")と記載する
public class Player {
    //@Idを付けた変数がテーブルのプライマーキーになる
    @Id
    //@GeneratedValueをつけると連番が自動で振られる
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty //名前にNotEmpty制約
    private String name;
    @NotNull //年齢にNotNull制約
    @Min(value = 0) //年齢の最小値を0に設定
    @Max(value = 150) //年齢の最大値を150に設定
    private Integer age;
    @Size(max = 20) //チームの文字数の最大値を20に設定
    private String team;
    private String position;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getTeam() {
        return team;
    }
    public void setTeam(String team) {
        this.team = team;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Player [id=" + id + ", name=" + name + ", age=" + age + ", team=" + team + ", position=" + position + "]";
    }
}